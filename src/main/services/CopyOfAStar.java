package main.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import main.fachwerte.EnumVertexColors;
import main.materialien.AdjacencyObject;
import main.materialien.Graph;
import main.materialien.GraphLine;
import main.materialien.GraphVertex;

public class CopyOfAStar implements CustomAlgorithm
{
	private Graph _graph;
	private LinkedList<GraphVertex> _queue;
	private LinkedList<GraphLine> _openListEdges;
	private LinkedList<GraphVertex> _openListVertexes;
	private HashSet<GraphVertex> _closedList;
	private GraphVertex _start;
	private GraphVertex _goal;
	private boolean _goalFound;
	private GraphVertex _actualNode;
	private int _iterations;
	private GraphVertex _bestNode;
	private GraphLine _bestLine;
	private HashMap<String, AdjacencyObject> _adjacencyMatrix;
	
	
	public CopyOfAStar()
	{
	}
	
	public void setGraphAndInitialize(Graph graph, GraphVertex start, GraphVertex goal)
	{
		_graph = graph;
		
		_queue = new LinkedList<GraphVertex>();
		_openListEdges = new LinkedList<GraphLine>();
		_openListVertexes = new LinkedList<GraphVertex>();
		_closedList = new HashSet<GraphVertex>();
		
		_bestLine = null;
		_bestNode = null;
		_start = start;
		_goal = goal;
		_goalFound = false;
		_iterations = 0;
		_queue.add(_start);
		
		
		// Adjazenzmatrix --- Startknoten setzen, rest auf unendlich initialisieren
			// AdjacencyObject(GraphVertex vertex, GraphVertex predecessor, gCost, hCost)
			_adjacencyMatrix = new HashMap<String, AdjacencyObject>();
			AdjacencyObject adjObj = new AdjacencyObject(_start, null, 0, 0);
			_adjacencyMatrix.put(_start.getLabel(), adjObj);
			
			// Restliche Knoten initialisieren (auf unendlich (-1) als Entfernung und keinen Vorgänger setzen)
			for( GraphVertex vertex : _graph.getAllVertexes() )
			{
				if( !vertex.getLabel().equals("vS") )
				{
					adjObj = new AdjacencyObject(vertex, null, -1, 0);
					_adjacencyMatrix.put(vertex.getLabel(), adjObj);
				}
			}
			
			// H-Value für jeden Knoten berechnen
				_adjacencyMatrix.get(goal.getLabel()).set_hCost(0); // Goal-Node hat H-Value 0
				CopyOnWriteArrayList<GraphVertex> hItNodeQueue = new CopyOnWriteArrayList<GraphVertex>();
				hItNodeQueue.add(_goal);
				int hLevel = 0;
				// Solange noch Knoten in der Queue sind
				for(int i = 0; i <= _graph.getAllVertexes().size(); i++)
				{
					HashSet<GraphVertex> merkliste = new HashSet<GraphVertex>();
//					
//					// Für jeden Knoten aus der Queue
					for( GraphVertex itNode : hItNodeQueue )
					{
//						// H-Cost für den Knoten auf das aktuelle hLevel setzen
						_adjacencyMatrix.get(itNode.getLabel()).set_hCost(hLevel);
//						
//						// Alle Vorgänger des aktuellen Knoten merken
						for( GraphLine incEdge : _graph.getIncomingEdgesOfVertex(itNode) )
						{
							merkliste.add(_graph.getVertex(incEdge.getVertex1().getLabel()));
						}
//						
//						// bearbeiteten Knoten aus der Queue nehmen
						hItNodeQueue.remove(itNode);
					}
//					
//					// Merkliste der Queue hinzufügen
					for( GraphVertex merkVertex : merkliste )
					{
						hItNodeQueue.add(merkVertex);
					}
//					
					// H-Level erhöhen
					hLevel++;
				}
				
				// Alle unerreichbaren Knoten haben H-Cost 0, dass muss erhöht werden
				for( GraphVertex blaVertex : _graph.getAllVertexes() )
				{
					if( !blaVertex.getLabel().equals("vG") && _adjacencyMatrix.get(blaVertex.getLabel()).get_hCost() == 0 )
					{
						_adjacencyMatrix.get(blaVertex.getLabel()).set_hCost(hLevel*100);
					}
				}
				
				for( GraphVertex blav : _graph.getAllVertexes() )
				{
					System.out.println("H-Cost von " + blav.getLabel() + ": " + _adjacencyMatrix.get(blav.getLabel()).get_hCost() );
				}
	}
	
