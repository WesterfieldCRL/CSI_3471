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

	static class sortByName implements Comparator<Make>
	{
		@Override
		public int compare(Make a, Make b)
		{
			return a.getMakeName().compareToIgnoreCase(b.getMakeName());
		}
	}
	private static final int FILE_NAME = 1;
	private static final int OPTION = 0;

	private static int readOption(String[] args) {
		Integer option = null;
		if (args.length != 2/* what is the expected length */) {
			System.err.println("USAGE: java Tester <filename>");
			System.exit(1);
		} else {
			try {
				option = Integer.parseInt(args[OPTION]);
			} catch (NumberFormatException e) {
				System.err.println("call as java Tester <filename>");
				System.exit(1);
			}
		}
		return option;
	}

	/*
	 * public static Collection<Make> populateSet(Collection<Make> set, String[]
	 * line){ //check the collection for existing make }
	 */

	private static ArrayList<Make> loadCSV(String file) throws FileNotFoundException {
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
			makes.sort(new sortByName());
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

	}
}
