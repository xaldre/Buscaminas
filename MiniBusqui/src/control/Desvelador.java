package control;

import javax.swing.JButton;

import modelo.Casilla;
import modelo.Coordenada;
import modelo.Tablero;

public class Desvelador {
	private Tablero tablero;
	private ParaUIBusqui paraUIBusqui;

	public Desvelador(Tablero tablero, ParaUIBusqui paraUIBusqui) {
		super();
		this.tablero = tablero;
		this.paraUIBusqui = paraUIBusqui;
	}

	private void desvelar(Coordenada coordenada) {
		Casilla casilla = tablero.getCasilla(coordenada);
		if (!casilla.isMarcada()) {
			casilla.setVelada(false);
			if (casilla.isMina()) {
				casilla.setFirstExplosion(true);
				desvelarMinas();
			} else if (casilla.isEmpty() || tablero.isMarkedArround(coordenada)) {
				Coordenada[] adyacent = tablero.getAdyacents(coordenada);
				for (Coordenada actual : adyacent) {
					Casilla casillaActual = tablero.getCasilla(actual);
					if (casillaActual.isVelada() && !casillaActual.isMarcada()) {
						casillaActual.setVelada(false);
						if (casillaActual.getMineCount() == 0 && !casilla.isMina()) {
							desvelar(actual);
						}
					}
				}
			}
		}

	}

	private void desvelarMinas() {
		Casilla[][] casillas = tablero.getCasillas();
		for (Casilla[] casillas2 : casillas) {
			for (Casilla casilla : casillas2) {
				if (casilla.isMina()) {
					casilla.setVelada(false);
				}
			}
		}
	}

	public void desvelarCasilla(JButton boton) {
		Coordenada coordenada = new Varios().obtenerCoordenada(boton);
		Casilla casilla = tablero.getCasilla(coordenada);
		if (casilla.isVelada()) {
			desvelar(coordenada);
		} else if (tablero.isMarkedArround(coordenada)) {
			desvelar(coordenada);
		}
		paraUIBusqui.rellenarBoton();
	}

}
