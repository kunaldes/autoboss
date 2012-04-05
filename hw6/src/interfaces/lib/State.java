package interfaces.lib;

import interfaces.interfaces.Rule;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Represents a state of a cellular automaton, supporting an arbitrary
 * number of dimensions. The dimension is specified by the type parameter.
 * It supports any finite size for the length of each axis, but it must
 * be finite. The nth axis goes from 0 to the value of the nth axis of the
 * point specified as the size. It also supports wrapping around (going back to
 * 0 after reaching the end of the axis) on any or all of
 * the axes. <br>
 * <br>
 * Lets you iterate over all of the points in the state
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 * 
 * @param <T>
 *            Specifies the number of dimensions this state should represent.
 *            Should be a strict subclass of Point.
 */
public class State<T extends Point> implements Iterable<T>
{

	private class Hood implements Neighborhood<T>
	{
		Point offset;
		int radius;

		public Hood(Point p, int radius)
		{
			offset = p.copy();
			this.radius = radius;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Iterator<T> iterator()
		{
			T p1 = (T) offset.copy();
			T p2 = (T) offset.copy();
			for (int i = 0; i < p1.numDimensions(); i++)
			{
				p1.setCoord(i, -radius);
				p2.setCoord(i, radius);
			}
			return new PointIterator(p1, p2);
		}

		@SuppressWarnings("unchecked")
		@Override
		public int getCellState(T relPoint)
		{
			T p = (T) relPoint.add(offset);
			p = modPoint(p);
			return grid.get(p);
		}

		@Override
		public int getRadius()
		{
			return radius;
		}

	}

	private class PointIterator implements Iterator<T>
	{
		Point p;
		Point max;

		@SuppressWarnings("unchecked")
		private PointIterator(T max)
		{
			T p = (T) max.copy();
			for (int i = 0; i < max.numDimensions(); i++)
				p.setCoord(i, 0);
			this.p = p;
			this.max = max.copy();
		}

		private PointIterator(T start, T max)
		{
			this.p = start.copy();
			this.max = max.copy();
		}

		@Override
		public boolean hasNext()
		{
			if (p.compareTo(max) < 1)
				return true;
			return false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next()
		{
			int i = 0;
			while (p.getCoord(i) + 1 >= max.getCoord(i))
			{
				i++;
				if (i >= p.numDimensions())
					throw new NoSuchElementException(
							"There are no more elements");
			}
			p.setCoord(i, p.getCoord(i) + 1);
			return (T) p.copy();
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}

	}

	private HashMap<T, Integer> grid;
	private T size;
	private boolean[] wraps;
	private int numStates;

	/**
	 * Creates a new State with default no wrapping
	 * 
	 * @param size
	 *            The point farthest from the origin, giving the bounds
	 * @param numStates
	 *            The total number of states a cell can be in. For example,
	 *            Conway's Game of Life has 2 states, alive and dead.
	 */
	public State(T size, int numStates)
	{
		this.size = size;
		this.numStates = numStates;
		this.wraps = new boolean[size.numDimensions()];
		this.grid = new HashMap<T, Integer>();
	}

	/**
	 * Creates a new State with wrapping as specified by the boolean array.
	 * The array is such that if wraps[i] is true, then the ith axis will
	 * wrap around.
	 * 
	 * @param size
	 *            The point farthest from the origin, giving the bounds
	 * @param numStates
	 *            The total number of states a cell can be in. For example,
	 *            Conway's Game of Life has 2 states, alive and dead.
	 * @param wraps
	 *            A boolean array specifying which axes should wrap around.
	 * @throws IllegalArgumentException
	 *             if size.numDimensions() != wraps.length
	 */
	public State(T size, int numStates, boolean[] wraps)
	{
		if (size.numDimensions() != wraps.length)
		{
			throw new IllegalArgumentException(
					"Dimension of point does not match dimension of wrapping");
		}
		this.size = size;
		this.numStates = numStates;
		this.wraps = wraps;
		this.grid = new HashMap<T, Integer>();
	}

	/**
	 * Creates a new State with wrapping as specified by the boolean array,
	 * and the given initial state.
	 * The array is such that if wraps[i] is true, then the ith axis will
	 * wrap around.
	 * In the HashMap grid, if a point maps to nothing, we assume it has
	 * state 0.
	 * 
	 * @param size
	 *            The point farthest from the origin, giving the bounds
	 * @param numStates
	 *            The total number of states a cell can be in. For example,
	 *            Conway's Game of Life has 2 states, alive and dead.
	 * @param wraps
	 *            A boolean array specifying which axes should wrap around.
	 * @param grid
	 *            The initial state.
	 * @throws IllegalArgumentException
	 *             if any of the states mapped to in grid are
	 *             negative, or greater than or equal to numStates or
	 *             size.numDimensions() != wraps.length
	 */
	public State(T size, int numStates, boolean[] wraps,
			HashMap<T, Integer> grid)
	{
		if (size.numDimensions() != wraps.length)
		{
			throw new IllegalArgumentException(
					"Dimension of point does not match dimension of wrapping");
		}
		else
		{
			for (T p : grid.keySet())
			{
				int i = grid.get(p);
				if (i < 0 || i > numStates)
					throw new IllegalArgumentException("Invalid state in grid");
			}
		}
		this.size = size;
		this.numStates = numStates;
		this.wraps = wraps;
		this.grid = grid;
	}

	/**
	 * Allows you to set whether each axis wraps all at once.
	 * 
	 * @param wraps
	 *            a boolean array where the ith value determines whether the ith
	 *            axis wraps around
	 * @throws IllegalArgumentException
	 *             if wraps.length != the number of dimensions of the state
	 */
	public void setWraps(boolean[] wraps)
	{
		if (wraps.length != size.numDimensions())
			throw new IllegalArgumentException(
					"Does not match the state's dimensions");
		this.wraps = wraps;
	}

