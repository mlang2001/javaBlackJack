import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Table extends JPanel implements ActionListener
{
	private ArrayList<Card> playerCards, dealerCards, deck;
	private int playerTotal, dealerTotal, timesHitPlayer, timesHitDealer, dealerWins, playerWins;
	private JButton hitButton, standButton, startButton, dealButton;
	private boolean stand, start, win, pointsGiven;
	private BufferedImage backgroundImage, cardBackImage;
	private Font font, buttonFont, buttonFont2;


	public Table()
	{
		this.setLayout(null);

		deck = new ArrayList<Card>();
		playerCards = new ArrayList<Card>();
		dealerCards = new ArrayList<Card>();
				
		deck.add(new Card(2, "2", "hearts"));
		deck.add(new Card(3, "3", "hearts"));		
		deck.add(new Card(4, "4", "hearts"));
		deck.add(new Card(5, "5", "hearts"));		
		deck.add(new Card(6, "6", "hearts"));
		deck.add(new Card(7, "7", "hearts"));
		deck.add(new Card(8, "8", "hearts"));
		deck.add(new Card(9, "9", "hearts"));		
		deck.add(new Card(10, "10", "hearts"));
		deck.add(new Card(10, "J", "hearts"));		
		deck.add(new Card(10, "Q", "hearts"));
		deck.add(new Card(10, "K", "hearts"));
		deck.add(new Card(11, "A", "hearts"));

		deck.add(new Card(2, "2", "spades"));
		deck.add(new Card(3, "3", "spades"));		
		deck.add(new Card(4, "4", "spades"));
		deck.add(new Card(5, "5", "spades"));		
		deck.add(new Card(6, "6", "spades"));
		deck.add(new Card(7, "7", "spades"));
		deck.add(new Card(8, "8", "spades"));
		deck.add(new Card(9, "9", "spades"));		
		deck.add(new Card(10, "10", "spades"));
		deck.add(new Card(10, "J", "spades"));		
		deck.add(new Card(10, "Q", "spades"));
		deck.add(new Card(10, "K", "spades"));
		deck.add(new Card(11, "A", "spades"));

		deck.add(new Card(2, "2", "clubs"));
		deck.add(new Card(3, "3", "clubs"));		
		deck.add(new Card(4, "4", "clubs"));
		deck.add(new Card(5, "5", "clubs"));		
		deck.add(new Card(6, "6", "clubs"));
		deck.add(new Card(7, "7", "clubs"));
		deck.add(new Card(8, "8", "clubs"));
		deck.add(new Card(9, "9", "clubs"));		
		deck.add(new Card(10, "10", "clubs"));
		deck.add(new Card(10, "J", "clubs"));		
		deck.add(new Card(10, "Q", "clubs"));
		deck.add(new Card(10, "K", "clubs"));
		deck.add(new Card(11, "A", "clubs"));

		deck.add(new Card(2, "2", "diamonds"));
		deck.add(new Card(3, "3", "diamonds"));		
		deck.add(new Card(4, "4", "diamonds"));
		deck.add(new Card(5, "5", "diamonds"));		
		deck.add(new Card(6, "6", "diamonds"));
		deck.add(new Card(7, "7", "diamonds"));
		deck.add(new Card(8, "8", "diamonds"));
		deck.add(new Card(9, "9", "diamonds"));		
		deck.add(new Card(10, "10", "diamonds"));
		deck.add(new Card(10, "J", "diamonds"));		
		deck.add(new Card(10, "Q", "diamonds"));
		deck.add(new Card(10, "K", "diamonds"));
		deck.add(new Card(11, "A", "diamonds"));

		shuffle();

		playerCards.add( deck.get(0) );
		dealerCards.add( deck.get(1) );
		playerCards.add( deck.get(2) );
		dealerCards.add( deck.get(3) );
			
		deck.remove(0); 
		deck.remove(0);
		deck.remove(0); 
		deck.remove(0);
		
		stand = false;
		start = false;
		win = false;
		playerWins = 0;
		dealerWins = 0;
		timesHitPlayer = 0;
		pointsGiven = false;
		font = new Font("Futura", Font.BOLD + Font.ITALIC, 100);
		buttonFont = new Font("Futura", Font.BOLD + Font.ITALIC, 30);
		buttonFont2 = new Font("Futura", Font.BOLD + Font.ITALIC, 55);

		startButton = new JButton("Start Game");
		startButton.setBounds(300,200,400,100);
		startButton.addActionListener(this);
		this.add(startButton);
		startButton.setFont(buttonFont2);
		startButton.setForeground(Color.WHITE);
		startButton.setBackground(Color.RED);
		startButton.setOpaque(true);
		startButton.setBorderPainted(false);

		hitButton = new JButton("Hit");
		hitButton.setBounds(740,460,200,50);
		hitButton.addActionListener(this);
		this.add(hitButton);
		hitButton.setFont(buttonFont);
		hitButton.setForeground(Color.WHITE);
		hitButton.setBackground(Color.RED);
		hitButton.setOpaque(true);
		hitButton.setBorderPainted(false);
		hitButton.setVisible(false);

		standButton = new JButton("Stand");
		standButton.setBounds(740,520,200,50);
		standButton.addActionListener(this);
		this.add(standButton);
		standButton.setFont(buttonFont);
		standButton.setForeground(Color.WHITE);
		standButton.setBackground(Color.RED);
		standButton.setOpaque(true);
		standButton.setBorderPainted(false);
		standButton.setVisible(false);

		dealButton = new JButton("Deal");
		dealButton.setBounds(740,400,200,50);
		dealButton.addActionListener(this);
		this.add(dealButton);
		dealButton.setFont(buttonFont);
		dealButton.setForeground(Color.WHITE);
		dealButton.setBackground(Color.RED);
		dealButton.setOpaque(true);
		dealButton.setBorderPainted(false);
		dealButton.setVisible(false);
		dealButton.setEnabled(false);	

		try {
				backgroundImage = ImageIO.read(new File("images/Bape.jpg"));
			} catch( IOException e){}

		try {
				cardBackImage = ImageIO.read(new File("images/Supreme.png"));
			} catch( IOException e){}

		this.setFocusable(true);	
	}
	
	public Dimension getPreferredSize() 
	{
		//Sets the size of the panel
		return new Dimension(1000,600);
	}

	public void paintComponent(Graphics g)
	{	
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, null);

		if (start == true)
		{
			dealButton.setVisible(true);
			standButton.setVisible(true);
			hitButton.setVisible(true);
			startButton.setVisible(false);
			
			int x = 50;
			playerTotal = 0;
			dealerTotal = 0;

			for(int i = 0; i < playerCards.size(); i++)
			{
				playerTotal = playerTotal + playerCards.get(i).getValue();
			}

			for(int i = 0; i < dealerCards.size(); i++)
			{
				dealerTotal = dealerTotal + dealerCards.get(i).getValue();
			}

			g.setFont(buttonFont);
			g.setColor(Color.WHITE);
			
			g.drawString("Total: " + playerTotal, 50, 590);

			if(stand == false)
			{
				int dealerShown = dealerTotal - dealerCards.get(0).getValue();
				g.drawString("Dealer Total: " + dealerShown, 50, 390);
			}
			else
			{
				g.drawString("Dealer Total: " + dealerTotal, 50, 390);
			}
			
			g.drawString("Dealer Wins: " + dealerWins, 500, 390);
			g.drawString("Player Wins: " + playerWins, 500, 590);
			
			int playerY = 405;
			for(int i = 0; i < playerCards.size(); i++)
			{
				playerCards.get(i).drawMe(g, x + i * 100, playerY);
			}

			int dealerY = 200;
			if(stand == false)
			{
				for(int i = 0; i < dealerCards.size(); i++)
				{
					dealerCards.get(i).drawMe(g, x + i * 100, dealerY);
				}
				g.drawImage(cardBackImage, 50, 200, null);
				/*g.setColor(Color.WHITE);
				g.fillRect(50, 200, 100, 150);
				g.setColor(Color.BLACK);
				g.drawRect(50, 200, 100, 150);*/
			}
			else
			{
				for(int i = 0; i < dealerCards.size(); i++)
				{
					dealerCards.get(i).drawMe(g, x + i * 100, dealerY);
				}
			}

			winText(checkWinner(), g);

			if(stand == true)
			{
				dealButton.setEnabled(true);
				standButton.setEnabled(false);
				hitButton.setEnabled(false);
			}

			
		}
		/*if(score == 0)
		{
			g.setColor(Color.RED);
			g.fillRect(270, 95, 460, 120);
			g.setColor(Color.WHITE);
			g.setFont(font);
			dealButton.setVisible(false);
			standButton.setVisible(false);
			hitButton.setVisible(false);
			g.drawString("You Lose", 295, 200);
		}*/
	}

	private void reset()
	{
		for(int i = 0; i < playerCards.size(); i++)
		{
			deck.add(playerCards.get(i));
		}
		for(int i = 0; i <= playerCards.size() + timesHitPlayer; i++)
		{
			playerCards.remove(0);
		}
		for(int i = 0; i < dealerCards.size(); i++)
		{
			deck.add(dealerCards.get(i));
		}
		for(int i = 0; i <= dealerCards.size() + timesHitDealer; i++)
		{
			dealerCards.remove(0);
		}
		timesHitPlayer = 0;
		timesHitDealer = 0;
		stand = false;
		win = false;
		pointsGiven = false;
		shuffle();
	}
	private void winText(boolean win, Graphics g)
	{

		if(playerTotal == dealerTotal)
		{
			g.setColor(Color.RED);
			g.fillRect(390, 50, 215, 120);
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("Tie", 430, 155);
		}
		else if(win == false && pointsGiven == true)
		{
			g.setColor(Color.RED);
			g.fillRect(270, 50, 460, 120);
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("You Lose", 295, 155);
		}
		else if(win = true && stand == true)
		{
			g.setColor(Color.RED);
			g.fillRect(270, 50, 460, 120);
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("You Win", 295, 155);
		}

		win = false;
	}

	private void dealerTurn()
	{
		if(dealerTotal < 17)
		{
			dealerCards.add(deck.get(0));
			deck.remove(0);
			timesHitDealer++;
			repaint();
		}
	}
	private boolean checkWinner()
	{
		if(playerTotal > 21 && pointsGiven == false)
		{
			win = false;
			dealerWins++;
			pointsGiven = true;
			this.playSoundLose();
			dealButton.setEnabled(true);
			hitButton.setEnabled(false);
			standButton.setEnabled(false);
		}
		if(playerTotal == 21)
		{
			stand = true;
		}
		if(stand == true && pointsGiven == false)
		{
			dealerTurn();
			if(dealerTotal > 16)
			{
				if(playerTotal > dealerTotal && playerTotal <= 21 && pointsGiven == false)
				{
					win = true;
					playerWins++;
					pointsGiven = true;
					this.playSoundWin();
				}
				else if(dealerTotal > 21 && playerTotal <= 21 && pointsGiven == false)
				{
					win = true;
					playerWins++;
					pointsGiven = true;
					this.playSoundWin();
				}
				else if(dealerTotal == 21 && playerTotal != 21 && pointsGiven == false)
				{
					win = false;
					dealerWins++;
					pointsGiven = true;
					this.playSoundLose();
				}
				else if(dealerTotal > playerTotal && dealerTotal <= 21&& pointsGiven == false)
				{
					win = false;
					dealerWins++;
					pointsGiven = true;
					this.playSoundLose();
				}
			}
		}
		repaint();
		return win;
	}
	private void shuffle()
	{
		for(int i = 0; i < deck.size() + 100; i++)
		{
			int a = (int)(Math.random() * deck.size());
			int b = (int)(Math.random() * deck.size());

			Card temp = deck.get(a);

			deck.set(a, deck.get(b));
			deck.set(b, temp);
		}	
	}

	public void playSoundWin()
	{
		try {
			URL url = this.getClass().getClassLoader().getResource("sounds/win.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}
	public void playSoundLose()
	{
		try {
			URL url = this.getClass().getClassLoader().getResource("sounds/lose.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}
	public void playSoundHit(int playerTotal)
	{
		if(playerTotal < 17)
		{
			try {
				URL url = this.getClass().getClassLoader().getResource("sounds/hit.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(url));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		}
	}
	public void playSoundStand(int playerTotal)
	{
		if(playerTotal > 16)
		{
			try {
				URL url = this.getClass().getClassLoader().getResource("sounds/stand.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(url));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == hitButton)
		{
			playerCards.add( deck.get(0) );
			timesHitPlayer++;
			playerTotal = playerTotal + deck.get(0).getValue();
			deck.remove(0);
			repaint();
			if(playerTotal < 21)
			{	
				this.playSoundHit(playerTotal);
				this.playSoundStand(playerTotal);
			}
		}
		if(e.getSource() == standButton)
		{
			stand = true;
			hitButton.setEnabled(false);
			standButton.setEnabled(false);
			repaint();
		}
		if(e.getSource() == startButton)
		{
			start = true;
			shuffle();
			repaint();

			this.playSoundHit(playerTotal);
			this.playSoundStand(playerTotal);
		}
		if(e.getSource() == dealButton)
		{
			reset();
			dealButton.setEnabled(false);
			hitButton.setEnabled(true);
			standButton.setEnabled(true);

			playerCards.add( deck.get(0) );
			dealerCards.add( deck.get(1) );
			playerCards.add( deck.get(2) );
			dealerCards.add( deck.get(3) );
				
			deck.remove(0); 
			deck.remove(0);
			deck.remove(0); 
			deck.remove(0);
			repaint();

			playerTotal = 0;

			for(int i = 0; i < playerCards.size(); i++)
			{
				playerTotal = playerTotal + playerCards.get(i).getValue();
			}

			this.playSoundHit(playerTotal);
			this.playSoundStand(playerTotal);
		}
	}

}
