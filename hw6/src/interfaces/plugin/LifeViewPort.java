package interfaces.plugin;

import interfaces.interfaces.Viewport;
import interfaces.interfaces.Visualization;
import interfaces.lib.Point2D;
import interfaces.lib.State;

import java.awt.Graphics;

import javax.swing.JToolBar;


public class LifeViewPort implements Viewport<Point2D> {
	private Visualization v;
	
	@Override
	public void drawState(State<Point2D> s, Graphics g) {
		g.setColor(v.getColorRep(1));
		int cellWidth = 10;
		for(Point2D p : s)
		{
			if(s.getCellState(p) == 1)
				g.fillRect(p.getX()*cellWidth, p.getY()*cellWidth, cellWidth, cellWidth);
		}
	}

	@Override
	public void setVisualization(Visualization v) {
		this.v = v;
	}

	@Override
	public JToolBar getToolbar()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
