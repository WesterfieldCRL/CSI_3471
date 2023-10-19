//Wesley Anastasi
//Assignment 2
//CSI 3471
//September 3, 2023
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;

public class CSVParser {

    public String parenthesisObliterator(String input)
    {
        int i = 0;
        boolean incI;
        do
        {
            incI = true;
            if (input.charAt(i) == '"')
            {
                input = input.substring(0, i) + input.substring(i + 1);
                incI = false;
            }
            if (incI) {
                i++;
            }
        } while(i < input.length());
        return input;
    }

    private static String getString(String scannedLine, int prevSegment) {
        String temp = scannedLine.substring(prevSegment);
        int vitaminStart = 0;
        for (int i = 0; i < temp.length(); i++)
        {
            if (temp.charAt(i) == '/')
            {
                if (vitaminStart < 1) {
                    vitaminStart = i + 1;
                }
                else
                {
                    temp = temp.substring(vitaminStart, i);
                }
            }
        }
        return temp;
    }

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        CSVParser csvParser = new CSVParser();
        int prevSegment;
        int commaCount;
        int longestWorkout = 0;
        int highestWeight = 0;
        int highestVCConsumption = 0;
        double averageWeight = 0;
        String scannedLine;
        SimpleDateFormat date1 = new SimpleDateFormat("M/d/y H:m");
        SimpleDateFormat date2 = new SimpleDateFormat("M/d/y H:m");
        Calendar cal1;
        Calendar cal2;
        Vector<String[]> parsedLines = new Vector<>();

        if (args == null || args.length != 1) {
            System.out.println("syntax is CSVParser <file path>");
            System.exit(0);
        }

        Scanner scanner = new Scanner(new File(args[0]));
        //Skip first line
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            prevSegment = 0;
            commaCount = 0;
            scannedLine = scanner.nextLine();
            parsedLines.add(new String[8]);
            for (int i = 0; i < scannedLine.length(); i++)
            {
                if (scannedLine.charAt(i) == ',' && commaCount <= 7)
                {
                    parsedLines.get(parsedLines.size()-1)[commaCount] = scannedLine.substring(prevSegment, i);
                    parsedLines.get(parsedLines.size()-1)[commaCount] = csvParser.parenthesisObliterator(parsedLines.get(parsedLines.size()-1)[commaCount]);
                    prevSegment = i+1;
                    commaCount++;
                }
            }

            if (commaCount == 7)
            {
                //Note: since we just want the vitamin C consumption
                //This segment is getting parsed to just have that

                String temp = getString(scannedLine, prevSegment);
                parsedLines.get(parsedLines.size()-1)[commaCount] = temp;

            }
            else
            {
                parsedLines.remove(parsedLines.size()-1);
            }
        }



        //use the parsed data
        for (int i = 0; i < parsedLines.size(); i++)
        {
            //Find true duration based on start and end time
            date1.parse(parsedLines.get(i)[5]);
            cal1 = date1.getCalendar();
            date2.parse(parsedLines.get(i)[6]);
            cal2 = date2.getCalendar();
            parsedLines.get(i)[4] = String.valueOf(cal2.getTimeInMillis() - cal1.getTimeInMillis());

            //check for larger numbers
            if (Long.parseLong(parsedLines.get(i)[4]) > Long.parseLong(parsedLines.get(longestWorkout)[4]))
            {
                longestWorkout = i;
            }
            if (Integer.parseInt(parsedLines.get(i)[2]) > Integer.parseInt(parsedLines.get(highestWeight)[2]))
            {
                highestWeight = i;
            }
            if (Integer.parseInt(parsedLines.get(i)[7]) > Integer.parseInt((parsedLines.get(highestVCConsumption)[7])))
            {
                highestVCConsumption = i;
            }

            //calculate average
            averageWeight += Double.parseDouble(parsedLines.get(i)[3]);
        }

        averageWeight /= parsedLines.size();

        //output results
        System.out.println("Longest workout session = " + parsedLines.get(longestWorkout)[0]);
        System.out.println("Largest weight lifted = " + parsedLines.get(highestWeight)[0]);
        System.out.println("Largest Vitamin C consumption = " + parsedLines.get(highestVCConsumption)[0]);
        System.out.println("There are " + parsedLines.size() + " people, with an average weight of " + averageWeight + " lbs");

        scanner.close();
    }
}