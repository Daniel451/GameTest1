package main.materialien;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import main.fachwerte.EnumVertexColors;
import main.fachwerte.Punkt;
import main.fachwerte.Zufallsbuchstabe;

@SuppressWarnings("serial")
public class GraphVertexUI extends JComponent
{
	private int _posX;
	private int _posY;
	private int _width;
	private int _height;
	private String _label;
	private Punkt _posCenter;
	private EnumVertexColors _color = EnumVertexColors.ColorStandard;
	
	public GraphVertexUI(int posX, int posY)
	{
		this(posX, posY, "v" + Zufallsbuchstabe.gibGrossBuchstaben() +(int)(Math.random() * 10));
	}
	
	public GraphVertexUI(int posX, int posY, String label)
	{
		_posX = posX;
		_posY = posY;
		_height = 30;
		_width = 30;
//		_posCenter = new Punkt(_posX + (int)(_width / 2), _posY + (int)(_height / 2));
		_posCenter = new Punkt( posX, posY );
		_label = label;
		setName(label);
		this.setLocation(posX, posY);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		g.setColor(_color.getColor());
		g.fillOval(_posX - (int)(_width / 2), _posY - (int)(_height / 2), _width, _height);
		g.setColor(Color.BLACK);
		g.drawOval(_posX - (int)(_width / 2), _posY - (int)(_height / 2), _width, _height);
		g.drawString(_label, _posX - 9, _posY + 4);
	}
	
	public void changeColor(EnumVertexColors color)
	{
		_color = color;
		this.repaint();
	}
	
	/**
	 * 
	 * @return Punkt Gibt den Punkt des Zentrums zurück
	 */
	public Punkt gibZentrum()
	{
		return _posCenter;
	}
	
	public String gibLabel()
	{
		return _label;
	}
}
