/*
 * Author: Wesley Anastasi
 * Course: CSI 3471
 * Assignment: Lab 5
 * File: ModelSettings.java
 * Description: This class is used to store the settings of a model.
 * It also contains the MPG class which is used to store the MPG of a model.
 */

package edu.baylor.cs.csi3471;

import java.util.Objects;

public class ModelSettings {
	private static int modelSettingsIdentification = 0;
	public static class MPG {
		int city;
		int combined;
		int avg;
		public MPG(int city, int combined, int avg)
		{
			this.city = city;
			this.combined = combined;
			this.avg = avg;
		}

		@Override
		public String toString() {
			return "{" +
					"city=" + city +
					", combined=" + combined +
					", avg=" + avg +
					"}";
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

			MPG input = (MPG) obj;
			return city == input.city
					&& combined == input.combined
					&& avg == input.avg;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ city;
			result = prime * result
					+ combined;
			result = prime * result
					+ avg;
			return result;
		}

	}

	private MPG mpg = null;
	private String cylinders;
	private String displacement;
	private String fuelType;
	private String model;
	private String transmission;
	private String VClass;
	private String year;
	private int modelSettingsID;
	private Make make;

	public ModelSettings(String[] line) {
		cylinders = line[2];
		displacement = line[3];
		fuelType = line[4];
		mpg = new MPG(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[5]));
		model = line[7];
		transmission = line[8];
		VClass = line[9];
		year = line[10];
		modelSettingsID = modelSettingsIdentification;
		modelSettingsIdentification++;
	}

	public void setMake(Make make)
	{
		this.make = make;
	}
	public MPG getMpg() {
		return mpg;
	}
	public void setMpg(MPG mpg) {
		this.mpg = mpg;
	}
	public String getModel()
	{
		return model;
	}
	public String getVClass()
	{
		return VClass;
	}

	public String getYear()
	{
		return year;
	}

	public int compareTo(ModelSettings ms)
	{
		int result = year.compareToIgnoreCase(ms.year);
		if (result == 0)
		{
			result = transmission.compareToIgnoreCase(ms.transmission);
			if (result == 0)
			{
				result = displacement.compareToIgnoreCase(ms.displacement);
			}
		}
		return result;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ cylinders.hashCode();
		result = prime * result
				+ displacement.hashCode();
		result = prime * result
				+ fuelType.hashCode();
		result = prime * result
				+ model.hashCode();
		result = prime * result
				+ transmission.hashCode();
		result = prime * result
				+ VClass.hashCode();
		result = prime * result
				+ year.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return	"\n{" +
				"mpg=" + mpg +
				", cylinders=" + cylinders +
				", displacement=" + displacement +
				", fuelType='" + fuelType + '\'' +
				", model='" + model + '\'' +
				", transmission='" + transmission + '\'' +
				", VClass='" + VClass + '\'' +
				", year=" + year +
				", modelSettingsID=" + modelSettingsID
				+ "}";
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

		ModelSettings input = (ModelSettings) obj;
		return cylinders.equals(input.cylinders)
				&& Objects.equals(displacement, input.displacement)
				&& fuelType.equals(input.fuelType)
				&& model.equals(input.model)
				&& transmission.equals(input.transmission)
				&& VClass.equals(input.VClass)
				&& year.equals(input.year)
				&& mpg.equals(input.mpg);
		//id is not included because a unique id exists for each instance
		//and the way make checks for duplicates would not work
		//with checking for the id
	}

}
