package com.solvd.library.async;

import com.solvd.library.inventory.Inventory;
import com.solvd.library.organization.Library;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class LibraryAsyncDemo {

    private static final Logger log = LogManager.getLogger(LibraryAsyncDemo.class);

    private LibraryAsyncDemo() {
    }

    public static void runDemo(Library library) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        try {
            CompletableFuture<String> libraryNameFuture =
                    CompletableFuture.supplyAsync(() -> "Library name: " + library.getName(), executor);

            CompletableFuture<Integer> inventoryCountFuture =
                    CompletableFuture.supplyAsync(() -> library.getInventorySize(), executor);

            CompletionStage<List<String>> inventoryNamesStage =
                    CompletableFuture.supplyAsync(() ->
                            library.getInventory().getItems().stream()
                                    .map(Inventory::getName)
                                    .toList(), executor);

            CompletionStage<String> firstItemStage =
                    CompletableFuture.supplyAsync(() -> {
                        Inventory first = library.getFirstInventoryItem();
                        return first == null ? "none" : first.getName();
                    }, executor);

            CompletableFuture<String> summaryFuture =
                    libraryNameFuture.thenCombine(inventoryCountFuture,
                            (name, count) -> name + ", inventory count: " + count);

            CompletableFuture<String> safeInventoryFuture =
                    CompletableFuture.supplyAsync(() -> {
                        if (library.isInventoryEmpty()) {
                            throw new IllegalStateException("Inventory is empty");
                        }
                        return "Inventory is available";
                    }, executor).exceptionally(ex -> "Fallback: inventory check failed");

            CompletionStage<String> combinedStage =
                    inventoryNamesStage.thenCombine(firstItemStage,
                            (names, first) -> "First item: " + first + ", all items: " + String.join(", ", names));

            CompletableFuture<String> authorSummaryFuture =
                    CompletableFuture.supplyAsync(() ->
                            library.getInventory().getItems().stream()
                                    .map(item -> item.getName() + " by " + item.getAuthor().getName())
                                    .findFirst()
                                    .orElse("No items"), executor);

            CompletableFuture<Void> all = CompletableFuture.allOf(
                    libraryNameFuture,
                    inventoryCountFuture,
                    summaryFuture,
                    safeInventoryFuture,
                    authorSummaryFuture,
                    combinedStage.toCompletableFuture()
            );

            all.join();

            log.info(libraryNameFuture.join());
            log.info("Inventory count: {}", inventoryCountFuture.join());
            log.info(summaryFuture.join());
            log.info(safeInventoryFuture.join());
            log.info(authorSummaryFuture.join());
            log.info(combinedStage.toCompletableFuture().join());
        } finally {
            executor.shutdown();
        }
    }
}