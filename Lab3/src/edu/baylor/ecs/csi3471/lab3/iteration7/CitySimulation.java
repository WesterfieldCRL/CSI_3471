package edu.baylor.ecs.csi3471.lab3.iteration7;

import java.util.Vector;

public class CitySimulation
{
    //it printed this is a student
    //this is for the same reason
    //that it worked on module 5
    //which is because it is the actual object calling the function
    //rather than looking at what type is being passed in.
    public void dispatch(Person p)
    {
        //p.printMe();
        //identify(p);
        p.accept(new CitySimulation());
    }
    public void identify(Person p)
    {
        System.out.println("this is a person");
    }
    public void identify(Student s)
    {
        System.out.println("this is a student");
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
