package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape implements Cloneable{

	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;
	private boolean selected;
	private Color color = Color.black;

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, Color color) {
		this(startPoint, endPoint);
		this.color = color;
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		this.setSelected(selected);
	}

	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		this.color = color;
	}

	public Line() {

	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Line) {
			return (int) (this.length() - ((Line) o).length());
		}
		return 0;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.startPoint.getX() - 3, this.startPoint.getY() - 3, 6, 6);
			g.drawRect(this.endPoint.getX() - 3, this.endPoint.getY() - 3, 6, 6);
			g.drawRect(this.middleOfLine().getX() - 3, this.middleOfLine().getY() - 3, 6, 6);
		}

	}

	public boolean contains(int x, int y) {
		if ((startPoint.dinstance(x, y) + endPoint.dinstance(x, y)) - length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}

	public Point middleOfLine() {
		int middleByX = (this.startPoint.getX() + this.endPoint.getX()) / 2;
		int middleByY = (this.startPoint.getY() + this.endPoint.getY()) / 2;
		Point middlePoint = new Point(middleByX, middleByY);
		return middlePoint;
	}

	public double length() {
		return startPoint.dinstance(endPoint.getX(), endPoint.getY());
	}

	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line pomocna = (Line) obj;
			if (this.startPoint.equals(pomocna.startPoint) && this.endPoint.equals(pomocna.endPoint)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public Line clone() {
		
		return new Line(startPoint.clone(), endPoint.clone(), getColor());
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
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
		// Line: StartPoint: x1 y2 --> EndPoint: x2 y2 Color: RGB
		return "Line: StartPoint: " +getStartPoint().getX() + " " + getStartPoint().getY() + " EndPoint: " +getEndPoint().getX() + " " +getEndPoint().getY() + " Color: " + getColor().getRGB();
	}

}
