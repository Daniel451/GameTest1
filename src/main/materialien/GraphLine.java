package main.materialien;

import java.util.LinkedList;

/**
 * Repräsentiert eine Kante zwischen zwei Knoten in einem Graph
 * 
 * @author Daniel
 *
 */
public class GraphLine
{
   private GraphVertice _vertice1;
   private GraphVertice _vertice2;
   private LinkedList<GraphVertice> _vertices;
   private String _label;
	
	public GraphLine (GraphVertice knoten1, GraphVertice knoten2)
	{
		_vertice1 = knoten1;
		_vertice2 = knoten2;
		_vertices.add(knoten1);
		_vertices.add(knoten2);
		_label = "";
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
	public GraphVertice getVertice1()
	{
		return _vertice1;
	}
	
	/**
	 * @return GraphVertice Gibt den zweiten Knoten zurück
	 */
	public GraphVertice getVertice2()
	{
		return _vertice2;
	}
	
	/**
	 * @return LinkedList<GraphVertice> Gibt eine LinkedList der beiden Knoten zurück
	 */
	public LinkedList<GraphVertice> getVertices()
	{
		return _vertices;
	}
}