	/**
	 * Sets whether axis number {@code dimension} wraps around or not
	 * 
	 * @param dimension
	 *            the axis
	 * @param wrap
	 *            true if you want the axis to wrap, false if not
	 * @throws IllegalArgumentException
	 *             if dimension is negative or >= the dimension of the state.
	 */
	public void setWrap(int dimension, boolean wrap)
	{
		try
		{
			wraps[dimension] = wrap;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			throw new IllegalArgumentException(
					"dimension was not within bounds of state's dimensions");
		}
	}

	/**
	 * Gets the number of axes this state holds a representation for.
	 * 
	 * @return the number of dimensions (or the number of axes)
	 */
	public int getDimension()
	{
		return size.numDimensions();
	}

	/**
	 * Returns the point farthest from the origin that this state can hold.
	 * Each of the coordinates of points in this state must have coordinate
	 * number
	 * i have value between 0 and getSize()'s ith coordinate, inclusive.
	 * 
	 * @return the farthest point from the origin
	 * @throws RuntimeException
	 *             if T implements copy incorrectly
	 */
	@SuppressWarnings("unchecked")
	public T getSize()
	{
		Point s = size.copy();
		if (size.getClass().isInstance(s))
			return (T) size.copy();
		else
			throw new RuntimeException(
					"Point subclass incorrectly implements copy method");
	}

	/**
	 * Sets the size of the state to the given size. If this is larger than the
	 * previous size, it will map all new cells to state 0. If it is smaller,
	 * all
	 * cells outside of the new bounds will be thrown out.
	 * 
	 * @param size
	 *            the new dimensions of the State
	 */
	public void setSize(T size)
	{
		this.size = size;
		Iterator<T> i = grid.keySet().iterator();
		while (i.hasNext())
		{
			T p = i.next();
			if (p.compareTo(size) >= 0)
				i.remove();
		}
	}

	/**
	 * Sets the state based on a mapping between T and states (stored as
	 * integers).
	 * 
	 * @param grid
	 * @throws IllegalArgumentException
	 *             if grid specifies values outside of the size or specifies
	 *             invalid states
	 */
	public void setState(HashMap<T, Integer> grid)
	{
		Iterator<T> i = grid.keySet().iterator();
		while (i.hasNext())
		{
			T p = i.next();
			if (p.compareTo(size) >= 0)
				throw new IllegalArgumentException(
						"Grid contains points that are not in bounds");
		}
	}

	/**
	 * Sets the state of the given cell to the given state.
	 * 
	 * @param cell
	 *            The cell you want to change the state of.
	 * @param state
	 *            the state to which you want to change the cell.
	 * @throws IllegalArgumentException
	 *             if cell is not in the bounds of this State, or if state is
	 *             negative or
	 *             greater than or equal to the number of allowed states.
	 *             or if Cell is of the wrong number of dimensions
	 */
	public void setCellState(T cell, int state)
	{
		if (cell.numDimensions() != this.size.numDimensions())
			throw new IllegalArgumentException(
					"Point is of different dimensions");
		if (state < 0 || state > this.numStates)
			throw new IllegalArgumentException("State is out of bounds");
		this.grid.put(cell, state);
	}

	/**
	 * Gets the state of the requested cell.
	 * 
	 * @param cell
	 *            the coordinates of the requested cell.
	 * @return The state of the requested cell. Defaults to 0 if it was never
	 *         explicitly stated.
	 * @throws IllegalArgumentException
	 *             if cell is not in the bounds of this State
	 */
	public int getCellState(T cell)
	{
		if (cell.compareTo(size) >= 0)
			throw new IllegalArgumentException("Point is not in bounds");
		Integer i = grid.get(cell);
		if (i == null)
			return 0;
		else
			return i;
	}

	/**
	 * Gets a neighborhood for a given cell, with the given radius.
	 * If wrapping is set to false, values outside of the bounds of size
	 * have state 0
	 * 
	 * @param cell
	 *            the cell to center the neighborhood around.
	 * @param radius
	 *            the radius of the neighborhood
	 * @return The requested neighborhood
	 */
	public Neighborhood<T> getNeighborhood(T cell, int radius)
	{
		return new Hood(cell, radius);
	}

	/**
	 * Steps the world once based on the rules specified in r
	 * 
	 * @param r
	 *            the rule used to step the state once.
	 */
	public void step(Rule<T> r)
	{
		for (T p : this)
		{
			int i = r.stepCell(getNeighborhood(p, r.getNeighborhoodSize()));
			if (i != 0)
			{
				grid.put(p, i);
			}
			else if (grid.containsKey(p))
			{
				grid.remove(p);
			}
		}
	}

	/**
	 * Iterates over all of the Points inside this State.
	 * 
	 * @return an iterator for the Points inside the State
	 */
	@Override
	public Iterator<T> iterator()
	{
		return new PointIterator(size);
	}

	/**
	 * Private helper method that takes a point and mods dimension i of p by the
	 * dimension i
	 * of size, if wraps[i] is true
	 * 
	 * @param ret
	 *            the point to mod
	 * @return point p, selectively modded by size
	 */
	@SuppressWarnings("unchecked")
	private T modPoint(T p)
	{
		T ret = (T) p.copy();
		for (int i = 0; i < ret.numDimensions(); i++)
		{
			if (wraps[i])
			{
				int mod = ret.getCoord(i) % size.getCoord(i);
				mod = (mod < 0) ? mod + size.getCoord(i) : mod;
				ret.setCoord(i, mod);
			}
		}
		return ret;
	}

}
