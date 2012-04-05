package interfaces.interfaces;

import interfaces.lib.Point;
import interfaces.lib.State;

import java.awt.Graphics;

import javax.swing.JToolBar;


/**
 * The Viewport class specifies a more advanced way than Visualization to
 * view the data in the cells. Should the user decide to use a Viewport other
 * than the default one automatically provided by the framework, they need to
 * specify how a given state should be drawn onto a Graphics object. This
 * class offers the most freedom of how a cellular automaton should be displayed
 * on screen, but most users should find the default sufficient for their needs
 * in 1 and 2 dimensions. 
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 *
 * @param <T> Gives a point with a concrete dimension that this viewport can be
 * 		used to visualize. Should be a strict subclass of Point.
 */
public interface Viewport<T extends Point> {

	/**
	 * This function draws the given State s onto the Graphics object g.
	 * @param s a state that should be visualized.
	 * @param g a Graphics (basically a canvas) that the Viewport should draw on
	 */
	void drawState(State<T> s, Graphics g);

	/**
	 * The Viewport uses the given Visualization to determine what colors each cell
	 * should be. In theory, a viewport can ignore this and determine colors on its own.
	 *  
	 * @param v The Visualization that the Viewport should use to determine colors. 
	 */
	public void setVisualization(Visualization v);
	
	public JToolBar getToolbar();

}
