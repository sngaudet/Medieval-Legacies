import java.util.Random;

public class Person 
{
	private int age;
	protected boolean isChild;
	protected boolean isAdult;
	protected boolean isElderly;
	protected boolean isKnight;
	//NOTE: It might not be necessary to create a name String field... Names are not relevant unless they become a noble.
	
	/*
	 * Default constructor for a Person generates them with a random age between 0 to 90
	 * Person will also have a 50 percent chance to be knight if they are an adult
	 */
	public Person()
	{
		Random ran = new Random();
		int a = ran.nextInt(91);
		age = a;
		
		if (age <= 13) isChild = true;
		else if (age > 13 && age <= 55) isAdult = true;
		else isElderly = true;
		
		if (isAdult)
		{
			int b = ran.nextInt(1);
			if (b == 0) isKnight = true;
		}
	}
	
	/*
	 * Specifying constructor for a Person generates them with the specified age
	 * @Param age: how old you want the Person to be
	 */
	public Person(int age)
	{
		this.age = age;
		
		if (age <= 13) isChild = true;
		else if (age > 13 && age <= 55) isAdult = true;
		else isElderly = true;
	}
	
	/*
	 * Overloaded specifying constructor for a Person generates them with the specified age and whether or not they are a knight
	 * @Param age: how old you want the Person to be
	 * @Param isKnight: whether or not they are a knight
	 */
	public Person(int age, boolean isKnight)
	{
		this.age = age;
		this.isKnight = isKnight;
		
		if (age <= 13) isChild = true;
		else if (age > 13 && age <= 55) isAdult = true;
		else isElderly = true;
	}
	
	public int getAge() 
	{
		return age;
	}

	public void setAge(int age) 
	{
		this.age = age;
	}
	
	
}
