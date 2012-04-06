package framework.lib;

import example.DefaultViewport2D;
import framework.interfaces.Rule;
import framework.interfaces.Visualization;


/**
 * A concrete implementation of a 2 dimensional simulation.
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 */
public class Simulation2D extends Simulation<Point2D>
{

	/**
	 * Constructs a Simulation2D with the given rule and visualization.
	 * 
	 * @param r the Rule to use in the simulation.
	 * @param v the Visualization to use in the simulation.
	 */
	public Simulation2D(Rule<Point2D> r, Visualization v)
	{
		super(r, v, new DefaultViewport2D());
	}

}
