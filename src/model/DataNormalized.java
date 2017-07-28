package model;

public class DataNormalized {

	String segmentName;
	Float averageRevenue;
	Float riskScore;
	Float age;
	Float residence;
	Float children;
	Float income;
	Float percentMale;

	public DataNormalized() {
		// TODO Auto-generated constructor stub
	}

	public DataNormalized(String segmentName, Float averageRevenue,
			Float riskScore, Float age, Float residence, Float children,
			Float income, Float percentMale) {
		super();
		this.segmentName = segmentName;
		this.averageRevenue = averageRevenue;
		this.riskScore = riskScore;
		this.age = age;
		this.residence = residence;
		this.children = children;
		this.income = income;
		this.percentMale = percentMale;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public Float getAverageRevenue() {
		return averageRevenue;
	}

	public Float getRiskScore() {
		return riskScore;
	}

	public Float getAge() {
		return age;
	}

	public Float getResidence() {
		return residence;
	}

	public Float getChildren() {
		return children;
	}

	public Float getIncome() {
		return income;
	}

	public Float getPercentMale() {
		return percentMale;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public void setAverageRevenue(Float averageRevenue) {
		this.averageRevenue = averageRevenue;
	}

	public void setRiskScore(Float riskScore) {
		this.riskScore = riskScore;
	}

	public void setAge(Float age) {
		this.age = age;
	}

	public void setResidence(Float residence) {
		this.residence = residence;
	}

	public void setChildren(Float children) {
		this.children = children;
	}

	public void setIncome(Float income) {
		this.income = income;
	}

	public void setPercentMale(Float percentMale) {
		this.percentMale = percentMale;
	}

}
