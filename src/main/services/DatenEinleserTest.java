package main.services;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.materialien.GraphLine;
import main.materialien.GraphVertex;

@SuppressWarnings("unused")
public class DatenEinleserTest
{
	
   private DatenEinleser _einleser;
	private ArrayList<GraphLine> _edges;
	private ArrayList<GraphVertex> _vertexes;
	private String _path = "templates/sample1.txt";
	private String _fileName = "sample1.txt";

	@Before
   public void setUp()
	{
   }
	
	@Test
	public void pathTesten()
	{
		File file = new File(_path);
		
		assertTrue(file.isFile());
		assertFalse(file.isDirectory());
		assertTrue(file.canRead());
		assertEquals(file.getName(), _fileName);
	}
	
	@Test
	public void fileEinlesenTesten()
	{
		File file = DatenEinleser.fileEinlesen(_path);
		
		assertNotNull(file);
	}
	
	@Test
	public void fileEinleserErstellenTesten()
	{
		File file = DatenEinleser.fileEinlesen(_path);
		
		FileReader fr = DatenEinleser.fileEinleserErstellen(file);
		
		assertNotNull(file);
		assertNotNull(fr);
	}
	
	@Test
	public void fileBufferedReaderErstellenTesten()
	{
		File file = DatenEinleser.fileEinlesen(_path);
		FileReader fr = DatenEinleser.fileEinleserErstellen(file);
		
		BufferedReader br = DatenEinleser.fileBufferedReaderErstellen(fr);
		
		assertNotNull(file);
		assertNotNull(fr);
		assertNotNull(br);
	}
	
	@Test
	public void zeilenAuslesenTesten()
	{
		File file = DatenEinleser.fileEinlesen(_path);
		FileReader fr = DatenEinleser.fileEinleserErstellen(file);
		BufferedReader br = DatenEinleser.fileBufferedReaderErstellen(fr);
		
		ArrayList<String> zeilen = DatenEinleser.zeilenAuslesen(br);
		
		assertNotNull(zeilen);
		
		assertEquals("#Vertices" , zeilen.get(1));
		assertEquals("-v01" , zeilen.get(2));
		assertEquals("-v02" , zeilen.get(3));
		assertEquals("-v03" , zeilen.get(4));
		assertEquals("-v04" , zeilen.get(5));
		assertEquals("" , zeilen.get(6));
		assertEquals("#Edges" , zeilen.get(8));
		assertEquals("-v01.v02.1" , zeilen.get(9));
		assertEquals("-v01.v03.2" , zeilen.get(10));
		assertEquals("-v02.v04.3" , zeilen.get(11));
	}
	
	@Test
	public void knotenAuslesenTest()
	{
		File file = DatenEinleser.fileEinlesen(_path);
		FileReader fr = DatenEinleser.fileEinleserErstellen(file);
		BufferedReader br = DatenEinleser.fileBufferedReaderErstellen(fr);
		ArrayList<String> zeilen = DatenEinleser.zeilenAuslesen(br);
		
		ArrayList<GraphVertex> knoten = DatenEinleser.knotenAuslesen(zeilen);
		
		assertNotNull(knoten);
		assertEquals(knoten.get(0).getLabel(), "v01");
		assertEquals(knoten.get(1).getLabel(), "v02");
		assertEquals(knoten.get(2).getLabel(), "v03");
		assertEquals(knoten.get(3).getLabel(), "v04");
	}
	
	@Test
	public void kantenAuslesenTest()
	{
		File file = DatenEinleser.fileEinlesen(_path);
		FileReader fr = DatenEinleser.fileEinleserErstellen(file);
		BufferedReader br = DatenEinleser.fileBufferedReaderErstellen(fr);
		ArrayList<String> zeilen = DatenEinleser.zeilenAuslesen(br);
		
		assertNotNull(zeilen);
		
		ArrayList<GraphLine> kanten = DatenEinleser.kantenAuslesen(zeilen);
		
		assertNotNull(kanten);
		assertTrue(kanten.size() == 3);
	}
	
	
}
