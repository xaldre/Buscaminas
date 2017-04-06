package modelo;

import control.Varios;

public class Tablero {
	private Casilla[][] casillas;

	public Tablero(int filas, int columnas) {
		super();
		generarTablero(filas, columnas);
	}

	public Tablero(int cuadrada) {
		this(cuadrada, cuadrada);
	}

	private void generarTablero(int filas, int columnas) {
		casillas = new Casilla[filas][columnas];
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				casillas[i][j] = new Casilla();
			}
		}
	}

	private void setCasillas(Casilla[][] casillas) {
		this.casillas = casillas;
	}

	public Casilla getCasilla(Coordenada coordenada) {
		return casillas[coordenada.getX()][coordenada.getY()];
	}

	public int length() {
		return casillas.length;
	}

	public boolean isValidCoordenada(Coordenada coordenada) {
		return comprobarLimites(coordenada.getX(), 0, casillas.length)
				&& comprobarLimites(coordenada.getY(), 0, casillas[0].length);
	}

	private boolean comprobarLimites(int x, int i, int length) {
		return x >= i && x < length;
	}

	private int getValidsCount(Coordenada[] adyacents) {
		int contador = 0;
		// Contamos las coordenadas validas
		for (Coordenada actual : adyacents) {
			if (isValidCoordenada(actual)) {
				contador++;
			}
		}
		return contador;
	}

	public Casilla[][] getCasillas() {
		return casillas;
	}

	public Coordenada[] getAdyacents(Coordenada coordenada) {
		int contador = 0;

		// Creamos un array de casillas en posiciones adyacentes
		Coordenada[] adyacents = new Varios().getAdyacentRange(coordenada);
		// Creamos el array con el numero de validas
		Coordenada[] valids = new Coordenada[getValidsCount(adyacents)];

		// Insertamos las validas en el nuevo array
		for (int i = 0, j = 0; i < adyacents.length; i++) {
			if (isValidCoordenada(adyacents[i])) {
				valids[j] = adyacents[i];
				j++;
			}
		}
		// for (Coordenada each : valids) {
		// if (isValidCoordenada(each)){
		// valids[contador++]=each;
		// }
		// }
		return valids;
	}

	public int getAdjacentMarkedCount(Coordenada coordenada) {
		int contador = 0;
		Coordenada[] adyacents = getAdyacents(coordenada);
		for (Coordenada coordenada2 : adyacents) {
			contador = (getCasilla(coordenada2).isMarcada()) ? contador + 1 : contador;
		}
		return contador;
	}

	public int getVeladasCount() {
		int cont = 0;
		for (Casilla[] casillas2 : casillas) {
			for (Casilla casilla : casillas2) {
				cont = (casilla.isVelada()) ? cont + 1 : cont;
			}
		}
		return cont;
	}

	public int getMinasCount() {
		int cont = 0;
		for (Casilla[] casillas2 : casillas) {
			for (Casilla casilla : casillas2) {
				cont = (casilla.isMina()) ? cont + 1 : cont;
			}
		}
		return cont;
	}
	
	public Casilla[] getMinas(){
		Casilla[] minas = new Casilla[getMinasCount()];
		int i = 0;
		for (Casilla[] casillas2 : casillas) {
			for (Casilla casilla : casillas2) {
				if (casilla.isMina()){
					minas[i++]=casilla;
				}
			}
		}
		return minas;
	}

	public boolean isMarkedArround(Coordenada coordenada, Casilla casilla) {
		return casilla.getMineCount() == getAdjacentMarkedCount(coordenada);
	}

	public boolean isEmpty(Casilla casilla) {
		return casilla.getMineCount() == 0 && !casilla.isMina();
	}

	// Unimplemented for time reasons
	// private int getCount(String str){
	// int cont=0;
	// for (Casilla[] casillas2 : casillas) {
	// for (Casilla casilla : casillas2) {
	// cont = (str) != null ? cont+ 1 : cont;
	// }
	// }
	// return cont;
	// }
}
