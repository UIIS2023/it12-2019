package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgLine extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartPointX;
	private JTextField txtStartPointY;
	private JTextField txtEndPointX;
	private JTextField txtEndPointY;
	private boolean isOK;
	public Line line;
	private Color color = Color.BLACK;
	private JButton btnOutLineColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		setTitle("Modify Line");
		setBackground(Color.WHITE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(230, 230, 250));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblStartPoint = new JLabel("Start Point: ");
			lblStartPoint.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 11));
			GridBagConstraints gbc_lblNewLabelSP = new GridBagConstraints();
			gbc_lblNewLabelSP.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelSP.gridx = 2;
			gbc_lblNewLabelSP.gridy = 0;
			contentPanel.add(lblStartPoint, gbc_lblNewLabelSP);
		}
		{
			JLabel lblStartPointX = new JLabel("Coordinate X");
			GridBagConstraints gbc_lblNewLabelSPX = new GridBagConstraints();
			gbc_lblNewLabelSPX.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelSPX.gridx = 2;
			gbc_lblNewLabelSPX.gridy = 1;
			contentPanel.add(lblStartPointX, gbc_lblNewLabelSPX);
		}
		{
			txtStartPointX = new JTextField();
			GridBagConstraints gbc_txtStartPointX = new GridBagConstraints();
			gbc_txtStartPointX.insets = new Insets(0, 0, 5, 0);
			gbc_txtStartPointX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtStartPointX.gridx = 5;
			gbc_txtStartPointX.gridy = 1;
			contentPanel.add(txtStartPointX, gbc_txtStartPointX);
			txtStartPointX.setColumns(10);
		}
		{
			JLabel lblStartPointY = new JLabel("Coordinate Y");
			GridBagConstraints gbc_lblNewLabelSPY = new GridBagConstraints();
			gbc_lblNewLabelSPY.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelSPY.gridx = 2;
			gbc_lblNewLabelSPY.gridy = 2;
			contentPanel.add(lblStartPointY, gbc_lblNewLabelSPY);
		}
		{
			txtStartPointY = new JTextField();
			GridBagConstraints gbc_txtStartPointY = new GridBagConstraints();
			gbc_txtStartPointY.insets = new Insets(0, 0, 5, 0);
			gbc_txtStartPointY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtStartPointY.gridx = 5;
			gbc_txtStartPointY.gridy = 2;
			contentPanel.add(txtStartPointY, gbc_txtStartPointY);
			txtStartPointY.setColumns(10);
		}
		{
			JLabel lblEndPoint = new JLabel("End Point: ");
			lblEndPoint.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 11));
			GridBagConstraints gbc_lblNewLabelEP = new GridBagConstraints();
			gbc_lblNewLabelEP.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelEP.gridx = 2;
			gbc_lblNewLabelEP.gridy = 4;
			contentPanel.add(lblEndPoint, gbc_lblNewLabelEP);
		}
		{
			JLabel lblEndPointX = new JLabel("Coordinate X");
			GridBagConstraints gbc_lblNewLabelEPX = new GridBagConstraints();
			gbc_lblNewLabelEPX.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelEPX.gridx = 2;
			gbc_lblNewLabelEPX.gridy = 5;
			contentPanel.add(lblEndPointX, gbc_lblNewLabelEPX);
		}
		{
			txtEndPointX = new JTextField();
			GridBagConstraints gbc_txtEndPointX = new GridBagConstraints();
			gbc_txtEndPointX.insets = new Insets(0, 0, 5, 0);
			gbc_txtEndPointX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEndPointX.gridx = 5;
			gbc_txtEndPointX.gridy = 5;
			contentPanel.add(txtEndPointX, gbc_txtEndPointX);
			txtEndPointX.setColumns(10);
		}
		{
			JLabel lblEndPointY = new JLabel("Coordinate Y");
			GridBagConstraints gbc_lblNewLabelEPY = new GridBagConstraints();
			gbc_lblNewLabelEPY.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelEPY.gridx = 2;
			gbc_lblNewLabelEPY.gridy = 6;
			contentPanel.add(lblEndPointY, gbc_lblNewLabelEPY);
		}
		{
			txtEndPointY = new JTextField();
			GridBagConstraints gbc_txtEndPointY = new GridBagConstraints();
			gbc_txtEndPointY.insets = new Insets(0, 0, 5, 0);
			gbc_txtEndPointY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEndPointY.gridx = 5;
			gbc_txtEndPointY.gridy = 6;
			contentPanel.add(txtEndPointY, gbc_txtEndPointY);
			txtEndPointY.setColumns(10);
		}

		btnOutLineColor = new JButton("Outline Color");
		btnOutLineColor.setForeground(Color.WHITE);
		btnOutLineColor.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 11));
		btnOutLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outLineColor = JColorChooser.showDialog(null, "Choose outline color",
						btnOutLineColor.getBackground());
				if (outLineColor != null)
					if (outLineColor.equals(Color.BLACK)) btnOutLineColor.setForeground(Color.WHITE);
					else if (outLineColor.equals(Color.WHITE)) btnOutLineColor.setForeground(Color.BLACK);
					btnOutLineColor.setBackground(outLineColor);

			}
		});
		GridBagConstraints gbc_btnOutLineColor = new GridBagConstraints();
		gbc_btnOutLineColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnOutLineColor.gridx = 2;
		gbc_btnOutLineColor.gridy = 8;
		contentPanel.add(btnOutLineColor, gbc_btnOutLineColor);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(173, 216, 230));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("OK");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtStartPointX.getText().trim().isEmpty() || txtStartPointY.getText().trim().isEmpty()
								|| txtEndPointX.getText().trim().isEmpty() || txtEndPointY.getText().trim().isEmpty()) {
							setOK(false);
							JOptionPane.showMessageDialog(null, "All fields are required!", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								if (Integer.parseInt(txtStartPointX.getText().toString()) < 0
										|| Integer.parseInt(txtStartPointY.getText().toString()) < 0
										|| Integer.parseInt(txtEndPointX.getText().toString()) < 0
										|| Integer.parseInt(txtEndPointY.getText().toString()) < 0) {
									JOptionPane.showMessageDialog(null, "Insert values greater than 0!", "ERROR",
											JOptionPane.ERROR_MESSAGE);
								} else {
									line = new Line(
											new Point(Integer.parseInt(txtStartPointX.getText().toString()),
													Integer.parseInt(txtStartPointY.getText().toString())),
											new Point(Integer.parseInt(txtEndPointX.getText().toString()),
													Integer.parseInt(txtEndPointY.getText().toString())),
											false, btnOutLineColor.getBackground());
									setOK(true);
									setVisible(false);
								}
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Enter numbers only!", "ERROR",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				btnOK.setBackground(new Color(152, 251, 152));
				btnOK.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 11));
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setBackground(new Color(250, 128, 114));
				btnCancel.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 11));
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public JTextField getTxtStartPointX() {
		return txtStartPointX;
	}

	public void setTxtStartPointX(JTextField txtStartPointX) {
		this.txtStartPointX = txtStartPointX;
	}

	public JTextField getTxtStartPointY() {
		return txtStartPointY;
	}

	public void setTxtStartPointY(JTextField txtStartPointY) {
		this.txtStartPointY = txtStartPointY;
	}

	public JTextField getTxtEndPointX() {
		return txtEndPointX;
	}

	public void setTxtEndPointX(JTextField txtEndPointX) {
		this.txtEndPointX = txtEndPointX;
	}

	public JTextField getTxtEndPointY() {
		return txtEndPointY;
	}

	public void setTxtEPY(JTextField txtEndPointY) {
		this.txtEndPointY = txtEndPointY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public JButton getBtnOutLineColor() {
		return btnOutLineColor;
	}

	public void setBtnOutLineColor(JButton btnOutLineColor) {
		this.btnOutLineColor = btnOutLineColor;
	}

}
