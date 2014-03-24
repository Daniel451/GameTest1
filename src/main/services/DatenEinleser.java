package main.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import main.materialien.GraphLine;
import main.materialien.GraphVertex;

/**
 * Liest ein Textfile ein um daraus einen Graphen zu erstellen
 * 
 * @author Daniel
 *
 */
public class DatenEinleser
{
	private File _datei;
	private FileReader _dateiLeser;
	private BufferedReader _dateiBufferedLeser;
	private ArrayList<String> _lines;
	private ArrayList<GraphVertex> _GraphVertices;
	private ArrayList<GraphLine> _GraphLines;
	
	/**
	 * Konstruktor
	 * 
	 * @param path String: Pfad zu der Datei, aus der der Graph gelesen werden soll
	 */
	public DatenEinleser(String path)
	{
		_datei = fileEinlesen(path);
		_dateiLeser = fileEinleserErstellen(_datei);
		_dateiBufferedLeser = fileBufferedReaderErstellen(_dateiLeser);
		_lines = zeilenAuslesen(_dateiBufferedLeser);
		_GraphVertices = knotenAuslesen(_lines);
		_GraphLines = kantenAuslesen(_lines);
	}
	
	public ArrayList<GraphVertex> getVertices()
	{
		return _GraphVertices;
	}
	
	public ArrayList<GraphLine> getEdges()
	{
		return _GraphLines;
	}
	
	public ArrayList<String> getLines()
	{
		return _lines;
	}
	
	public BufferedReader getBR()
	{
		return _dateiBufferedLeser;
	}
	
	public FileReader getFR()
	{
		return _dateiLeser;
	}
	
	public File getFile()
	{
		return _datei;
	}
	
	/**
	 * Erstellt ein File-Objekt aus dem gegeben Pfad.
	 * 
	 * @param path String: Pfad zu der einzulesenden Datei
	 * @return File File-Objekt
	 */
	public static File fileEinlesen(String path)
	{
		return new File(path);
	}
	
	/**
	 * Erstellt ein FileReader-Objekt aus dem gegebenen File-Objekt
	 * 
	 * @return FileReader FileReader-Objekt
	 */
	public static FileReader fileEinleserErstellen(File file)
	{
		FileReader einleser = null;
		
		if(file.canRead())
		{
			try
         {
	         einleser = new FileReader(file);
         } catch (FileNotFoundException e)
         {
         }
		}
		
		return einleser;
	}
	
	/**
	 * Erstellt aus einem gegebenen FileReader-Objekt einen BufferedReader
	 * 
	 * @param fr FileReader-Objekt
	 * @return BufferedReader BufferedReader-Objekt
	 */
	public static BufferedReader fileBufferedReaderErstellen(FileReader fr)
	{
		BufferedReader br;
		
		br = new BufferedReader(fr);
		
		return br;
	}
	
	/**
	 * Liest aus einem Textfile mit Hilfe eines BufferedReaders jede Zeile aus und speichert Sie
	 * in einer ArrayList aus Strings
	 * 
	 * @param br BufferedReader-Objekt des auszulesenden Files
	 * @return ArrayList<String>-Objekt (die ausgelesenen Zeilen)
	 */
	public static ArrayList<String> zeilenAuslesen(BufferedReader br)
	{
		String zeile = null;
		ArrayList<String> zeilen = new ArrayList<String>();
		
		try
      {
	      while( (zeile = br.readLine()) != null )
	      {
	      	zeilen.add(zeile);
	      }
      } catch (IOException e)
      {
      }
		
		return zeilen;
	}
	
	/**
	 * Liest alle Knotenbezeichnungen aus einer ArrayList aus Strings aus und
	 * erstellt daraus eine ArrayList aus GraphVertice-Objekten (Knoten)
	 * 
	 * Um Knoten auslesen zu können, muss das Textfile folgende Konvention erfüllen
	 * 
	 * #Vertices
	 * -knoten1
	 * -knoten2
	 * ...
	 * 
	 * @param lines ArrayList<String>-Objekt: Eine ArrayList, in der nach Knoteneinträgen gesucht wird
	 * @return ArrayList<GraphVertice> Eine ArrayList aus GraphVertice-Objekten
	 */
	public static ArrayList<GraphVertex> knotenAuslesen(ArrayList<String> lines)
	{
		// Leere ArrayList deklarieren/initialisieren
		ArrayList<GraphVertex> vertices = new ArrayList<GraphVertex>();
		
		// '#Vertice'-Zeile finden und in die nächste Zeile springen
		int index = lines.indexOf("#Vertices") + 1;
		
		// Alle Zeilen ab index auslesen, die mit einem '-' beginnen
		while(index < lines.size() && lines.get(index).length() > 0 && lines.get(index).charAt(0) == '-' )
		{
			// Neues GraphVertice-Objekt erstellen
			// Das '-' am Anfang der Zeile wird nicht mit eingelesen,
			// der Rest wird für das Label des Knotens verwendet
			vertices.add(new GraphVertex(lines.get(index).substring(1)));
			// Index inkrementieren, damit die nächste Zeile ausgelesen werden kann
			index++;
		}
		
		return vertices;
	}
	
	/**
	 * Liest alle Kantenbezeichnungen aus einer ArrayList aus Strings aus und
	 * erstellt daraus eine ArrayList aus GraphLine-Objekten (Kanten)
	 * 
	 * Um Kanten auslesen zu können, muss das Textfile folgende Konvention erfüllen
	 * 
	 * #Edges
	 * -knoten1.knoten2
	 * ...
	 * 
	 * @param lines ArrayList<String>-Objekt: Eine ArrayList, in der nach Kanteneinträgen gesucht wird
	 * @return ArrayList<GraphVertice> Eine ArrayList aus GraphLine-Objekten
	 */
	public static ArrayList<GraphLine> kantenAuslesen(ArrayList<String> lines)
	{
		String strKnoten1 = null;
		String strKnoten2 = null;
		String strKosten = null;
		GraphVertex knoten1 = null;
		GraphVertex knoten2 = null;
		GraphLine edge = null;
		ArrayList<GraphLine> edges = new ArrayList<GraphLine>();
		
		int index = lines.indexOf("#Edges") + 1;
		
		while(index < lines.size() && lines.get(index).length() > 0 && lines.get(index).charAt(0) == '-' )
		{
			String line = lines.get(index);
			line = line.substring(1);
			
			String[] lineArr = line.split("\\.");
			
			strKnoten1 = lineArr[0];
			strKnoten2 = lineArr[1];
			strKosten = lineArr[2];
			
			knoten1 = new GraphVertex(strKnoten1);
			knoten2 = new GraphVertex(strKnoten2);
			
			edge = new GraphLine(knoten1, knoten2, Integer.valueOf(strKosten), strKnoten1 + "." + strKnoten2 );
			
			edges.add(edge);
			
			index++;
		}
		
		return edges;
	}
	
}