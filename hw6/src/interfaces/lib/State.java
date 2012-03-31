package interfaces.lib;

import interfaces.interfaces.Rule;

import java.util.HashMap;
import java.util.Iterator;


/**
 * Represents a state of a cellular automaton, supporting an arbitrary
 * number of dimensions. The dimension is specified by the type parameter.
 * It supports any finite size for the length of each axis, but it must
 * be finite. The nth axis goes from 0 to the value of the nth axis of the
 * point specified as the size. It also supports wrapping around (going back to
 * 0 after reaching the end of the axis) on any or all of
 * the axes.
 * <br><br>
 * Lets you iterate over all of the points in the state 
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 *
 * @param <T> Specifies the number of dimensions this state should represent.
 * 		 Should be a strict subclass of Point.
 */
public class State<T extends Point> implements Iterable<T>
{

	private HashMap<T, Integer> grid;
	private T size;
	private boolean[] wraps;
	private int numStates;

	/**
	 * Creates a new State with default no wrapping
	 * @param size The point farthest from the origin, giving the bounds
	 * @param numStates The total number of states a cell can be in. For example,
	 * 		Conway's Game of Life has 2 states, alive and dead.
	 */
	public State(T size, int numStates)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Creates a new State with wrapping as specified by the boolean array.
	 * The array is such that if wraps[i] is true, then the ith axis will
	 * wrap around.
	 * 
	 * @param size The point farthest from the origin, giving the bounds
	 * @param numStates The total number of states a cell can be in. For example,
	 * 		Conway's Game of Life has 2 states, alive and dead.
	 * @param wraps A boolean array specifying which axes should wrap around.
	 */
	public State(T size, int numStates, boolean[] wraps)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Creates a new State with wrapping as specified by the boolean array,
	 * and the given initial state.
	 * The array is such that if wraps[i] is true, then the ith axis will
	 * wrap around.
	 * In the HashMap grid, if a point maps to nothing, we assume it has
	 * state 0.
	 * 
	 * @param size The point farthest from the origin, giving the bounds
	 * @param numStates The total number of states a cell can be in. For example,
	 * 		Conway's Game of Life has 2 states, alive and dead.
	 * @param wraps A boolean array specifying which axes should wrap around.
	 * @param grid The initial state.
	 * @throws IllegalArgumentException if any of the states mapped to in grid are
	 * negative, or greater than or equal to numStates
	 */
	public State(T size, int numStates, boolean[] wraps,
			HashMap<T, Integer> grid)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Allows you to set whether each axis wraps all at once.
	 * @param wraps a boolean array where the ith value determines whether the ith axis wraps around
	 * @throws IllegalArgumentException if wraps.length != the number of dimensions of the state
	 */
	public void setWraps(boolean[] wraps)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Sets whether axis number {@code dimension} wraps around or not
	 * @param dimension the axis
	 * @param wrap true if you want the axis to wrap, false if not
	 * @throws IllegalArgumentException if dimension is negative or >= the dimension of the state.
	 */
	public void setWrap(int dimension, boolean wrap)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Gets the number of axes this state holds a representation for.
	 * 
	 * @return the number of dimensions (or the number of axes)
	 */
	public int getDimension()
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Returns the point farthest from the origin that this state can hold.
	 * Each of the coordinates of points in this state must have coordinate number
	 * i have value between 0 and getSize()'s ith coordinate, inclusive.
	 * @return the farthest point from the origin
	 */
	public T getSize()
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Sets the size of the state to the given size. If this is larger than the
	 * previous size, it will map all new cells to state 0. If it is smaller, all
	 * cells outside of the new bounds will be thrown out.
	 * 
	 * @param size the new dimensions of the State
	 */
	public void setSize(T size)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Sets the state based on a mapping between T and states (stored as integers).
	 * @param grid
	 * @throws IllegalArgumentException if grid specifies values outside of the size or specifies invalid states
	 */
	public void setState(HashMap<T, Integer> grid)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Sets the state of the given cell to the given state. 
	 * @param cell The cell you want to change the state of.
	 * @param state the state to which you want to change the cell.
	 * @throws IllegalArgumentException if cell is not in the bounds of this State, or if state is negative or
	 * greater than or equal to the number of allowed states.
	 */
	public void setCellState(T cell, int state)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Gets the state of the requested cell.
	 * @param cell the coordinates of the requested cell.
	 * @return The state of the requested cell. Defaults to 0 if it was never explicitly stated.
	 * @throws IllegalArgumentException if cell is not in the bounds of this State
	 */
	public int getCellState(T cell)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Gets a neighborhood for a given cell, with the given radius.
	 * If wrapping is set to false, values outside of the bounds of size
	 * have state 0
	 * 
	 * @param cell the cell to center the neighborhood around.
	 * @param radius the radius of the neighborhood
	 * @return The requested neighborhood 
	 */
	public Neighborhood<T> getNeighborhood(T cell, int radius)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Steps the world once based on the rules specified in r
	 * @param r the rule used to step the state once.
	 */
	public void step(Rule<T> r)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}
	
	/**
	 * Iterates over all of the Points inside this State.
	 * @return an iterator for the Points inside the State
	 */
	@Override
	public Iterator<T> iterator() 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Private helper method that takes a point and mods dimension i of p by the dimension i
	 * of size, if wraps[i] is true 
	 * @param p the point to mod 
	 * @return point p, selectively modded by size
	 */
	private Point modPoint(Point p)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

}
