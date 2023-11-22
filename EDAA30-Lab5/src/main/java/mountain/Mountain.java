package mountain;

import java.util.HashMap;

import fractal.*;

public class Mountain extends Fractal {
	private Point a, b, c;
	private Double dev;
	private HashMap<Side, Point> sideMap = new HashMap<Side, Point>();

	/**
	 * Creates a Mountain.
	 * 
	 * @param dev the deviation of the mountain sides
	 * @param a   the point for the first corner
	 * @param b   the point for the second corner
	 * @param c   the point for the third corner
	 */
	public Mountain(Double dev, Point a, Point b, Point c) {
		super();
		this.dev = dev;
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * Returns the title.
	 * 
	 * @return the title
	 */
	@Override
	public String getTitle() {
		return "Mountain Fractal";
	}

	/**
	 * Draws the fractal.
	 * 
	 * @param turtle the turtle graphic object
	 */
	@Override
	public void draw(TurtleGraphics turtle) {
		fractalTriangle(turtle, super.order, dev, a, b, c);
	}

	/**
	 * Returns the midpoint of the side. If the midpoint is already calculated, it
	 * is returned from the map.
	 * 
	 * @param side
	 * @param dev
	 * @return
	 */
	private Point getMidPoint(Side side, double dev) {
		if (sideMap.containsKey(side)) {
			return sideMap.get(side);
		} else {
			// Calculate the midpoint and add it to the map
			Point mid = new Point((side.getA().getX() + side.getB().getX()) / 2,
					(side.getA().getY() + side.getB().getY() + (int) RandomUtilities.randFunc(dev)) / 2);
			sideMap.put(side, mid);
			return mid;
		}
	}

	/*
	 * Reursive method: Draws a recursive triangle.
	 */
	private void fractalTriangle(TurtleGraphics turtle, int order, double dev, Point a, Point b, Point c) {
		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.penDown();
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
			turtle.penUp();
		} else {

			Side ab = new Side(a, b);
			Side bc = new Side(b, c);
			Side ca = new Side(c, a);

			Point abMid = getMidPoint(ab, dev);
			Point bcMid = getMidPoint(bc, dev);
			Point caMid = getMidPoint(ca, dev);

			fractalTriangle(turtle, order - 1, dev / 2, a, abMid, caMid);
			fractalTriangle(turtle, order - 1, dev / 2, abMid, b, bcMid);
			fractalTriangle(turtle, order - 1, dev / 2, caMid, bcMid, c);
			fractalTriangle(turtle, order - 1, dev / 2, abMid, bcMid, caMid);
		}
	}
}
