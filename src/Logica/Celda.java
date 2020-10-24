package Logica;

/**
 * Clase Celda que representa una Celda de un tablero de juego
 * @author Maslein Martin
 */

public class Celda {
	
	/**
	 *  Numero entero que contiene la celda
	 */
	private Integer numero;
	
	/**
	 *  Objeto grafico que contiene la imagen que representa al numero de la celda
	 */
	private Grafico grafico;
	
	/**
	 *  Fila del tablero a la que pertenece la celda
	 */
	private int fila;
	
	/**
	 *  Columna del tablero a la que pertenece la celda
	 */
	private int columna;
	
	/**
	 *  Variable control para saber si es una celda para inicializar el tablero de juego
	 */
	private boolean esInicio;

	/**
	 * Constructor de Celda sin parametros
	 */
	public Celda() {
		this.numero = null;
		this.grafico = new Grafico();
		esInicio = false;
	}
	
	/**
	 * Constructor de Celda sin parametros
	 * @param n Numero a ingresar dentro de la celda
	 */
	public Celda(int n) {
		numero = n;
		grafico = new Grafico();
		grafico.actualizar(n);
	}
	
	/**
	 * Retorna el numero de la celda
	 * @return numero de la celda
	 */
	public Integer getNumero() {
		return this.numero;
	}
	
	/**
	 * Setea el valor pasado por parametro en la celda
	 * @param valor a insertar en la celda;
	 */
	public void setNumero(Integer valor) {
		if (valor!=null && valor < 10) {
			this.numero = valor;
			this.grafico.actualizar(numero);
		} else {
			this.numero = null;
		}
	}
	
	/**
	 * Retorna el objeto de clase Grafico que contiene la celda 
	 * @return Grafico de la celda
	 */
	public Grafico getGrafico() {
		return this.grafico;
	}
	
	/**
	 * Setea el objeto de clase Grafico que contiene la celda
	 * @param g Grafico a setear
	 */
	public void setGrafico(Grafico g) {
		this.grafico = g;
	}
	
	/**
	 * Retorna si es celda de inicio de tablero o una celda corriente
	 * @return celdaInicio True si es celda inicializada o false si es una selda corriente
	 */
	public boolean getEsInicio() {
		return esInicio;
	}
	
	/**
	 * Setea a la celda si va a ser de inicio de tablero o una celda corriente
	 * @param b True si es celda inicializada o false si es celda corriente
	 */
	public void setCeldaInicio(boolean b) {
		esInicio = b;
	}
	
	/**
	 * Retorna la fila a la que pertenece la celda
	 * @return fila 
	 */
	public int getFila() {
		return fila;
	}
	
	/**
	 * Retorna la columna a la que pertenece la celda
	 * @return columna
	 */
	public int getColumna() {
		return columna;
	}
	
	/**
	 * Setea la fila a la que pertenece la celda
	 * @param f
	 */
	public void setFila(int f) {
		fila = f;
	}
	
	/**
	 * Setea la columna a la que pertenece la celda
	 * @param c
	 */
	public void setColumna(int c) {
		columna = c;
	}
}