package main.materialien;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.fachwerte.Zufallsbuchstabe;

import org.junit.Before;
import org.junit.Test;

public class GraphVertexTest
{
	
	@Before
   public void setUp()
	{
   }
	
	@Test
	public void hashcodeTesten1()
	{
		GraphVertex vertex1 = new GraphVertex("test1");
		GraphVertex vertex2 = new GraphVertex("test2");
		
		int int1 = vertex1.hashCode();
		int int2 = vertex2.hashCode();
		
		assertNotEquals(int1, int2);
	}
	
	@Test
	public void hashcodeTesten2()
	{
		GraphVertex vertex1 = new GraphVertex("test1");
		GraphVertex vertex2 = new GraphVertex("test1");
		
		int int1 = vertex1.hashCode();
		int int2 = vertex2.hashCode();
		
		assertEquals(int1, int2);
	}
	
	@Test
	public void hashcodeUndEqualsTesten()
	{
		GraphVertex vertex1 = null;
		GraphVertex vertex2 = null;
		String str1 = null;
		String str2 = null;
		
		for( int i = 0; i < 1000000; i++)
		{
			str1 = Character.toString(Zufallsbuchstabe.gibGrossBuchstaben())
					+ Character.toString(Zufallsbuchstabe.gibGrossBuchstaben())
					+ Character.toString(Zufallsbuchstabe.gibGrossBuchstaben());
			
			str2 = Integer.toString((int)(Math.random() * 100));
			
			vertex1 = new GraphVertex(str1);
			vertex2 = new GraphVertex(str1);
			
			assertTrue( vertex1.hashCode() == vertex2.hashCode() );
			assertTrue( vertex1.equals(vertex2) );
			assertTrue( vertex2.equals(vertex1) );
			assertEquals( vertex1, vertex2 );
			
			vertex1 = new GraphVertex(str2);
			vertex2 = new GraphVertex(str2);
			
			assertTrue( vertex1.hashCode() == vertex2.hashCode() );
			assertTrue( vertex1.equals(vertex2) );
			assertTrue( vertex2.equals(vertex1) );
			assertEquals( vertex1, vertex2 );
			
			vertex1 = new GraphVertex(str1+str2);
			vertex2 = new GraphVertex(str1+str2);
			
			assertTrue( vertex1.hashCode() == vertex2.hashCode() );
			assertTrue( vertex1.equals(vertex2) );
			assertTrue( vertex2.equals(vertex1) );
			assertEquals( vertex1, vertex2 );
		}
	}
	
	@Test
	public void equalsTesten1()
	{
		GraphVertex vertex1 = new GraphVertex("test1");
		GraphVertex vertex2 = new GraphVertex("test2");
		
		assertFalse(vertex1.equals(vertex2));
	}
	
	@Test
	public void equalsTesten2()
	{
		GraphVertex vertex1 = new GraphVertex("test1");
		GraphVertex vertex2 = new GraphVertex("test1");
		
		assertTrue(vertex1.equals(vertex2));
	}
	
	@Test
	public void GraphVertexArrayListContainsTest()
	{
		GraphVertex vertex = new GraphVertex("test1");
		
		ArrayList<GraphVertex> testliste = new ArrayList<GraphVertex>();
		
		testliste.add(vertex);
		
		assertTrue(testliste.contains(vertex));
		assertTrue(testliste.contains(new GraphVertex("test1")));
		assertFalse(testliste.contains(new GraphVertex("test12")));
		
		assertEquals( new GraphVertex("test1"), testliste.get(0) );
	}
	
}