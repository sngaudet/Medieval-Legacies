import java.util.Random;

public class Kingdom 
{
	//population variables
	protected double population;
	protected double numChildren;
	protected double numAdults;
	protected double numElderly;
	protected double numKnights;
	protected double numFarmers;
	protected double numTradesmen;
	protected double numPeasants;
	
	//food supply
	protected double foodSupply;
	
	//royal treasury
	protected double treasury;
	protected double taxRate;
	protected double income;
	
	//growth rate
	protected double growthRate;
	
	//risk of rebellion (rebellion triggered at 100)
	protected double unrest;
	
	//amount of rebels if there is a rebellion
	protected double numRebels;
	
	public Kingdom(int pop)
	{
		population = pop;
		
		Random r = new Random();
		
		for (int i = 0; i < pop; ++i)
		{
			int y = r.nextInt(3);
			
			if (y == 0) ++numChildren;
			else if (y == 1) ++numAdults;
			else ++numElderly;
		}	
		
		double adults = numAdults;
		numKnights = (adults * .2); //20 percent of all adults are knights
		numFarmers = (adults * .7); //70 percent of all adults are farmers
		numTradesmen = (adults * .1); //10 percent of all adults are tradesmen
		numPeasants = numFarmers + numTradesmen; //farmers and tradesmen combined, this is the taxed portion of the population
		
		foodSupply = (numFarmers/4)* 30; //4 farmers per farm, each farm produces 30 bushels per year
		taxRate = 30;
		income = numPeasants * taxRate;
		treasury = 10000;
		
		growthRate = .2;
	}
	
	private double getKingdomPop()
	{
		return population;
	}
	
	public void kingdomSummary()
	{
		System.out.println("England- ");
		System.out.println("Population size: " + (int)getKingdomPop());
		
		//totals for different age groups
		System.out.println("Children: " + (int)numChildren);
		System.out.println("Adults: " + (int)numAdults);
		System.out.println("Elderly: " + (int)numElderly);
		
		System.out.println("");
		
		//the different classes of the population
		System.out.println("Knights: " + (int)numKnights);
		System.out.println("Peasantry: " + (int)numPeasants);
		System.out.println("Farmers: " + (int)numFarmers);
		System.out.println("Tradesmen: " + (int)numTradesmen);
		
		System.out.println("");
		
		System.out.println("Food Supply: " + (int)foodSupply);
		
		System.out.println("");
		
		System.out.println("Treasury: " + (int)treasury);
		System.out.println("Income per year: " + (int)income);
	}
}
