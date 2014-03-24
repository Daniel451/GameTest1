package main.materialien;

import java.util.LinkedList;

import main.fachwerte.Zufallsbuchstabe;

/**
 * Repräsentiert eine Kante zwischen zwei Knoten in einem Graph
 * 
 * @author Daniel
 *
 */
public class GraphLine
{
   private final GraphVertex _vertex1;
   private final GraphVertex _vertex2;
   private final LinkedList<GraphVertex> _vertices;
   private String _label;
   private int _cost;
	
	public GraphLine(GraphVertex knoten1, GraphVertex knoten2)
	{
		this(knoten1, knoten2, 1);
	}
	
	public GraphLine(GraphVertex knoten1, GraphVertex knoten2, int cost)
	{
		this(knoten1, knoten2, cost,
				Character.toString(Zufallsbuchstabe.gibGrossBuchstaben())
				+ Character.toString(Zufallsbuchstabe.gibGrossBuchstaben())
				+ Character.toString(Zufallsbuchstabe.gibGrossBuchstaben())
				);
	}
	
	public GraphLine(GraphVertex knoten1, GraphVertex knoten2, int cost, String label)
	{
		_vertex1 = knoten1;
		_vertex2 = knoten2;
		
		LinkedList<GraphVertex> liste = new LinkedList<GraphVertex>();
		liste.add(_vertex1);
		liste.add(_vertex2);
		
		_vertices = liste;
		
		_cost = cost;
		
		_label = label;
	}
	
	public int getCost()
	{
		return _cost;
	}
	
	/**
	 * @return String Beschriftung der Kante
	 */
	public String getLabel()
	{
		return _label;
	}
	
	/**
	 * @param str String: Neue Beschriftung der Kante
	 */
	public void setLabel(String str)
	{
		_label = str;
	}
	
	/**
	 * @return GraphVertice Gibt den ersten Knoten zurück
	 */
	public GraphVertex getVertex1()
	{
		return _vertex1;
	}
	
	/**
	 * @return GraphVertice Gibt den zweiten Knoten zurück
	 */
	public GraphVertex getVertex2()
	{
		return _vertex2;
	}
	
	/**
	 * @return LinkedList<GraphVertice> Gibt eine LinkedList der beiden Knoten zurück
	 */
	public LinkedList<GraphVertex> getVertexes()
	{
		return _vertices;
	}
}