package main.fachwerte;

import java.awt.Point;

@SuppressWarnings("serial")
public class Punkt extends Point
{
	public Punkt(int x, int y)
	{
		super(x, y);
	}
	
	public int getIntX()
	{
		return super.x;
	}
	
	public int getIntY()
	{
		return super.y;
	}
}
