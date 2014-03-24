package main.materialien;

import main.fachwerte.EnumVertexColors;
import main.fachwerte.Zufallsbuchstabe;

public class GraphVertex
{
	private String _label;
	
	// Farben aus GraphVertexUI
		//	private final Color ColorStandard = Color.CYAN;
		//	private final Color ColorActivated = new Color(255, 162, 162);
		//	private final Color ColorOpenList = new Color(162, 255, 177);
		//	private final Color ColorClosedList = Color.LIGHT_GRAY;
	// Setzt eine Farbe
	private EnumVertexColors _color = EnumVertexColors.ColorStandard;
	
	
	public GraphVertex()
	{
		this( 
				Character.toString(Zufallsbuchstabe.gibGrossBuchstaben())
				+ Character.toString(Zufallsbuchstabe.gibGrossBuchstaben())
				+ Character.toString(Zufallsbuchstabe.gibGrossBuchstaben())
				);
	}
	
	public GraphVertex(String label)
	{
		_label = label;
	}
	
	@Override
	public boolean equals(Object o)
	{		
		if( !(o instanceof GraphVertex) || o == null )
			return false;
		
		GraphVertex other = (GraphVertex)o;
		
		return _label.equals(other.getLabel());
	}
	
	@Override
	public int hashCode()
	{
		return _label.hashCode();
//		return 1;
	}
	
	/**
	 * @return String Label des Knotens
	 */
	public String getLabel()
   {
	   return _label;
   }
	
	public void setColor(EnumVertexColors color)
	{
		_color = color;
	}
	
	public EnumVertexColors getColor()
	{
		return _color;
	}

	/**
	 * @param label String: Neues Label des Knotens
	 */
	public void setLabel(String label)
   {
	   _label = label;
   }
}
