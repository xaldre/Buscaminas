package control;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
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
			} else if (isEmpty(casilla) || isMarkedArround(coordenada, casilla)) {
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

	private boolean isMarkedArround(Coordenada coordenada, Casilla casilla) {
		return casilla.getMineCount() == tablero.getAdjacentMarkedCount(coordenada);
	}

	private boolean isEmpty(Casilla casilla) {
		return casilla.getMineCount() == 0 && !casilla.isMina();
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
		} else if (isMarkedArround(coordenada, casilla)) {
			desvelar(coordenada);
		}
		paraUIBusqui.rellenarBoton();
	}

}
