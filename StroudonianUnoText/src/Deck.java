import java.util.ArrayList;
import java.util.Collections;

public class Deck 
{
	private ArrayList <Card> cards;
	private ArrayList <Card> discard;
	public Deck()
	{
		cards = new ArrayList<Card>(108);
		discard = new ArrayList<Card>();
	}
	public void fill()
	{
		/*# 0 -9
		10 +2
		11 Reverse
		12 Skip
		13 Wildcard
		14 +4*/
		String color="";
		for (int i = 0; i < 4; i++)
		{
			//wild
			cards.add(new Card("BLACK", 13));
			//+4
			cards.add(new Card("+4", 14));
			if (i == 0)
				color = "RED";
			else if (i == 1)
				color = "BLUE";
			else if (i == 2)
				color = "YELLOW";
			else if (i == 3)
				color = "GREEN";
			cards.add(new Card(color, 0));
			for (int r = 0; r < 2; r++)
			{
				for (int x = 1; x < 10; x++) 
				{
				cards.add(new Card(color, x));
				}
				//+2
				cards.add(new Card(color, 10));
				//rev
				cards.add(new Card(color, 11));
				//skip
				cards.add(new Card(color, 12));
			}
		}
	}
	public void Shufflecards()
	{
		Collections.shuffle(cards);
	}
	public Card removetop()
	{
		return cards.remove(0);
	}
	public void add(Card i)
	{
		discard.add(0, i);
	}
	public Card getdiscardcard(int i)
	{
		return discard.get(i);
	}
	public Card getcard(int i)
	{
		return cards.get(i);
	}
	public Card ontop()
	{
		return discard.get(0);
	}
	public int getSize()
	{
		return cards.size();
	}
	public void initdiscard()
	{
		discard.add(cards.get(0));
		cards.remove(0);
		//if the card played is a black card
		if(discard.get(0).getVal()==14||discard.get(0).getVal()==13)
		{
			Card c = discard.get(0);
			discard.add(cards.get(0));
			removetop();
			addtodraw(c);
			Shufflecards();
		}
	}
	public int getdiscardsize()
	{
		return discard.size();
	}
	public void addtodraw(Card i)
	{
		if(i.getVal()!=-1)
			cards.add(cards.size()-1, i);
	}
	public void removebot()
	{
		discard.remove(discard.size()-1);
	}
	public int getdrawsize()
	{
		return cards.size();
	}
}