package interfaces.lib;

import interfaces.interfaces.Rule;
import interfaces.interfaces.Viewport;
import interfaces.interfaces.Visualization;

/**
 * Defines a simulation for a cellular automaton. This class handles
 * the GUI elements for simulations of arbitrary dimension. Implementations
 * are given for 1 and 2 dimensional simulations, but one can easily create
 * a simulation for a higher dimension.
 * <br><br>
 * This class offers a factory method that can create a simulation for
 * {@code Rule<Point1D>} and {@code Rule<Point2D>}, as well as a way to
 * create simulations for other types of Points.
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 *
 * @param <T> A point that specifies a concrete dimension that this simulation
 * 		will support. Should be a strict subclass of Point.
 */
public abstract class Simulation <T extends Point> 
{

	/**
	 * This is a factory method takes a rule and a visualization and produces a
	 * Simulation suitable for viewing the automaton.
	 * 
	 * @param r A Rule, which must be of Point1D or Point2D, otherwise this method will fail 
	 * @param v a visualization to use for coloring the states
	 * @return a Simulation that can be used to visualize a cellular automaton.
	 */
	public static <T extends Point> Simulation<T> getSimulation(Rule<T> r,
			Visualization v)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Constructs a simulation.
	 */
	protected Simulation()
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Causes the simulation to move one step forward according to the given rules.
	 * If no rules are set, this method will fail with an IllegalStateException.
	 * @throws IllegalStateException If no Rule has been given to this simulation
	 */
	protected void step(){
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Calls {@code step()} times number of times.
	 * @param times the number of times to call {@code step()}
	 */
	protected void step(int times)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Called by the client to start the GUI. If the rules haven't been set,
	 * this method will fail. The factory method will automatically set the rules, so
	 * the only time this will fail is if you create your own subclass of Simulation
	 */
	public void run()
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Allows you to swap Viewports of the program out.
	 * @param v a Viewport you want to use to visualize the data.
	 */
	public void setViewport(Viewport<T> v)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Allows you to swap out a Visualization (color picker)
	 * @param v the new visualization
	 */
	public void setVisualization(Visualization v)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Gets the current state of the simulation
	 * @return the current state of the simulation
	 */
	public abstract State<T> getState();

	/**
	 * This method allows for on-the-fly switching with rules, as well as
	 * setting the initial rule. 
	 * @param r the new Rule to use for the simulation.
	 */
	public abstract void setRule(Rule<T> r);

}
