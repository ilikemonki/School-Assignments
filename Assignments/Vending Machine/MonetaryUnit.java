//Meng Cha
//CECS 277
//Assignment 1 Vending Machine
//2/14/2018

public class MonetaryUnit {

	double monetaryValue;
	String monetaryUnit;
	public MonetaryUnit(double monetaryValue, String monetaryUnit)	//initializes the value and unit
	{
		this.monetaryValue = monetaryValue;
		this.monetaryUnit = monetaryUnit;
	}
	public double value()	//returns the value
	{
		return monetaryValue;
	}
}
