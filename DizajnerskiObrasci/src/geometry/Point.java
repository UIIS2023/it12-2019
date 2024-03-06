package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape implements Cloneable {

	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private boolean selected;
	private Color color = Color.black;

	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, boolean selected) {
		this(x, y);
		this.setSelected(selected);
	}

	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected);
		this.setColor(color);
	}

	public double dinstance(int x, int y) {
		double dx = this.x - x;
		double dy = this.y - y;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point pocetak = new Point(0, 0);
			return (int) (this.dinstance(pocetak.getX(), pocetak.getY())
					- ((Point) o).dinstance(pocetak.getX(), pocetak.getY()));
		}
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.x - 2, this.y, this.x + 2, this.y);
		g.drawLine(this.x, this.y - 2, this.x, this.y + 2);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.x - 3, this.y - 3, 6, 6);
		}

	}

	public boolean contains(int x, int y) {
		return this.dinstance(x, y) <= 3;
		/*
		 * if (this.distance(x, y) <= 3) { return true; } else { return false; }
		 */
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point pomocna = (Point) obj;
			if (this.x == pomocna.x && this.y == pomocna.y) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public Point clone() {
		Point p = new Point();
		
		p.setX(this.getX());
		p.setY(this.getY());
		p.setColor(this.getColor());
		
		return p;
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.x = this.x + byX;
		this.y += byY;
	}

	public int getX() {
		return this.x;
	}

	public int setX(int x) {
		return this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public int setY(int y) {
		return this.y = y;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public String toString() {
		// Point: x y color: RGB
		return "Point: " + getX()+" "+getY() + " Color: " + getColor().getRGB();
	}

}
