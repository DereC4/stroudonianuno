import java.util.ArrayList;
import java.util.Arrays;

public class Player 
{
	private ArrayList<Card>hand;
	public Player()
	{
		hand = new ArrayList<Card>();
	}
	public void addcard(Card c)
	{
		hand.add(c);
	}
	public void removecard(int c)
	{
		hand.remove(c);
	}
	public Card getcard(int i)
	{
		return hand.get(i);
	}
	public String print()
	{
		String str = "";
		for (int i = 0; i < hand.size(); i++)
			str+= "["+i+"] "+hand.get(i).toString()+"\n";
		return str;
	}
	public int getSize()
	{
		return hand.size();
	}
}
