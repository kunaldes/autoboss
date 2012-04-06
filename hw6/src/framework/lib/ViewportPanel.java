package framework.lib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import framework.interfaces.Viewport;

public class ViewportPanel<T extends Point> extends JPanel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Viewport<T> visualization;
	private State<T> state;
	
	public ViewportPanel(Viewport<T> v, State<T> state)
	{
		super();
		this.visualization = v;
		this.state = state;
		this.setBackground(Color.green);
		this.setMinimumSize(new Dimension(500, 500));
		this.setPreferredSize(new Dimension(500, 500));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		visualization.drawState(state, g);
	}
}
