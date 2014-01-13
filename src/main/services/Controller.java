package main.services;

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
public class Controller
{
	private static Controller _instance = null;
	private static SpielfeldWerkzeug _spielfeld;
	
	/**
	 * Konstruktor
	 */
	private Controller()
	{
		_spielfeld = SpielfeldWerkzeug.getInstance();
		
		
	}
	
	public static Controller getInstance()
	{
		if(_instance == null)
		{
			_instance = new Controller();
		}
		
		return _instance;
	}
}