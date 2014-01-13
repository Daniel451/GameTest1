package main.materialien;

public class GraphVertice
{
	private String _label = "";
	
	public GraphVertice()
	{
	}
	
	public GraphVertice(String label)
	{
		this();
		setLabel(label);
	}

	/**
	 * @return String Label des Knotens
	 */
	public String getLabel()
   {
	   return _label;
   }

	/**
	 * @param label String: Neues Label des Knotens
	 */
	public void setLabel(String label)
   {
	   _label = label;
   }
}
