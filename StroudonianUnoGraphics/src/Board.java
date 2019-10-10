import java.awt.Graphics;
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
		init();
		deck.initdiscard();
	}
	public void init()
	{
		player = new Player[4];
		for (int i = 0; i < player.length; i ++)
			player[i]= new Player();
		int i = (int)(Math.random() * 3);
			turn = i;
		deck.fill();
		deck.Shufflecards();
		dalos();
		onward=true;
	}
	public void dalos()
	{
		for (int i = 0; i < player.length; i++)
		{
			for (int c = 0; c < 7; c++)
			{
				player[i].addcard(deck.getdrawontop());
				deck.removetop();
			}
		}
	}
	public int nextturn()
	{
		int temp = turn;
		if(onward)
			temp++;
		else
		{
			temp--;
			if(temp<0)
				temp = 3;
		}
		return temp%4;
	}
	public void setturn()
	{
		turn = nextturn();
	}
	public void jialiangge()//addtwo
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
	public void jiasige()//addfour
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
	public boolean canplay (Card c)
	{
		if(c.getColor().equals(deck.getontop().getColor())||c.getVal()==deck.getontop().getVal())
			return true;
		//13 wild 14 +4
		if(c.getVal()==13||c.getVal()==14)
			return true;
		return false;
	}
	public boolean checkuno()//dos
	{
		if(player[turn].getSize()<=1)
		{
			return true;
		}
		return false;
	}
	public void sacar(int i)//draw
	{
		player[i].addcard(deck.getdrawontop());
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
	public Card getcard(int mildred)
	{
		return player[turn].getcard(mildred);
	}
	public Card getdiscardontop()
	{
		return deck.getdiscardcard(0);
	}
	public int getturn()
	{
		return turn;
	}
	public Player getplayer()
	{
		return player[turn];
	}
	public void draw()
	{
		player[turn].addcard(deck.getdrawontop());
		deck.removetop();
	}
	public int getcurrentplayerhandsize()
	{
		return player[turn].getSize();
	}
	public Card getdeckontop()
	{
		return deck.getdrawontop();
	}
	public void setonward(boolean boo)
	{
		onward=boo;
	}
	public int getdiscardsize()
	{
		return deck.getdiscardsize();
	}
	public boolean getonward()
	{
		return onward;
	}
	public void addwildcolor(String color)
	{
		deck.add(new Card(color, -1));
	}
	public int getplayersize()
	{
		return player.length;
	}
	public int getspecificplayersize(int i)
	{
		return player[turn].getSize();
	}
	public int getnextplayer()
	{
		if (onward)
		{
			if (turn==player.length-1)
				return 1;
			else if (turn==player.length-2)
				return 0;
			else if (turn<player.length-1)
				return turn+=1;
		}
		else if (!onward)
		{
			if (turn==0)
				return player.length-2;
			else if (turn==1)
				return player.length-1;
			else if (turn>1)
				return turn-=1;
		}
		return -1;
	}
	public int getnextplayerlength(int next)
	{
		return player[next].getSize();
	}
	public int getdrawsize()
	{
		return deck.getSize();
	}
	public int getspecificplayer(int i)
	{
		if (onward)
		{
			if (turn+1==player.length)
				return 0;
			if (turn+i<=player.length-1)
				return turn+i;
			else if (turn+i>=player.length)
				return turn+i%4;
		}
		else if (!onward)
		{
			if (turn-1==0)
				return player.length-1;
			else if (turn-i>=0)
				return turn-i;
			else if (turn-i<=0)
				return -1*(turn-i)%4;
		}
		return -1;
	}
}