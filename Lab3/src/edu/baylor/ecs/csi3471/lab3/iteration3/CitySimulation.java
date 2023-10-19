package edu.baylor.ecs.csi3471.lab3.iteration3;

import edu.baylor.ecs.csi3471.lab3.iteration3.Student;

import java.util.Vector;

public class CitySimulation
{
    public static void main(String[] args)
    {
        Student student1 = new Student("John", 20, 40);
        Student student2 = new Student("John", 20, 40);


        System.out.println("student1.equals(student2) = " + student1.equals(student2));

    }
}
