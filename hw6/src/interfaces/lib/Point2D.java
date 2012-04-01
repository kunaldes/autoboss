package interfaces.lib;

/**
 * Creates a concrete 2 dimensional point implementation. (0,0) is considered
 * the origin. Gives functions to get the x and y coordinates of the point.
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 * 
 */
public final class Point2D extends Point
{

	/**
	 * Constructs a new Point object representing the point (x, y)
	 * 
	 * @param x
	 *            The x-coordinate of the point being constructed
	 * @param y
	 *            The y-coordinate of the point being constructed
	 */
	public Point2D(int x, int y)
	{
		super(2);
		setCoord(0, x);
		setCoord(1, y);
	}

	/**
	 * Gets the x-coordinate of the given point.
	 * 
	 * @return the x-coordinate
	 */
	public int getX()
	{
		return getCoord(0);
	}

	/**
	 * Gets the y-coordinate of the given point.
	 * 
	 * @return the y-coordinate
	 */
	public int getY()
	{
		return getCoord(1);
	}

	@Override
	public Point copy()
	{
		return new Point2D(getX(), getY());
	}

}
