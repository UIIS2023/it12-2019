package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Drawing() {
		setBackground(Color.WHITE);
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Drawing");
		frame.setSize(800, 600);
		Drawing drawing = new Drawing();
		frame.getContentPane().add(drawing);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void paint(Graphics g) {

		Point p = new Point(50, 50, true, Color.BLUE);
		// p.draw(g);

		Line l = new Line(new Point(70, 70), new Point(150, 200), true, Color.RED);
		// l.draw(g);

		Circle c = new Circle(new Point(200, 120), 40, true, Color.RED, Color.GREEN);
		// c.draw(g);

		Rectangle r = new Rectangle(new Point(300, 60), 70, 120, true, Color.BLACK, Color.MAGENTA);
		// r.draw(g);

		Donut d = new Donut(new Point(400, 300), 80, 20, true, Color.BLACK, Color.YELLOW);
		// d.draw(g);

		// ArrayList
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		shapes.add(p);
		shapes.add(l);
		shapes.add(c);
		shapes.add(r);
		shapes.add(d);

		Iterator<Shape> iterator = shapes.iterator();
		while (iterator.hasNext()) {
			System.out.println("Selected: " + iterator.next().isSelected());
		}

		// iscrtati treci element iz liste shapes
		shapes.get(2).draw(g);

		// iscrtati poslednji element iz liste
		shapes.get(shapes.size() - 1).draw(g);

		// iscrtati element sa indeksom 3
		shapes.get(3).draw(g);

		// kreirati i dodati u listu, a potom i iscrtati novu liniju l1 tako da ona bude
		// 4. element u listi
		Line l1 = new Line(new Point(450, 200), new Point(550, 200));
		shapes.add(3, l1);
//				
		shapes.get(3).draw(g);

		// ukloniti drugi element liste
		shapes.remove(1);

		// Pomocu for each iscrtati svaki oblik iz liste shapes
		for (Shape s : shapes) {
			s.draw(g);
		}

		// setovati svaki oblik iy liste shapes kao selektovan pomocu iteratora iterator
		while (iterator.hasNext()) {
			iterator.next().setSelected(true);
		}

		// iscrtati samo povrsinske oblike
		for (Shape s : shapes) {
			if (s instanceof SurfaceShape) {
				s.draw(g);
			}
		}
	}
}
