package framework.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import framework.lib.Point;
import framework.lib.Point1D;
import framework.lib.Point2D;

import org.junit.Test;


public class PointTests
{

	private void checkDim(Point p)
	{
		int dims = p.numDimensions();

		if (p instanceof Point1D) {
			assertTrue(dims == 1);
		}
		else if (p instanceof Point2D) {
			assertTrue(dims == 2);
		}
		else {
			assertTrue(dims >= 0);
		}
	}

	private boolean pointsEqual(Point p1, Point p2)
	{
		boolean eq1 = p1.equals(p2);
		boolean eq2 = p1.hashCode() == p2.hashCode();
		boolean eq3 = p1.compareTo(p2) == 0;

		if (p1.numDimensions() != p2.numDimensions()) assertTrue(eq3 && !eq1);
		if (eq1) {
			assertTrue(eq2); // hashCodes must be equal if points equal
			assertTrue(eq3); // compareTo must give 0
		}
		// some checks for good measure
		checkDim(p1);
		checkDim(p2);

		return eq1;
	}

	@Test
	public void testCopy1D()
	{
		Point1D p1 = new Point1D(0);
		Point1D p2 = new Point1D(-5);
		Point1D p3 = new Point1D(999999);

		assertTrue(pointsEqual(p1, p1.copy()));
		assertTrue(pointsEqual(p1.copy(), p1));

		Point1D copy = p1;
		for (int i = 0; i < 50; i++) {
			copy = (Point1D) copy.copy();
			assertTrue(pointsEqual(copy, p1));
			assertFalse(pointsEqual(copy, p2));
			assertFalse(pointsEqual(copy, p3));

			checkDim(copy);
		}
	}

	@Test
	public void testCopy2D()
	{
		Point2D p1 = new Point2D(42, -1);
		Point2D p2 = new Point2D(0, 0);
		Point2D p3 = new Point2D(999999, 9999);

		assertTrue(pointsEqual(p1, p1.copy()));
		assertTrue(pointsEqual(p1.copy(), p1));

		Point2D copy = p1;
		for (int i = 0; i < 50; i++) {
			copy = (Point2D) copy.copy();
			assertTrue(pointsEqual(copy, copy));
			assertTrue(pointsEqual(copy, p1));
			assertFalse(pointsEqual(copy, p2));
			assertFalse(pointsEqual(copy, p3));

			checkDim(copy);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdd1D()
	{
		Point1D p1 = new Point1D(-3);
		Point1D p2 = new Point1D(1);
		Point1D p3 = new Point1D(945345345);

		assertTrue(pointsEqual(p1.add(p2), new Point1D(-2)));
		assertTrue(pointsEqual(p2.add(p1), new Point1D(-2)));
		assertTrue(pointsEqual(p1.add(p3), new Point1D(945345342)));
		assertTrue(pointsEqual(p2.add(p3), new Point1D(945345346)));

		p1.add(new Point2D(0, 0));
	}


	@Test(expected = IllegalArgumentException.class)
	public void testAdd2D()
	{
		Point2D p1 = new Point2D(9, -3);
		Point2D p2 = new Point2D(0, 99);
		Point2D p3 = new Point2D(-23233223, 435334543);

		assertTrue(pointsEqual(p1.add(p2), new Point2D(9, 96)));
		assertTrue(pointsEqual(p2.add(p1), new Point2D(9, 96)));
		assertTrue(pointsEqual(p1.add(p3), new Point2D(-23233214, 435334540)));
		assertTrue(pointsEqual(p2.add(p3), new Point2D(-23233223, 435334642)));

		try {
			p1.add(new Point1D(0));
			fail("should be exception on prev line");
		}
		catch (IllegalArgumentException e) {
		}

		p1.add(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSub1D()
	{
		Point1D p1 = new Point1D(-3);
		Point1D p2 = new Point1D(1);
		Point1D p3 = new Point1D(945345345);

		assertTrue(pointsEqual(p1.sub(p2), new Point1D(-4)));
		assertTrue(pointsEqual(p2.sub(p1), new Point1D(4)));
		assertTrue(pointsEqual(p1.sub(p3), new Point1D(-945345348)));
		assertTrue(pointsEqual(p2.sub(p3), new Point1D(-945345344)));

		try {
			p1.sub(new Point2D(0, 0));
			fail("should be exception on prev line");
		}
		catch (IllegalArgumentException e) {
		}

		p1.add(null);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testSub2D()
	{
		Point2D p1 = new Point2D(9, -3);
		Point2D p2 = new Point2D(0, 99);
		Point2D p3 = new Point2D(-23233223, 435334543);

		assertTrue(pointsEqual(p1.sub(p2), new Point2D(9, -102)));
		assertTrue(pointsEqual(p2.sub(p1), new Point2D(-9, 102)));
		assertTrue(pointsEqual(p1.sub(p3), new Point2D(23233232, -435334546)));
		assertTrue(pointsEqual(p2.sub(p3), new Point2D(23233223, -435334444)));

		try {
			p1.sub(new Point1D(0));
			fail("should be exception on prev line");
		}
		catch (IllegalArgumentException e) {
		}
		
		p1.add(null);
	}

	@Test
	public void testGetXGetY()
	{
		Point2D p1 = new Point2D(9, -3);
		Point2D p2 = new Point2D(0, 99);

		assertEquals(9, p1.getX());
		assertEquals(-3, p1.getY());
		assertEquals(0, p2.getX());
		assertEquals(99, p2.getY());
	}

	@Test
	public void testGetCoord()
	{
		Point1D p1 = new Point1D(-3);
		Point1D p2 = new Point1D(1);

		assertEquals(-3, p1.getCoord());
		assertEquals(1, p2.getCoord());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompareTo1D()
	{
		Point1D p1 = new Point1D(-3);
		Point1D p2 = new Point1D(1);

		assertEquals(0, p1.compareTo(p1));
		assertEquals(-1, p1.compareTo(p2));
		assertEquals(1, p2.compareTo(p1));

		p1.compareTo(null);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testCompareTo2D()
	{
		Point2D p1 = new Point2D(1, -3);
		Point2D p2 = new Point2D(2, 99);

		assertEquals(0, p1.compareTo(p1));
		assertEquals(-1, p1.compareTo(p2));
		assertEquals(1, p2.compareTo(p1));

		p1.compareTo(null);
	}
}
