package framework.plugin;

import java.util.HashMap;

import framework.interfaces.Rule;
import framework.lib.Neighborhood;
import framework.lib.Point2D;
import framework.lib.State;


public class LifeRule implements Rule<Point2D>
{

	private static final int	NeighborHoodSize	= 1;

	@Override
	public int stepCell(Neighborhood<Point2D> n)
	{
		int s = n.getCellState(new Point2D(0, 0));
		int sum = 0;
		for (Point2D i : n) {
			sum += n.getCellState(i);
		}
		sum -= s;
		if (s == 0 && sum == 3)
			return 1;
		else if (s == 1 && (sum == 2 || sum == 3)) return 1;
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
		return LifeRule.NeighborHoodSize;
	}

	@Override
	public State<Point2D> getInitialState()
	{
		HashMap<Point2D, Integer> state = new HashMap<Point2D, Integer>();
		
		state.put(new Point2D(1,2), 1);
		state.put(new Point2D(2,2), 1);
		state.put(new Point2D(3,2), 1);
		return new State<Point2D>(new Point2D(10, 10), 2, null, state);
	}

}
