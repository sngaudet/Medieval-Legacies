import java.util.Random;
import java.util.Scanner;

public class Game 
{
	private Scanner stdIn = new Scanner(System.in);
	Random r = new Random();
	
	//in-game year (year is A.D. (also known as C.E.)
	private int year = 500;
	
	//Playable Kingdoms
	private Kingdom england = new Kingdom(100000);
	private Kingdom france = new Kingdom(100000);
	
	//Placeholder for the kingdom the player chooses
	private Kingdom playerKingdom;
	
	//default constructor
	public Game() {}
	
	/*
	 * Begins the game by allowing the player to choose a starting kingdom
	 */
	public void start()
	{
		boolean valid = false;
		
		//***Play game***
		
		//Welcome
		System.out.println("Welcome to Medieval Legacies!");
		
		//Choose Kingdom
		System.out.println("Playable Kingdoms:");
		System.out.println(">England");
		System.out.println(">France\n");
		
		do
		{
			System.out.print("Choose a kingdom to rule: ");
			String input = stdIn.next();
			if (input.equalsIgnoreCase("England")) 
			{
				playerKingdom = england;
				playerKingdom.setPlayerKingdomName(input);
				valid = true;
			}
			else if (input.equalsIgnoreCase("France")) 
			{
				playerKingdom = france;
				playerKingdom.setPlayerKingdomName(input);
				valid = true;
			}
			else System.out.println("Sorry, that is not a valid kingdom");
		} while (!valid);


		System.out.println("\nIt is the year 500 of your lord and savior, Jesus Christ. \nYou are the king of " + playerKingdom.getPlayerKingdomName() + ".");
		playerKingdom.kingdomSummary();

	}
	
	public void takeTurn()
	{	
		System.out.println("\n\nOptions: ");
		System.out.println("1. Raise taxes ");
		System.out.println("2. Fight rebels (if any...)");
		System.out.println("End Turn (type end)");
		System.out.println("End Game (type exit)");
		boolean valid = false;
		
		do
		{
			System.out.print("\n\nWhat would you like to do? ");
			String input = stdIn.next();
			
			if (input.equalsIgnoreCase("exit"))
			{
				System.out.println("Thank you for playing!\n");
				return;
			}
			
			else if (input.equalsIgnoreCase("end"))
			{
				valid = true;
				endTurn();
			}
			
			else if (input.equalsIgnoreCase("1"))
			{
				raiseTaxes();	
			}
			
			else if (input.equalsIgnoreCase("2"))
			{
				if (playerKingdom.getNumRebels() > 0) fightRebels();
				else System.out.println("There is currently no rebellion...\n");
			}
			
			else
			{
				System.out.print("Sorry, that is not a valid option...\nWhat would you like to do? ");
				input = stdIn.next();
			}
		} while (!valid);
		
		takeTurn();
	}
	
	private void endTurn()
	{
		++year;
		
		System.out.println("\n            " + year + " A.D.\n");
		
		playerKingdom.passYear();
		playerKingdom.kingdomSummary();
	}
	
	private void raiseTaxes()
	{
		String input;
		boolean valid = false;
		System.out.println("\nCurrent tax rate is " + (int)playerKingdom.getTaxRate() + " pounds per peasant.");
		do
		{
			System.out.print("Raise to " + (int)(playerKingdom.getTaxRate() + 5) + " pounds per peasant? ");
			input = stdIn.next();
			
			if (input.equalsIgnoreCase("yes"))
			{
				double taxRate = playerKingdom.getTaxRate();
				playerKingdom.setTaxRate(taxRate+=5);
				double income = playerKingdom.getIncome();
				double numPeasants = playerKingdom.getNumPeasants();
				playerKingdom.setIncome(numPeasants * taxRate);
				double unrest = playerKingdom.getUnrest();
				playerKingdom.setUnrest(unrest += ((r.nextDouble() * 30) + 10)); //could increase by as much as 40, and as little as 10 percent
				System.out.println("New Tax Rate: " + (int)taxRate);
				System.out.println("New Income per year: " + (int)income);
				if (unrest >= 100)
				{
					System.out.println("\nRebellion! The peasantry has risen against you!!!");
					peasantRebellion();
				}
				else if (unrest > 90) System.out.println("As your palanquin was being escorted to the gardens, you heard many shouts of discontent, and your guards had to arrest several peasants who attempted to assault you...");
				else if (unrest > 70) System.out.println("Your advisors inform you that something must be done soon, lest the peasants rise in rebellion against you...");
				else if (unrest > 50) System.out.println("You hear rumblings of discontent amongst the peasantry...");
					
				valid = true;
			}
			else if (input.equalsIgnoreCase("no"))
			{
				valid = true;
				//do nothing
			}
			else
			{
				System.out.println("Sorry, that is not a valid option... ");
			}
		} while (!valid);
	}
	
	public void peasantRebellion()
	{
		double numPeasants = playerKingdom.getNumPeasants();
		double percentRebelled = r.nextDouble();
		if (percentRebelled < .3) percentRebelled = .3;
		else if (percentRebelled > .9) percentRebelled = .9;
		playerKingdom.setNumRebels(numPeasants * percentRebelled);
		
		double numRebels = playerKingdom.getNumRebels();
		playerKingdom.setNumPeasants(numPeasants-=numRebels);
		
		System.out.println("\n" + (int)numRebels + " peasants have mustered together into a formidable fighting force.");
		System.out.println("Your advisors report that " + (int)numPeasants + " peasants have remained loyal to you and continue to pay your taxes.");
	}
	
	public void fightRebels()
	{
		double numKnights = playerKingdom.getNumKnights();
		double numRebels = playerKingdom.getNumRebels();
		double knightsRoll = r.nextInt(6);
		knightsRoll += 1;
		double knightsNumModifier = numKnights/1000;
		knightsRoll+=knightsNumModifier;
		double rebelsRoll = r.nextInt(6);
		double rebelsNumModifier = numRebels/1000;
		rebelsRoll+=rebelsNumModifier;
		
		if (knightsRoll > rebelsRoll)
		{
			double percentCasualties = r.nextDouble();
			double casualties = numRebels*percentCasualties;
			playerKingdom.setNumRebels(numRebels-casualties);
			
			System.out.println("After long hours of fighting you emerge victorious!");
			System.out.println("\nKnights: " + (int)playerKingdom.getNumKnights());
			System.out.println("\nRebels: " + (int)playerKingdom.getNumRebels());
		}
		else if (knightsRoll < rebelsRoll)
		{
			double percentCasualties = r.nextDouble();
			double casualties = numKnights*percentCasualties;
			playerKingdom.setNumKnights(numKnights-casualties);
			
			System.out.println("After long hours of fighting you find the rebels have bested you...");
			System.out.println("\nKnights: " + (int)playerKingdom.getNumKnights());
			System.out.println("\nRebels: " + (int)playerKingdom.getNumRebels());
		}
		
		if (playerKingdom.getNumRebels() <= 1000)
		{
			playerKingdom.setNumRebels(0);
			playerKingdom.setUnrest(0); // a successful war against the rebels means they have no desire to rebel again anytime soon.
			System.out.println("\nVictory!!! The rebels numbers are so few that they cannot bare to fight any longer.\nThe rebels are crushed...\n");
		}
		else
		{
			System.out.println("\nThe rebellion continues...\n");
		}
	}
}
