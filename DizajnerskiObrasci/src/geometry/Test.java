package geometry;

import java.util.HashMap;

public class Test {

	public static void main(String[] args) {

		Point p = new Point();
		p.setX(10);
		p.setY(20);
		p.setSelected(true);
		System.out.println("X cordinate of point p is: " + p.getX() + '\n' + "Y cordinate of point p is: " + p.getY()
				+ '\n' + "Point p  is selected: " + p.isSelected());

		double result = p.dinstance(40, 50);
		System.out.println(result);

		Point p1 = new Point();
		p1.setX(15);
		p1.setY(27);
		p1.setSelected(true);

		Line l1 = new Line();
		Rectangle r1 = new Rectangle();
		Circle c1 = new Circle();

		// Inicijalizovati x koordinatu tacke p
		// na vrednost y koordinate tacke p1

		p.setX(p1.getY());
		System.out.println("X of p = " + p.getX());

		// Postaviti za pocetnu tacku linije l1 tacku p, a
		// za krajnju tacku linije l1 tacku p1

		l1.setStartPoint(p);
		l1.setEndPoint(p1);
		System.out.println(
				"Start point line l1 is: " + l1.getStartPoint() + '\n' + "End point line l1 is: " + l1.getEndPoint());

		// Postaviti y koordinatu krajnje tacke l1 na 23
		l1.getEndPoint().setY(23);
		System.out.println("Y of end point line l1 is: " + l1.getEndPoint().getY());

		// Inicijalizovati x koordinatu pocetne tacke linije l1
		// na vrednost y koordinate krajnje tacke linije l1

		l1.getStartPoint().setX(l1.getEndPoint().getY());
		System.out.println("X of the start point line l1 is: " + l1.getStartPoint().getX());

		// Postaviti x koordinatu krajnje tacke l1 na vrednost
		// duzine linije l1 umanjene za vrednost zbira x i y
		// koordinate pocetne tacke linije l1

		l1.getEndPoint().setX((int) l1.length() - (l1.getStartPoint().getX() + l1.getStartPoint().getY()));
		System.out.println("X of the end point line l1 is: " + l1.getEndPoint().getX());

		// Postaviti x koordinatu tacke gore levo pravougaonika
		// r1 na vrednost 10 i y koordinatu na vrednost 15

		r1.setUpperLeftPoint(p1);
		r1.getUpperLeftPoint().setX(10);
		r1.getUpperLeftPoint().setY(15);
		System.out.println("X of the upperLeft point is: " + r1.getUpperLeftPoint().getX() + '\n'
				+ "Y of the upperLeft point is: " + r1.getUpperLeftPoint().getY());

		// Postaviti centar kruga c1 na koordinate tacke
		// gore levo od r1

		c1.setCenter(r1.getUpperLeftPoint());
		System.out.println("Center circle is: " + c1.getCenter());

		// Postaviti x koordinatu centra kruga c1 na vrednost razlike
		// povrsine pravougaonika r1 i y koordinate pocetne tacke linije l1

		r1.setHeight(20);
		r1.setWidth(30);
		c1.getCenter().setX(r1.area() - l1.getStartPoint().getY());
		System.out.println("X of the center of c1: " + c1.getCenter().getX());

		Point p2 = new Point(50, 100);
		Point p4 = new Point(50, 100);
		Line l2 = new Line(p4, new Point(400, 500));
		Rectangle r2 = new Rectangle(p1, 100, 200);
		Circle c2 = new Circle(new Point(300, 300), 50);

		System.out.println(p2);
		System.out.println(p4);
		System.out.println(l2);
		System.out.println(r2);
		System.out.println(c2);

		int a = 5;
		int b = 5;
		System.out.println(a == b);

		String s1 = new String("Hello World");
		String s2 = new String("Hello World");

		System.out.println(s1 == s2); // poredjenje po referenci
		System.out.println(s1.equals(s2)); // poredjenje po vrednosti

		System.out.println(p4.equals(p1));
		System.out.println(p4.equals(c2));
		System.out.println(p4.equals(p2));

		Donut d = new Donut();

		Circle d1 = new Donut();

		System.out.println(d instanceof Donut);
		System.out.println(d instanceof Circle);
		System.out.println(d1 instanceof Shape);
		System.out.println(d1 instanceof SurfaceShape);

		Point p3 = new Point(10, 10);
		Point p5 = new Point(5, 5);
		Point p6 = new Point(2, 2);
		Point p7 = new Point(20, 20);

		// Niz
		Point[] points = { p3, p5, p6, p7 };

		System.out.println("Nesortiran niz tacaka");
		for (int i = 0; i < points.length; i++) {
			System.out.println(points[i]);
		}

		// HashMap
		HashMap<String, Shape> map = new HashMap<String, Shape>();

		map.put("point", p1);
		map.put("rectangle", r1);
		map.put("Point", p3);

		System.out.println("point from map is: " + map.get("point"));
		System.out.println("point from map is: " + map.get("Point"));

		Point p11 = new Point(3, 3);
		map.put("point", p11);
		System.out.println("point from map is: " + map.get("point"));

	}

}
