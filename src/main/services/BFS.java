package main.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import main.fachwerte.EnumVertexColors;
import main.materialien.Graph;
import main.materialien.GraphLine;
import main.materialien.GraphVertex;

public class BFS implements CustomAlgorithm
{
	private Graph _graph;
	private LinkedList<GraphVertex> _queue;
	private HashSet<GraphVertex> _vSet;
	private HashSet<GraphVertex> _closedList;
	private GraphVertex _start;
	private GraphVertex _goal;
	private boolean _goalFound;
	private GraphVertex _actualNode;
	private int _iterations;
	
	
	public BFS()
	{
	}
	
	public void setGraphAndInitialize(Graph graph, GraphVertex start, GraphVertex goal)
	{
		_graph = graph;
		
		_queue = new LinkedList<GraphVertex>();
		_vSet = new HashSet<GraphVertex>();
		_closedList = new HashSet<GraphVertex>();
				
		_start = start;
		_goal = goal;
		_goalFound = false;
		_iterations = 0;
		_queue.add(_start);
	}
	
	public void iterate()
	{
		// Aktiviert die Custom-Debugger-Ausgabe auf der Konsole 
		boolean iteration_debug = false;
		
		if(_goalFound)
			return;
		
		String ausgabe = "-------------------------------\n";
		ausgabe += "Iteration (Nr. " + _iterations + ") gestartet.\n";
		ausgabe += "-------------------------------\n";
		
		ausgabe += "Farbe Knoten vS: " + _graph.getVertex("vS").getColor() + "\n";
		
		// Anzahl der Iterationen um einen erhöhen
		_iterations++;
		
		for( GraphVertex vertex : _closedList )
		{
			vertex.setColor(EnumVertexColors.ColorClosedList);
		}
		
		// Wenn die Queue noch nicht leer ist
		if( _queue.size() > 0 )
		{			
			// Den nächsten Knoten aus der Queue nehmen
			_actualNode = _queue.remove();
			_actualNode.setColor(EnumVertexColors.ColorActivated);
			_closedList.add(_actualNode);
			
			ausgabe += ("Aktueller aktivierter Knoten: " + _actualNode.getLabel() + ", Farbe geändert auf: " + _actualNode.getColor()) + "\n";
			ausgabe += "_actualNode: " + _actualNode.toString() + " | _graph: " + _graph.getVertex(_actualNode.getLabel()).toString() + "\n";
			ausgabe += ( _actualNode == _graph.getVertex(_actualNode.getLabel()) ) + "\n";
			
			// Ist der aktuelle Knoten das Ziel?
			if( _actualNode.equals(_goal) )
			{
				_actualNode.setColor(EnumVertexColors.ColorGoalFound);
				_goalFound = true;
//				System.out.println("--- Goal gefunden --- Goal: " + _actualNode.getLabel());
//				System.out.println("Aktuelle Queue: ");
//				for(GraphVertex blubb : _queue)
//				{
//					System.out.print(blubb);
//				}
				return;
			}
			
			// Jede Kante checken, die von vertex ausgehend ist und adjazente Knoten
			// dem Set / der Queue hinzufügen, sofern noch nicht erkundet
			for( GraphLine edge : _graph.getOutgoingEdgesOfVertex(_actualNode) )
			{
				GraphVertex successor = _graph.getVertex(edge.getVertex2().getLabel());
				if( !_vSet.contains(successor) )
				{
					_vSet.add(successor);
					_queue.add(successor);
					successor.setColor(EnumVertexColors.ColorOpenList);
					ausgabe += "Knoten " + successor.getLabel() + " wurde der Queue und dem Set hinzugefügt, Farbe " 
					+" geändert auf " + successor.getColor() + "\n";
				}
			}
			
			ausgabe += "In der Queue befinden sich folgende Knoten: ";
			for( GraphVertex q : _queue )
			{
				ausgabe += "'" + q.getLabel() + "' ";
			}
			ausgabe += "\n";
			
			ausgabe += "In dem vSet befinden sich folgende Knoten: ";
			for( GraphVertex vs : _vSet )
			{
				ausgabe += "'" + vs.getLabel() + "' ";
			}
			ausgabe += "\n";
			
			// Custom-Debug-Ausgabe
			if(iteration_debug)
				System.out.println(ausgabe);
		}
	}
	
	public void reset()
	{
		
	}
}
