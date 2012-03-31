package interfaces.lib;

/**
 * An abstract representation of a point, that supports dimensions as large as the maximum
 * array length. This class should be subclassed to represent a concrete dimension of point.
 * Provided are 1D and 2D point classes, but this can be used to create points of more dimensions.
 * 
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 *
 */
public abstract class Point
{

	private final int[] coords;

	/**
	 * Constructs a point with a given dimension.
	 * @param dim the dimension of the point being constructed.
	 */
	protected Point(int dim)
	{
		coords = new int[dim];
	}

	/**
	 * Gets the coordinate of the {@code dim}th dimension.
	 * @param dim the dimension, indexed from 0, you want to get the value of
	 * @return the coordinate at the requested dimension.
	 * @throws IllegalArgumentException If dim is negative or greater than or equal to the dimension.
	 * 		specified in the constructor.
	 */
	protected int getCoord(int dim)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Used to set the coordinate of the {@code dim}th dimension. Subclasses should never
	 * allow the user to directly call this.
	 * Instead they should call functions from subclasses like {@code getX} and
	 * {@code getY} for example, from a two dimensional subclass of point.
	 *  
	 * @param dim the dimension, indexed from 0, you want to set
	 * @param val
	 * @throws IllegalArgumentException if dim is negative or greater than or equal to the dimension of the point
	 */
	protected void setCoord(int dim, int val)
	{
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	/**
	 * Returns the number of dimensions of the current point.
	 * @return the number of dimensions of the current point.
	 */
	public int numDimensions()
	{
		return coords.length;
	}
	
	/**
	 * Checks equality with another point
	 * @param other the other object to check equality against
	 * @return true if the object {@code other} represents the same point as {@code this}
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Point) {
			Point o = (Point) other;
			if (o.coords.length == coords.length) {
				for (int i = 0; i < coords.length; i++) {
					if (coords[i] != o.coords[i]) {
						return false;
					}
				}
				
				return true;
			}
		}
		return false;
	}

}
