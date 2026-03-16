package library.person;

public class Author extends Person {

    private String name;
    private String nationality;

    public Author(String name, String nationality) {
        super(name, 0);
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Author[name=" + name + ", nationality=" + nationality + "]";
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}