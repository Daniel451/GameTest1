package main.materialien;

public class AdjacencyObject
{
	private GraphVertex _predecessor; // Vorgängerknoten
	private final GraphVertex _vertex; // Knoten, für denen Vorgänger und Kosten gespeichert werden
	private int _gCost; // kumulierte G-Pfadkosten
	private int _hCost; // geschätzte heuristische Kosten zum Ziel
	
	public AdjacencyObject( GraphVertex vertex, GraphVertex predecessor, int gCost, int hCost )
	{
		_vertex = vertex;
		setPredecessor(predecessor);
		set_gCost(gCost);
		set_hCost(hCost);
	}

	public GraphVertex getPredecessor()
   {
	   return _predecessor;
   }

	public void setPredecessor(GraphVertex predecessor)
   {
	   _predecessor = predecessor;
   }

	public int get_gCost()
   {
	   return _gCost;
   }

	public void set_gCost(int gCost)
   {
	   _gCost = gCost;
   }

	public int get_hCost()
   {
	   return _hCost;
   }

	public void set_hCost(int hCost)
   {
	   _hCost = hCost;
   }

	public GraphVertex getVertex()
   {
	   return _vertex;
   }
}
