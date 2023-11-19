/*
 * Author: Wesley Anastasi
 * Course: CSI 3471
 * Assignment: Lab 5
 * File: Make.java
 * Description: This class is used to store the settings of a make.
 * It contains a list of ModelSettings objects.
 */

package edu.baylor.cs.csi3471;

import java.util.*;

public class Make {
	static class sortSettings implements Comparator<ModelSettings>
	{
		@Override
		public int compare(ModelSettings a, ModelSettings b)
		{
			return a.compareTo(b);
		}
	}
	static  class sortYear implements Comparator<ModelSettings>
	{
		@Override
		public int compare(ModelSettings a, ModelSettings b)
		{
			String year1 = a.getYear();
			String year2 = b.getYear();
			return year1.compareTo(year2);
		}
	}
	private String makeName;
	private int makeID;
	public static int makeIdentification = 0;
	private ArrayList<ModelSettings> settingsArr;
	public Make(String[] line) {
		ModelSettings mSetting = new ModelSettings(line);
		makeName = line[6];
		makeID = makeIdentification;
		makeIdentification++;
		mSetting.setMake(this);
		settingsArr = new ArrayList<>();
		settingsArr.add(mSetting);

	}
	public String getMakeName()
	{
		return makeName;
	}

	public void setMakeID(int ID)
	{
		this.makeID = ID;
	}
	public int getMakeID() {return makeID;}
	public ArrayList<ModelSettings> getMakeSettingsList()
	{
		return settingsArr;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
		{
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}

		Make input = (Make) obj;
		return makeName.equals(input.makeName)
				&& settingsArr.equals(input.settingsArr)
				&& makeID == input.makeID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ makeName.hashCode();
		result = prime * result
				+ settingsArr.hashCode();
		return result;
	}

	@Override
	public String toString() {
		//Sort by year, transmission, displacement
		settingsArr.sort(new sortSettings());
		return "Make{" +
				"makeName='" + makeName + '\'' +
				", makeID= " + makeID +
				", modelSettings:" + settingsArr +
				'}';
	}

	public void printFilter(String column, String value)
	{
		if (column.equals("make"))
		{
			if (makeName.contains(value))
			{
				System.out.println(this);
			}
		}
		else if (column.equals("model"))
		{
			boolean contains = false;
			for (ModelSettings ms : settingsArr)
			{
				if (ms.getModel().contains(value))
				{
					contains = true;
				}
			}
			if (contains) {
				System.out.print("Make{" +
						"makeName='" + makeName + '\'' +
						", makeID= " + makeID + ", modelSettings:");
				for (ModelSettings ms : settingsArr) {
					if (ms.getModel().contains(value)) {
						System.out.print(ms);
					}
				}
				System.out.println('}');
			}
		}
	}

	public void printNumModels()
	{
		settingsArr.sort(new sortYear());
		String currYear = settingsArr.get(0).getYear();
		int numYears = 1;
		for (int i = 1; i < settingsArr.size(); i++)
		{
			if (!currYear.equals(settingsArr.get(i).getYear()))
			{
				System.out.println(makeName + ";" + currYear + ";" + numYears);
				currYear = settingsArr.get(i).getYear();
				numYears = 1;
			}
			else
			{
				numYears++;
			}
		}
	}

	public void addModelSettings(String[] line)
	{
		boolean updatedSettings = false;
		ModelSettings temp = new ModelSettings(line);
		temp.setMake(this);
		for (int i = 0; i < settingsArr.size(); i++)
		{
			if (settingsArr.get(i).equals(temp))
			{
				settingsArr.set(i, temp);
				updatedSettings = true;
			}
		}
		if (!updatedSettings)
		{
			settingsArr.add(temp);
		}
	}
}
