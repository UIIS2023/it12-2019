package adapter;

import java.awt.Graphics;

import geometry.Point;
import geometry.SurfaceShape;
import hexagon.Hexagon;
import java.awt.Color;

public class HexagonAdapter extends SurfaceShape implements Cloneable{
	
	private static final long serialVersionUID = 1L;
	Hexagon hexagon;
	
	public HexagonAdapter() {
		
	}
	
	public HexagonAdapter(Point p) {
		hexagon.setX(p.getX());
		hexagon.setY(p.getY());
	}
	
	public HexagonAdapter(Point p, int radius) {
		hexagon.setX(p.getX());
		hexagon.setY(p.getY());
		hexagon.setR(radius);
	}
	
	public HexagonAdapter(Hexagon hex) {
		this.hexagon = hex;
	}
	
	public HexagonAdapter(Point p, int radius, boolean selected) {
		this.hexagon = new Hexagon(p.getX(), p.getY(), radius);
		hexagon.setSelected(selected);
	}
	
	public HexagonAdapter(Point p, int radius, Color color, Color innerColor) {
		this.hexagon = new Hexagon(p.getX(), p.getY(), radius);
		this.hexagon.setBorderColor(color);
		this.hexagon.setAreaColor(innerColor);
	}
	
	public HexagonAdapter(Point p, int radius, boolean selected, Color color) {
		this.hexagon = new Hexagon(p.getX(), p.getY(), radius);
		this.hexagon.setSelected(selected);
		this.hexagon.setBorderColor(color);
	}
	
	public HexagonAdapter(Point p, int radius, boolean selected, Color color, Color innerColor) {
		this.hexagon = new Hexagon(p.getX(), p.getY(), radius);
		this.hexagon.setSelected(selected);
		this.hexagon.setBorderColor(color);
		this.hexagon.setAreaColor(innerColor);
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.hexagon.setX(byX);
		this.hexagon.setY(byY);		
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Hexagon) {
			return this.hexagon.getR() - ((Hexagon) o).getR();
		}
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		//hexagon.setSelected(isSelected());
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.hexagon.getX() - 3, this.hexagon.getY() - 3, 6, 6);
			g.drawRect(this.hexagon.getX() - this.hexagon.getR() - 3, this.hexagon.getY() - 3, 6, 6);
			g.drawRect(this.hexagon.getX() + this.hexagon.getR() - 3, this.hexagon.getY() - 3, 6, 6);
			g.drawRect(this.hexagon.getX() - (this.hexagon.getR() / 2) - 3, this.hexagon.getY() - this.hexagon.getR() - 3, 6, 6);
			g.drawRect(this.hexagon.getX() + (this.hexagon.getR() / 2) - 3, this.hexagon.getY() - this.hexagon.getR() - 3, 6, 6);
			g.drawRect(this.hexagon.getX() - (this.hexagon.getR() / 2) - 3, this.hexagon.getY() + this.hexagon.getR() - 3, 6, 6);
			g.drawRect(this.hexagon.getX() + (this.hexagon.getR() / 2) - 3, this.hexagon.getY() + this.hexagon.getR() - 3, 6, 6);
		}
	}
	
	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HexagonAdapter clone() {
		Hexagon hex = new Hexagon(hexagon.getX(), hexagon.getY(), hexagon.getR());
		hex.setBorderColor(getColor());
		hex.setAreaColor(getInnerColor());
		return new HexagonAdapter(hex);
	}
	
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
		super.setColor(color);
	}
	
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}
	
	public void setInnerColor(Color color) {
		hexagon.setAreaColor(color);
		super.setInnerColor(color);
	}
	
	@Override
	public String toString() {
		//Hexagon: Center: x y Radius= radius BorderColor: color AreaColor: color
		return "Hexagon: Center: " + getHexagon().getX() + " " + getHexagon().getY() + " Radius= " + getHexagon().getR() + " BorderColor: " + getHexagon().getBorderColor().getRGB() + " AreaColor: " + getHexagon().getAreaColor().getRGB();
	}
}
