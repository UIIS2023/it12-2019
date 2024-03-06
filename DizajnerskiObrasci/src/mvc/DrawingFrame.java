package mvc;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import java.awt.ScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;

public class DrawingFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private DrawingView view = new DrawingView();
	private DrawingController controller;

	private JPanel contentPane = new JPanel();
	ButtonGroup btnGroup = new ButtonGroup();
	private Color color = Color.BLACK;
	
	private JToggleButton tglbtnPoint = new JToggleButton();
	private JToggleButton tglbtnLine = new JToggleButton();
	private JToggleButton tglbtnRectangle = new JToggleButton();
	private JToggleButton tglbtnCircle = new JToggleButton();
	private JToggleButton tglbtnDonut = new JToggleButton();
	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	private JToggleButton tglbtnHexagon = new JToggleButton();
	
	private JButton btnDelete = new JButton();
	private JButton btnModify = new JButton("Modify");
	private JButton btnUndo = new JButton();
	private JButton btnRedo = new JButton();
	private JButton btnSaveLog = new JButton("Save Log");
	private JButton btnSaveDrawing = new JButton("Save Draw");
	private JButton btnOpenLog = new JButton ("Open Log");
	private JButton btnOpenDrawing = new JButton("Open Draw");
	private JButton btnNext = new JButton("Next");
	private JButton btnBTF = new JButton("BringToFront");
	private JButton btnTF = new JButton("ToFront");
	private JButton btnBTB = new JButton("BringToBack");
	private JButton btnTB = new JButton("ToBack");
	private JButton btnInnerColor = new JButton("Area");
	private JButton btnOutlineColor = new JButton("Border");

	private final JTextArea textArea = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane(textArea);
	
	public DrawingFrame() {	
		
		getBtnUndo().setEnabled(false);
		getBtnRedo().setEnabled(false);
		
		//Kopirano iz konstruktora klase FrmDrawing
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
			
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setTitle("Delcev Selena, IT12-2019");
		setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		view.setBackground(Color.WHITE);
		getContentPane().add(view, BorderLayout.CENTER);

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(Color.pink);
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		tglbtnPoint.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/dot.png")));

		pnlNorth.add(tglbtnPoint);
		tglbtnLine.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/line.png")));
		pnlNorth.add(tglbtnLine);
		tglbtnCircle.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/circle.png")));
		pnlNorth.add(tglbtnCircle);
		tglbtnDonut.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/donut.png")));
		pnlNorth.add(tglbtnDonut);
		tglbtnRectangle.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/rectangle.png")));
		pnlNorth.add(tglbtnRectangle);
		tglbtnHexagon.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/hexagon.png")));
		pnlNorth.add(tglbtnHexagon);
		
		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnHexagon);
	
		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(Color.pink);
		contentPane.add(pnlWest, BorderLayout.WEST);
		pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
		pnlWest.add(Box.createRigidArea(new Dimension(0, 70)));
		pnlWest.add(btnSaveLog);
		
		btnSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
			}
		});
		
		pnlWest.add(Box.createRigidArea(new Dimension(0, 8)));
		
		pnlWest.add(btnOpenLog);
		
		btnOpenLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openLog();
			}
		});
		btnNext.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/next.png")));
		
		pnlWest.add(btnNext);
		btnNext.setVisible(false);
		
		pnlWest.add(Box.createRigidArea(new Dimension(0, 8)));
		
		pnlWest.add(btnSaveDrawing);
		
		btnSaveDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveDraw();
			}
		});
		
		pnlWest.add(Box.createRigidArea(new Dimension(0, 8)));
		
		pnlWest.add(btnOpenDrawing);
		
		btnOpenDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openDrawing();
			}
		});
		
		btnGroup.add(btnSaveLog);
		btnGroup.add(btnOpenLog);
		btnGroup.add(btnSaveDrawing);
		btnGroup.add(btnOpenDrawing);
		
		JPanel pnlEast = new JPanel();
		pnlEast.setBackground(Color.pink);
		contentPane.add(pnlEast, BorderLayout.EAST);
		pnlEast.setLayout(new BoxLayout(pnlEast, BoxLayout.Y_AXIS));
		pnlEast.add(Box.createRigidArea(new Dimension(0, 70)));
		pnlEast.add(btnBTF);
		
		btnBTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.BTF();
			}
		});
		
		pnlEast.add(Box.createRigidArea(new Dimension(0, 8)));
		
		pnlEast.add(btnTF);
		
		btnTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.TF();
			}
		});
		
		pnlEast.add(Box.createRigidArea(new Dimension(0, 8)));
		
		pnlEast.add(btnBTB);
		
		btnBTB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.BTB();
			}
		});
		
		pnlEast.add(Box.createRigidArea(new Dimension(0, 8)));
		
		pnlEast.add(btnTB);
		
		btnTB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.TB();
			}
		});
		
		btnGroup.add(btnBTF);
		btnGroup.add(btnTF);
		btnGroup.add(btnBTB);
		btnGroup.add(btnTB);

		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.Y_AXIS));
		pnlSouth.setBackground(Color.pink);
		
		JPanel pnlButtons = new JPanel();
		pnlSouth.add(pnlButtons);
		
		btnInnerColor.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/color.png")));
		pnlButtons.add(btnInnerColor);
		btnInnerColor.setBackground(Color.WHITE);
		
		btnInnerColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				color = controller.btnInnerColorClicked();
				if (color != null) {
					if (color.equals(Color.BLACK)) btnInnerColor.setForeground(Color.WHITE);
					else if (color.equals(Color.WHITE)) btnInnerColor.setForeground(Color.BLACK);
					btnInnerColor.setBackground(color);
				}
				
			}
			
		});
		btnOutlineColor.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/color.png")));
		pnlButtons.add(btnOutlineColor);
		btnOutlineColor.setBackground(Color.BLACK);
		btnOutlineColor.setForeground(Color.WHITE);
		
		btnOutlineColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				color = controller.btnOutlineColorClicked();
				if (color != null) {
					if (color.equals(Color.BLACK)) 
						btnOutlineColor.setForeground(Color.WHITE);
					else if (color.equals(Color.WHITE))
						btnOutlineColor.setForeground(Color.BLACK);
					btnOutlineColor.setBackground(color);
				}
			}
			
		});
		btnGroup.add(btnInnerColor);
		btnGroup.add(btnOutlineColor);
		
		pnlButtons.add(tglbtnSelect);
		pnlButtons.setBackground(Color.pink);
		
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify();
			}
		});
		
		pnlButtons.add(btnModify);
		btnGroup.add(btnModify);

		btnDelete.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/trash.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});
		
		pnlButtons.add(btnDelete);
		btnGroup.add(btnDelete);
		btnUndo.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/undo.png")));
		
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		
		pnlButtons.add(btnUndo);
		btnGroup.add(btnUndo);
		btnRedo.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/redo.png")));
		
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		
		pnlButtons.add(btnRedo);
		btnGroup.add(btnRedo);
		
		JPanel pnlTextArea = new JPanel();
		pnlSouth.add(pnlTextArea);
		
		textArea.setTabSize(30);
		textArea.setColumns(45);
		textArea.setRows(4);
		textArea.setSize(300, 50);
		textArea.setEditable(false);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlTextArea.add(scrollPane, gbc_scrollPane);
		
		
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
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

	public JPanel getContentPane() {
		return contentPane;
	}

	public DrawingController getController() {
		return controller;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}

	public JButton getBtnSaveLog() {
		return btnSaveLog;
	}

	public void setBtnSaveLog(JButton btnSaveLog) {
		this.btnSaveLog = btnSaveLog;
	}

	public JButton getBtnSaveDrawing() {
		return btnSaveDrawing;
	}

	public void setBtnSaveDrawing(JButton btnSaveDrawing) {
		this.btnSaveDrawing = btnSaveDrawing;
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

	public JButton getBtnBTF() {
		return btnBTF;
	}

	public void setBtnBTF(JButton btnBTF) {
		this.btnBTF = btnBTF;
	}

	public JButton getBtnTF() {
		return btnTF;
	}

	public void setBtnTF(JButton btnTF) {
		this.btnTF = btnTF;
	}

	public JButton getBtnBTB() {
		return btnBTB;
	}

	public void setBtnBTB(JButton btnBTB) {
		this.btnBTB = btnBTB;
	}

	public JButton getBtnTB() {
		return btnTB;
	}

	public void setBtnTB(JButton btnTB) {
		this.btnTB = btnTB;
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
	
	public JButton getBtnUndo() {
		return btnUndo;
	}
	
	public JButton getBtnRedo() {
		return btnRedo;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public JButton getBtnOpenLog() {
		return btnOpenLog;
	}
	
	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JButton getBtnOutlineColor() {
		return btnOutlineColor;
	}

	public void setBtnOutlineColor(JButton btnOutlineColor) {
		this.btnOutlineColor = btnOutlineColor;
	}
	
	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}
	
	

}
