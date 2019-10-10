//import java.awt.*;
//import java.awt.event.*;
//import java.util.ArrayList;
//import javax.swing.*;
//import javax.swing.text.DefaultCaret;
//
//public class Graphics implements MouseListener, ActionListener, KeyListener
//{
//	private JPanel paine;
//	private ArrayList <JLabel> currentdeck;
//	private JFrame thomas;
//	private JButton draw;
//	private JTextArea textarea;
//	private Board UNO;
//	private JLabel ontop;
//	private JLabel onward;
//	//private boolean colorchoosingmode;
//	private JLabel g, yellow, b, r, left, top, right;
//	private JLabel currentplayer;
//	public Font roman;
//	public Graphic() 
//	{	
//		UNO = new Board();
//		init();
//		//f to pay respects
//		smalllennie();
//	}
//	public void init() 
//	{
//		//colorchoosingmode = false;
//		currentdeck = new ArrayList <JLabel> ();
//		currentplayer = new JLabel();
//		
//		//panel WIDTH, HEIGHT
//		paine = new JPanel();
//		paine.setSize(2000, 900);
//		paine.setLayout(null);
//		paine.setBackground(new Color(35, 146, 219));
//
//	    //frame
//	    thomas = new JFrame("Stroudonian UNO");
//	    thomas.setSize(2000, 900);
//	    thomas.setResizable(false);
//	    thomas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    thomas.setBackground(new Color(35, 146, 219));
//
//	    //draw a card
//	    draw = new JButton("DRAW");
//	    draw.addActionListener(this);
//	    draw.setBounds(paine.getWidth()/2+100, paine.getHeight()/2-100, 100, 50);
//	    draw.setBackground(Color.cyan);
//	    
//	    //action box on top corner
//	    textarea = new JTextArea(4, 200);
//	    textarea.setEditable(false);
//	    DefaultCaret caret = (DefaultCaret)textarea.getCaret();
//	    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
//	    JScrollPane scroll = new JScrollPane(textarea);
//	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//	    scroll.setBounds(1500, 0, 500, 250);
//	    
//	    //other
//	    ImageIcon stroudonia = new ImageIcon("unocards/pileofcards.png");
//	    JLabel backofcard = new JLabel(stroudonia);
//	    backofcard.setBounds(paine.getWidth()/2-430, paine.getHeight()/2-100, 164, 182);
//	    roman = new Font("TimesRoman", Font.PLAIN, 30);
//	    currentplayer = new JLabel();
//	    currentplayer.setBounds(900, 100, 200, 100);
//	    currentplayer.setFont(roman);
//	    initontop(); 		
//	    setonward();
//	    initsideplayers();
//	    
//	    //add
//	    paine.add(backofcard);
//	    paine.add(currentplayer);
//	    paine.add(ontop);
//	    paine.add(onward);
//	    paine.add(scroll);
//	    paine.add(draw);
//	    thomas.add(paine);
//	    thomas.setVisible(true);
//	    
//	    //init messages
//	    say("~~~UNO~~~");
//	    say("\nYour mission, should you choose to accept it, is to play this game of \nStroudonian UNO\nInstructions:\nClick a CARD to play a CARD (Duh)\nClick \"DRAW\" to draw\nAs always, should any Stroudonian agents be caught or killed,\nthe Secretary will disavow any knowledge of your actions.\nGood luck.\n");
//	    say("Unfortunately, due to budget cuts, our wildcards and +4s \ncurrently only accept random values\nFull functionality will be added soon!");
//	}
//	public void initImage() 
//	{	
//		say("\nPlayer "+UNO.getturn()+" has");
//		int shift = 0;
//		int row = 550;
//		//deck size
//		int playercards = UNO.getcurrentplayerhandsize();
//		for (int i = 0; i <= playercards-1; i++)
//		{
//			say("\n");
//			say("Loaded a "+UNO.getcard(i).getColor()+"_"+UNO.getcard(i).getVal());
//			//"unocards/"+UNO.getcard(i).getVal()+"_"+UNO.getcard(i).getVal()+".png"
//			ImageIcon icon = new ImageIcon("unocards/"+UNO.getcard(i).getColor()+"_"+UNO.getcard(i).getVal()+".png");
//			JLabel temp = new JLabel(icon);
//			if(shift>=thomas.getWidth())	
//			{
//				shift=0;
//				row+=50;
//				temp.setBounds(new Rectangle(new Point(shift, row), temp.getPreferredSize()));
//			}
//			else
//				temp.setBounds(new Rectangle(new Point(shift, row), temp.getPreferredSize()));
//			temp.addMouseListener(this);
//			currentdeck.add(temp);
//			shift+=135;
//		}
//		for (int i = 0; i <= playercards-1; i ++)
//		{
//			paine.add(currentdeck.get(i));
//			paine.repaint();
//		}	
//	}
//	public void setonward()
//	{
//		if(UNO.getonward())
//		{
//			ImageIcon onwards = new ImageIcon("unocards/onward.png");
//			onward = new JLabel(onwards);
//			onward.setBounds(0, 0, 100, 100);
//			onward.setText("Clockwise");
//			onward.setHorizontalTextPosition(JLabel.CENTER);
//			onward.setVerticalTextPosition(JLabel.CENTER);
//		}
//		else if (!UNO.getonward())
//		{
//			ImageIcon onwards = new ImageIcon("unocards/onwardfalse.png");
//			onward = new JLabel(onwards);
//			onward.setBounds(0, 0, 100, 100);
//			onward.setText("Counterclockwise");
//			onward.setHorizontalTextPosition(JLabel.CENTER);
//			onward.setVerticalTextPosition(JLabel.CENTER);
//		}
//	}
//	public void initontop()
//	{
//		ImageIcon i = new ImageIcon("unocards/"+UNO.getdiscardontop().getColor()+"_"+UNO.getdiscardontop().getVal()+".png");
//		ontop = new JLabel(i);
//		ontop.setBounds(new Rectangle(new Point((paine.getWidth()/2)-100, paine.getHeight()/2-100), ontop.getPreferredSize()));
//	}
//	public void removecard(int i)
//	{
//		currentdeck.remove(i);
//	}
//	public void initsideplayers()
//	{
//		left = new JLabel();
//		left.setText("Player "+UNO.getturn()+1+" has "+UNO.getspecificplayersize(UNO.nextturn()));
//		left.setBounds(0, paine.getHeight()/2-100, 100, 50); 
//		right = new JLabel();
//		right.setText("Player "+UNO.getnextplayer()+1+" has\n"+UNO.getspecificplayersize(UNO.nextturn()));
//		right.setBounds(0, paine.getWidth()/2-100, 100, 50);
//		top = new JLabel();
//		top.setText("Player "+UNO.getnextplayer()+1+" has\n"+UNO.getspecificplayersize(UNO.nextturn()));
//		top.setBounds(paine.getWidth()/2, 0, 100, 50);
//		paine.add(left);
//		paine.add(right);
//		paine.add(top);
//	}
//	public void mouseEntered(MouseEvent arg0) 
//	{
//		//no
//	}
//	public void mouseExited(MouseEvent arg0) 
//	{
//		//no
//	}
//	public void mousePressed(MouseEvent arg0) 
//	{
//		double x = MouseInfo.getPointerInfo().getLocation().getX();
//		double y = MouseInfo.getPointerInfo().getLocation().getY();
//		JLabel label = (JLabel) arg0.getSource();
//		int index = currentdeck.indexOf(label);
//		String george;
//		try
//		{
//			if (x >= label.getX() && x <= label.getX() + label.getWidth() && y >= label.getY() && y <= label.getY() + label.getHeight())
//			{
//				//+4 14
//				if (UNO.getplayer().getcard(index).getVal()==14)
//				{
//					UNO.playcard(index);
//					int i = (int)(Math.random() * 3);
//					if(i==0)
//					{
//						george = "GREEN";
//						UNO.addwildcolor(george);
//						say("\nColor changed to "+george);
//					}
//					if(i==1)
//					{
//						george = "YELLOW";
//						UNO.addwildcolor(george);
//						say("\nColor changed to "+george);
//					}
//					if(i==2)
//					{
//						george = "BLUE";
//						UNO.addwildcolor(george);
//						say("\nColor changed to "+george);
//					}
//					if(i==3)
//					{
//						george = "RED";
//						UNO.addwildcolor(george);
//						say("\nColor changed to "+george);
//					}
//					//colorchoosingmode=true; colorchoose();
//					if(UNO.getturn()+1>UNO.getplayersize()-1)
//						say("\nPlayer 0 got +4ed!");
//					else if (UNO.getturn()+1<UNO.getplayersize()-1)
//						say("\nPlayer "+UNO.getnextplayer()+" got +4ed!");
//					UNO.jiasige();
//					smalllennie();
//					return;
//				}
//				//wildcard 13
//				else if (UNO.getplayer().getcard(index).getVal()==13)
//				{
//					UNO.playcard(index);
//					int i = (int)(Math.random() * 3);
//					if(i==0)
//					{
//						george = "GREEN";
//						UNO.addwildcolor(george);
//						say("\nColor changed to "+george);
//					}
//					if(i==1)
//					{
//						george = "YELLOW";
//						UNO.addwildcolor(george);
//						say("\nColor changed to "+george);
//					}
//					if(i==2)
//					{
//						george = "BLUE";
//						UNO.addwildcolor(george);
//						say("\nColor changed to "+george);
//					}
//					if(i==3)
//					{
//						george = "RED";
//						UNO.addwildcolor(george);
//						say("\nColor changed to "+george);
//					}
//					//colorchoosingmode=true;colorchoose();
//					UNO.setturn();
//					smalllennie();
//					return;
//				}
//				else if (UNO.canplay(UNO.getcard(index)))
//				{
//					/* +2 10
//					 rev 11
//					 skip 12 */
//					//+2
//					if (UNO.getplayer().getcard(index).getVal() == 10)
//					{
//						UNO.playcard(index);
//						getUNO();
//						removecards();
//						say("\nPlayer " + UNO.getnextplayer() + " got +2ed!");
//						UNO.jialiangge();
//						smalllennie();
//						return;
//					}
//					//reverse
//					else if (UNO.getplayer().getcard(index).getVal() == 11)
//					{
//						UNO.playcard(index);
//						getUNO();
//						removecards();
//						UNO.setonward(!UNO.getonward());
//						say("\nPlayer " + UNO.getnextplayer() + " played a reverse!");
//						UNO.setturn();
//						smalllennie();
//						return;
//					}
//					//skip
//					else if (UNO.getplayer().getcard(index).getVal() == 12)
//					{
//						UNO.playcard(index);
//						getUNO();
//						removecards();
//						UNO.zuzhi();
//						say("Skipped!");
//						smalllennie();
//						return;
//					}
//					else if (index <= UNO.getplayer().getSize() - 1 && UNO.canplay(UNO.getcard(index)))
//					{
//						say("\nPlayer " + UNO.getturn() + " played a " + UNO.getplayer().getcard(index).toString());
//						UNO.playcard(index);
//						getUNO();
//						removecards();
//						UNO.setturn();
//						smalllennie();
//						return;
//					}
//				}
//				say("\nCannot play card");
//				removecards();
//				smalllennie();
//			}
//		}
//		catch (Exception e)
//		{
//			say("\n"+e.toString());
//		}
//		/*if(colorchoosingmode&&x >= label.getX() && x <= label.getX() + label.getWidth() && y >= label.getY() && y <= label.getY() + label.getHeight())
//		{
//			if(label.equals(g))
//			{
//				george = "GREEN";
//				UNO.addwildcolor(george);
//				say("\nColor changed to "+george);
//				endcolorchoose();
//				UNO.jiasige();
//				smalllennie();
//			}
//			if(label.equals(yellow))
//			{
//				george = "YELLOW";
//				UNO.addwildcolor(george);
//				say("\nColor changed to "+george);
//				UNO.jiasige();
//				smalllennie();
//				endcolorchoose();
//			}
//			if(label.equals(b))
//			{
//				george = "BLUE";
//				UNO.addwildcolor(george);
//				say("\nColor changed to "+george);
//				endcolorchoose();
//				UNO.jiasige();
//				smalllennie();
//			}
//			if(label.equals(r))
//			{
//				george = "RED";
//				UNO.addwildcolor(george);
//				say("\nColor changed to "+george);
//				endcolorchoose();
//				UNO.jiasige();
//				smalllennie();
//			}
//		}*/
//	}
//	public void mouseClicked(MouseEvent arg0) 
//	{
//		//no
//	}
//	/*public void colorchoose() 
//	{
//		if(colorchoosingmode)
//		{
//			say("\nColor Choosing Mode!");
//			//green
//			ImageIcon green = new ImageIcon("unocards/greensquare.png");
//			g = new JLabel(green);
//			g.setBounds(new Rectangle(new Point(600, 150), g.getPreferredSize()));
//			g.setText("Green");
//			g.addMouseListener(this);
//			paine.add(g);
//			//yellow
//			ImageIcon y = new ImageIcon("unocards/yellowsquare.png");
//			yellow = new JLabel(y);
//			yellow.setBounds(new Rectangle(new Point(700, 150), yellow.getPreferredSize()));
//			yellow.setText("Yellow");
//			yellow.addMouseListener(this);
//			paine.add(yellow);
//			//blue
//			ImageIcon blue = new ImageIcon("unocards/bluesquare.png");
//			b = new JLabel(blue);
//			b.setBounds(new Rectangle(new Point(800, 150), b.getPreferredSize()));
//			b.setText("Blue");
//			b.addMouseListener(this);
//			paine.add(b);
//			//red
//			ImageIcon red = new ImageIcon("unocards/redsquare.png");
//			r = new JLabel(red);
//			r.setBounds(new Rectangle(new Point(900, 150), r.getPreferredSize()));
//			r.setText("Red");
//			r.addMouseListener(this);
//			paine.add(r);
//		}
//	}*/
//	public void endcolorchoose()
//	{
//		paine.remove(g);
//		paine.remove(yellow);
//		paine.remove(b);
//		paine.remove(r);
//		//colorchoosingmode=false;
//	}
//	public void mouseReleased(MouseEvent arg0) 
//	{
//		//no
//	}
//	public void actionPerformed(ActionEvent e) 
//	{
//		//change later so anonymous
//		say("\nPlayer "+UNO.getturn()+" drew a "+UNO.getdeckontop().toString());
//		UNO.draw();
//		removecards();
//		UNO.setturn();
//		smalllennie();
//		return;
//	}
//	public void keyPressed(KeyEvent arg0) 
//	{
//		String stroud = (String)arg0.getSource();
//		if (stroud.toUpperCase().equals("Y"))
//			System.exit(0);
//	}
//	public void keyReleased(KeyEvent arg0) 
//	{
//		//no
//	}
//	public void keyTyped(KeyEvent arg0)
//	{
//		//no
//	}
//	public void removecards()
//	{
//		Component[] compsci = paine.getComponents();
//		for(Component c : compsci)
//		{
//		    if(c instanceof JLabel&&c.getY()>=550)
//		    {
//		    	currentdeck.remove(c);
//		    	paine.remove(c);
//		    }
//		}
//		paine.remove(ontop);
//		paine.revalidate();
//		paine.repaint();
//	}
//	public void say(String i)
//	{
//		textarea.append(i);
//	}
//	public void getUNO()
//	{
//		if(UNO.checkuno())
//		{
//			if(UNO.getplayer().getSize()==1)		
//				say("\nPlayer "+UNO.getturn()+" has an UNO!");
//			if(UNO.getplayer().getSize()==0)
//				intheendgame();
//		}
//	}
//	//avengers...
//	public void intheendgame()
//	{
//		paine.removeAll();
//		paine.setBackground(Color.white);
//		removecards();
//		currentdeck.clear();
//		JLabel thanos = new JLabel();
//		thanos.setBounds(100,100, 100, 100);
//		thanos.setFont(new Font("Courier", Font.PLAIN, 30));
//		thanos.setText("Player "+UNO.getturn()+"has won the game. You are the worthy one. Thanks for playing! Press Y to end");
//		thanos.setHorizontalTextPosition(JLabel.CENTER);
//		thanos.setVerticalTextPosition(JLabel.CENTER);
//		paine.add(thanos);
//		paine.addKeyListener(this);
//	}
//	//f
//	public void smalllennie()
//	{
//		initImage();
//		initontop();
//		setonward();
//		say("\nCurrent Card is a "+UNO.getdiscardontop().toString());
//		say("\nNumber of cards in discard: "+UNO.getdiscardsize());
//		say("\nNumber of cards in draw pile "+UNO.getdrawsize());
//		currentplayer.setText("Player "+UNO.getturn());
//	}
//}