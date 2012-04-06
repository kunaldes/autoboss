package example;

import java.util.HashMap;

import framework.interfaces.Rule;
import framework.lib.Neighborhood;
import framework.lib.Point2D;
import framework.lib.State;

public class BriansBrainRule implements Rule<Point2D>
{

	@Override
	public int stepCell(Neighborhood<Point2D> n)
	{
		int center = n.getCellState(getOrigin());
		if(center != 0)
			return center - 1;
		
		int aliveCount = 0;
		for(Point2D p : n)
			aliveCount += n.getCellState(p) == 2 ? 1 : 0;
		
		return (aliveCount == 2) ? 2 : 0;
	}

	@Override
	public Point2D getOrigin()
	{
		return new Point2D(0, 0);
	}

	@Override
	public int getNeighborhoodSize()
	{
		return 1;
	}

	@Override
	public State<Point2D> getInitialState()
	{
		HashMap<Point2D, Integer> state = new HashMap<Point2D, Integer>();
		for(int i = 0; i < 50; i++)
			state.put(new Point2D((int)(Math.random() * 50), (int)(Math.random() * 50)), 2);
		boolean[] wraps = { true, true };
		
		return new State<Point2D>(new Point2D(100, 100), 3, wraps, state);
	}

}
