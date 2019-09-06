package com.stackroute.KafkaRun.Domain;


public class SampleObject {
    String Name;
    int id;
    String Salary;

    public SampleObject() {
    }

    public SampleObject(String name, int id, String salary) {
        Name = name;
        this.id = id;
        Salary = salary;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    @Override
    public String toString() {
        return "SampleObject{" +
                "Name='" + Name + '\'' +
                ", id=" + id +
                ", Salary='" + Salary + '\'' +
                '}';
    }
}
