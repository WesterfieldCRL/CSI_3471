package edu.baylor.ecs.csi3471.lab3.iteration5;

import java.util.Vector;

public class CitySimulation
{
    //it printed I am a student
    //the dispatch function had a student passed to it
    //and since it has inherited everything from Person
    //this caused no errors, and allowed printMe
    //to be called on the Student object
    public void dispatch(Person p)
    {
        p.printMe();
    }
    public static void main(String[] args)
    {
        CitySimulation sim = new CitySimulation();
        Vector<Person> citizen = new Vector<>();
        //Vector<Student> students = new Vector<>();

        for (int i = 0; i < 100; i++)
        {
            int randNum = (int)(Math.random()*2);
            if (randNum == 0)
            {
                citizen.add(new Person("Mr. Smith", 101));
            }
            else
            {
                citizen.add(new Student("Smith Jr.", 100, 61.0));
            }
            sim.dispatch(citizen.get(i));
        }

        System.out.println(Person.getPopulation() - Student.getPopulation() + " persons were generated, and " + Student.getPopulation() + " students were generated.");

    }
}
