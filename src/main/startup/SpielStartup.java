package main.startup;

import main.services.Controller;

/**
 * Startet das Spiel
 * 
 * @author Daniel
 *
 */
public class SpielStartup
{
	// Klassenvariablen
		// Werkzeuge
		@SuppressWarnings("unused")
      private static Controller _controller;
	
	/**
	 * Main Methode
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		_controller = Controller.getInstance();
	}
}
