package com.krasnov;

public class EmployeeImpl implements Employee {

    private String firstName, lastName;
    private int salary;
    private Employee manager;
    public EmployeeImpl() {
        salary = 1000;
    }
    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public void increaseSalary(int value) {
        salary += value;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    @Override
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public String getManagerName() {
        if (manager == null)
            return "No manager";
        else
            return manager.getFullName();
    }

    @Override
    public Employee getTopManager() {
        if (this.manager == null)
            return this;
        else
            return manager.getTopManager();
    }
}