	public void iterate()
	{
		boolean debugMode = true;
		
		if(_goalFound)
			return;
		
		// Debug - Start
				String ausgabe = "------------------------\n";
				ausgabe += "Starte Iteration ( " + _iterations + " )\n";
				ausgabe += "------------------------\n";
				
				ausgabe += "Queue (anfangs): ";
				for( GraphVertex qV : _queue )
				{
					ausgabe += qV.getLabel() + " ";
				}
				ausgabe += "\n------------------------\n";
		// Debug - Ende
				
		// Farbe für closedList setzen
		for( GraphVertex vertex : _closedList )
		{
			vertex.setColor(EnumVertexColors.ColorClosedList);
		}
		
		
		if( _queue.size() > 0 )
		{
			// Den nächsten Knoten aus der Queue nehmen
			_actualNode = _queue.remove();
			_actualNode.setColor(EnumVertexColors.ColorActivated);
			// Debug
					ausgabe += "_actualNode: " + _actualNode.getLabel() + "\n";
					ausgabe += "Queue jetzt: ";
					for( GraphVertex qV : _queue )
					{
						ausgabe += qV.getLabel() + " ";
					}
					
			
			// Zielknoten erreicht?
			if( _actualNode.equals(_goal) )
			{
				_actualNode.setColor(EnumVertexColors.ColorGoalFound);
				_goalFound = true;
				return;
			}
			
			// OpenListEdges bauen
			for( GraphLine line : _graph.getOutgoingEdgesOfVertex(_actualNode) )
			{
				// Adjazenzmatrix korrigieren
					// Wenn der Knoten noch nie erkundet wurde
						if( 
								_adjacencyMatrix.get(line.getVertex2().getLabel()).get_gCost() == -1
								&& _adjacencyMatrix.get(line.getVertex2().getLabel()).getPredecessor() == null
							)
						{
							GraphVertex adjVertex = _adjacencyMatrix.get(line.getVertex2().getLabel()).getVertex();
							GraphVertex adjPredecessor = _graph.getVertex(line.getVertex1().getLabel());
							int adjGCost = _adjacencyMatrix.get(adjPredecessor.getLabel()).get_gCost() + line.getCost();
							int adjHCost = getHValue(adjVertex);
							ausgabe += "\n G-Cost: " + adjPredecessor.getLabel() + " + " + adjVertex.getLabel() + ": "
									+ _adjacencyMatrix.get(adjPredecessor.getLabel()).get_gCost() + " + " + line.getCost()
									+ " = " + ( _adjacencyMatrix.get(adjPredecessor.getLabel()).get_gCost() + line.getCost() )
									+ " für Knoten " + adjVertex.getLabel()
									+ " | H-Cost " + adjVertex.getLabel() + " :" + _adjacencyMatrix.get(adjVertex.getLabel()).get_hCost() + "";
							
							AdjacencyObject adjObj = new AdjacencyObject(adjVertex, adjPredecessor, adjGCost, adjHCost);
							_adjacencyMatrix.put(adjVertex.getLabel(), adjObj);
						}
					
					// Wenn der Knoten geupdatet werden muss, da es einen besseren Pfad gibt
						GraphVertex upNeuerKnoten = _adjacencyMatrix.get(line.getVertex2().getLabel()).getVertex();
						GraphVertex NeuerPredecessor = _adjacencyMatrix.get(line.getVertex1().getLabel()).getVertex();
						
						int gCostOld = _adjacencyMatrix.get(upNeuerKnoten.getLabel()).get_gCost();
						int gCostNew = _adjacencyMatrix.get(NeuerPredecessor.getLabel()).get_gCost() + line.getCost();
						
						if( gCostNew < gCostOld )
						{
							int adjHCost = getHValue(upNeuerKnoten);
							
							AdjacencyObject adjUpObj =  new AdjacencyObject(upNeuerKnoten, NeuerPredecessor, gCostNew, adjHCost);
							_adjacencyMatrix.put(upNeuerKnoten.getLabel(), adjUpObj);
						}
					
				
				if( !_openListEdges.contains(line)
						&& !_openListVertexes.contains(_graph.getVertex(line.getVertex2().getLabel()))
						&& !_closedList.contains(_graph.getVertex(line.getVertex2().getLabel()))
						)
				{
					_openListEdges.add(line);
					_openListVertexes.add(_graph.getVertex(line.getVertex2().getLabel()));
					_graph.getVertex(line.getVertex2().getLabel()).setColor(EnumVertexColors.ColorOpenList);
				}
			}
					
			// OpenList durchlaufen und Startkante suchen
			for( GraphLine line : _openListEdges )
			{				
				if( !_closedList.contains( _graph.getVertex(line.getVertex2().getLabel()) ) );
				{
					_bestLine = line;
					_bestNode = _graph.getVertex(_bestLine.getVertex2().getLabel());
					// Debug
						ausgabe += "\nStartkante: " + _bestLine.getLabel();
					break;
				}
			}
			
			
			// Debug
				ausgabe += "\n _openListEdges (vor dem Suchen nach optimaler Kante): ";
				for( GraphLine line : _openListEdges )
				{
					ausgabe += line.getLabel() + " ";
				}
				ausgabe += "\n _openListVertexes (vor dem Suchen nach optimalem Knoten): ";
				for( GraphVertex vertex : _openListVertexes )
				{
					ausgabe += _graph.getVertex(vertex.getLabel()).getLabel() + "(" + _adjacencyMatrix.get(vertex.getLabel()).get_gCost() + ") ";
				}
				
				
			// OpenList durchlaufen und nach optimaler Kante/Knoten suchen
			for( GraphLine line : _openListEdges )
			{				
				// Wenn die aktuelle Kante in der Iteration geringere Kosten hat als die aktuelle _bestLine
				// und außerdem der damit verbundene Knoten noch nicht auf der closedList steht
				if( getFValue(line) < getFValue(_bestLine) && !_closedList.contains(_graph.getVertex(line.getVertex2().getLabel())) )
				{
					_bestLine = line;
					// Debug
						ausgabe += "\n Beste Kante: " + _bestLine.getLabel();
					_bestNode = _graph.getVertex(_bestLine.getVertex2().getLabel());
					// Debug
						ausgabe += "\n Bester Knoten: " + _bestNode.getLabel();
					break;
				}
			}
			
			// Debug
				ausgabe += "\n_closedList: ";
				for( GraphVertex vertex : _closedList )
				{
					ausgabe += vertex.getLabel() + " ";
				}
			
			
			// Einträge aus den OpenLists löschen
			_openListEdges.remove(_bestLine);
			_openListVertexes.remove(_bestNode);
			
			// ClosedList Einträge setzen
			_closedList.add(_actualNode);
			
			_queue.add(_bestNode);		
			_iterations++;
			System.out.println(ausgabe);
		}
	}
	
	// accumulated movement costs (G+H)
	private int getFValue(GraphLine edge)
	{
		GraphVertex vertex1 = _graph.getVertex(edge.getVertex1().getLabel());
		GraphVertex vertex2 = _graph.getVertex(edge.getVertex2().getLabel());
		
		return getGValue(vertex2) + getHValue(vertex2);
	}
	
	// Movement Cost function
	private int getGValue(GraphVertex vertex)
	{
		return _adjacencyMatrix.get(vertex.getLabel()).get_gCost();
	}
	
	// Heuristic function
	private int getHValue(GraphVertex vertex)
	{
		return _adjacencyMatrix.get(vertex.getLabel()).get_hCost();
	}
}
