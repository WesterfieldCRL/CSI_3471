package edu.baylor.ecs.csi3471.AnastasiWesley.module1;

public class StringExercise1 {
    public static void main(String []args){
        StringExercise1 se1 = new StringExercise1();
        for (String arg : args) {
            System.out.println(se1.crazyCase(arg));
        }
    }
    public String crazyCase(String input) {
        input = input.toLowerCase();
        char[] chars = input.toCharArray();
        if (Character.toLowerCase(chars[0]) != 'i') {
            for (int i = 0; i < input.length(); i++) {
                if (i % 2 != 1) {
                    chars[i] = Character.toUpperCase(chars[i]);
                }
            }
        }

        return String.valueOf(chars);
    }
}
