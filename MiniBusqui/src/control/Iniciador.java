package control;

import java.util.Random;

import modelo.Casilla;
import modelo.Coordenada;
import modelo.Tablero;

public class Iniciador {
	private final static int MAX_LENGTH = 55;
	private final static int MIN_LENGTH = 5;
	private static final int DIFICULTY_THRESHOLD = 9;
	private int dificultad = 3;
	private int rows = 50;
	private Tablero tablero;

	int getRows() {
		return rows;
	}

	private void setRows(int rows) {
		this.rows = rows;
	}

	Tablero getTablero() {
		return tablero;
	}

	private void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public Iniciador() {
		normalize();
		tablero = new Tablero(rows);
		colocarMinas();
		contarMinasAlrededor();
	}

	private void contarMinasAlrededor() {
		Casilla[][] casillas = tablero.getCasillas();
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				Coordenada coordenada = new Coordenada(i, j);
				if (tablero.getCasilla(coordenada).isMina()) {
					incrementarContiguas(coordenada);
				}
			}
		}
	}

	private void incrementarContiguas(Coordenada coordenada) {
		Coordenada[] adyacents = tablero.getAdyacents(coordenada);
		for (Coordenada actual : adyacents) {
			if (!tablero.getCasilla(actual).isMina()) {
				incrementarValor(actual);
			}
		}
	}

	private void incrementarValor(Coordenada actual) {
		tablero.getCasilla(actual).setMineCount(tablero.getCasilla(actual).getMineCount() + 1);
	}

	private void colocarMinas() {
		for (int i = 0; i < rows * getDificulty(); i++) {
			colocarUnaMina();
		}
	}

	private int getDificulty() {
		int incremento = 1;
		if (rows > 10) {
			incremento = 2;
		} else if (rows > 20) {
			incremento = 3;
		}
		return dificultad * incremento;
	}

	private void colocarUnaMina() {
		Casilla casilla = new Casilla();
		do {
			Coordenada coordenada = sortearCoordenada();
			casilla = tablero.getCasilla(coordenada);
		} while (casilla.isMina());
		casilla.setMina(true);
	}

	private Coordenada sortearCoordenada() {
		return new Coordenada(sortearPosicion(), sortearPosicion());
	}

	private int sortearPosicion() {
		Random random = new Random();
		return random.nextInt(tablero.length());
	}

	private void normalize() {
		rows = (rows > MAX_LENGTH) ? MAX_LENGTH : rows;
		rows = (rows < MIN_LENGTH) ? MIN_LENGTH : rows;
		dificultad = (rows<DIFICULTY_THRESHOLD)?1:dificultad;
	}
	
	boolean isEnded(){
		return isVictoria()||isDerrota();
	}

	private boolean isDerrota() {
		Casilla[] minas = tablero.getMinas();
		int contador = 0;
		for (Casilla casilla : minas) {
			if(!casilla.isVelada()){
				contador++;
			}
		}
		return contador==minas.length;
	}

	private boolean isVictoria() {
		return tablero.getMinasCount()==tablero.getVeladasCount();
	}
}
