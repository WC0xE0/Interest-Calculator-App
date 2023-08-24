package model;

public class Interest {
	private double principle;
	private double rate;
	private int years;
	private String ans;
	
	public Interest(double principle, double rate, int years) {
		this.principle = principle;
		this.rate = rate;
		this.years = years;
	}
	
	public String simpleInterest(double principle, double rate, int years) {
		double simpleInterest = principle + principle * (rate/100) * years;
		return String.format("%.2f", simpleInterest);
	}
	
	public String compoundInterest(double principle, double rate, int years) {
		double compoundInterest = principle * Math.pow((1 + rate/100), years);
		return String.format("%.2f", compoundInterest);
	}
	
	public String getSimpleInterest(double principle, double rate, int years) {
		String ans = "Principal: $" + String.format("%.2f",principle) + ", Rate: " + rate;
		ans += "\nYear, Simple Interest Amount";

		for (int i = 1; i <= years; i++) {
			ans += "\n" + i + "-->$" + simpleInterest(principle, rate, i);
		}

		return ans;
	}
	
	public String getCompoundInterest(double principle, double rate, int years) {
		String ans = "Principal: $" + String.format("%.2f", principle) + ", Rate: " + rate;
		ans += "\nYear, Compound Interest Amount";

		for (int i = 1; i <= years; i++) {
			ans += "\n" + i + "-->$" + compoundInterest(principle, rate, i);
		}

		return ans;
	}
	
	public String getBothInterests(double principle, double rate, int years) {
		String ans = "Principal: $" + String.format("%.2f", principle) + ", Rate: " + rate;
		ans += "\nYear, Simple Interest Amount, Compound Interest Amount";

		for (int i = 1; i <= years; i++) {
			ans += "\n" + i + "-->$" + simpleInterest(principle, rate, i);
			ans += "-->$" + compoundInterest(principle, rate, i);
		}

		return ans;
	}
	
}
