package main.werkzeuge;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.OverlayLayout;

import main.materialien.GraphLineUI;
import main.materialien.GraphVerticeUI;

public class SpielfeldUI
{
	private JFrame _mainframe;
	private Container _container;
	private SpielfeldFlaeche _spielfeldFlaeche;
	private int _width;
	private int _height;
	private LinkedList<Component>[] _componentListHolder;
	private LinkedList<Component> _componentsLine;
	private LinkedList<Component> _componentsVertice;
	private LinkedList<Component> _componentsOther;
	
	public SpielfeldUI()
	{
		// Felder initialisieren
		initializeFields();

      // LayoutManager und Fenstereigenschaften setzen
      setWindowStartSettings();
		
		// Komponenten zusammenfügen
		_mainframe.add(_container);
		
//		addComponent(new GraphVertice(150, 150));
//		_container.add(new GraphVertice(250, 150));
//		_container.add(new GraphVertice(200, 225));
//		_container.add(new GraphLine(150, 150, 250, 150));
//		_container.add(new GraphLine(250, 150, 200, 225));
//		_container.add(new GraphLine(200, 225, 150, 150));
		_container.add(_spielfeldFlaeche);
		
		zeigeFenster();
  	}
	
	public void zeigeFenster()
	{
		_mainframe.setSize(_width, _height);
		_mainframe.setLocation(20, 20);
		_mainframe.setVisible(true);
	}
	
	public JFrame gibMainFrame()
	{
		return _mainframe;
	}
	
	public int getWidth()
	{
		return _width;
	}
	
	public int getHeight()
	{
		return _height;
	}
	
	public void addComponent(Component c)
	{
		_componentListHolder[giveCLHIndex(c)].add(c);
		repaintFrame();
	}
	
	public void removeComponent(Component c)
	{
		_componentListHolder[giveCLHIndex(c)].remove(c);
		repaintFrame();
	}
	
	public void removeAllComponents()
	{
		repaintFrame();
	}
	
	/**
	 * Geht alle Component-Holder durch, fügt die Komponenten dem container hinzu
	 * und führt dann ein repaint auf dem mainframe durch
	 */
	public void repaintFrame()
	{
		// durch alle Component-Holder-Listen iterieren
		for(LinkedList<Component> list : _componentListHolder)
		{
			// durch die jeweilige Liste iterieren und jede Komponente dem Container hinzufügen
			for(Component c : list)
			{
				_container.add(c);
			}
		}
		_container.add(_spielfeldFlaeche);
		_mainframe.repaint();
	}
	
	private void setWindowStartSettings()
	{
		_mainframe.setLayout(new BorderLayout());
      _container.setLayout(new OverlayLayout(_container));
            
      _mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_mainframe.setLocationRelativeTo(null);
		_mainframe.setSize(_width, _height);
		_mainframe.setResizable(false);
		
		_mainframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_mainframe.setTitle("Simulation");	
	}
	
	/**
	 * Felder initialisieren
	 */
	@SuppressWarnings("unchecked")
   private void initializeFields()
	{
		_width = 1200;
		_height = 750;
      _mainframe = new JFrame();
      _container = new Container();
      _spielfeldFlaeche = new SpielfeldFlaeche(_width, _height);
      
      _componentsLine = new LinkedList<Component>();
      _componentsVertice = new LinkedList<Component>();
      _componentsOther = new LinkedList<Component>();
      
      _componentListHolder = new LinkedList[3];
      	_componentListHolder[0] = _componentsVertice;
      	_componentListHolder[1] = _componentsLine;
      	_componentListHolder[2] = _componentsOther;
	}
	
	/**
	 * Gibt den Index für die entsprechende Komponente zurück
	 * 
	 * @return int Index im Array componentListHolder
	 */
	private int giveCLHIndex(Component c)
	{
		int ret;
		
		if(c instanceof GraphVerticeUI)
		{
			ret = 0;
		}
		else if (c instanceof GraphLineUI)
		{
			ret = 1;
		}
		else
		{
			ret = 2;
		}
		
		return ret;
	}
}
