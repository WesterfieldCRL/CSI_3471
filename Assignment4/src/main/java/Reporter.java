import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;



public class Reporter
{
    static class sortCity implements Comparator<String[]>
    {
        @Override
        public int compare(String[] a, String[] b)
        {
            return a[1].compareToIgnoreCase(b[1]);
        }

    }
    public static void main(String[] args) throws FileNotFoundException 
    {

        ArrayList<String[]> reports = new ArrayList<>();
        if (args == null || args.length != 2) {
            System.out.println("syntaxt is Reported <file path> <1-3>");
            System.exit(0);
        }
        int type = Integer.parseInt(args[1]);
        Scanner scanner = new Scanner(new File(args[0]));
        //Skip title line
        scanner.nextLine();
        while (scanner.hasNext())
        {
            String[] values = scanner.nextLine().split(",");
            String[] temp = new String[5];
            System.arraycopy(values, 3, temp, 0, 5);
            reports.add(temp);
        }
        reports.sort(new sortCity());
        if (type == 1)
        {

            for (String[] aList : reports)
            {
                if (!aList[0].toLowerCase().contains("tech"))
                {
                    for (int i = 0; i < aList.length - 1; i++) {
                        System.out.print(aList[i] + ", ");
                    }
                    System.out.println(aList[aList.length - 1]);
                }
            }
        }
        else if (type == 2)
        {

            //<City, number of institutions>
            Map<String, Long> cityGroups =
                    reports.stream().collect((Collectors.groupingBy(strArray -> strArray[1], Collectors.counting())));
            ArrayList<String> cities = new ArrayList<>(cityGroups.keySet());
            cities.sort(String.CASE_INSENSITIVE_ORDER);
            for (String cityList : cities)
            {
                System.out.println(cityList + " has " + cityGroups.get(cityList) + " institutions.");
            }
        }
        else if (type == 3)
        {
            //<City, number of institutions>
            Map<String, Long> cityGroups =
                    reports.stream().collect((Collectors.groupingBy(strArray -> strArray[2], Collectors.counting())));
            //Swap keys and values
            Map<Long, String> stateGroups = new HashMap<>();
            for (Map.Entry<String, Long> entry : cityGroups.entrySet())
            {
                String key = entry.getKey();
                Long value = entry.getValue();

                stateGroups.put(value, key);
            }

            ArrayList<Long> stateInsts = new ArrayList<>(stateGroups.keySet());
            stateInsts.sort(Collections.reverseOrder());
            for (Long stateIts : stateInsts)
            {
                System.out.println(stateGroups.get(stateIts) + " has " + stateIts + " Institutions");
            }
        }
    }
}


