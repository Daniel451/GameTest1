package main.werkzeuge;

import main.materialien.GraphLineUI;

public class SpielfeldWerkzeug
{
	private static SpielfeldWerkzeug _instance = null;
	private static SpielfeldUI _spielfeld;
	
	private SpielfeldWerkzeug()
	{
		_spielfeld = new SpielfeldUI();
		_spielfeld.addComponent(new GraphLineUI(0,0,100,100));
	}
	
	public static SpielfeldWerkzeug getInstance()
	{
		if(_instance == null)
		{
			_instance = new SpielfeldWerkzeug();
		}
		
		return _instance;
	}
}
