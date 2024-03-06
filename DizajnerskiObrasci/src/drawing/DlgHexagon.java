package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import adapter.HexagonAdapter;
import geometry.Point;

public class DlgHexagon extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private HexagonAdapter hexagon;
	private boolean isOK;
	private JButton btnInnerColor;
	private JButton btnOutLineColor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgHexagon dialog = new DlgHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgHexagon() {
		setBounds(100, 100, 450, 300);
		setTitle("Add or modify circle");
		setResizable(false);
		setModal(true);
		setBackground(Color.WHITE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(230, 230, 250));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCenter = new JLabel("Center of Circle:");
			lblCenter.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 11));
			GridBagConstraints gbc_lblNewLabelCircle = new GridBagConstraints();
			gbc_lblNewLabelCircle.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelCircle.gridx = 2;
			gbc_lblNewLabelCircle.gridy = 0;
			contentPanel.add(lblCenter, gbc_lblNewLabelCircle);
		}
		{
			JLabel lblNewLabelX = new JLabel("Coordinate X:");
			GridBagConstraints gbc_lblNewLabelX = new GridBagConstraints();
			gbc_lblNewLabelX.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelX.gridx = 2;
			gbc_lblNewLabelX.gridy = 1;
			contentPanel.add(lblNewLabelX, gbc_lblNewLabelX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 4;
			gbc_txtX.gridy = 1;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblNewLabelY = new JLabel("Coordinate Y:");
			GridBagConstraints gbc_lblNewLabelY = new GridBagConstraints();
			gbc_lblNewLabelY.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelY.gridx = 2;
			gbc_lblNewLabelY.gridy = 2;
			contentPanel.add(lblNewLabelY, gbc_lblNewLabelY);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 4;
			gbc_txtY.gridy = 2;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			GridBagConstraints gbc_lblNewLabelRadius = new GridBagConstraints();
			gbc_lblNewLabelRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelRadius.gridx = 2;
			gbc_lblNewLabelRadius.gridy = 5;
			contentPanel.add(lblRadius, gbc_lblNewLabelRadius);
		}
		{
			txtRadius = new JTextField();
			txtRadius.setText("");
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius.gridx = 4;
			gbc_txtRadius.gridy = 5;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}

		btnInnerColor = new JButton("Inner Color");
		btnInnerColor.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 11));
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color innerColor = JColorChooser.showDialog(null, "Choose inner color", btnInnerColor.getBackground());
				if (innerColor != null)
					if (innerColor.equals(Color.BLACK)) btnInnerColor.setForeground(Color.WHITE);
					else if (innerColor.equals(Color.WHITE)) btnInnerColor.setForeground(Color.BLACK);
					btnInnerColor.setBackground(innerColor);

			}
		});
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInnerColor.gridx = 2;
		gbc_btnInnerColor.gridy = 6;
		contentPanel.add(btnInnerColor, gbc_btnInnerColor);

		btnOutLineColor = new JButton("OutLine Color");
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
		gbc_btnOutLineColor.gridy = 7;
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
						if (txtX.getText().trim().isEmpty() || txtY.getText().trim().isEmpty()
								|| txtRadius.getText().trim().isEmpty()) {
							setOK(false);
							JOptionPane.showMessageDialog(null, "All fields are required!", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								if (Integer.parseInt(txtX.getText().toString()) < 0
										|| Integer.parseInt(txtY.getText().toString()) < 0
										|| Integer.parseInt(txtRadius.getText().toString()) <= 0) {
									JOptionPane.showMessageDialog(null, "Insert values greater than 0!", "ERROR",
											JOptionPane.ERROR_MESSAGE);
								} else {
									hexagon = new HexagonAdapter(
											new Point(Integer.parseInt(txtX.getText().toString()),
													Integer.parseInt(txtY.getText().toString())),
											Integer.parseInt(txtRadius.getText().toString()), false,
											btnOutLineColor.getBackground(), btnInnerColor.getBackground());
									setOK(true);
									setVisible(false);
								}
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Enter numbers only", "ERROR",
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

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public HexagonAdapter getHexagon() {
		return hexagon;
	}

	public void setHexagon(HexagonAdapter hexagon) {
		this.hexagon = hexagon;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JButton getBtnOutLineColor() {
		return btnOutLineColor;
	}

	public void setBtnOutLineColor(JButton btnOutLineColor) {
		this.btnOutLineColor = btnOutLineColor;
	}

}
