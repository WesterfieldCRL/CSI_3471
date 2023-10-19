package edu.baylor.ecs.csi3471.lab3.iteration1;

public class Student
{
    double GPA;
    String name;
    int ID;
    public Student(String name, int ID, double GPA)
    {
        this.name = name;
        this.ID = ID;
        this.GPA = GPA;
    }
    public String getName()
    {
        return name;
    }
    public int getID()
    {
        return ID;
    }
    public double getGPA()
    {
        return GPA;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setID(int ID)
    {
        this.ID = ID;
    }
    public void setGPA(double GPA)
    {
        this.GPA = GPA;
    }
}
