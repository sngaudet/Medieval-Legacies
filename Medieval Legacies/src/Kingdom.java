import java.util.Random;

public class Kingdom 
{
	// kingdom name
	private String setPlayerKingdomName;
	
	public String getPlayerKingdomName() {
		return setPlayerKingdomName;
	}

	public void setPlayerKingdomName(String playerKingdomName) {
		this.setPlayerKingdomName = playerKingdomName;
	}
	
	private Population population;
	private Economy economy;
	
	//risk of rebellion (rebellion triggered at 100)
	private double unrest;
	
	public double getUnrest() {
		return unrest;
	}

	public void setUnrest(double unrest) {
		this.unrest = unrest;
	}
	
	public double getNumRebels() {
		return population.getNumRebels();
	}

	public void setNumRebels(double numRebels) {
		population.setNumRebels(numRebels);;
	}
	
	public Kingdom(int pop)
	{
		population = new Population(pop);
		economy = new Economy(population.getNumFarmers(), population.getNumPeasants());
	}
	
	public double getKingdomPop()
	{
		return population.getPopulation();
	}
	
	public double getTreasury() {
		return economy.getTreasury();
	}
	
	public double getTaxRate()
	{
		return economy.getTaxRate();
	}
	
	public double getIncome() {
		return economy.getIncome();
	}
	
	public double getNumPeasants() {
		return population.getNumPeasants();
	}
	
	public void setTreasury(double treasury) {
		economy.setTreasury(treasury);
	}
	
	public void setTaxRate(double taxRate) {
		economy.setTaxRate(taxRate);
	}
	
	public void setIncome(double income) {
		economy.setIncome(income);
	}
	
	public void setNumPeasants(double numPeasants) {
		population.setNumPeasants(numPeasants);
	}
	
	public void passYear()
	{
		population.passYearPopulation();
		economy.passYearEconomy(population.getPopulation(), population.getNumPeasants());
	}
	
	public void kingdomSummary()
	{
		System.out.println(setPlayerKingdomName + "- ");
		System.out.println("Population size: " + (int)getKingdomPop());
		
		//totals for different age groups
		System.out.println("Children: " + (int)population.getNumChildren());
		System.out.println("Adults: " + (int)population.getNumAdults());
		System.out.println("Elderly: " + (int)population.getNumElderly());
		
		System.out.println("");
		
		//the different classes of the population
		System.out.println("Knights: " + (int)population.getNumKnights());
		System.out.println("Peasantry: " + (int)population.getNumPeasants());
		System.out.println("Farmers: " + (int)population.getNumFarmers());
		System.out.println("Tradesmen: " + (int)population.getNumTradesmen());
		
		System.out.println("");
		
		System.out.println("Food Supply: " + (int)economy.getFoodSupply());
		
		System.out.println("");
		
		System.out.println("Treasury: " + (int)economy.getTreasury());
		System.out.println("Income per year: " + (int)economy.getIncome());
	}
}
