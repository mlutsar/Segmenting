package model;

public class Data {

	String segmentName;
	Integer averageRevenue;
	Integer riskScore;
	Integer age;
	Integer residence;
	Float children;
	Integer income;
	Integer percentMale;

	public Data(String segmentName, Integer averageRevenue, Integer riskScore,
			Integer age, Integer residence, Float children, Integer income,
			Integer percentMale) {
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

	public Data() {
		// TODO Auto-generated constructor stub
	}

	public String getSegmentName() {
		return segmentName;
	}

	public Integer getAverageRevenue() {
		return averageRevenue;
	}

	public Integer getRiskScore() {
		return riskScore;
	}

	public Integer getAge() {
		return age;
	}

	public Integer getResidence() {
		return residence;
	}

	public Float getChildren() {
		return children;
	}

	public Integer getIncome() {
		return income;
	}

	public Integer getPercentMale() {
		return percentMale;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public void setAverageRevenue(Integer averageRevenue) {
		this.averageRevenue = averageRevenue;
	}

	public void setRiskScore(Integer riskScore) {
		this.riskScore = riskScore;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setResidence(Integer residence) {
		this.residence = residence;
	}

	public void setChildren(Float children) {
		this.children = children;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public void setPercentMale(Integer percentMale) {
		this.percentMale = percentMale;
	}

	@Override
	public String toString() {
		return "Data [segmentName=" + segmentName + ", averageRevenue="
				+ averageRevenue + ", riskScore=" + riskScore + ", age=" + age
				+ ", residence=" + residence + ", children=" + children
				+ ", income=" + income + ", percentMale=" + percentMale + "]";
	}

}
