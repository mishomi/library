package workers;

public abstract class Worker implements StaffMember{

    private int salary;
    private String name;

    public Worker(int salary, String name) {
        this.salary = salary;
        this.name = name;
    }

    @Override
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}