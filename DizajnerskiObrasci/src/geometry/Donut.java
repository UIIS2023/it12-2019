package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Area;
import java.awt.Shape;

public class Donut extends Circle implements Cloneable{

	private static final long serialVersionUID = 1L;
	private int innerRadius;

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, Color color, Color innerColor) {
		this(center, radius, innerRadius);
		this.setColor(color);
		this.setInnerColor(innerColor);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		this.setSelected(selected);

	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		this.setColor(color);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		this.setInnerColor(innerColor);
	}

	public Donut() {

	}

	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}

	public void draw(Graphics g) {
		
		Graphics2D g2D = (Graphics2D)g;

		Shape outerCircle = new Ellipse2D.Double(
				getCenter().getX() - this.getRadius(), 
				getCenter().getY() - this.getRadius(), 
				this.getRadius()*2, 
				this.getRadius()*2);
		Shape innerCircle = new Ellipse2D.Double(
				getCenter().getX() - this.getInnerRadius(), 
				getCenter().getY() - this.getInnerRadius(), 
				this.getInnerRadius()*2, 
				this.getInnerRadius()*2);

		Area area = new Area(outerCircle);
		Area inner = new Area(innerCircle);
		area.subtract(inner);
   
		g2D.setColor(getInnerColor());
		g2D.fill(area);
		g2D.setColor(getColor());
		g2D.draw(area);
		
		if (isSelected()) {
			g2D.setColor(Color.BLUE);
			g2D.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - 3, 6, 6);
			g2D.drawRect(this.getCenter().getX() - this.getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g2D.drawRect(this.getCenter().getX() + this.getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g2D.drawRect(this.getCenter().getX()- 3, this.getCenter().getY() - this.getRadius()- 3, 6, 6);
			g2D.drawRect(this.getCenter().getX()- 3,this.getCenter().getY()+ this.getRadius()- 3, 6, 6);
		}
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().dinstance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}

	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().dinstance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut don = (Donut) obj;
			if (this.getCenter().equals(don.getCenter()) && this.getRadius() == don.getRadius()
					&& this.innerRadius == don.innerRadius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public Donut clone() {
		
		return new Donut(center.clone(), radius, innerRadius, getColor(), getInnerColor());
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public String toString() {
		//Donut: Center= center, radius=radius, InnerRadius= innerRadius
		return "Donut: " +super.toString() + " InnerRadius= " + getInnerRadius();
	}

}
