package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import geometry.Line;
import geometry.Point;
import geometry.Shape;

public class PnlDrawing extends JPanel {

	private static final long serialVersionUID = 1L;
	private FrmDrawing frame;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Point startPoint;
	private Shape selectedShape;

	public PnlDrawing() {

	}

	public PnlDrawing(FrmDrawing frame) {
		this.frame = frame;
		setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouse) {
				thisMouseClicked(mouse);
			}
		});
	}

	protected void thisMouseClicked(MouseEvent mouse) {

		Shape newShape = null;
		Point click = new Point(mouse.getX(), mouse.getY());
		if (frame.getTglbtnSelect().isSelected()) {
			selectedShape = null;
			Iterator<Shape> iterator = shapes.iterator();
			while (iterator.hasNext()) {
				Shape shape = iterator.next();
				shape.setSelected(false);
				if (shape.contains(click.getX(), click.getY()))
					selectedShape = shape;
			}
			if (selectedShape != null)
				selectedShape.setSelected(true);
		} else if (frame.getTglbtnPoint().isSelected()) {
			newShape = new Point(click.getX(), click.getY(), false, Color.black);
		} else if (frame.getTglbtnLine().isSelected()) {
			if (startPoint == null)
				startPoint = click;
			else {
				newShape = new Line(startPoint, new Point(mouse.getX(), mouse.getY(), false, Color.black));
				startPoint = null;
			}
		} else if (frame.getTglbtnRectangle().isSelected()) {
			DlgRectangle dlg = new DlgRectangle();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + Integer.toString(mouse.getX()));
			dlg.getTxtX().setEditable(false);
			dlg.getTxtY().setText("" + Integer.toString(mouse.getY()));
			dlg.getTxtY().setEditable(false);
			dlg.setVisible(true);
			if (!dlg.isOK())
				return;
			try {
				newShape = dlg.getRectangle();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Wrong data type", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnCircle().isSelected()) {
			DlgCircle dlg = new DlgCircle();
			dlg.getTxtX().setText("" + Integer.toString(click.getX()));
			dlg.getTxtX().setEditable(false);
			dlg.getTxtY().setText("" + Integer.toString(click.getY()));
			dlg.getTxtY().setEditable(false);
			dlg.setVisible(true);
			if (dlg.isOK())
				newShape = dlg.getCircle();
		} else if (frame.getTglbtnDonut().isSelected()) {
			DlgDonut dlg = new DlgDonut();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + Integer.toString(click.getX()));
			dlg.getTxtX().setEditable(false);
			dlg.getTxtY().setText("" + Integer.toString(click.getY()));
			dlg.getTxtY().setEditable(false);
			dlg.setVisible(true);
			if (dlg.isOK())
				newShape = dlg.getDonut();
		} else if (frame.getTglbtnHexagon().isSelected()) {
			DlgHexagon dlg = new DlgHexagon();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + Integer.toString(click.getX()));
			dlg.getTxtX().setEditable(false);
			dlg.getTxtY().setText("" + Integer.toString(click.getY()));
			dlg.getTxtY().setEditable(false);
			dlg.setVisible(true);
			if (dlg.isOK())
				newShape = dlg.getHexagon();
		}

		if (newShape != null)
			shapes.add(newShape);

		repaint();

	}

	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> iterator = shapes.iterator();
		while (iterator.hasNext())
			iterator.next().draw(g);

	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

}
