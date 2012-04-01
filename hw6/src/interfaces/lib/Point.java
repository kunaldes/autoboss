package interfaces.lib;

import java.util.Arrays;


/**
 * An abstract representation of a point, that supports dimensions as large as
 * the maximum array length. This class should be subclassed to represent a
 * concrete dimension of point. Provided are 1D and 2D point classes, but this
 * can be used to create points of more dimensions.
 * 
 * Note: this class has a natural ordering that is inconsistent with equals.
 * this.compareTo(other) != this.equals(other).
 * Instead, this.compareTo(other)==0 implies that this and other are either
 * of different dimensions, or that neither has a  
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 * 
 */
public abstract class Point implements Comparable<Point>
{

	int[] coords;

	/**
	 * Constructs a point with a given dimension.
	 * 
	 * @param dim
	 *            the dimension of the point being constructed.
	 * @throws IllegalArgumentException if dim is non-positive
	 */
	protected Point(int dim)
	{
		if(dim <= 0)
			throw new IllegalArgumentException("Point dimension must be positive");
		coords = new int[dim];
	}

	/**
	 * Gets the coordinate of the {@code dim}th dimension.
	 * 
	 * @param dim
	 *            the dimension, indexed from 0, you want to get the value of
	 * @return the coordinate at the requested dimension.
	 * @throws IllegalArgumentException
	 *             If dim is negative or greater than or equal to the dimension.
	 *             specified in the constructor.
	 */
	protected int getCoord(int dim)
	{
		if (dim < 0 || dim >= coords.length)
		{
			throw new IllegalArgumentException("Invalid Dimension");
		}

		return coords[dim];
	}
	
	/**
	 * Used to set the coordinate of the {@code dim}th dimension. Subclasses
	 * should never allow the user to directly call this. Instead they should
	 * call functions from subclasses like {@code getX} and {@code getY} for
	 * example, from a two dimensional subclass of point.
	 * 
	 * @param dim
	 *            the dimension, indexed from 0, you want to set
	 * @param val
	 *            the value of the dim-th dimension
	 * @throws IllegalArgumentException
	 *             if dim is negative or greater than or equal to the dimension
	 *             of the point
	 */
	protected void setCoord(int dim, int val)
	{
		if (dim < 0 || dim >= coords.length)
		{
			throw new IllegalArgumentException("Invalid Dimension");
		}

		coords[dim] = val;
	}

	/**
	 * Returns the number of dimensions of the current point.
	 * 
	 * @return the number of dimensions of the current point.
	 */
	public int numDimensions()
	{
		return coords.length;
	}

	/**
	 * Checks equality with another point
	 * 
	 * @param other
	 *            the other object to check equality against
	 * @return true if the object {@code other} represents the same point as
	 *         {@code this}
	 */
	@Override
	public boolean equals(Object other)
	{
		if (other instanceof Point)
		{
			Point o = (Point) other;
			if (o.coords.length == coords.length)
			{
				for (int i = 0; i < coords.length; i++)
				{
					if (coords[i] != o.coords[i])
					{
						return false;
					}
				}

				return true;
			}
		}
		return false;
	}

	/**
	 * Forms a deep copy of the point. Follows the same idea as Object.clone()
	 * in the form of a factory method.
	 * 
	 * for any Point p, (p!=p.copy() && p.equals(p.copy())) must be true
	 * furthermore, p.getClass().equals(p.copy().getClass()) should be true
	 * 
	 * @return a copy of the point
	 */
	public abstract Point copy();
	
	@Override
	public int compareTo(Point p)
	{
		if(this.coords.length != p.coords.length)
			return 0;
		else
		{
			int c = Integer.signum(Integer.compare(this.coords[0], p.coords[0]));
			if(c == 0) //strictly speaking unnecessary, but prevents extra checks
				return c;
			for(int i = 1; i < coords.length; i++)
			{
				if(Integer.signum(Integer.compare(this.coords[i], p.coords[i])) != c)
					return 0;
			}
			return c;
		}
	}

	public Point add(Point o)
	{
		if(!this.getClass().isInstance(o))
			throw new IllegalArgumentException("Point addition is only defined for Points of the same class");
		Point p = this.copy();
		for(int i = 0; i<coords.length; i++)
		{
			p.coords[i] += o.coords[i];
		}
		return p;
	}
	
	public Point sub(Point o)
	{
		if(!this.getClass().isInstance(o))
			throw new IllegalArgumentException("Point addition is only defined for Points of the same class");
		Point p = this.copy();
		for(int i = 0; i<coords.length; i++)
		{
			p.coords[i] -= o.coords[i];
		}
		return p;
	}
	
	@Override
	public int hashCode()
	{
		return Arrays.hashCode(coords);
	}
}