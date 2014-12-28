package main.materialien;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphDirected implements Graph
{
   private ArrayList<GraphVertex> _vertexes;
   private ArrayList<GraphLine> _edges;
   private HashMap<String, ArrayList<GraphLine>> _adjacencyListOutgoing;
   private HashMap<String, ArrayList<GraphLine>> _adjacencyListIncoming;

   public GraphDirected(ArrayList<GraphVertex> vertexes,
         ArrayList<GraphLine> edges)
   {
      _vertexes = vertexes;
      _edges = edges;
      _adjacencyListOutgoing = new HashMap<String, ArrayList<GraphLine>>();
      _adjacencyListIncoming = new HashMap<String, ArrayList<GraphLine>>();

      createAdjacencyListOutgoing();
      createAdjacencyListIncoming();
   }

   private void createAdjacencyListOutgoing()
   {
      // Alle Kanten durchlaufen und ausgehende Adjazenzlisten generieren
      for ( GraphLine line : _edges )
      {
         GraphVertex currentVertex = line.getVertex1();

         // Wenn die Adjazenzliste den ersten Knoten der aktuellen Kante bereits
         // enthält
         if( _adjacencyListOutgoing.containsKey(currentVertex.getLabel()) )
         {
            ArrayList<GraphLine> containerList =
                  _adjacencyListOutgoing.get(currentVertex.getLabel());

            // Der Adjazenzliste die aktuelle Kante hinzufügen, sofern sie noch
            // nicht enthalten ist
            if( !containerList.contains(line) )
            {
               containerList.add(line);

               // Alte Adjazenzliste updaten (neue Liste in die Map einfügen)
               _adjacencyListOutgoing.put(currentVertex.getLabel(),
                     containerList);
            }
         }
         // Wenn es zu diesem Knoten noch keinen Eintrag gibt, dann eine neue
         // Adjazenzliste erstellen
         // und diese in die Map einfügen
         else
         {
            ArrayList<GraphLine> containerList = new ArrayList<GraphLine>();

            containerList.add(line);

            _adjacencyListOutgoing.put(currentVertex.getLabel(), containerList);
         }
      }

      // Falls irgendwelche Knoten keine ausgehenden Kanten besitzen, leere
      // Adjazenzlisten hinzufügen
      for ( GraphVertex vertex : _vertexes )
      {
         // Wenn ein Knoten noch nicht enthalten ist
         if( !_adjacencyListOutgoing.containsKey(vertex.getLabel()) )
         {
            _adjacencyListOutgoing.put(vertex.getLabel(),
                  new ArrayList<GraphLine>());
         }
      }
   }

   private void createAdjacencyListIncoming()
   {
      // Alle Kanten durchlaufen und eingehende Adjazenzlisten generieren
      for ( GraphLine line : _edges )
      {
         GraphVertex currentVertex = line.getVertex2();

         // Wenn die Adjazenzliste den ersten Knoten der aktuellen Kante bereits
         // enthält
         if( _adjacencyListIncoming.containsKey(currentVertex.getLabel()) )
         {
            ArrayList<GraphLine> containerList =
                  _adjacencyListIncoming.get(currentVertex.getLabel());

            // Der Adjazenzliste die aktuelle Kante hinzufügen, sofern sie noch
            // nicht enthalten ist
            if( !containerList.contains(line) )
            {
               containerList.add(line);

               // Alte Adjazenzliste updaten (neue Liste in die Map einfügen)
               _adjacencyListIncoming.put(currentVertex.getLabel(),
                     containerList);
            }
         }
         // Wenn es zu diesem Knoten noch keinen Eintrag gibt, dann eine neue
         // Adjazenzliste erstellen
         // und diese in die Map einfügen
         else
         {
            ArrayList<GraphLine> containerList = new ArrayList<GraphLine>();

            containerList.add(line);

            _adjacencyListIncoming.put(currentVertex.getLabel(), containerList);
         }
      }

      // Falls irgendwelche Knoten keine ausgehenden Kanten besitzen, leere
      // Adjazenzlisten hinzufügen
      for ( GraphVertex vertex : _vertexes )
      {
         // Wenn ein Knoten noch nicht enthalten ist
         if( !_adjacencyListIncoming.containsKey(vertex.getLabel()) )
         {
            _adjacencyListIncoming.put(vertex.getLabel(),
                  new ArrayList<GraphLine>());
         }
      }
   }

   public ArrayList<GraphVertex> getAllVertexes()
   {
      return _vertexes;
   }

   public ArrayList<GraphLine> getAllEdges()
   {
      return _edges;
   }

   /**
    * Gibt zurück, ob ein Knoten in dem Graph enthalten ist oder nicht
    * 
    * @return boolean
    */
   @Override
   public boolean containsVertex(GraphVertex vertex)
   {
      return containsVertex(vertex.getLabel());
   }

   /**
    * Gibt zurück, ob ein Knoten in dem Graph enthalten ist oder nicht
    * 
    * @return boolean
    */
   @Override
   public boolean containsVertex(String vertex)
   {
      return _adjacencyListOutgoing.containsKey(vertex);
   }

   /**
    * Gibt anhand eines Strings (Label des Knotens) den entsprechenden Knoten
    * zurück, sofern er enthalten ist. Ansonsten wird eine Nullreferenz
    * zurückgegeben.
    * 
    * @return GraphVertex Ein valides Knotenobjekt, dass dem übergebenen String
    *         entspricht
    */
   @Override
   public GraphVertex getVertex(String vertex)
   {
      GraphVertex vertexObj = new GraphVertex(vertex);

      if( _vertexes.contains(vertexObj) )
      {
         for ( GraphVertex searchVertex : _vertexes )
         {
            if( searchVertex.getLabel().equals(vertex) )
            {
               return searchVertex;
            }
         }
         return null;
      }
      else
      {
         return null;
      }
   }

   @Override
   public int getVertexDegree(GraphVertex vertex)
   {
      int deg = 0;

      for ( GraphLine edge : _edges )
      {
         if( edge.getVertex1().equals(vertex)
               || edge.getVertex2().equals(vertex) )
         {
            deg++;
         }
      }

      return deg;
   }

   @Override
   public ArrayList<GraphVertex> getVertexNeighbors(GraphVertex vertex)
   {
      ArrayList<GraphLine> list1 =
            _adjacencyListOutgoing.get(vertex.getLabel());
      ArrayList<GraphLine> list2 =
            _adjacencyListIncoming.get(vertex.getLabel());

      ArrayList<GraphVertex> retList = new ArrayList<GraphVertex>();

      // Ausgehende Kanten checken und Vertex2 der Nachbarliste hinzufügen,
      // falls noch nicht vorhanden
      for ( GraphLine edge : list1 )
      {
         if( !retList.contains(edge.getVertex2()) )
            retList.add(edge.getVertex2());
      }

      // Eingehende Kanten checken und Vertex1 der Nachbarliste hinzufügen,
      // falls noch nicht vorhanden
      for ( GraphLine edge : list2 )
      {
         if( !retList.contains(edge.getVertex1()) )
            retList.add(edge.getVertex1());
      }

      return retList;
   }

   @Override
   public ArrayList<GraphLine> getOutgoingEdgesOfVertex(GraphVertex vertex)
   {
      return _adjacencyListOutgoing.get(vertex.getLabel());
   }

   @Override
   public ArrayList<GraphLine> getIncomingEdgesOfVertex(GraphVertex vertex)
   {
      return _adjacencyListIncoming.get(vertex.getLabel());
   }

   public void printAllData()
   {
      System.out.println("-------------------");

      System.out.println("-------------------");
      System.out.println("Vertexes" + " ( " + _vertexes.size()
            + " Vertex(es) in Graph )");
      System.out.println("-------------------");

      for ( int i = 0; i < _vertexes.size(); i++ )
      {
         System.out.println("_vertexes.get(" + i + "): " + "'"
               + _vertexes.get(i).getLabel() + "'");
      }

      System.out.println("-------------------");
      System.out.println("Edges" + " ( " + _edges.size()
            + " Edge(s) in Graph )");
      System.out.println("-------------------");

      for ( int i = 0; i < _edges.size(); i++ )
      {
         System.out.println("_edges.get(" + i + "): " + "'"
               + _edges.get(i).getLabel() + "'");
      }

      System.out.println("-------------------");
      System.out.println("Printing HashMap _adjacencyListOutgoing");
      System.out.println("[key] -> 'value'");
      System.out.println("-------------------");

      for ( String vertexLabel : _adjacencyListOutgoing.keySet() )
      {
         System.out.println("[" + vertexLabel + "] -> '"
               + _adjacencyListOutgoing.get(vertexLabel).toString() + "'");
      }

      System.out.println("-------------------");
      System.out.println("Printing HashMap _adjacencyListIncoming");
      System.out.println("[key] -> 'value'");
      System.out.println("-------------------");

      for ( String vertexLabel : _adjacencyListIncoming.keySet() )
      {
         System.out.println("[" + vertexLabel + "] -> '"
               + _adjacencyListIncoming.get(vertexLabel).toString() + "'");
      }
   }

}
