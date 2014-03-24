package main.fachwerte;

import java.awt.Color;

public enum EnumVertexColors
{
	ColorStandard (Color.CYAN),
	ColorActivated (new Color(255, 162, 162)),
	ColorOpenList (new Color(162, 255, 177)),
	ColorClosedList (Color.LIGHT_GRAY),
	ColorGoalFound (new Color(255,249,90));
	
	private final Color _color;
	
	EnumVertexColors(Color newColor)
	{
		_color = newColor;
	}
	
	public Color getColor()
	{
		return _color;
	}
}
