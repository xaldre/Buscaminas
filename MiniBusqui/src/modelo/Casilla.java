package modelo;

public class Casilla {
	private boolean velada=true;
	private boolean marcada=false;
	private boolean mina=false;
	private int mineCount=0;
	private boolean firstExplosion=false;
	
	public boolean isMina() {
		return mina;
	}
	public void setMina(boolean mina) {
		this.mina = mina;
	}
	public int getMineCount() {
		return mineCount;
	}
	public void setMineCount(int numeroMinas) {
		this.mineCount = numeroMinas;
	}
	public boolean isVelada() {
		return velada;
	}
	public void setVelada(boolean velada) {
		this.velada = velada;
	}
	public boolean isMarcada() {
		return marcada;
	}
	public void setMarcada(boolean marcada) {
		this.marcada = marcada;
	}
	public boolean isFirstExplosion() {
		return firstExplosion;
	}
	public void setFirstExplosion(boolean firstExplosion) {
		this.firstExplosion = firstExplosion;
	}
	public boolean isEmpty() {
		return getMineCount() == 0 && !isMina();
	}
	
}
