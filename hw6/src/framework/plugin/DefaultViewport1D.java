package framework.plugin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JToolBar;

import framework.interfaces.Viewport;
import framework.interfaces.Visualization;
import framework.lib.Point1D;
import framework.lib.State;


public class DefaultViewport1D implements Viewport<Point1D>
{
	private Visualization	v;

	@Override
	public void drawState(State<Point1D> s, Graphics g)
	{
		Rectangle r = g.getClipBounds();
		Point1D size = s.getSize();
		int cellWidth = r.width/size.getCoord();
		for (Point1D p : s) {
			if (s.getCellState(p) == 1) {
				g.setColor(v.getColorRep(1));
				g.fillRect(p.getCoord() * cellWidth, 0, cellWidth, cellWidth);
			}
			else {
				g.setColor(v.getColorRep(0));
				g.fillRect(p.getCoord() * cellWidth, 0, cellWidth, cellWidth);
			}
			g.setColor(Color.white);
			g.drawRect(p.getCoord() * cellWidth, 0, cellWidth, cellWidth);
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
