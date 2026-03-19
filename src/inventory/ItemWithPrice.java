package inventory;

import java.math.BigDecimal;

public interface ItemWithPrice {
    BigDecimal getPrice();
    void setPrice(BigDecimal price);
}
