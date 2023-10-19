package edu.baylor.ecs.csi3471.AnastasiWesley.module1;

public class StringExercise3 {
    public static void main(String[] args){
        StringExercise3 se3 = new StringExercise3();
        for (String arg : args) {
            System.out.println(se3.httpRemover(arg));
        }
    }
    public String httpRemover(String input){
        if (input.toUpperCase().contains("HTTPS"))
        {
            input = input.substring(5);
        }
        else
        {
            input = input.substring(4);
        }

        return input;
    }
}
