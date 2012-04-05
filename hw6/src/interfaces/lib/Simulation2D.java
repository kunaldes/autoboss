package interfaces.lib;

import interfaces.interfaces.Rule;
import interfaces.interfaces.Visualization;

/**
 * A concrete implementation of a 2 dimensional simulation.
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 *
 */
public class Simulation2D extends Simulation<Point2D> {

	/**
	 * Constructs a Simulation2D with the given rule and visualization.
	 * @param r the Rule to use in the simulation.
	 * @param v the Visualization to use in the simulation.
	 */
	public Simulation2D(Rule<Point2D> r, Visualization v) {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}
	/**
	 * Changes the rule to the new rule specified by r. Can be used to change
	 * rules in the GUI.
	 * @param r the new Rule to use.
	 */
	@Override
	public void setRule(Rule<Point2D> r)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public State<Point2D> getState()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
