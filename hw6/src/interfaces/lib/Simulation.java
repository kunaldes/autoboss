package interfaces.lib;

import interfaces.interfaces.Rule;
import interfaces.interfaces.Viewport;
import interfaces.interfaces.Visualization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Defines a simulation for a cellular automaton. This class handles
 * the GUI elements for simulations of arbitrary dimension. Implementations
 * are given for 1 and 2 dimensional simulations, but one can easily create
 * a simulation for a higher dimension. <br>
 * <br>
 * This class offers a factory method that can create a simulation for
 * {@code Rule<Point1D>} and {@code Rule<Point2D>}, as well as a way to
 * create simulations for other types of Points.
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 * 
 * @param <T>
 *            A point that specifies a concrete dimension that this simulation
 *            will support. Should be a strict subclass of Point.
 */
public abstract class Simulation<T extends Point> implements ActionListener
{

	private Viewport<T> viewport;
	private State<T> state;
	private Rule<T> rule;
	private boolean running = false;

	/**
	 * This is a factory method takes a rule and a visualization and produces a
	 * Simulation suitable for viewing the automaton.
	 * 
	 * @param r
	 *            A Rule, which must be of Point1D or Point2D, otherwise this
	 *            method will fail
	 * @param v
	 *            a visualization to use for coloring the states
	 * @return a Simulation that can be used to visualize a cellular automaton.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Point> Simulation<T> getSimulation(Rule<T> r,
			Visualization v)
	{
		Class<? extends Point> c = r.getOrigin().getClass();
		if(c.isInstance(Point1D.class))
			return (Simulation<T>) new Simulation1D((Rule<Point1D>) r, v);
		else if(c.isInstance(Point2D.class))
			return (Simulation<T>) new Simulation2D((Rule<Point2D>) r, v);
		else
			throw new RuntimeException("Illegal Point");
	}

	/**
	 * Constructs a simulation.
	 */
	protected Simulation()
	{
		//Gui Stuffs
	}

	/**
	 * Causes the simulation to move one step forward according to the given
	 * rules.
	 * If no rules are set, this method will fail with an IllegalStateException.
	 * 
	 * @throws IllegalStateException
	 *             If no Rule has been given to this simulation
	 */
	protected void step()
	{
		this.state.step(this.rule);
	}

	/**
	 * Calls {@code step()} times number of times.
	 * 
	 * @param times
	 *            the number of times to call {@code step()}
	 */
	protected void step(int times)
	{
		for (int i = 0; i < times; i++)
		{
			this.state.step(this.rule);
		}
	}

	/**
	 * Called by the client to start the GUI. If the rules haven't been set,
	 * this method will fail. The factory method will automatically set the
	 * rules, so
	 * the only time this will fail is if you create your own subclass of
	 * Simulation
	 */
	public void run()
	{
		this.running = true;
		while (this.running)
		{
			this.state.step(this.rule);
		}
	}

	/**
	 * Allows you to swap Viewports of the program out.
	 * 
	 * @param v
	 *            a Viewport you want to use to visualize the data.
	 */
	public void setViewport(Viewport<T> v)
	{
		this.viewport = v;
	}

	/**
	 * Allows you to swap out a Visualization (color picker)
	 * 
	 * @param v
	 *            the new visualization
	 */
	public void setVisualization(Visualization v)
	{
		this.viewport.setVisualization(v);
	}

	/**
	 * This method allows for on-the-fly switching with rules, as well as
	 * setting the initial rule.
	 * 
	 * @param r
	 *            the new Rule to use for the simulation.
	 */
	public void setRule(Rule<T> r)
	{
		rule = r;
	}

	/**
	 * Gets the current state of the simulation
	 * @return the current state of the simulation
	 */
	public abstract State<T> getState();
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand()) {
		case "stop":
			this.running = false;
			break;
		default:
			break;
		}
	}
}
