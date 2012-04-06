package framework.lib;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import framework.interfaces.Viewport;


/**
 * A panel that contains the Viewport and lets the Viewport draw on this panel's
 * graphics object, in Viewport's drawState.
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 */

public class ViewportPanel<T extends Point> extends JPanel
{
	private static final long		serialVersionUID	= 1L;
	private static final Dimension	dimensions			= new Dimension(500, 500);
	private Viewport<T>				viewport;
	private State<T>				state;

	/**
	 * Constructs ViewportPanel
	 * 
	 * @param v a Viewport which can draw the state
	 * @param state a state of the cellular automata
	 */
	public ViewportPanel(Viewport<T> v, State<T> state)
	{
		super();
		this.viewport = v;
		this.state = state;
		this.setMinimumSize(dimensions);
		this.setPreferredSize(dimensions);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		viewport.drawState(state, g);
	}
}
