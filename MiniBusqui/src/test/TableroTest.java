package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Casilla;
import modelo.Coordenada;
import modelo.Tablero;

public class TableroTest {

	@Test
	public void testLength() {
		Tablero tablero = new Tablero(20);
		assertEquals(20, tablero.length());
	}
	
	@Test
	public void testValidarCoordenada(){
		Tablero instancia = new Tablero(20);
		Coordenada[] validas = {new Coordenada(0,0),new Coordenada(19,19)};
		Coordenada[] erroneas = {new Coordenada(-1,-1),new Coordenada(20,20)};
		
		for (Coordenada coordenada : validas) {
			assertTrue(instancia.isValidCoordenada(coordenada));
		}
		for (Coordenada coordenada : erroneas) {
			assertFalse(instancia.isValidCoordenada(coordenada));
		}
	}
	
	@Test
	public void testGetAdyacentMarkedCount(){
		Tablero tablero = new Tablero(3);
		Casilla[][] casillas = tablero.getCasillas();
		for (Casilla[] casillas2 : casillas) {
			for (Casilla casilla : casillas2) {
				casilla.setMarcada(true);
			}
		}
		Coordenada coordenada = new Coordenada(1, 1);
		assertEquals(8, tablero.getAdjacentMarkedCount(coordenada));
	}
	
	@Test
	public void testGetMinas(){
		Tablero tablero = new Tablero(3);
		Casilla[][] casillas = tablero.getCasillas();
		for (Casilla[] casillas2 : casillas) {
			for (Casilla casilla : casillas2) {
				casilla.setMina(true);
			}
		}
		assertEquals(9, tablero.getMinasCount());
	}
	

}
