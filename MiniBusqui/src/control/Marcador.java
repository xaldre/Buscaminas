package control;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import modelo.Casilla;
import modelo.Coordenada;
import modelo.Tablero;

public class Marcador {
	
	private Tablero tablero;

	public Marcador(Tablero tablero) {
		super();
		this.tablero = tablero;
	}

	/**
	 * Marca y desmarca la casilla y coloca un icono de bandera encima de esta
	 * 
	 * @param boton
	 *            JButton que lleva las coordenadas para localizar la casilla
	 * @param tablero
	 *            Tablero donde buscamos la casilla mediante las coordenadas del
	 *            boton
	 */
	public void marcarCasilla(JButton boton) {
		// Creamos los iconos
		ImageIcon iconBig = new ImageIcon("icons/big_flag_red.png");
		ImageIcon icon = new ImageIcon("icons/flag_red.png");

		// Obtenemos la coordenada del boton introducido
		Coordenada coordenada = new Varios().obtenerCoordenada(boton);

		// Mediante la coordenada obtenemos la casilla
		Casilla casilla = tablero.getCasilla(coordenada);

		// Comprobamos si esta velada, si no lo esta no hacemos nada
		if (casilla.isVelada()) {
			// Comprobamos si esta marcada
			if (casilla.isMarcada()) {
				// Si lo esta le quitamos el icono
				boton.setIcon(null);
			} else {
				// Si no lo esta le ponemos el icono; pequeño para cuando tiene
				// mas de 10 filas y grande para cuando tiene menos
				boton.setIcon((tablero.length() < 11) ? iconBig : icon);
			}
			// Invertimos el estado de la casilla
			casilla.setMarcada(!casilla.isMarcada());
		}
	}
}
