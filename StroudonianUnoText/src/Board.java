import java.util.Random;
import java.util.Scanner;

public class Board 
{
	private Player[]player;
	private Deck deck;
	private int turn;
	private boolean onward;
	public Board()
	{
		deck = new Deck();
		player = new Player[4];
		for (int i = 0; i < player.length; i ++)
			player[i]= new Player();
		init();
		deck.initdiscard();
	}
	public void init()
	{
		System.out.println("~~~~~~~~~~~~~~~~~~~UNO~~~~~~~~~~~~~~~~~~~");
		System.out.println("Your mission, should you choose to accept it, is to play this game of Stroudonian UNO\nInstructions:\nType CARD NUMBER to play a card\nType any variation of \"draw\" to draw\nAs always, should any Stroudonian agents be caught or killed, the Secretary will disavow any knowledge of your actions. This message will self-destruct in five seconds.\nGood luck.\n");
		int i = (int)(Math.random() * 3);
			turn = i;
		deck.fill();
		deck.Shufflecards();
		dalos();
		onward=true;
	}
	public void dalos()//"to give"
	{
		for (int i = 0; i < player.length; i++)
		{
			for (int c = 0; c < 7; c++)
			{
				player[i].addcard(deck.getcard(0));
				deck.removetop();
			}
		}
	}
	public void nextturn()
	{
		//continue turn
		if (onward)
		{
			if(turn != player.length-1)
				turn++;
			else 
				turn = 0;
		}
		//backward
		else if (!onward)
		{
			if(turn > 0)
				turn--;
			else if (turn<=0)
				turn = player.length-1;
		}
	}
	public void jialiangge()
	{
		//onward true does usual add onward false will remove backwards. Simple as that
		if(onward)
		{
			if (turn==player.length-1)
			{
				sacar(0);
				sacar(0);
				turn=1;
			}
			else if (turn==player.length-2)
			{
				sacar(3);
				sacar(3);
				turn=0;
			}
			else if (turn<player.length-1)
			{
				sacar(turn+1);
				sacar(turn+1);
				turn+=2;
			}
		}
		else if (!onward)
		{
			if (turn==0)
			{
				sacar(player.length-1);
				sacar(player.length-1);
				turn=player.length-2;
			}
			else if (turn==1)
			{
				sacar(0);
				sacar(0);
				turn=player.length-1;
			}
			else if (turn>1)
			{
				sacar(turn-1);
				sacar(turn-1);
				turn-=2;
			}
		}
	}
	public void jiasige()
	{
		//onward true does usual add onward false will remove backwards. Simple as that
		if(onward)
		{
			if (turn==player.length-1)
			{
				sacar(0);
				sacar(0);
				sacar(0);
				sacar(0);
				turn=1;
			}
			else if (turn==player.length-2)
			{
				sacar(3);
				sacar(3);
				sacar(3);
				sacar(3);
				turn=0;
			}
			else if (turn<player.length-1)
			{
				sacar(turn+1);
				sacar(turn+1);
				sacar(turn+1);
				sacar(turn+1);
				turn+=2;
			}
		}
		else if (!onward)
		{
			if (turn==0)
			{
				sacar(player.length-1);
				sacar(player.length-1);
				sacar(player.length-1);
				sacar(player.length-1);
				turn=player.length-2;
			}
			else if (turn==1)
			{
				sacar(0);
				sacar(0);
				sacar(0);
				sacar(0);
				turn=player.length-1;
			}
			else if (turn>1)
			{
				sacar(turn-1);
				sacar(turn-1);
				sacar(turn-1);
				sacar(turn-1);
				turn-=2;
			}
		}
	}
	public boolean canplay (Card ontop, Card c)
	{
		if(c.getColor().equals(ontop.getColor())||c.getVal()==ontop.getVal())
			return true;
		//13 wild 14 +4
		if(c.getVal()==13||c.getVal()==14)
			return true;
		return false;
	}
	public void UNO()//dos
	{
		Random rand = new Random();
		int uselessgameendtext = rand.nextInt(5);
		if (uselessgameendtext == 1)
			System.out.println("I call hax");
		if (uselessgameendtext == 2)
			System.out.println("Stroudonia salutes you");
		if (uselessgameendtext == 3)
			System.out.println("Mission Success");
		if (uselessgameendtext == 4)
			System.out.println("Ladies and Gentlemen, We Got'em");
		if (uselessgameendtext == 5)
			System.out.println("Mission Accomplished");
	}
	public void checkUNO()
	{
		if(player[turn].getSize()==1)
			System.out.println("Player "+turn+" has an UNO!");
		if(player[turn].getSize()<=0)
		{
			System.out.println("Player "+turn+"... you are the worthy one");
			UNO();
			endgame();
		}
	}
	public void endgame()
	{
		System.out.println("Player "+turn+" is a true Stroudonian. \nGame end");
		System.exit(0);
	}
	public void sacar(int i)//draw
	{
		player[i].addcard(deck.getcard(0));
		deck.removetop();
	}
	public void zuzhi()
	{
		if(onward)
		{
			if (turn==player.length-1)
				turn=1;
			else if (turn==player.length-2)
				turn=0;
			else if (turn<player.length-1)
				turn+=2;
		}
		else if (!onward)
		{
			if (turn==0)
				turn=player.length-2;
			else if (turn==1)
				turn=player.length-1;
			else if (turn>1)
				turn-=2;
		}
	}
	public void playcard(int montag)
	{
		deck.add(player[turn].getcard(montag));
		player[turn].removecard(montag);
		Card temp = deck.getdiscardcard(1);
		deck.addtodraw(temp);
		deck.removebot();
	}
	public void smalllennie() 
	{
		Scanner scan = new Scanner (System.in);
		System.out.println("Player Number: " + turn);
		System.out.println("Cards: \n\n"+player[turn].print());
		System.out.println("Current Deck Card: "+deck.getdiscardcard(0));
		System.out.println("Deck Size: "+deck.getdiscardsize());
		System.out.println("Draw Pile Size: "+deck.getdrawsize());
		if(onward)	
			System.out.println("Current direction: Clockwise");
		if(!onward)
			System.out.println("Current direction: Counterclockwise");
		String choice = scan.next();
		if(choice.toUpperCase().equals("DRAW"))
		{
			System.out.println("Player "+turn+" has drawn a card");
			sacar(turn);
			checkUNO();
			nextturn();
			smalllennie();
			return;
		}
		else if (Integer.parseInt(choice)>=0) 
		{
			int montag = Integer.parseInt(choice);
			//check number is out of bounds or it cannot play
			if (montag>=player[turn].getSize()||canplay(deck.ontop(), player[turn].getcard(montag))==false)
			{
				System.out.println("Play a valid card, dummy");
				smalllennie();
				return;
			}
			//14 +4
			else if (player[turn].getcard(montag).getVal()==14)
			{
				playcard(montag);
				checkUNO();
				System.out.println("Enter a color (Red, Blue, Yellow, Green)");
				String color = scan.next().toUpperCase();
				deck.add(new Card(color, -1));
				jiasige();
				System.out.println("4ed! Color changed to "+color);
				smalllennie();
				return;
			}
			//wildcard 13
			else if (player[turn].getcard(montag).getVal()==13)
			{
				playcard(montag);
				checkUNO();
				System.out.println("Enter a color (Red, Blue, Yellow, Green)");
				String color = scan.next().toUpperCase();
				System.out.println("Color changed to "+color);
				deck.add(new Card(color, -1));
				nextturn();
				smalllennie();
				return;
			}
			//all other cards
			else if (montag<=player[turn].getSize()-1 && canplay(deck.ontop(), player[turn].getcard(montag)))
			{
				/* +2 10
				 rev 11
				 skip 12 */
				//+2
				if (player[turn].getcard(montag).getVal()==10)
				{
					playcard(montag);
					checkUNO();
					jialiangge();
					System.out.println("2ed!");
					smalllennie();
					return;
				}
				//reverse
				else if (player[turn].getcard(montag).getVal()==11)
				{
					playcard(montag);
					checkUNO();
					onward=!onward;
					System.out.println("Player "+turn+" played a reverse!");
					nextturn();
					smalllennie();
					return;
				}
				//skip
				else if (player[turn].getcard(montag).getVal()==12)
				{
					playcard(montag);
					checkUNO();
					zuzhi();
					System.out.println("Skipped!");
					smalllennie();
					return;
				}
				//normal card
				else if (montag<=player[turn].getSize()-1 && canplay(deck.ontop(), player[turn].getcard(montag)))
				{
					System.out.println("Player "+turn+" played a "+player[turn].getcard(montag).getColor()+" "+player[turn].getcard(montag).getVal());
					playcard(montag);
					checkUNO();
					nextturn();
					smalllennie();
					return;
				}
			}
		}
		System.out.println("Play a valid card, dummy");
		smalllennie();
	}
}