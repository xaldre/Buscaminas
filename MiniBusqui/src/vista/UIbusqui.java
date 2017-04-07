package vista;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JMenu;
import java.awt.BorderLayout;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class UIbusqui extends JFrame {

	protected JPanel contentPane;
	protected JButton button;
	protected JPanel buttonPane;
	private JMenuBar menuBar;
	protected JLabel lblTitulo;
	private JMenu mnTama;
	protected JMenuItem mntmFacil;
	protected JMenuItem mntmMedio;
	protected JMenuItem mntmDificil;
	protected JMenuItem mntmExtremo;
	private JMenu mnColor;
	protected JMenuItem mntmRainbow;
	protected JMenuItem mntmAzul;
	protected JMenuItem mntmRojo;
	protected JMenuItem mntmGris;
	private JMenu mnOpciones;
	protected JMenuItem mntmRestart;
	protected Color color;
	protected boolean rainbow=true;

	
	/**
	 * Create the frame.
	 */
	public UIbusqui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		mntmRestart = new JMenuItem("Reinicio");
		mnOpciones.add(mntmRestart);
		
		mnTama = new JMenu("Dificultad");
		menuBar.add(mnTama);
		
		mntmFacil = new JMenuItem("Facil");
		mntmFacil.setHorizontalAlignment(SwingConstants.LEFT);
		mnTama.add(mntmFacil);
		
		mntmMedio = new JMenuItem("Medio");
		mnTama.add(mntmMedio);
		
		mntmDificil = new JMenuItem("Dificil");
		mnTama.add(mntmDificil);
		
		mntmExtremo = new JMenuItem("Pls stahp");
		mnTama.add(mntmExtremo);
		
		mnColor = new JMenu("Color");
		menuBar.add(mnColor);
		
		mntmRainbow = new JMenuItem("Rainbow");
		mnColor.add(mntmRainbow);
		
		mntmAzul = new JMenuItem("Azul");
		mnColor.add(mntmAzul);
		
		mntmGris = new JMenuItem("Gris");
		mnColor.add(mntmGris);
		
		mntmRojo = new JMenuItem("Rojo");
		mnColor.add(mntmRojo);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{430, 0};
		gbl_contentPane.rowHeights = new int[]{14, 393, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
				
				lblTitulo = new JLabel("BUSCAMINAS");
				lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
				GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
				gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
				gbc_lblTitulo.gridx = 0;
				gbc_lblTitulo.gridy = 0;
				contentPane.add(lblTitulo, gbc_lblTitulo);
		
				resetButtonPane();
	}

	protected void resetButtonPane() {
		buttonPane = new JPanel();
		buttonPane.setBackground(new Color(240, 240, 240));
		buttonPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonPane.setBorder(null);
		GridBagConstraints gbc_buttonPane = new GridBagConstraints();
		buttonPane.setLayout(new GridLayout(1, 0, 0, 0));
		gbc_buttonPane.fill = GridBagConstraints.BOTH;
		gbc_buttonPane.gridx = 0;
		gbc_buttonPane.gridy = 1;
		contentPane.add(buttonPane, gbc_buttonPane);
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
				if (rainbow){
					button.setBackground(Color.getHSBColor(getColorIncrement(((float)(i+j)/2), rows), 0.65f, 1f));
				} else {
					button.setBackground(color);
				}
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
