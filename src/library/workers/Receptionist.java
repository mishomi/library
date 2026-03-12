package library.workers;

public class Receptionist extends Worker {

    private String knownLanguages;

    public Receptionist(int salary, String name, String knownLanguages) {

        super(salary, name);
        this.knownLanguages = knownLanguages;
    }

    public String getKnownLanguages() {
        return knownLanguages;
    }

    public void setKnownLanguages(String knownLanguages) {
        this.knownLanguages = knownLanguages;
    }
}