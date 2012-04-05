package interfaces.lib;

/**
 * Defines a neighborhood around a given type of point, with a given radius.
 * This is used by the Rule objects to get all the neighbors around a given
 * cell, and eventually determine the new state of the center cell. The
 * neighborhood's origin - (0,0) in 2D - is the cell you are focusing on, and
 * all points around it are indexed with a relative point. For example, if you
 * have a Point2D neighborhood of radius 1, you can request values between with
 * x values between -1 and 1 inclusive, and y values between -1 and 1 inclusive. <br>
 * <br>
 * You can also iterate over all of the points in the neighborhood,
 * <i>including</i> the origin.
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 * @param <T> The type of point of the coordinate system.
 */
public interface Neighborhood<T extends Point> extends Iterable<T>
{

	/**
	 * This function gets the state of a cell. If you're in a 2 dimensional
	 * grid, requesting point (0,0) will get the point at the center of the
	 * neighborhood, and requesting (-1,-1) will get one one to the right and
	 * one up from the center.
	 * 
	 * @param relPoint a point indexed relative to the center, which is the
	 *            origin
	 * @return The state of the requested point.
	 * @throws IllegalArgumentException If the given point has any coordinate
	 *             with absolute value greater than the radius.
	 */
	public int getCellState(T relPoint);

	/**
	 * This function returns the radius of the given Neighborhood.
	 * 
	 * @return the radius of the current neighborhood
	 */
	public int getRadius();

}
