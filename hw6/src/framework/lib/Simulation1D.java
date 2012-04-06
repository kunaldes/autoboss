package framework.lib;

import example.DefaultViewport1D;
import framework.interfaces.Rule;
import framework.interfaces.Visualization;


/**
 * Gives a concrete simulation for 1 dimensional cellular automata.
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 */
public class Simulation1D extends Simulation<Point1D>
{

	/**
	 * Constructs a Simulation for a given ruleset and visualization using the
	 * default Viewport
	 * 
	 * @param r the Rule to abide by
	 * @param v the Visualization specifying colors
	 */
	public Simulation1D(Rule<Point1D> r, Visualization v)
	{
		super(r, v, new DefaultViewport1D());
	}

}
