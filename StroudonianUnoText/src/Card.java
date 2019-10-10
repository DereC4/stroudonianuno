public class Card 
{
	private String color;// r 0 b 1 y 2 g 3 s
	private int val;
	public Card(String color, int val)
	{
		this.color=color;
		this.val=val;
	}
	public String getColor()
	{
		return color;
	}
	public int getVal()
	{
		return val;
	}
	public String toString()
	{
		/* +2 10
		 rev 11
		 skip 12 */
		if (val==10)
			return color + " " + "+2";
		if (val==11)
			return color + " " + "REVERSE";
		if (val==12)
			return color + " " + "SKIP";
		//Card that tilts me every match 
		if (val==14)
			return "+4";
		if (val==13)
			return "WILDCARD";
		return color + " " + val;
	}
}