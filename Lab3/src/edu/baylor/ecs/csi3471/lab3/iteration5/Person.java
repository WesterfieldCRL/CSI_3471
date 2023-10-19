package edu.baylor.ecs.csi3471.lab3.iteration5;

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
    public void printMe()
    {
        System.out.println("I am a Person");
    }
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass())
        {
            return false;
        }

        Person input = (Person) obj;
        return ID == input.ID
                && name.equals(input.name);

    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + name.hashCode();
        result = prime * result + ID;
        return result;
    }
}
