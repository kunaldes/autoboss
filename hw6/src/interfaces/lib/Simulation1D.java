package interfaces.lib;

import interfaces.interfaces.Rule;
import interfaces.interfaces.Visualization;

/**
 * Gives a concrete simulation for 1 dimensional cellular automata.
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 *
 */
public class Simulation1D extends Simulation<Point1D> {

	/**
	 * Constructs a Simulation for a given ruleset and visualization using the default Viewport
	 * @param r the Rule to abide by
	 * @param v the Visualization specifying colors
	 */
	public Simulation1D(Rule<Point1D> r, Visualization v) {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	/**
	 * Returns the current state of the simulation.
	 * @return the current state of the simulation.
	 */
	@Override
	public State<Point1D> getState()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Changes the rule to the new rule specified by r. Can be used to change
	 * rules in the GUI.
	 * @param r the new Rule to use.
	 */
	@Override
	public void setRule(Rule<Point1D> r)
	{
		// TODO Auto-generated method stub
		
	}

}
