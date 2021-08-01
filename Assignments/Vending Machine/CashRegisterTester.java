//Meng Cha
//CECS 277
//Assignment 1 Vending Machine
//2/14/2018

public class CashRegisterTester
{
public static void main(String[] args)
{
final double NICKEL_VALUE = 0.05;
//final double DIME_VALUE = 0.10;
final double QUARTER_VALUE = 0.25;
final double DOLLAR_VALUE = 1.00;
CashRegister myRegister = new CashRegister();
myRegister.recordPurchase(1.82);	//purchase price
myRegister.enterPayment(1, new MonetaryUnit(DOLLAR_VALUE, "dollar bill"));
myRegister.enterPayment(3, new MonetaryUnit(QUARTER_VALUE, "quarter"));
myRegister.enterPayment(2, new MonetaryUnit(NICKEL_VALUE, "nickel"));
double myChange = myRegister.giveChange();
System.out.println("Change: " + myChange);
System.out.println("Expected: 0.03");
}
}
