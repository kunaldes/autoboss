package framework.interfaces;

import framework.lib.Neighborhood;
import framework.lib.Point;
import framework.lib.State;


/**
 * Defines a ruleset for a single cellular automaton. The ruleset should be type
 * instantiated with a subclass of Point, which specifies the concrete dimension
 * of the automaton. This class is responsible for specifying a starting state
 * of the world, and giving a function from a neighborhood around a cell to the
 * new state of the cell after one step.
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 * @param <T> A point in the concrete dimension (i.e. 1D, 2D) that the automaton
 *            runs in. Should be a strict subclass of Point.
 */
public interface Rule<T extends Point>
{

	/**
	 * The primary function of the rule that takes a neighborhood of size
	 * specified by the user in getNeighborhoodSize(), and returns a new state
	 * that the center of the neighborhood n should step to.
	 * 
	 * @param n a neighborhood around a cell, which is used to determine the new
	 *            state of the cell.
	 * @return the new state of the center of Neighborhood n
	 */
	int stepCell(Neighborhood<T> n);

	/**
	 * Returns the origin (0,0, etc.) in the coordinate system used by the
	 * ruleset. In a 2-D grid, (0,0) represents the top left corner, and in a
	 * 1-D grid, (0) is the leftmost point.
	 * 
	 * @return
	 */
	public T getOrigin();

	/**
	 * Lets the framework know how large the neighborhood around a cell should
	 * be that is used to determine what the next state of that cell is. For
	 * example, Conway's Game of Life uses a neighborhood size of 1. <br>
	 * <br>
	 * Formally, if the return value is r, the neighborhood around point (x, y)
	 * will be {(a, b) | x-r <= a <= x+r && y-r <= b <= y+r}
	 * 
	 * @return the size (radius) of the neighborhood that this ruleset uses to
	 *         determine the next state.
	 */
	int getNeighborhoodSize();

	/**
	 * Specifies the initial state of the world. The simulation will use this as
	 * the starting state for the simulation. It is possible that this state
	 * could be modified at run time by the end user of the GUI, but this is
	 * required to start the GUI.
	 * 
	 * @return the initial state of the world.
	 */
	public State<T> getInitialState();

}
