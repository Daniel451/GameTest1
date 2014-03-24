package main.werkzeuge;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class SpielfeldFlaeche extends JComponent
{
	private final int _width;
	private final int _height;
	
	public SpielfeldFlaeche(int width, int height)
	{
		_width = width;
		_height = height;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, _width, _height);
	}
}
