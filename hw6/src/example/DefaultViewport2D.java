package example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JToolBar;

import framework.interfaces.Viewport;
import framework.interfaces.Visualization;
import framework.lib.Point2D;
import framework.lib.State;


public class DefaultViewport2D implements Viewport<Point2D>
{
	private Visualization	v;

	@Override
	public void drawState(State<Point2D> s, Graphics g)
	{
		Rectangle r = g.getClipBounds();
		Point2D size = s.getSize();
		int cellWidth = r.height/((size.getX() < size.getY()) ? size.getY() : size.getX());
		for (Point2D p : s) {
			g.setColor(v.getColorRep(s.getCellState(p)));
			g.fillRect(p.getX() * cellWidth, p.getY() * cellWidth, cellWidth, cellWidth);

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
