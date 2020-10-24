package Logica;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;

import GUI.GUI;

/**
 * Clase Sudoku que representa el tablero de juego de 9x9 con Celdas
 * @author Maslein Martín
 */
public class Sudoku  {
	
	public static void main (String [] args) {
		
	}
	
	/**
	 * Matriz de celdas que representan al tablero de juego
	 */
	private Celda [][] tablero;
	
	/**
	 * Dimension del tablero
	 */
	public final int dimension = 9;
	
	
	/**
	 * Constructor del Sudoku, inicializa el tablero de celdas con una dimension de 9x9, 
	 * cada una de las celdas inicializadas provienen de un archivo leido llamado "Sudoku.txt"
	 */
	public Sudoku() throws InvalidSudokuException {
		tablero = new Celda[dimension][dimension];
		
		InputStream in = GUI.class.getClassLoader().getResourceAsStream("archivo/sudoku.txt");
		Scanner lector = new Scanner(in);
		
		// Recorre el archivo e inicializa todas las celdas con sus respectivos numeros
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				tablero[i][j] = new Celda(lector.nextInt());
				// Celdas con texturas grises unicamente por ser las que inicialmente estan en el tablero
				// Las inicializo en esta y de esta manera porque es la unica vez que se utilizan
				tablero[i][j].getGrafico().setImagen(new ImageIcon(getClass().getResource("/img/"+tablero[i][j].getNumero()+"G.png")));
				tablero[i][j].setFila(i);
				tablero[i][j].setColumna(j);
				tablero[i][j].setCeldaInicio(true);
			}
		}

		// Cierro el archivo
		lector.close();
		
		// Compruebo que el sudoku tenga solucion
		Celda[][] sudokuValida = buscarConflicto();
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (sudokuValida[i][j] != null)
					throw new InvalidSudokuException();
			}
		}
		
		// Re-inicializo algunas celdas para comenzar el juego
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				Random random = new Random();
				double rndValue = random.nextDouble();
				if (rndValue <= 0.7) {
					tablero[i][j] = new Celda(0);
					tablero[i][j].setFila(i);
					tablero[i][j].setColumna(j);
				}
			}
		}
	}
	
	public void nuevaCelda(Celda c) {
		tablero[c.getFila()][c.getColumna()] = new Celda();
	}
	
	/**
	 * Obtiene la celda del tablero en la posicion i,j
	 * @param i
	 * @param j
	 * @return celda en la fila i y en la columna j
	 */
	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}
	
	/**
	 * Setea en una celda en la fila i y en la columna j, el numero n
	 * @param i fila a insertar
	 * @param j columna a insertar
	 * @param n numero a insertar
	 */
	public void setCelda(int i, int j, int n) {
		this.tablero[i][j].setNumero(n);
	}
	
	/**
	 * Retorna la dimension del tablero de juego
	 * @return dimension
	 */
	public int getDimension() {
		return dimension;
	}	
	
	/**
	 * Genero y devuelvo una matriz que contiene a las celdas que generan conflicto en el juego,
	 * es decir que tienen numeros repetidos en columnas, filas o regiones
	 * @return salida Matriz con celdas erroneas
	 */
	public Celda[][] buscarConflicto() {
		Celda[][] salida = new Celda[dimension][dimension];

		for (int fila = 0; fila < this.dimension; fila++) {
			for (int columna = 0; columna < dimension; columna++) {
				Celda c = getCelda(fila, columna);

				if (c.getNumero() != null) {

					// Recorro la fila
					for (int j = 0; j < this.dimension; j++) {
						if (tablero[fila][j] != c && tablero[fila][j].getNumero() == c.getNumero()) //
							salida[fila][j] = tablero[fila][j];
					}

					// Recorro la columna
					for (int i = 0; i < this.dimension; i++) {
						if (tablero[i][columna] != c && tablero[i][columna].getNumero() == c.getNumero()) //
							salida[i][columna] = tablero[i][columna];
					}

					// Recorro regiones
					int indiceFila = 0;
					int indiceColumna = 0;

					// Segun la posicion en la matriz, el indice que se suma a la iteracion
					switch (fila) {
						case 0:
						case 1:
						case 2:
							indiceFila = 0;
							break;
						case 3:
						case 4:
						case 5:
							indiceFila = 3;
							break;
						case 6:
						case 7:
						case 8:
							indiceFila = 6;
							break;
						default:
							break;
					}

					switch (columna) {
						case 0:
						case 1:
						case 2:
							indiceColumna = 0;
							break;
						case 3:
						case 4:
						case 5:
							indiceColumna = 3;
							break;
						case 6:
						case 7:
						case 8:
							indiceColumna = 6;
							break;
						default:
							break;
					}

					for (int i = indiceFila; i < indiceFila + 3; i++) {
						for (int j = indiceColumna; j < indiceColumna + 3; j++) {
							if (tablero[i][j] != c && tablero[i][j].getNumero() == c.getNumero())
								salida[i][j] = salida[i][j];
						}
					}
				}
			}
		}
		return salida;
	}
}