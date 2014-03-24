package main.materialien;

import java.util.ArrayList;

public interface Graph
{
	/**
	 * Gibt alle im Graph enthaltenen Knoten zurück
	 * @return
	 */
	public ArrayList<GraphVertex> getAllVertexes();
	
	/**
	 * Gibt alle im Graph enthaltenen Kanten zurück
	 * @return
	 */
	public ArrayList<GraphLine> getAllEdges();
	
	/**
	 * Gibt anhand der Referenz zu einem Knoten zurück, ob dieser Knoten in dem Graph enthalten ist
	 * 
	 * @param GraphVertex Eine Knotenreferenz
	 * @return boolean
	 */
	public boolean containsVertex(GraphVertex vertex);
	
	/**
	 * Gibt anhand der Referenz zu einem Knoten zurück, ob dieser Knoten in dem Graph enthalten ist
	 * 
	 * @param vertex Ein String, der dem Label eines Knotens entspricht
	 * @return boolean
	 */
	boolean containsVertex(String vertex);
	
	/**
	 * Gibt die Referenz zu einem Knoten zurück, der den übergebenen String als Label hat
	 * 
	 * @param vertex String: Bezeichnung eines Knotens
	 * @return GraphVertex Eine Knotenreferenz
	 */
	public GraphVertex getVertex(String vertex);
	
	/**
	 * Gibt den Grad des Knotens zurück
	 * 
	 * @param vertex
	 * @return int Grad des Knotens
	 */
	public int getVertexDegree(GraphVertex vertex);
	
	/**
	 * Sucht alle Nachbarn (per Kante verbundenen Knoten) von dem übergebenen Knoten
	 * 
	 * @param vertex Knoten, dessen Nachbarn zurückgegeben werden sollen
	 * @return ArrayList<GraphVertex> Eine Liste der Nachbarknoten
	 */
	public ArrayList<GraphVertex> getVertexNeighbors(GraphVertex vertex);
	
	/**
	 * Gibt eine Liste aller ausgehenden Kanten zurück
	 * 
	 * @param vertex Knoten, dessen ausgehende Kanten zurückgegeben werden sollen
	 * @return ArrayList<GraphLine> ausgehende Kanten
	 */
	public ArrayList<GraphLine> getOutgoingEdgesOfVertex(GraphVertex vertex);
	
	/**
	 * Gibt eine Liste aller eingehenden Kanten zurück
	 * 
	 * @param vertex Knoten, dessen eingehende Kanten zurückgegeben werden sollen
	 * @return ArrayList<GraphLine> eingehende Kanten
	 */
	public ArrayList<GraphLine> getIncomingEdgesOfVertex(GraphVertex vertex);
}
