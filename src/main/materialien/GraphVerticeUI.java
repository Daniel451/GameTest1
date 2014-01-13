package main.materialien;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import main.fachwerte.Punkt;

@SuppressWarnings("serial")
public class GraphVerticeUI extends JComponent
{
	private int _posX;
	private int _posY;
	private int _width;
	private int _height;
	private Punkt _posCenter;
	
	public GraphVerticeUI(int posX, int posY)
	{
		_posX = posX;
		_posY = posY;
		_height = 30;
		_width = 30;
		_posCenter = new Punkt(_posX + (int)(_width / 2), _posY + (int)(_height / 2));
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		g.setColor(Color.CYAN);
		g.fillOval(_posX - (int)(_width / 2), _posY - (int)(_height / 2), _width, _height);
		g.setColor(Color.BLACK);
		g.drawOval(_posX - (int)(_width / 2), _posY - (int)(_height / 2), _width, _height);
	}
	
	/**
	 * 
	 * @return Punkt Gibt den Punkt des Zentrums zurück
	 */
	public Punkt gibZentrum()
	{
		return _posCenter;
	}
}
