package model;

public class DistanceObject {

	String dataOne;
	String dataTwo;
	double distance;

	public DistanceObject(String dataOne, String dataTwo, double distance) {
		super();
		this.dataOne = dataOne;
		this.dataTwo = dataTwo;
		this.distance = distance;
	}

	public String getDataOne() {
		return dataOne;
	}

	public String getDataTwo() {
		return dataTwo;
	}

	public double getDistance() {
		return distance;
	}

	public void setDataOne(String dataOne) {
		this.dataOne = dataOne;
	}

	public void setDataTwo(String dataTwo) {
		this.dataTwo = dataTwo;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "DistanceObject [dataOne=" + dataOne + ", dataTwo=" + dataTwo
				+ ", distance=" + distance + "]";
	}

}
