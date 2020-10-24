package Logica;

@SuppressWarnings("serial")
public class InvalidSudokuException extends Exception {

	public InvalidSudokuException() {
		super("El sudoku es invalido, no tiene solucion");
	}
}