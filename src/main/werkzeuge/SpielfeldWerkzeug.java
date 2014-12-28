package main.werkzeuge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;

import javax.swing.JFileChooser;

import main.fachwerte.ObservedData;
import main.fachwerte.Punkt;
import main.materialien.Graph;
import main.materialien.GraphLine;
import main.materialien.GraphLineUI;
import main.materialien.GraphVertex;
import main.materialien.GraphVertexUI;

public class SpielfeldWerkzeug extends Observable
{
   private static SpielfeldWerkzeug _instance = null;
   private static SpielfeldUI _spielfeld;
   private static Graph _graph;
   // Für Zeichenvorgänge
   // Menge bereits gezeichneter Knoten
   HashSet<GraphVertex> _drawnVertexes = new HashSet<GraphVertex>();
   // Map, in dem jedem Label eines Knotens eine GraphVertexUI zugeordnet wird
   HashMap<String, GraphVertexUI> _vertexUImap =
         new HashMap<String, GraphVertexUI>();

   private SpielfeldWerkzeug()
   {
      _spielfeld = new SpielfeldUI();
      erstelleButtonListener();
   }

   public static SpielfeldWerkzeug getInstance()
   {
      if( _instance == null )
      {
         _instance = new SpielfeldWerkzeug();
      }

      return _instance;
   }

   public SpielfeldUI getSpielfeldUI()
   {
      return _spielfeld;
   }

   public void zeichneGraph(Graph graph)
   {
      _spielfeld.removeAllComponentsFromStage();
      _graph = graph;
      zeichneKnoten();
   }

   private void zeichneKnoten()
   {
      // Vorbereitungen

      // Startwert für ersten Knoten
      int x_a = 100;
      int y_a = 300;

      // Startknoten schon gezeichnet?
      boolean startVertexIsAlreadyDrawn = false;

      // Startknoten
      GraphVertex startVertex = null;

      // Menge bereits gezeichneter Knoten
      _drawnVertexes = new HashSet<GraphVertex>();
      _vertexUImap = new HashMap<String, GraphVertexUI>();

      // Zeichnen

      // Startknoten ausfindig machen und zeichnen
      for ( GraphVertex knoten : _graph.getAllVertexes() )
      {
         // Wenn ein Knoten ausgehende Kanten hat, als "Startknoten" wählen
         if( _graph.getOutgoingEdgesOfVertex(knoten).size() >= 1
               && !startVertexIsAlreadyDrawn )
         {
            GraphVertexUI neuerUIknoten =
                  new GraphVertexUI(x_a, y_a, knoten.getLabel());
            neuerUIknoten.changeColor(knoten.getColor());
            _spielfeld.addComponent(neuerUIknoten);
            _vertexUImap.put(knoten.getLabel(), neuerUIknoten);
            startVertex = knoten;
            _drawnVertexes.add(knoten);
            break;
         }
      }

      // Alle direkten Nachfolger von dem Startknoten und seinen transitiven
      // Nachfolgern zeichnen
      paintSuccessorVertexes(startVertex, x_a, y_a, 1);

      // Alle Kanten zeichnen
      zeichneKanten();

      // UI aktualisieren
      _spielfeld.repaintFrame();
   }

