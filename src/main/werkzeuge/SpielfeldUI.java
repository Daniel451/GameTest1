package main.werkzeuge;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.OverlayLayout;

import main.materialien.ButtonHolder;
import main.materialien.GraphLineUI;
import main.materialien.GraphVertexUI;

public class SpielfeldUI
{
	private JFrame _mainframe;
	private SpielfeldFlaeche _spielfeldFlaeche;
	private int _width;
	private int _height;
	
	// Container
	private Container _container;
	private Container _containerSpielfeld;
	private Container _containerVertices;
	private Container _containerLines;
	private Container _containerOther;
	private Container _containerButtons;
	
	// Buttons
	private ButtonHolder _buttonHolder;
	private JButton _buttonFileChooser;
	private JRadioButton _radioBFS, _radioAStar;
	private ButtonGroup _radioButtonGroup;
	private JButton _buttonReset;
	private JButton _buttonNextStep;
	private JButton _buttonStart;
	private HashMap<String, JButton> _buttonRegister;
	
	// FileChooser
	private JFileChooser _fileChooser;
	
	public SpielfeldUI()
	{
		// Felder initialisieren
		initializeFields();

      // LayoutManager und Fenstereigenschaften setzen
      setWindowStartSettings();
      
      // Buttons hinzufügen
      erstelleButtonHolder();
      erstelleButtons();
      
      _containerButtons.add(_buttonHolder);
      
      _container.add(_containerVertices);
      _container.add(_containerLines);
      _container.add(_containerOther);
      _container.add(_containerSpielfeld);
      
      _mainframe.add(_containerButtons, BorderLayout.PAGE_START);
      _mainframe.add(_container, BorderLayout.CENTER);
		
		zeigeFenster();
  	}
	
	private void erstelleRadioButtons()
	{
		_radioBFS = new JRadioButton("BFS");
		
		_radioAStar = new JRadioButton("A*");
		
		_radioButtonGroup = new ButtonGroup();
		
		_radioButtonGroup.add(_radioBFS);
		_radioButtonGroup.add(_radioAStar);
		
		_buttonHolder.add(_radioBFS);
		_buttonHolder.add(_radioAStar);
	}
	
	public JRadioButton getBFSButton()
	{
		return _radioBFS;
	}
	
	public JRadioButton getAStar()
	{
		return _radioAStar;
	}
	
	private void erstelleButtonHolder()
	{
		_buttonHolder = new ButtonHolder();
		_buttonHolder.setLayout(new FlowLayout());
	}
	
	private void erstelleButtons()
	{
		erstelleFileChooserButton();
		erstelleRadioButtons();
		erstelleStartButton();
		erstelleNextButton();
		erstelleResetButton();
	}
	
	private void erstelleStartButton()
	{
		_buttonStart = new JButton("Start");
		_buttonHolder.add(_buttonStart);
		_buttonRegister.put("button_start", _buttonStart);
	}
	
	private void erstelleNextButton()
	{
		_buttonNextStep = new JButton("Next step");
		_buttonHolder.add(_buttonNextStep);
		_buttonRegister.put("button_next", _buttonNextStep);
	}
	
	private void erstelleResetButton()
	{
		_buttonReset = new JButton("Reset");
		_buttonHolder.add(_buttonReset);
		_buttonRegister.put("button_reset", _buttonReset);
	}
	
	private void erstelleFileChooserButton()
	{
		_buttonFileChooser = new JButton("Template auswählen");
		_buttonHolder.add(_buttonFileChooser);
		_buttonRegister.put("button_template", _buttonFileChooser);
	}
	
	public ButtonGroup getRadioButtonGroup()
	{
		return _radioButtonGroup;
	}
	
	public JButton getButton(String name)
	{
		return _buttonRegister.get(name);
	}
	
	public JFileChooser getFileChooser()
	{
		return _fileChooser;
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
		if( c instanceof GraphVertexUI )
		{
			_containerVertices.add(c);
		}
		else if ( c instanceof GraphLineUI )
		{
			_containerLines.add(c);
		}
		else
		{
			_containerOther.add(c);
		}
	}
	
	public void removeComponent(Component c)
	{
		if( c instanceof GraphVertexUI )
		{
			_containerVertices.remove(c);
		}
		else if ( c instanceof GraphLineUI )
		{
			_containerLines.remove(c);
		}
		else
		{
			_containerOther.remove(c);
		}
	}
	
	/**
	 * Löscht nur die aktuelle Darstellung der Komponenten, nicht aber deren
	 * Zustand in dem _componentListHolder
	 * Repaintet aber nicht das Frame
	 */
	public void removeAllComponentsFromStage()
	{
		_containerOther.removeAll();
		_containerLines.removeAll();
		_containerVertices.removeAll();
	}
	
	/**
	 * Geht alle Component-Holder durch, fügt die Komponenten dem container hinzu
	 * und führt dann ein repaint auf dem mainframe durch
	 */
	public void repaintFrame()
	{				
		_mainframe.validate();
		_mainframe.repaint();
	}
	
	private void setWindowStartSettings()
	{
		_mainframe.setLayout(new BorderLayout());
		
		_container.setLayout(new OverlayLayout(_container));
		_containerButtons.setLayout(new FlowLayout());
      _containerSpielfeld.setLayout(new OverlayLayout(_containerSpielfeld));
      _containerVertices.setLayout(new OverlayLayout(_containerVertices));
      _containerLines.setLayout(new OverlayLayout(_containerLines));
      _containerOther.setLayout(new OverlayLayout(_containerOther));
            
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
	private void initializeFields()
	{
		_width = 1200;
		_height = 750;
      
		_mainframe = new JFrame();
      
		_container = new Container();
		_containerButtons = new Container();
      _containerSpielfeld = new Container();
      _containerVertices = new Container();
      _containerLines = new Container();
      _containerOther = new Container();
      
      _spielfeldFlaeche = new SpielfeldFlaeche(_width, _height);
      
      _containerSpielfeld.add(_spielfeldFlaeche);
      
      _buttonRegister = new HashMap<String, JButton>();
      
      _fileChooser = new JFileChooser();
	}
	
	public void printComponentInfo(Component c)
	{
		System.out.println( "Name: " + c.getName() +  ", posX: " + c.getX() + ", posY: " + c.getY());
	}
}
