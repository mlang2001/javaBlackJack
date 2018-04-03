import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card 
{
	private int value;
	private String name, suit;
	private BufferedImage suitImage;
	private Font cardFont, cardFont2, textFont;

	public Card(int value, String name, String suit)
	{
		this.value = value;
		this.name = name;
		this.suit = suit;
		cardFont = new Font("Futura", Font.BOLD + Font.ITALIC, 100);
		cardFont2 = new Font("Futura", Font.BOLD + Font.ITALIC, 80);
		textFont = new Font("Futura", Font.BOLD + Font.ITALIC, 50);

		if(suit.equals("hearts"))
		{
			try {
				suitImage = ImageIO.read(new File("images/hearts.png"));
			} catch( IOException e){}
		}
		else if(suit.equals("clubs"))
		{
			try {
				suitImage = ImageIO.read(new File("images/clubs.png"));
			} catch( IOException e){}
		}
		else if(suit.equals("spades"))
		{
			try {
				suitImage = ImageIO.read(new File("images/spades.png"));
			} catch( IOException e){}
		}
		else if(suit.equals("diamonds"))
		{
			try {
				suitImage = ImageIO.read(new File("images/diamonds.png"));
			} catch( IOException e){}
		}
	}
	public void drawMe(Graphics g, int x, int y)
	{
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 100, 150);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 100, 150);
		if(suit.equals("hearts") || suit.equals("diamonds"))
		{
			g.setColor(Color.BLACK);
		}
		else if(suit.equals("clubs") || suit.equals("spades"))
		{
			g.setColor(Color.RED);
		}
		g.drawImage(suitImage, x+10, y+35, null);
		if(name.equals("10"))
		{
			g.setFont(cardFont2);
			g.drawString(name, x - 10, y+100);
		}
		else
		{
			g.setFont(cardFont);
			g.drawString(name, x + 5, y+105);
		}
		
		g.setFont(textFont);
	}
	public int getValue()
	{
		return value;
	}
	public String getName()
	{
		return name;
	}
	public String getSuit()
	{
		return suit;
	}

}