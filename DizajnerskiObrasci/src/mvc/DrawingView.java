package mvc;

import javax.swing.JPanel;

import geometry.Shape;

import java.awt.Graphics;
import java.util.Iterator;

public class DrawingView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private DrawingModel model = new DrawingModel();

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

}
