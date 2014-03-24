package main.materialien;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.services.DatenEinleser;

import org.junit.Before;
import org.junit.Test;

public class GraphDirectedTest
{

	private GraphDirected _graph;
	private ArrayList<GraphVertex> _vertexes;
	private ArrayList<GraphLine> _edges;
	
	@Before
   public void setUp()
	{
		DatenEinleser einleser = new DatenEinleser("templates/sample1.txt");
		
		_graph = new GraphDirected(einleser.getVertices(), einleser.getEdges());
		_vertexes = einleser.getVertices();
		_edges = einleser.getEdges();
   }
	
	@Test
	public void predecessorsTesten()
	{
		DatenEinleser einleser = new DatenEinleser("templates/sample2.txt");
		
		GraphDirected graph = new GraphDirected(einleser.getVertices(), einleser.getEdges());
		ArrayList<GraphVertex> vertexes = einleser.getVertices();
		ArrayList<GraphLine> dges = einleser.getEdges();
		
		ArrayList<GraphLine> incEdges = graph.getIncomingEdgesOfVertex(graph.getVertex("v07"));
		
		for( GraphLine edge : incEdges )
		{
			assertTrue( graph.getVertex("v05") == graph.getVertex(edge.getVertex1().getLabel())
					|| graph.getVertex("v08") == graph.getVertex(edge.getVertex1().getLabel())
					);
			assertTrue( graph.getVertex("v05").equals(graph.getVertex(edge.getVertex1().getLabel()))
					|| graph.getVertex("v08").equals(graph.getVertex(edge.getVertex1().getLabel()))
					);
			assertEquals(2, incEdges.size());
		}
	}
	
	@Test
	public void getVertextTest()
	{
		assertTrue( _graph.getVertex("v02") == _graph.getVertex("v02") );
	}
	
	@Test
	public void DirectedGraphErstellenTest()
	{
		DatenEinleser einleser = new DatenEinleser("templates/sample1.txt");
		GraphDirected graph = new GraphDirected(einleser.getVertices(), einleser.getEdges());
		
		assertNotNull(einleser);
		assertNotNull(graph);
		
		assertTrue(graph instanceof GraphDirected);
		
		for(GraphVertex vertex : graph.getAllVertexes())
		{
			assertNotNull(vertex);
			assertTrue(vertex instanceof GraphVertex);
		}
		
		for(GraphLine edge : graph.getAllEdges() )
		{
			assertNotNull(edge);
			assertTrue(edge instanceof GraphLine);
		}
	}
	
	@Test
	public void edgesAndVertexesExist()
	{
		for( GraphVertex vertex : _vertexes )
		{
			assertNotNull(vertex);
			assertTrue(vertex instanceof GraphVertex);
		}
		for( GraphLine edge : _edges )
		{
			assertNotNull(edge);
			assertTrue(edge instanceof GraphLine);
		}
	}

	@Test
	public void EnthaeltKnotenTest()
	{	
		assertTrue( _graph.containsVertex("vS") );
		assertTrue( _graph.containsVertex("v02") );
		assertTrue( _graph.containsVertex("v03") );
		assertTrue( _graph.containsVertex("v04") );
		
		assertFalse( _graph.containsVertex(new GraphVertex("v1")) );
		assertFalse( _graph.containsVertex(new GraphVertex("v2")) );
		assertFalse( _graph.containsVertex(new GraphVertex("v3")) );
		assertFalse( _graph.containsVertex(new GraphVertex("v4")) );
//		for(GraphVertex vertex : _vertexes)
//		{
//			assertTrue(vertex instanceof GraphVertex);
//			assertNotNull(vertex);
//			assertTrue(_graph.containsVertex(vertex));
//		}
	}
	
	@Test
	public void inhaltVonVertexesArraylistTesten()
	{
		GraphVertex vertex1 = _vertexes.get(0);
		GraphVertex testVertex1 = new GraphVertex("vS");
		
		assertTrue( (vertex1 instanceof GraphVertex) && (testVertex1 instanceof GraphVertex) );
		assertNotNull(vertex1);
		assertNotNull(testVertex1);
		
		assertEquals( "vS", vertex1.getLabel() );
		assertEquals( "vS", testVertex1.getLabel() );
		assertEquals( vertex1.getLabel(), testVertex1.getLabel() );
		assertTrue( vertex1.equals(testVertex1) );
		assertEquals( vertex1, testVertex1 );
		
		assertEquals( _vertexes.get(0), new GraphVertex("vS") );
		assertEquals( _vertexes.get(1), new GraphVertex("v02") );
		assertEquals( _vertexes.get(2), new GraphVertex("v03") );
		assertEquals( _vertexes.get(3), new GraphVertex("v04") );
		
		assertTrue(_vertexes.contains(new GraphVertex("vS")));
		assertTrue(_vertexes.contains(new GraphVertex("v02")));
		assertTrue(_vertexes.contains(new GraphVertex("v03")));
		assertTrue(_vertexes.contains(new GraphVertex("v04")));
		
		assertFalse(_vertexes.contains(new GraphVertex("v1")));
		assertFalse(_vertexes.contains(new GraphVertex("v2")));
		assertFalse(_vertexes.contains(new GraphVertex("v3")));
		assertFalse(_vertexes.contains(new GraphVertex("v4")));
	}
	
	@Test
	public void gibEinenBestimmtenKnoten()
	{
		assertNotNull(_graph.getVertex("vS"));
		assertEquals(_graph.getVertex("vS"), new GraphVertex("vS"));
	}
	
//	@Test
//	public void testeAusgabe()
//	{
//		_graph.printAllData();
//	}

}