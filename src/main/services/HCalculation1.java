package main.services;

import java.util.concurrent.CopyOnWriteArrayList;

import main.materialien.Graph;
import main.materialien.GraphVertex;

public class HCalculation1
{
	private Graph _graph;
	private GraphVertex _start;
	private CopyOnWriteArrayList<GraphVertex> _adjacencyList;
	
	
	public HCalculation1(Graph graph, GraphVertex start)
	{
		_graph = graph;
		_start = start;
		_adjacencyList = new CopyOnWriteArrayList<GraphVertex>();
		
		calculate();
	}
	
	private void calculate()
	{
		
	}
}
