import java.util.Random;

public class Population 
{
	private double population;
	private double numChildren;
	private double numAdults;
	private double numElderly;
	private double numKnights;
	private double numFarmers;
	private double numTradesmen;
	private double numPeasants;

	private double growthRate;
	
	public double getGrowthRate() {
		return growthRate;
	}

	public double getPopulation() {
		return population;
	}

	public double getNumChildren() {
		return numChildren;
	}

	public double getNumAdults() {
		return numAdults;
	}

	public double getNumElderly() {
		return numElderly;
	}

	public double getNumKnights() {
		return numKnights;
	}

	public double getNumFarmers() {
		return numFarmers;
	}

	public double getNumTradesmen() {
		return numTradesmen;
	}

	public double getNumPeasants() {
		return numPeasants;
	}
	
	public Population(double pop)
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
		
		growthRate = .2;
	}
	
	public void setNumPeasants(double numPeasants) {
		this.numPeasants = numPeasants;
	}
	
	public void passYear()
	{
		population = (population + (population * growthRate));
		numChildren = (numChildren + (numChildren * growthRate));
		numAdults = (numAdults + (numAdults * growthRate));
		numElderly = (numElderly + (numElderly * growthRate));
		numKnights = (numKnights + (numKnights * growthRate));
		numFarmers = (numFarmers + (numFarmers * growthRate));
		numTradesmen = (numTradesmen + (numTradesmen * growthRate));
		numPeasants = (numPeasants + (numPeasants * growthRate));
	}
}
