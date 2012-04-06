package example;

import java.util.HashMap;

import framework.interfaces.Rule;
import framework.lib.Neighborhood;
import framework.lib.Point2D;
import framework.lib.State;

public class Rule110 implements Rule<Point2D>
{

	@Override
	public int stepCell(Neighborhood<Point2D> n)
	{
		boolean s1 = n.getCellState(new Point2D(-1, -1)) == 1;
		boolean s2 = n.getCellState(new Point2D(0, -1)) == 1;
		boolean s3 = n.getCellState(new Point2D(1, -1)) == 1;
		
		if((s1 && s2 && !s3)|| (s1 && !s2 && s3) || 
				(!s1 && s2 && !s3) || (!s1 && !s2 && s3))
			return 1;
		return 0;
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
		state.put(new Point2D(0, 0), 1);
		boolean[] wraps = { true, true };
		
		return new State<Point2D>(new Point2D(100, 100), 2, wraps, state);
	}

}
