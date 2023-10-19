package edu.baylor.ecs.csi3471.AnastasiWesley.module1;

public class StringExercise2 {
    public static void main(String[] args){
        StringExercise2 se2 = new StringExercise2();
        String input = args[0];
        //NOTE: not removing parenthesis, because it seems to be done automatically

        String[] output = se2.splitter(input);
        for (String s : output) {
            System.out.println(s);
        }

    }
    public String[] splitter(String input){
        char[] chars = input.toCharArray();
        int numCommas = 0;
        for (char aChar : chars) {
            if (aChar == ',') {
                numCommas++;
            }
        }
        int splitPoint = 0;
        int numOutput = 0;
        String[] output = new String[numCommas+1];
        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] == ',')
            {
                output[numOutput] = input.substring(splitPoint,i);
                splitPoint = i+2; //NOTE: assuming here I need to remove the spaces as well
                numOutput++;
            }
        }
        output[numOutput] = input.substring(splitPoint);
        return output;
    }
}
