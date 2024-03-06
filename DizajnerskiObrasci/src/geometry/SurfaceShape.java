package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape {
	
	private static final long serialVersionUID = 1L;
	private Color innerColor;
	public abstract void fill(Graphics g);

	public SurfaceShape(Color color) {
		super(color);
	}

	public SurfaceShape(Color color, boolean selected) {
		this(color);
		this.setSelected(selected);
	}

	public SurfaceShape() {

	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

}
