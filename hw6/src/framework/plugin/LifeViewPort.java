package framework.plugin;

import framework.interfaces.Viewport;
import framework.interfaces.Visualization;
import framework.lib.Point2D;
import framework.lib.State;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JToolBar;


public class LifeViewPort implements Viewport<Point2D>
{
	private Visualization	v;

	@Override
	public void drawState(State<Point2D> s, Graphics g)
	{

		int cellWidth = 10;
		for (Point2D p : s) {
			if (s.getCellState(p) == 1) {
				g.setColor(v.getColorRep(1));
				g.fillRect(p.getX() * cellWidth, p.getY() * cellWidth, cellWidth, cellWidth);
			}
			else {
				g.setColor(v.getColorRep(0));
				g.fillRect(p.getX() * cellWidth, p.getY() * cellWidth, cellWidth, cellWidth);
			}
			g.setColor(Color.white);
			g.drawRect(p.getX() * cellWidth, p.getY() * cellWidth, cellWidth, cellWidth);
		}
	}

	@Override
	public void setVisualization(Visualization v)
	{
		this.v = v;
	}

	@Override
	public JToolBar getToolbar()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
