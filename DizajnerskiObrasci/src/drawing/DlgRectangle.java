package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

import geometry.Point;
import geometry.Rectangle;
import java.awt.Font;

public class DlgRectangle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private boolean isOK;
	public Rectangle rectangle;
	private JButton btnOutLineColor;
	private JButton btnInnerColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setBounds(100, 100, 450, 300);
		setTitle("Add or modify rectangle");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(230, 230, 250));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblUpperLeftPoint = new JLabel("Upper Left Point:");
			lblUpperLeftPoint.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 11));
			GridBagConstraints gbc_lblNewLabelULP = new GridBagConstraints();
			gbc_lblNewLabelULP.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelULP.gridx = 2;
			gbc_lblNewLabelULP.gridy = 0;
			contentPanel.add(lblUpperLeftPoint, gbc_lblNewLabelULP);
		}
		{
			JLabel lblUpperLeftPointX = new JLabel("Coordinate X:");
			GridBagConstraints gbc_lblNewLabelULPX = new GridBagConstraints();
			gbc_lblNewLabelULPX.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelULPX.gridx = 2;
			gbc_lblNewLabelULPX.gridy = 1;
			contentPanel.add(lblUpperLeftPointX, gbc_lblNewLabelULPX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 5);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 4;
			gbc_txtX.gridy = 1;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblUpperLeftPointY = new JLabel("Coordinate Y:");
			GridBagConstraints gbc_lblNewLabelULPY = new GridBagConstraints();
			gbc_lblNewLabelULPY.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelULPY.gridx = 2;
			gbc_lblNewLabelULPY.gridy = 2;
			contentPanel.add(lblUpperLeftPointY, gbc_lblNewLabelULPY);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 5);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 4;
			gbc_txtY.gridy = 2;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Height:");
			GridBagConstraints gbc_lblNewLabelHeight = new GridBagConstraints();
			gbc_lblNewLabelHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelHeight.gridx = 2;
			gbc_lblNewLabelHeight.gridy = 4;
			contentPanel.add(lblHeight, gbc_lblNewLabelHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.insets = new Insets(0, 0, 5, 5);
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 4;
			gbc_txtHeight.gridy = 4;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			GridBagConstraints gbc_lblNewLabelWidth = new GridBagConstraints();
			gbc_lblNewLabelWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelWidth.gridx = 2;
			gbc_lblNewLabelWidth.gridy = 5;
			contentPanel.add(lblWidth, gbc_lblNewLabelWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.insets = new Insets(0, 0, 5, 5);
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 4;
			gbc_txtWidth.gridy = 5;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtHeight.setColumns(10);
		}

		btnInnerColor = new JButton("Inner Color");
		btnInnerColor.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 11));
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color innerColor = JColorChooser.showDialog(null, "Choose inner color!", btnInnerColor.getBackground());
				if (innerColor != null)
					if (innerColor.equals(Color.BLACK)) btnInnerColor.setForeground(Color.WHITE);
					else if (innerColor.equals(Color.WHITE)) btnInnerColor.setForeground(Color.BLACK);
					btnInnerColor.setBackground(innerColor);
			}
		});

		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.anchor = GridBagConstraints.SOUTH;
		gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInnerColor.gridx = 3;
		gbc_btnInnerColor.gridy = 7;
		contentPanel.add(btnInnerColor, gbc_btnInnerColor);

		contentPanel.add(btnInnerColor, gbc_btnInnerColor);

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
		gbc_btnOutLineColor.gridx = 3;
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
						try {
							if (txtX.getText().trim().isEmpty() || txtY.getText().trim().isEmpty()
									|| txtHeight.getText().trim().isEmpty() || txtWidth.getText().trim().isEmpty()) {
								setOK(false);
								JOptionPane.showMessageDialog(null, "All values are required!", "Error",
										JOptionPane.ERROR_MESSAGE);
							} else {
								if (Integer.parseInt(txtWidth.getText().toString()) <= 0
										|| Integer.parseInt(txtHeight.getText().toString()) <= 0
										|| Integer.parseInt(txtX.getText().toString()) < 0
										|| Integer.parseInt(txtY.getText().toString()) < 0) {
									JOptionPane.showMessageDialog(null, "Insert values greater then 0!", "Error",
											JOptionPane.ERROR_MESSAGE);

								} else {
									rectangle = new Rectangle(
											new Point(Integer.parseInt(getTxtX().getText().toString()),
													Integer.parseInt(getTxtY().getText().toString())),
											Integer.parseInt(getTxtWidth().getText().toString()),
											Integer.parseInt(getTxtHeight().getText().toString()), false,
											btnOutLineColor.getBackground(), btnInnerColor.getBackground());

									setOK(true);
									setVisible(false);
								}
							}
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Enter numbers only!", "Error",
									JOptionPane.ERROR_MESSAGE);
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

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public JButton getBtnOutLineColor() {
		return btnOutLineColor;
	}

	public void setBtnOutLineColor(JButton btnOutLineColor) {
		this.btnOutLineColor = btnOutLineColor;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

}
