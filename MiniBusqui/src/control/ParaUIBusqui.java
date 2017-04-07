package control;

import modelo.Casilla;
import modelo.Coordenada;
import modelo.Tablero;
import vista.UIbusqui;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;

public class ParaUIBusqui extends UIbusqui {

	// creo las filas y las columnas para poder asignarlas a las propiedades del
	// padre

	Iniciador iniciador;
	Varios varios = new Varios();
	// Tablero tablero;

	/*
	 * al crear UI le paso por parametros las filas y las columnas, para
	 * modificar el grid layout del buttonPane
	 */
	public ParaUIBusqui() {
		iniciador = new Iniciador();
		initialize();
	}

	private void initialize() {
		addButtonsUI();
		setResizable(false);
		Desvelador desvelador = new Desvelador(iniciador.getTablero(), this);
		Marcador marcador = new Marcador(iniciador.getTablero());

		// Eventos para todos los botones de la botonera
		Component[] botones = buttonPane.getComponents();
		for (Component component : botones) {
			MouseAdapter adapter = new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (!iniciador.isEnded()) {
						if (SwingUtilities.isRightMouseButton(e)) {
							marcador.marcarCasilla((JButton) e.getSource());
						}
						if (SwingUtilities.isLeftMouseButton(e)) {
							desvelador.desvelarCasilla((JButton) e.getSource());
						}
					}
				}
			};
			((JButton) component).addMouseListener(adapter);
		}
		MouseAdapter reset = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					Principal.restartApplication(null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		mntmRestart.addMouseListener(reset);

		mntmRainbow.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setRainbowStyle();
			}
		});

		mntmRojo.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setColor(Color.RED);
			}
		});
	}

	protected void endGame(boolean victoria) {
		if (victoria) {
			lblTitulo.setForeground(Color.GREEN);
			lblTitulo.setText("¡VICTORIA!");
		} else {
			lblTitulo.setForeground(Color.RED);
			lblTitulo.setText("¡HAS PERDIDO!");
		}
	}

	private void addButtonsUI() {
		generateButtons(iniciador.getRows(), iniciador.getRows());
		buttonPane.setLayout(new GridLayout(iniciador.getRows(), iniciador.getRows(), 0, 0));
		setBounds(100, 100, ((int) getDimensions(iniciador.getRows())),
				((int) getDimensions(iniciador.getRows()) + 50));
	}

	private double getDimensions(int i) {
		return 250.0 * (((1.0 / 17.0) * i) + (14.0 / 17.0));
	}

	void rellenarBoton() {
		Varios varios = new Varios();
		Component[] botones = buttonPane.getComponents();
		ImageIcon bomb = getBombIcon(botones[0]);
		for (Component boton : botones) {
			Coordenada coordenada = varios.obtenerCoordenada(((JButton) boton));
			if (!iniciador.getTablero().getCasilla(coordenada).isVelada()) {
				((JButton) boton).setBackground(new Color(225, 225, 225));
				Casilla actual = iniciador.getTablero().getCasilla(coordenada);
				if (actual.isMina()) {
					// Explosion
					((JButton) boton).setIcon(bomb);
					if (actual.isFirstExplosion()) {
						((JButton) boton).setBackground(Color.RED);
					}

				} else if (iniciador.getTablero().getCasilla(coordenada).getMineCount() > 0) {
					// Contador
					((JButton) boton)
							.setForeground(getColor(iniciador.getTablero().getCasilla(coordenada).getMineCount()));
					textoBoton(boton, coordenada);

				}
			}
		}
		if (iniciador.isEnded()) {
			endGame(iniciador.isVictoria());
		}
	}

	private ImageIcon getBombIcon(Component component) {
		ImageIcon srcExplosion = new ImageIcon("icons/mine.png");
		Image scaleExplosion = srcExplosion.getImage().getScaledInstance(component.getWidth(), component.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon explosionIcon = new ImageIcon(scaleExplosion, null);
		return explosionIcon;
	}

	private Color getColor(int mineCount) {
		Color color = new Color(0, 0, 0);
		switch (mineCount) {
		default:
			break;
		case 1:
			// Azul
			color = new Color(0, 67, 224);
			break;
		case 2:
			// Verde
			color = new Color(0, 181, 33);
			break;
		case 3:
			// Rojo
			color = new Color(219, 0, 0);
			break;
		case 4:
			// Azul oscuro
			color = new Color(0, 41, 140);
			break;
		case 5:
			// Verde oscuro
			color = new Color(0, 102, 18);
			break;
		case 6:
			// Rojo oscuro
			color = new Color(104, 0, 0);
			break;
		case 7:
			// Azul muy oscuro
			color = new Color(0, 20, 71);
			break;
		case 8:
			// Verde muy oscuro
			color = new Color(0, 58, 10);
			break;
		}
		return color;
	}

	private void textoBoton(Component boton, Coordenada coordenada) {
		((JButton) boton).setText(String.valueOf(iniciador.getTablero().getCasilla(coordenada).getMineCount()));
	}

	private void setRainbowStyle() {
		resetButtonPane();
		setRainbow(true);
		initialize();
		rellenarBoton();
	}

	private void setColor(Color color) {
		setRainbow(false);
		this.color = color;
		Component[] components = buttonPane.getComponents();
		for (Component boton : components) {
			if (iniciador.getTablero().getCasilla(varios.obtenerCoordenada((JButton) boton)).isVelada()) {
				((JButton) boton).setBackground(color);
			}
		}
	}

	private void setRainbow(boolean Boolean) {
		rainbow = Boolean;
	}
}
