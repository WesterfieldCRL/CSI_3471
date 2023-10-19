package edu.baylor.ecs.csi3471.lab3.iteration2;

import java.util.Vector;

public class CitySimulation
{
    public static void main(String[] args)
    {
        Vector<Person> persons = new Vector<>();
        Vector<Student> students = new Vector<>();

        for (int i = 0; i < 100; i++)
        {
            int randNum = (int)(Math.random()*2);
            if (randNum == 0)
            {
                persons.add(new Person("Mr. Smith", 101));
            }
            else
            {
                students.add(new Student("Smith Jr.", 100, 61.0));
            }
        }

        System.out.println(Person.getPopulation() - Student.getPopulation() + " persons were generated, and " + Student.getPopulation() + " students were generated.");

    }
}
