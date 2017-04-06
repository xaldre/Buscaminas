package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Cursor;

public class UIbusqui extends JFrame {

	protected JPanel contentPane;
	protected JButton button;
	protected JPanel buttonPane;
	private JPanel panel;
	private JLabel lblExplorar;

	public JPanel getButtonPane() {
		return buttonPane;
	}

	
	/**
	 * Create the frame.
	 */
	public UIbusqui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{430, 0};
		gbl_contentPane.rowHeights = new int[]{14, 393, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
				
				panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.insets = new Insets(0, 0, 5, 0);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 0;
				contentPane.add(panel, gbc_panel);
				
				lblExplorar = new JLabel("Explorar");
				lblExplorar.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblExplorar);
		
				buttonPane = new JPanel();
				buttonPane.setBackground(Color.ORANGE);
				buttonPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				buttonPane.setBorder(null);
				GridBagConstraints gbc_buttonPane = new GridBagConstraints();
				gbc_buttonPane.fill = GridBagConstraints.BOTH;
				gbc_buttonPane.gridx = 0;
				gbc_buttonPane.gridy = 1;
				contentPane.add(buttonPane, gbc_buttonPane);
				buttonPane.setLayout(new GridLayout(1, 0, 0, 0));
	}

	protected void generateButtons(int rows, int columns) {
		Color borderLight = new Color(1f, 1f, 1f, 0.4f);
		Color borderDark = new Color(0, 0, 0, 0.4f);
		Border bordeLinea = new LineBorder(borderLight, 1);
		Font small = new Font("Arial Black", Font.BOLD, 15);
		Font big = new Font("Arial Black", Font.BOLD, 30);
		BevelBorder bevel = new BevelBorder(NORMAL, borderLight, borderDark);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				button = new JButton("");
				button.setName(i + " " + j);
				button.setBackground(Color.getHSBColor(getColorIncrement(((float)(i+j)/2), rows), 0.65f, 1f));
				button.setFont((rows > 10) ? small : big);
				button.setBorder(bevel);
				buttonPane.add(button);
				// getColorIncrement(((i > j) ? i : j), rows);
			}
		}
	}

	// TODO:convertir a privado
	public float getBrightIncrement(float i, float max) {
		float incremento = 0.644444f, cuenta = i / max;
		float baseBrightness = 0.35f;
		return baseBrightness + (cuenta * incremento);
	}

	private float getColorIncrement(float i, int max) {
		float incremento = 0.02f;
		float cuenta = i+0.1f/ max;
		float baseColor = 0.6f;
		return baseColor + (cuenta * incremento);
	}

}
