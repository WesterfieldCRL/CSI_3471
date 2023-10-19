package edu.baylor.ecs.csi3471.lab3.iteration1;

import java.util.Vector;

public class CitySimulation
{
    public static void main(String[] args)
    {
        Vector<Person> persons = new Vector<>();
        Vector<Student> students = new Vector<>();
        int personCount = 0;
        int studentCount = 0;

        for (int i = 0; i < 100; i++)
        {
            int randNum = (int)(Math.random()*2);
            if (randNum == 0)
            {
                persons.add(new Person("Mr. Smith", 101));
                personCount++;
            }
            else
            {
                students.add(new Student("Smith Jr.", 100, 61.0));
                studentCount++;
            }
        }

        System.out.println(personCount + " persons were generated, and " + studentCount + " students were generated.");

    }
}
