package com.company;

public class Employee {

    private String name;
    private String id;
    private double salary;
    private int age;

    public Employee(){}
    public void setName(String name)
    {
        this.name = name;
    }

    public Employee(String name, String id, double salary, int age)
    {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.age = age;
    }

    public String getName()
    {
        return this.name;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public double getSalary()
    {
        return this.salary;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return this.age;
    }

    public String toString()
    {
        return this.id + " " + this.name + " " + this.salary + " " + this.age;
    }
}
