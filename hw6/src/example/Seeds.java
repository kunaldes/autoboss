package example;

import java.util.HashMap;

import framework.interfaces.Rule;
import framework.lib.Neighborhood;
import framework.lib.Point2D;
import framework.lib.State;

public class Seeds implements Rule<Point2D>
{

	@Override
	public int stepCell(Neighborhood<Point2D> n)
	{
		if(n.getCellState(getOrigin()) == 1)
			return 0;
		
		int aliveCount = 0;
		for(Point2D p : n)
			aliveCount += n.getCellState(p);
		
		return aliveCount == 2 ? 1 : 0; 
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
		state.put(new Point2D(50, 50), 1);
		state.put(new Point2D(51, 50), 1);
		state.put(new Point2D(52, 51), 1);
		
		boolean[] wraps = { true, false };
		
		return new State<Point2D>(new Point2D(100, 100), 2, wraps, state);
	}

}
