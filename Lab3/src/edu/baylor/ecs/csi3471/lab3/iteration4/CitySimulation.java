package edu.baylor.ecs.csi3471.lab3.iteration4;

import edu.baylor.ecs.csi3471.lab3.iteration4.Person;
import edu.baylor.ecs.csi3471.lab3.iteration4.Student;

import java.util.Vector;

public class CitySimulation
{
    public static void main(String[] args)
    {
        Vector<edu.baylor.ecs.csi3471.lab3.iteration4.Person> persons = new Vector<>();
        Vector<edu.baylor.ecs.csi3471.lab3.iteration4.Student> students = new Vector<>();

        for (int i = 0; i < 100; i++)
        {
            int randNum = (int)(Math.random()*2);
            if (randNum == 0)
            {
                persons.add(new edu.baylor.ecs.csi3471.lab3.iteration4.Person("Mr. Smith", 101));
            }
            else
            {
                students.add(new edu.baylor.ecs.csi3471.lab3.iteration4.Student("Smith Jr.", 100, 61.0));
            }
        }

        System.out.println(Person.getPopulation() - edu.baylor.ecs.csi3471.lab3.iteration2.Student.getPopulation() + " persons were generated, and " + Student.getPopulation() + " students were generated.");

    }
}
