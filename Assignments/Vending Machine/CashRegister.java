//Meng Cha
//CECS 277
//Assignment 1 Vending Machine
//2/14/2018

import java.text.DecimalFormat;
public class CashRegister {
	
	double purchasePrice;
	int quantityMoney;
	double total;
	MonetaryUnit m;
	DecimalFormat df = new DecimalFormat("#.##");	//set the total to 2 decimals
	public CashRegister() //default
	{
		
	}
	
	public void recordPurchase(double purchasePrice)	//Gets the price
	{
		this.purchasePrice = purchasePrice;
	}
	
	public void enterPayment(int quantityMoney, MonetaryUnit m)	//Totals up all the money
	{
		this.quantityMoney = quantityMoney;
		this.m = m;
		total += quantityMoney * m.value();
	}
	
	public double giveChange()	//returns the change
	{
		return Double.valueOf(df.format(total - purchasePrice));
	}
}
