package control;

import java.util.StringTokenizer;

import javax.swing.JButton;

import modelo.Coordenada;

public class Varios {

	public Coordenada obtenerCoordenada(JButton boton) {
		// DE alguna forma entrego una coordenada
		StringTokenizer coords = new StringTokenizer(boton.getName());

		return new Coordenada(Integer.valueOf(coords.nextToken()), Integer.valueOf(coords.nextToken()));
	}

	public Coordenada[] getAdyacentRange(Coordenada coordenada) {
		Coordenada[] adyacents = new Coordenada[8];
		int[][] posiciones = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, };

		for (int i = 0; i < adyacents.length; i++) {
			adyacents[i] = new Coordenada(coordenada.getX() + posiciones[i][0], coordenada.getY() + posiciones[i][1]);
		}
		return adyacents;
	}

}
