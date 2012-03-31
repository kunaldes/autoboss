package interfaces.plugin;

import interfaces.interfaces.Rule;
import interfaces.lib.Neighborhood;
import interfaces.lib.Point2D;
import interfaces.lib.State;

public class LifeRule implements Rule<Point2D>
{

	private static final int NeighborHoodSize = 1;

	@Override
	public int stepCell(Neighborhood<Point2D> n)
	{
		int s = n.getCellState(new Point2D(0,0));
		int sum = 0;
		for(Point2D i : n)
		{
			sum += n.getCellState(i);
		}
		sum -= s;
		if(s == 0 && sum == 3)
			return 1;
		else if(s == 1 && (sum == 2 || sum == 3))
			return 1;
		return 0;
	}

	@Override
	public Point2D getOrigin()
	{
		return new Point2D(0,0);
	}

	@Override
	public int getNeighborhoodSize()
	{
		return LifeRule.NeighborHoodSize;
	}

	@Override
	public State<Point2D> getInitialState()
	{
		return new State<Point2D>(new Point2D(5,5), 2);
	}

}
