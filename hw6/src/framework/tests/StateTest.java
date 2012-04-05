package framework.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import interfaces.lib.Point1D;
import interfaces.lib.Point2D;
import interfaces.lib.State;

import org.junit.Test;


public class StateTest
{

	@Test
	public void sanityTest1()
	{
		Point1D size = new Point1D(20);
		State<Point1D> st = new State<Point1D>(size, 4);
		assertEquals(1, st.getDimension());
		assertEquals(size, st.getSize());
		assertNotSame(size, st.getSize());
		assertEquals(0, st.getCellState(new Point1D(12)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void sanityTest2()
	{
		Point1D size = new Point1D(-3);
		new State<Point1D>(size, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void sanityTest3()
	{
		Point2D size = new Point2D(1, 1);
		new State<Point2D>(size, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void sanityTest4()
	{
		Point2D size = new Point2D(2, 2);
		boolean[] wraps = { true };
		new State<Point2D>(size, 2, wraps);
	}

	@Test(expected = IllegalArgumentException.class)
	public void sanityTest5()
	{
		Point2D size = new Point2D(2, 2);
		boolean[] wraps = { true, false };
		HashMap<Point2D, Integer> st = new HashMap<Point2D, Integer>();

		// Note: Explicitly setting something to null is equivalent to
		// explicitly setting to zero. Therefore, this should fail because
		// Point2D(2, 1) is out of bounds, since wrapping is ignored here.
		st.put(new Point2D(2, 1), null);
		new State<Point2D>(size, 2, wraps, st);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetWraps1()
	{
		Point1D size = new Point1D(12);
		State<Point1D> st = new State<Point1D>(size, 2);
		boolean[] wraps = new boolean[3];

		st.setWraps(wraps);
	}

	@Test
	public void testSetWraps2()
	{
		Point2D size = new Point2D(15, 15);
		boolean[] wraps = { true, false };
		State<Point2D> st = new State<Point2D>(size, 5, wraps);

		st.setCellState(new Point2D(0, 0), 3);
		st.setCellState(new Point2D(12, 4), 4);
		st.setCellState(new Point2D(20, 5), 2);

		assertEquals(2, st.getCellState(new Point2D(5, 5)));
		assertEquals(4, st.getCellState(new Point2D(27, 4)));
		assertEquals(3, st.getCellState(new Point2D(15, 0)));
		assertEquals(0, st.getCellState(new Point2D(500, 7)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetWrap1()
	{
		Point2D size = new Point2D(5, 5);
		State<Point2D> st = new State<Point2D>(size, 2);
		st.setWrap(2, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetWrap2()
	{
		Point2D size = new Point2D(5, 5);
		State<Point2D> st = new State<Point2D>(size, 2);
		st.setWrap(-1, true);
	}

	@Test
	public void testSetWrap3()
	{
		Point1D size = new Point1D(5);
		State<Point1D> st = new State<Point1D>(size, 2);
		st.setWrap(0, true);
		assertEquals(0, st.getCellState(new Point1D(100)));
		st.setCellState(new Point1D(5), 1);
		assertEquals(1, st.getCellState(new Point1D(0)));
	}

	@Test
	public void testGetDimension()
	{
		Point1D size = new Point1D(0);
		State<Point1D> st = new State<Point1D>(size, 2);
		assertEquals(1, st.getDimension());
	}

	@Test
	public void testGetSize()
	{
		Point1D size = new Point1D(12);
		State<Point1D> st = new State<Point1D>(size, 2);
		assertEquals(size, st.getSize());
		assertEquals(new Point1D(12), st.getSize());
		assertNotSame(size, st.getSize());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSize1()
	{
		Point1D size = new Point1D(12);
		boolean[] wrapping = { true };
		State<Point1D> st = new State<Point1D>(size, 2, wrapping);

		st.setSize(new Point1D(-1));
	}

	@Test
	public void testSetSize2()
	{
		Point1D size = new Point1D(12);
		boolean[] wrapping = { true };
		State<Point1D> st = new State<Point1D>(size, 2, wrapping);

		st.setCellState(new Point1D(12), 1);
		st.setCellState(new Point1D(4), 1);
		st.setCellState(new Point1D(11), 1);

		assertEquals(1, st.getCellState(new Point1D(0)));
		assertEquals(1, st.getCellState(new Point1D(4)));
		assertEquals(1, st.getCellState(new Point1D(11)));

		st.setSize(new Point1D(10));

		assertEquals(0, st.getCellState(new Point1D(11)));
		assertEquals(1, st.getCellState(new Point1D(14)));
		assertEquals(1, st.getCellState(new Point1D(10)));

		st.setSize(new Point1D(12));

		assertEquals(0, st.getCellState(new Point1D(11)));
	}

}
