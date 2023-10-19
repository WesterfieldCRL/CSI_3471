package edu.baylor.ecs.csi3471.lab3.iteration2;

public class Student extends Person
{
    private static int population = 0;
    double GPA;
    public Student(String name, int ID, double GPA)
    {
        super(name, ID);
        this.GPA = GPA;
        population++;
    }
    public static int getPopulation()
    {
        return population;
    }
    public double getGPA()
    {
        return GPA;
    }
    public void setGPA(double GPA)
    {
        this.GPA = GPA;
    }
}
