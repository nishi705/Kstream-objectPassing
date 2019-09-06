package com.stackroute.KafkaRun;





import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class KafkaRunApplication {

//	public static void main(String[] args) {
//	SpringApplication.run(KafkaRunApplication.class, args);
//
    String name;
    public String id;
    private double salary;
    public KafkaRunApplication(String name, String id, double salary)
    {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
    public String getName()
    {
        return name;
    }
    public String getId()
    {
        return id;
    }
    public Double getSalary() {
        return salary;

    }
}
