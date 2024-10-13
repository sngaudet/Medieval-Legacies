import java.util.Random;

public class Economy 
{
	//food supply
	private double foodSupply;
	
	//royal treasury
	private double treasury;
	private double taxRate;
	private double income;

	public double getFoodSupply() {
		return foodSupply;
	}

	public double getTreasury() {
		return treasury;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public double getIncome() {
		return income;
	}
	
	public Economy(double farmers, double peasants)
	{
		foodSupply = (farmers/4)* 30; //4 farmers per farm, each farm produces 30 bushels per year
		taxRate = 30;
		income = peasants * taxRate;
		treasury = 10000;
	}
	
	public void setTreasury(double treasury) {
		this.treasury = treasury;
	}
	
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	
	public void setIncome(double income) {
		this.income = income;
	}
	
	public void passYearEconomy(double population, double numPeasants)
	{
		foodSupply = (foodSupply - population) + foodSupply;
		treasury = (treasury + income); 
		income = numPeasants * taxRate;
	}
}
