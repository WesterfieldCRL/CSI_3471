/*
 * Author: Wesley Anastasi
 * Course: CSI 3471
 * Assignment: Lab 5
 * File: Tester.java
 * Description: This class is used to test the Make and MakeSettings classes.
 * It reads in a CSV file and creates Make and MakeSettings objects from the data.
 * It then performs the selected option.
 */

package edu.baylor.cs.csi3471;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Tester {

	public static class sortByName implements Comparator<Make>
	{
		@Override
		public int compare(Make a, Make b)
		{
			return a.getMakeName().compareToIgnoreCase(b.getMakeName());
		}
	}

	static class sortByNameReverse implements Comparator<Make>
	{
		@Override
		public int compare(Make a, Make b)
		{
			return (a.getMakeName().compareToIgnoreCase(b.getMakeName()))*-1;
		}
	}
	static class sortVClass implements  Comparator<String[]>
	{
		@Override
		public int compare(String[] a, String[] b)
		{
			return a[0].compareToIgnoreCase(b[0]);
		}
	}
	private static final int FILE_NAME = 1;
	private static final int OPTION = 0;

	private static int readOption(String[] args) {
		Integer option = null;
		/*if (args.length != 2/* what is the expected length *) {
			System.err.println("USAGE: java Tester <filename>");
			System.exit(1);
		} else {
			try {
				option = Integer.parseInt(args[OPTION]);
			} catch (NumberFormatException e) {
				System.err.println("call as java Tester <filename>");
				System.exit(1);
			}
		}*/
		option = Integer.parseInt(args[OPTION]);
		return option;
	}

	/*
	 * public static Collection<Make> populateSet(Collection<Make> set, String[]
	 * line){ //check the collection for existing make }
	 */

	public static ArrayList<Make> loadCSV(String file) throws FileNotFoundException {
		ArrayList<Make> makes = new ArrayList<>();
		boolean foundMake;
		BufferedReader reader = null;
		try {
			// ok, this is much faster than scanner :)
			reader = new BufferedReader(new FileReader(new File(file)));
			String line = null;
			reader.readLine();
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] split = line.split(",");

				if (makes.isEmpty())
				{
					makes.add(new Make(split));
				}
				else
				{
					foundMake = false;
					for (Make make : makes)
					{
						if (make.getMakeName().equals(split[6]))
						{
							make.addModelSettings(split);
							foundMake = true;
						}
					}
					if (!foundMake)
					{
						makes.add(new Make(split));
					}
				}
			}
			return makes;


		} catch (IOException e) {
			String hint = "";
			try {
				hint = "Current dir is: " + new File(".").getCanonicalPath();
			} catch (Exception local) {
				hint = local.getLocalizedMessage();
			}
			throw new FileNotFoundException(e.getLocalizedMessage() + "\n" + hint);

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.err.println(e.getLocalizedMessage());
				}
			}
		}

	}

	public static void main(String[] args) {
		int option = readOption(args);

		ArrayList<Make> makes = null;

		try {
			makes = loadCSV(args[FILE_NAME]);
		} catch (FileNotFoundException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

		if (option == 1)
		{
			System.out.println("Number of makes = " + makes.size());
			System.out.println("===============");
			makes.sort(new sortByNameReverse());
			for (Make out : makes)
			{
				System.out.println("Make name = " + out.getMakeName() + ", number of make settings = " + out.getMakeSettingsList().size());
			}
			System.out.println("===============");
			for (Make out : makes)
			{
				System.out.println(out);
			}
		}
		else if (option == 2)
		{
			String columnName = args[2];
			String value = args[3];
			makes.sort(new sortByName());
			for (Make out : makes)
			{
				out.printFilter(columnName, value);
			}

		}
		else if (option == 3)
		{
			ArrayList<String[]> VClass = new ArrayList<>();
			for (Make out : makes)
			{
				for (ModelSettings ms : out.getMakeSettingsList())
				{
					if (VClass.isEmpty())
					{
						String[] temp = new String[]{ms.getVClass(), String.valueOf(ms.getMpg().combined), "1"};
						VClass.add(temp);
					}
					else
					{
						boolean isFound = false;
						for (int i = 0; i < VClass.size(); i++)
						{
							if (VClass.get(i)[0].equals(ms.getVClass()))
							{
								int temp = Integer.parseInt(VClass.get(i)[1]);
								temp += ms.getMpg().combined;
								int updateSize = Integer.parseInt(VClass.get(i)[2]);
								updateSize++;
								String[] disaster = new String[]{ms.getVClass(), String.valueOf(temp), String.valueOf(updateSize)};
								VClass.set(i, disaster);
								isFound = true;
								i = VClass.size();
							}
						}
						if (!isFound)
						{
							String[] temp = new String[]{ms.getVClass(), String.valueOf(ms.getMpg().combined), "1"};
							VClass.add(temp);
						}
					}
				}
			}
			VClass.sort(new sortVClass());
			for (String[] out : VClass)
			{
				int avg = Integer.parseInt(out[1]) / Integer.parseInt(out[2]);
				System.out.println("VClass = " + out[0] + ", avg = " + avg);
			}
		}
		else if (option == 4)
		{
			makes.sort(new sortByName());
			for (Make out : makes)
			{
				out.printNumModels();
			}
		}
		else if (option == 5)
		{
			//list top 5 car models with the best average fuel efficiency
			int[] top5MPG = new int[]{-1, -1, -1, -1, -1};
			String[] top5MPGName = new String[5];
			for (Make out : makes)
			{
				for (ModelSettings ms : out.getMakeSettingsList())
				{
					boolean exists = false;
					for (int i = 0; i < 5; i++)
					{
						if (ms.getModel().equals(top5MPGName[i]))
						{
							exists = true;
							if (ms.getMpg().avg > top5MPG[i])
							{
								top5MPG[i] = ms.getMpg().avg;
								top5MPGName[i] = ms.getModel();
								break;
							}
						}
					}
					if (!exists)
					{
						for (int i = 0; i < 5; i++) {
							if (ms.getMpg().avg > top5MPG[i]) {

								top5MPG[i] = ms.getMpg().avg;
								top5MPGName[i] = ms.getModel();
								break;
							}
						}
					}
				}
			}
			for (int i = 0; i < 5; i++)
			{
				System.out.println(top5MPGName[i] + ", mpg = " + top5MPG[i]);
			}

			ArrayList<String[]> VClass = new ArrayList<>();
			for (Make out : makes)
			{
				for (ModelSettings ms : out.getMakeSettingsList())
				{
					String[] temp = new String[]{ms.getVClass(), String.valueOf(ms.getMpg().city)};
					VClass.add(temp);
				}
			}

			int result = 0;
			int prevAvg = 0;
			int VClassAvg = 0;
			String currVClass = VClass.get(0)[0];
			for (String[] hmm : VClass)
			{
				if (currVClass.equals(hmm[0]))
				{
					VClassAvg += Integer.parseInt(hmm[1]);
				}
				else
				{
					if (prevAvg != 0)
					{
						result += prevAvg - VClassAvg;
					}
					prevAvg = VClassAvg;
					VClassAvg = 0;
					currVClass = hmm[0];
				}
			}
			//If result is negative, it means that the average is increasing
			//if positive, it means the average is decreasing
			if (result < 0)
			{
				System.out.println("True");
			}
			else if (result > 0)
			{
				System.out.println("False");
			}
			else
			{
				System.out.println("No correlation");
			}

		}

	}
}