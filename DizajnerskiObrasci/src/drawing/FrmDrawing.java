package drawing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmDrawing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ButtonGroup btnGroup = new ButtonGroup();
	private PnlDrawing pnlDrawing = new PnlDrawing(this);

	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	private JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setTitle("Delcev Selena, IT12-2019");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		pnlDrawing.setBackground(Color.WHITE);
		contentPane.add(pnlDrawing, BorderLayout.CENTER);

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(Color.pink);
		contentPane.add(pnlNorth, BorderLayout.NORTH);

		pnlNorth.add(tglbtnPoint);
		pnlNorth.add(tglbtnLine);
		pnlNorth.add(tglbtnCircle);
		pnlNorth.add(tglbtnDonut);
		pnlNorth.add(tglbtnRectangle);
		pnlNorth.add(tglbtnHexagon);

		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnHexagon);

		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		pnlSouth.setBackground(Color.pink);
		pnlSouth.add(tglbtnSelect);

		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pnlDrawing.getSelectedShape() != null) {
					modify();
					pnlDrawing.getSelectedShape().setSelected(false);
				} else {
					JOptionPane.showMessageDialog(null, "Please insert what you want to modify!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					tglbtnSelect.setSelected(true);
				}
				pnlDrawing.setSelectedShape(null);
				tglbtnSelect.setSelected(false);
			}
		});
		pnlSouth.add(btnModify);
		btnGroup.add(btnModify);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				tglbtnSelect.setSelected(false);
			}
		});
		pnlSouth.add(btnDelete);
		btnGroup.add(btnDelete);

		pnlDrawing.repaint();
	}

	protected void delete() {
		Shape selectedShape = pnlDrawing.getSelectedShape();

		if (selectedShape != null) {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?",
					"Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				pnlDrawing.getShapes().remove(selectedShape);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "ERROR",
					JOptionPane.WARNING_MESSAGE);
		}
		pnlDrawing.setSelectedShape(null);
		pnlDrawing.repaint();
	}

	protected void modify() {
		Shape selectedShape = pnlDrawing.getSelectedShape();

		if (selectedShape != null) {
			if (selectedShape instanceof Point) {
				Point p = (Point) selectedShape;
				DlgPoint dlg = new DlgPoint();

				dlg.getTxtX().setText("" + Integer.toString(p.getX()));
				dlg.getTxtY().setText("" + Integer.toString(p.getY()));
				dlg.getBtnColor().setBackground(p.getColor());
				dlg.setVisible(true);

				if (dlg.isOK()) {
					pnlDrawing.getShapes().remove(selectedShape);
					pnlDrawing.getShapes().add(dlg.getP());
					repaint();
				}
			} else if (selectedShape instanceof Line) {
				Line line = (Line) selectedShape;
				DlgLine dlg = new DlgLine();

				dlg.getTxtStartPointX().setText("" + Integer.toString(line.getStartPoint().getX()));
				dlg.getTxtStartPointY().setText("" + Integer.toString(line.getStartPoint().getY()));
				dlg.getTxtEndPointX().setText("" + Integer.toString(line.getEndPoint().getX()));
				dlg.getTxtEndPointY().setText("" + Integer.toString(line.getEndPoint().getY()));
				dlg.getBtnOutLineColor().setBackground(line.getColor());
				dlg.setVisible(true);

				if (dlg.isOK()) {
					pnlDrawing.getShapes().remove(selectedShape);
					pnlDrawing.getShapes().add(dlg.getLine());
					repaint();
				}
			} else if (selectedShape instanceof Rectangle) {
				Rectangle rect = (Rectangle) selectedShape;
				DlgRectangle dlg = new DlgRectangle();

				dlg.getTxtX().setText("" + Integer.toString(rect.getUpperLeftPoint().getX()));
				dlg.getTxtY().setText("" + Integer.toString(rect.getUpperLeftPoint().getY()));
				dlg.getTxtHeight().setText("" + Integer.toString(rect.getHeight()));
				dlg.getTxtWidth().setText("" + Integer.toString(rect.getWidth()));
				dlg.getBtnInnerColor().setBackground(rect.getInnerColor());
				dlg.getBtnOutLineColor().setBackground(rect.getColor());
				dlg.setModal(true);
				dlg.setVisible(true);

				if (dlg.isOK()) {
					pnlDrawing.getShapes().remove(selectedShape);
					pnlDrawing.getShapes().add(dlg.getRectangle());
					repaint();
				}

			} else if (selectedShape instanceof Donut) {
				Donut donut = (Donut) selectedShape;
				DlgDonut dlg = new DlgDonut();

				dlg.getTxtX().setText("" + Integer.toString(donut.getCenter().getX()));
				dlg.getTxtY().setText("" + Integer.toString(donut.getCenter().getY()));
				dlg.getTxtRadius().setText("" + Integer.toString(donut.getRadius()));
				dlg.getTxtInnerRadius().setText("" + Integer.toString(donut.getInnerRadius()));
				dlg.getBtnInnerColor().setBackground(donut.getInnerColor());
				dlg.getBtnOutLineColor().setBackground(donut.getColor());
				dlg.setModal(true);
				dlg.setVisible(true);

				if (dlg.isOK()) {
					pnlDrawing.getShapes().remove(selectedShape);
					pnlDrawing.getShapes().add(dlg.getDonut());
					repaint();
				}
			} else if (selectedShape instanceof Circle && (selectedShape instanceof Donut) == false) {
				Circle circle = (Circle) selectedShape;
				DlgCircle dlg = new DlgCircle();

				dlg.getTxtX().setText("" + Integer.toString(circle.getCenter().getX()));
				dlg.getTxtY().setText("" + Integer.toString(circle.getCenter().getY()));
				dlg.getTxtRadius().setText("" + Integer.toString(circle.getRadius()));
				dlg.getBtnInnerColor().setBackground(circle.getInnerColor());
				dlg.getBtnOutLineColor().setBackground(circle.getColor());
				dlg.setModal(true);
				dlg.setVisible(true);

				if (dlg.isOK()) {
					pnlDrawing.getShapes().remove(selectedShape);
					pnlDrawing.getShapes().add(dlg.getCircle());
					repaint();
				}
			} else if (selectedShape instanceof HexagonAdapter) {
				HexagonAdapter hexagon = (HexagonAdapter) selectedShape;
				DlgHexagon dlg = new DlgHexagon();
				
				dlg.getTxtX().setText("" + Integer.toString(hexagon.getHexagon().getX()));
				dlg.getTxtY().setText("" + Integer.toString(hexagon.getHexagon().getY()));
				dlg.getTxtRadius().setText("" + Integer.toString(hexagon.getHexagon().getR()));
				dlg.getBtnInnerColor().setBackground(hexagon.getHexagon().getAreaColor());
				dlg.getBtnOutLineColor().setBackground(hexagon.getHexagon().getBorderColor());
				dlg.setModal(true);
				dlg.setVisible(true);

				if (dlg.isOK()) {
					pnlDrawing.getShapes().remove(selectedShape);
					pnlDrawing.getShapes().add(dlg.getHexagon());
					repaint();
				}
			}

		}
	}

	public PnlDrawing getPnlDrawing() {
		return pnlDrawing;
	}

	public void setPnlDrawing(PnlDrawing pnlDrawing) {
		this.pnlDrawing = pnlDrawing;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}
	
	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

}
