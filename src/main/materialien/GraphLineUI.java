package main.materialien;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import main.fachwerte.Punkt;

@SuppressWarnings("serial")
public class GraphLineUI extends JComponent
{
	private Punkt _start;
	private Punkt _end;
	
	/**
	 * Konstruktor einer GraphLine für Integer-Angaben von Start- und Endpunkt
	 * 
	 * @param startX int X-Startkoordinate
	 * @param startY int Y-Startkoordinate
	 * @param endX int X-Endkoordinate
	 * @param endY int Y-Endkoordinate
	 */
	public GraphLineUI(int startX, int startY, int endX, int endY)
	{
		this(new Punkt(startX, startY), new Punkt(endX, endY));
	}
	
	/**
	 * Konstruktor einer GraphLine für 2 Points
	 * 
	 * @param start Point -> Startpunkt
	 * @param end Point -> Endpunkt
	 */
	public GraphLineUI(Punkt start, Punkt end)
	{
		_start = start;
		_end = end;
	}
	
	protected void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawLine(_start.getIntX(), _start.getIntY(), _end.getIntX(), _end.getIntY());
	}
}
