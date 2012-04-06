package framework.lib;

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
		this.setMinimumSize(new Dimension(500, 500)); //Decided to make it 500 pixels wide for funzies
		this.setPreferredSize(new Dimension(500, 500));
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		visualization.drawState(state, g);
	}
}
