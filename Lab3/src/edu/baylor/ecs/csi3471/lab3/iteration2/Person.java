package edu.baylor.ecs.csi3471.lab3.iteration2;

public class Person
{
    private static int population = 0;
    String name;
    int ID;
    public Person(String name, int ID)
    {
        this.name = name;
        this.ID = ID;
        population++;
    }
    public static int getPopulation()
    {
        return population;
    }
    public String getName()
    {
        return name;
    }
    public int getID()
    {
        return ID;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setID(int ID)
    {
        this.ID = ID;
    }
}
