package main.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import main.fachwerte.ObservedData;
import main.materialien.Graph;
import main.materialien.GraphDirected;
import main.materialien.GraphLine;
import main.materialien.GraphVertex;
import main.werkzeuge.SpielfeldUI;
import main.werkzeuge.SpielfeldWerkzeug;


/**
 * Controller-Klasse
 * 
 * Kontrolliert das spätere Geschehen.
 * Hält Referenzen auf die wichtigen Objekte (Spielfeld, usw.)
 * 
 * @author Daniel
 *
 */
public class Controller implements Observer
{
	private static Controller _instance = null;
	private static SpielfeldWerkzeug _spielfeldWerkzeug;
   private static SpielfeldUI _spielfeldUI;
	private static ArrayList<GraphLine> _edges;
	private static ArrayList<GraphVertex> _vertexes;
	private static Graph _graph;
	private static File _file;
	private static DatenEinleser _einleser;
	@SuppressWarnings("unused")
   private static AStar _astar;
	private static BFS _bfs;
	
	/**
	 * Konstruktor
	 */
	private Controller()
	{
		_spielfeldWerkzeug = SpielfeldWerkzeug.getInstance();
		_spielfeldUI = _spielfeldWerkzeug.getSpielfeldUI();
		_spielfeldWerkzeug.addObserver(this);
		_astar = new AStar();
		_bfs = new BFS();
	}
	
	public static Controller getInstance()
	{
		if(_instance == null)
		{
			_instance = new Controller();
		}
		
		return _instance;
	}

	@Override
   public void update(Observable o, Object arg)
   {		
		if( arg instanceof ObservedData )
		{
			ObservedData obj = (ObservedData)arg;
			
			switch(obj.getDataName())
			{
				case "TemplateSelected":
					_file = (File)obj.getData();
					_einleser = new DatenEinleser(_file.getAbsolutePath());
					
					_edges = _einleser.getEdges();
					_vertexes = _einleser.getVertices();
					
					_graph = new GraphDirected(_vertexes, _edges);
					
					_spielfeldWerkzeug.zeichneGraph(_graph);
				break;
				
				case "StartButton":
					// Radiobuttons disablen
					_spielfeldUI.getBFSButton().setEnabled(false);
					_spielfeldUI.getAStar().setEnabled(false);
					
					if( _spielfeldUI.getBFSButton().isSelected() )
					{
						_bfs.setGraphAndInitialize(_graph, _graph.getVertex("vS"), _graph.getVertex("vG"));
						_bfs.iterate();
						_spielfeldWerkzeug.zeichneGraph(_graph);
					}
					else if ( _spielfeldUI.getAStar().isSelected() )
					{						
						_astar.setGraphAndInitialize(_graph, _graph.getVertex("vS"), _graph.getVertex("vG"));
						_astar.iterate();
						_spielfeldWerkzeug.zeichneGraph(_graph);
					}
				break;
				
				case "NextButton":
					if( _spielfeldUI.getBFSButton().isSelected() )
					{
						_bfs.iterate();
						_spielfeldWerkzeug.zeichneGraph(_graph);
					}
					else if ( _spielfeldUI.getAStar().isSelected() )
					{
						_astar.iterate();
						_spielfeldWerkzeug.zeichneGraph(_graph);
					}
				break;
				
				case "ResetButton":
					_astar = new AStar();
					_bfs = new BFS();
					_spielfeldUI.getBFSButton().setEnabled(true);
					_spielfeldUI.getAStar().setEnabled(true);
					
					// Datei neu einlesen
					_einleser = new DatenEinleser(_file.getAbsolutePath());
					
					// Kanten und Knoten neu erstellen
					_edges = _einleser.getEdges();
					_vertexes = _einleser.getVertices();
					
					// Graph neu erstellen
					_graph = new GraphDirected(_vertexes, _edges);

					// Neuen Graphen zeichnen
					_spielfeldWerkzeug.zeichneGraph(_graph);
				break;
			}
		}
   }
}