   /**
    * Zeichnet die Nachfolger eines Knotens rekursiv
    * 
    * @param vertex
    *           Aktueller Knoten, vom die Nachfolger gezeichnet werden sollen
    * @param x_a
    *           Aktueller X-Wert
    * @param y_a
    *           Aktueller Y-Wert
    * @param level
    *           Aktuelle Tiefe des Baumes
    */
   private void paintSuccessorVertexes(GraphVertex vertex, int x_a, int y_a,
         int level)
   {
      // Vorbereitungen
      // X-Inkrementierung pro Tiefenlevel im Graph
      int x_inc = 85;
      int y_inc = 120;

      // Adjazenzliste (Kanten) von aktuellem Knoten holen (Nachbarknoten)
      ArrayList<GraphLine> liste = _graph.getOutgoingEdgesOfVertex(vertex);

      if( liste.size() >= 1 )
      {
         for ( int i = 0; i < liste.size(); i++ )
         {
            GraphVertex itVertex =
                  _graph.getVertex(liste.get(i).getVertex2().getLabel());

            if( !_drawnVertexes.contains(liste.get(i).getVertex2()) )
            {
               // x_neu wird jedes Level um x_inc * level erhöht
               int x_neu = x_a + x_inc * level;
               // Die Mitte (y_a) wird als Start genommen,
               // davon die Hälfte der gesamten neuen Höhe (Anzahl neue Knoten *
               // y_inc / 2)
               // abgezogen und am Ende für jeden Iterationsschritt wieder y_inc
               // * i hinzuaddiert
               int y_neu = (y_a - (y_inc * (liste.size() - 1) / 2)) + y_inc * i;

               GraphVertexUI neuerUIknoten =
                     new GraphVertexUI(x_neu, y_neu, itVertex.getLabel());
               neuerUIknoten.changeColor(itVertex.getColor());
               _spielfeld.addComponent(neuerUIknoten);
               _vertexUImap.put(itVertex.getLabel(), neuerUIknoten);
               _drawnVertexes.add(itVertex);

               // rekursiver Aufruf, um die Kinder des aktuellen Knotens zu
               // zeichnen
               paintSuccessorVertexes(itVertex, x_a, neuerUIknoten.getY(),
                     level + 1);
            }
         }
      }
   }

   private void zeichneKanten()
   {
      ArrayList<GraphLine> kantenliste = _graph.getAllEdges();

      for ( GraphLine kante : kantenliste )
      {
         GraphVertex ausgehenderKnoten = kante.getVertex1();
         GraphVertex eingehenderKnoten = kante.getVertex2();

         GraphVertexUI ausgehenderKnotenUI =
               _vertexUImap.get(ausgehenderKnoten.getLabel());
         GraphVertexUI eingehenderKnotenUI =
               _vertexUImap.get(eingehenderKnoten.getLabel());

         Punkt p_start = ausgehenderKnotenUI.gibZentrum();
         Punkt p_end = eingehenderKnotenUI.gibZentrum();

         _spielfeld.addComponent(new GraphLineUI(p_start.getIntX(), p_start
               .getIntY(), p_end.getIntX(), p_end.getIntY(), kante.getLabel(),
               kante.getCost()));
      }
   }

   private void erstelleButtonListener()
   {
      // Template-Button Listener
      _spielfeld.getButton("button_template").addActionListener(
            new ActionListener()
            {
               @Override
               public void actionPerformed(ActionEvent e)
               {
                  JFileChooser chooser = _spielfeld.getFileChooser();
                  chooser.setCurrentDirectory(new File("."));
                  int chooserAction = chooser.showOpenDialog(null);

                  // Wenn eine Datei ausgewählt wird
                  if( chooserAction == JFileChooser.APPROVE_OPTION )
                  {
                     setChanged();
                     ObservedData data =
                           new ObservedData("TemplateSelected", chooser
                                 .getSelectedFile());
                     notifyObservers(data);
                  }
               }

            });

      // Start-Button Listener
      _spielfeld.getButton("button_start").addActionListener(
            new ActionListener()
            {
               @Override
               public void actionPerformed(ActionEvent e)
               {
                  setChanged();
                  ObservedData data =
                        new ObservedData("StartButton", _spielfeld
                              .getRadioButtonGroup().getSelection());
                  notifyObservers(data);
               }

            });

      // Next-Button Listener
      _spielfeld.getButton("button_next").addActionListener(
            new ActionListener()
            {
               @Override
               public void actionPerformed(ActionEvent e)
               {
                  setChanged();
                  ObservedData data =
                        new ObservedData("NextButton", _spielfeld
                              .getRadioButtonGroup().getSelection());
                  notifyObservers(data);
               }

            });

      // Reset-Button Listener
      _spielfeld.getButton("button_reset").addActionListener(
            new ActionListener()
            {
               @Override
               public void actionPerformed(ActionEvent e)
               {
                  setChanged();
                  ObservedData data = new ObservedData("ResetButton", "reset");
                  notifyObservers(data);
               }

            });
   }
}
