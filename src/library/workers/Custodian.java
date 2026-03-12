package library.workers;

public class Custodian extends Worker {

    private int supervisorId;

    public Custodian(int salary, String name, int supervisorId) {

        super(salary, name);
        this.supervisorId = supervisorId;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }
}