package main.materialien;

import java.util.ArrayList;

public class GraphUndirected implements Graph
{

	@Override
   public boolean containsVertex(GraphVertex vertex)
   {
	   // TODO Auto-generated method stub
	   return false;
   }
	
	@Override
   public boolean containsVertex(String vertex)
   {
	   // TODO Auto-generated method stub
	   return false;
   }

	@Override
   public GraphVertex getVertex(String vertex)
   {
	   // TODO Auto-generated method stub
	   return null;
   }

	@Override
   public int getVertexDegree(GraphVertex vertex)
   {
	   // TODO Auto-generated method stub
	   return 0;
   }

	@Override
   public ArrayList<GraphVertex> getVertexNeighbors(GraphVertex vertex)
   {
	   // TODO Auto-generated method stub
	   return null;
   }

	@Override
   public ArrayList<GraphLine> getOutgoingEdgesOfVertex(GraphVertex vertex)
   {
	   // TODO Auto-generated method stub
	   return null;
   }

	@Override
   public ArrayList<GraphLine> getIncomingEdgesOfVertex(GraphVertex vertex)
   {
	   // TODO Auto-generated method stub
	   return null;
   }

	@Override
   public ArrayList<GraphVertex> getAllVertexes()
   {
	   // TODO Auto-generated method stub
	   return null;
   }

	@Override
   public ArrayList<GraphLine> getAllEdges()
   {
	   // TODO Auto-generated method stub
	   return null;
   }

}
