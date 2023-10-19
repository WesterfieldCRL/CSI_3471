package edu.baylor.ecs.csi3471.lab3.iteration3;

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

        Student input = (Student) obj;
        return ID == input.ID
                && name.equals(input.name)
                && GPA == input.GPA;

    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + name.hashCode();
        result = prime * result + ID;
        result = prime * result + (int)GPA;
        return result;
    }

}
