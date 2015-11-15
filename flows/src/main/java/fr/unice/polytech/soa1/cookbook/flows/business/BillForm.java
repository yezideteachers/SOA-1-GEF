package fr.unice.polytech.soa1.cookbook.flows.business;


import java.io.Serializable;

public class BillForm implements Serializable {

	private double amount;
	private double sum;
	private String date;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "BillForm{" +
				"amount=" + amount +
				", sum=" + sum +
				", date='" + date + '\'' +
				'}';
	}
}
