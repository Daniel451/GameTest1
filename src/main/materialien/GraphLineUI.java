package main.materialien;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

import main.fachwerte.Punkt;
import main.fachwerte.Zufallsbuchstabe;

@SuppressWarnings("serial")
public class GraphLineUI extends JComponent
{
	private Punkt _start;
	private Punkt _end;
	private String _label;
	private int _cost;
	private Punkt _mittelpunkt;
	private final int ARR_SIZE = 10;
	
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
		this(start, end, "e" + Zufallsbuchstabe.gibGrossBuchstaben() + (int)(Math.random() * 10));
	}
	
	public GraphLineUI(int startX, int startY, int endX, int endY, String label)
	{
		this(new Punkt(startX, startY), new Punkt(endX, endY), label);
	}
	
	public GraphLineUI(Punkt start, Punkt end, String label)
	{
		this(start, end, label, 1);
	}
	
	public GraphLineUI(int startX, int startY, int endX, int endY, String label, int cost)
	{
		this(new Punkt(startX, startY), new Punkt(endX, endY), label, cost);
	}
	
	public GraphLineUI(Punkt start, Punkt end, String label, int cost)
	{
		_start = start;
		_end = end;
		_label = label;
		_cost = cost;
		this.setName(label);
		this.setLocation(start);
		
		// Mittelpunkt für Kostendarstellung berechnen
		int xm = (_start.getIntX()+_end.getIntX())/2;
		int ym = (_start.getIntY()+_end.getIntY())/2;
		_mittelpunkt = new Punkt(xm,ym);
		
	}
	
	public int gibKosten()
	{
		return _cost;
	}
	
	public String gibLabel()
	{
		return _label;
	}

	void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
      Graphics2D g = (Graphics2D) g1.create();

      double dx = x2 - x1, dy = y2 - y1;
      double angle = Math.atan2(dy, dx);
      int len = (int) Math.sqrt(dx*dx + dy*dy);
      AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
      at.concatenate(AffineTransform.getRotateInstance(angle));
      g.transform(at);

      // Draw horizontal arrow starting in (0, 0)
      g.drawLine(0, 0, len, 0);
      g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                    new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
  }
	
	protected void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
//		g.drawLine(_start.getIntX(), _start.getIntY(), _end.getIntX(), _end.getIntY());
		g.drawString( Integer.toString(_cost), _mittelpunkt.getIntX(), _mittelpunkt.getIntY());
		
		int xa = _start.getIntX();
		int ya = _start.getIntY();
		int xb = _end.getIntX() - (int)(( _end.getIntX() - xa ) * 0.09);
		int yb = _end.getIntY() - (int)(( _end.getIntY() -ya ) * 0.09);
		
		drawArrow(g, xa, ya, xb, yb);
	}
}
