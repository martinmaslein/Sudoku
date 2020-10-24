package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Logica.Celda;
import Logica.InvalidSudokuException;
import Logica.Sudoku;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;
import java.util.Scanner;
import java.awt.Color;

/**
 * Clase que modela graficamente el Sudoku para uso del usuario
 * @author Maslein Martin
 */

public class GUI {

	private JFrame Sudoku;
	private Sudoku juego;
	private miCronometro cronometro;
	private JButton [][] matrizBotones;
	
	
	/**
	 * Lanza la aplicacion
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.Sudoku.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Llama al metodo initialize() que inicializa el juego
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Crea el frame e inicializa los componentes que contiene
	 */
	private void initialize() {
		// Creo el juego
		try {
			juego = new Sudoku();
		} catch (InvalidSudokuException e1) {
			System.out.println(e1.getMessage());
		}
		matrizBotones = new JButton[juego.dimension][juego.dimension];
		
		// Inicializo panel principal y lo agrega al Frame
		Sudoku = new JFrame();
		Sudoku.setTitle("Sudoku");
		Sudoku.setBounds(100, 100, 548, 417);
		Sudoku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Sudoku.getContentPane().setLayout(new BorderLayout(0, 0));	
		
		// Creo el panel que contendra el tablero
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Color.DARK_GRAY);
		Sudoku.getContentPane().add(panelInferior, BorderLayout.SOUTH);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.DARK_GRAY);
		Sudoku.getContentPane().add(panelSuperior, BorderLayout.NORTH);
		
		// Creo el cronometro que se utilizara para representar el tiempo de juego
		cronometro = new miCronometro(panelInferior);
		cronometro.setEnabled(true);
		
		// Uso un gridLayout para el tablero con la dimension del juego
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.DARK_GRAY);
		Sudoku.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(juego.getDimension(), juego.getDimension() , 5, 5));
		
		JButton reglas = new JButton("Reglas de juego");
		panelSuperior.add(reglas);
		reglas.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				
				JOptionPane.showMessageDialog(panelCentral, "Para cambiar cada casilla de valor, deberas apretar la casilla y "
						+ " presionar la tecla del numero que desea ingresar en el tablero");
				JOptionPane.showMessageDialog(panelCentral, "Si desea vaciar una casilla de juego, debera apretar la tecla con el numero 0");
				JOptionPane.showMessageDialog(panelCentral, "¡ Suerte !");
			}  
		});  
		
		// Recorro todo el tablero para ajustar imagenes y establecer los listener
		for (int i = 0; i <juego.getDimension(); i++) {
			for(int j =0; j<juego.getDimension(); j++) {				
				
				Celda c = juego.getCelda(i,j);
				ImageIcon imagen = c.getGrafico().getImagen();	
				JButton boton = new JButton();
				panelCentral.add(boton);
				
				
				if(c.getEsInicio() == true) {
					
					boton.addComponentListener(new ComponentAdapter() {	
						
						// Redimensiono la imagen para ajustar al tamaño del boton
						public void componentResized(ComponentEvent e) {
							reDimensionar(boton, imagen);
							boton.setIcon(imagen);
						}
					});
					
				} else {
					
					boton.addComponentListener(new ComponentAdapter() {	
						
						public void componentResized(ComponentEvent e) {
							reDimensionar(boton, imagen);
							boton.setIcon(imagen);
						}
					});
					
					// keyListener que al presionar una tecla numerica cambia el valor de la celda por el numero
					boton.addKeyListener(new KeyListener() {

						public void keyPressed(KeyEvent e) {
							Integer i = Integer.valueOf(e.getKeyChar());
							switch(i) {
								case(48) : {
									// Setea la celda vacia
									c.setNumero(0);
									reDimensionar(boton,c.getGrafico().getImagen());
									break;
								}
								case(49) : {
									c.setNumero(1);
									reDimensionar(boton,c.getGrafico().getImagen());
									break;		
								} 
								case(50) : {
									c.setNumero(2);
									reDimensionar(boton,c.getGrafico().getImagen());
									break;
								}
								case(51) : {
									c.setNumero(3);
									reDimensionar(boton,c.getGrafico().getImagen());
									break;
								}
								case(52) : {
									c.setNumero(4);
									reDimensionar(boton,c.getGrafico().getImagen());
									break;
								}
								case(53) : {
									c.setNumero(5);
									reDimensionar(boton,c.getGrafico().getImagen());
									break;
								}
								case(54) : {
									c.setNumero(6);
									reDimensionar(boton,c.getGrafico().getImagen());
									break;
								} 
								case(55) : {
									c.setNumero(7);
									reDimensionar(boton,c.getGrafico().getImagen());
									break;
								}
								case(56) : {
									c.setNumero(8);
									reDimensionar(boton,c.getGrafico().getImagen());
									break;
								}
								case(57) : {
									c.setNumero(9);
									reDimensionar(boton,c.getGrafico().getImagen());
									break;
								}
								default: {
									// Si el usuario ingresa una tecla incorrecta le saldra una aviso
									JOptionPane.showMessageDialog(panelCentral, "Presione un numero del 1 al 10.");
								}
							}
							
								// Modifica las celdas del tablero de manera que las celdas que no tengan conflicto,
								// tendran un marco verde y las que si lo tengan, un marco rojo
								Celda [][] conflictos = juego.buscarConflicto();
								for(int a=0; a < juego.dimension; a++) {
									for(int b=0; b < juego.dimension; b++) {
										if(conflictos[a][b] != null && juego.getCelda(a,b).getNumero() != 0) {
											Border line = new LineBorder(Color.RED);
											Border margin = new EmptyBorder(5, 15, 5, 15);
											Border compound = new CompoundBorder(line, margin);
											matrizBotones[a][b].setBorder(compound);
										} else {
											if(juego.getCelda(a,b).getNumero() != null && juego.getCelda(a,b).getNumero() != 0) {
												Border line = new LineBorder(Color.GREEN);
												Border margin = new EmptyBorder(5, 15, 5, 15);
												Border compound = new CompoundBorder(line, margin);
												matrizBotones[a][b].setBorder(compound);
											}
										}
									}
								}
								
								// Siempre a una celda vacia le seteo borde blanco
								if(c.getNumero() == 0) {
									Border line = new LineBorder(Color.WHITE);
									Border margin = new EmptyBorder(5, 15, 5, 15);
									Border compound = new CompoundBorder(line, margin);
									matrizBotones[c.getFila()][c.getColumna()].setBorder(compound);
								}
							
								// Recorro el archivo "sudoku.txt" y lo comparo con el tablero del usuario
								// para saber si ha completado exitosamente el juego
								boolean gano = true;
								InputStream in = GUI.class.getClassLoader().getResourceAsStream("archivo/sudoku.txt");
								Scanner lector = new Scanner(in);
							
								for (int a = 0; a < 9 && gano; a++) {
									for (int b = 0; b < 9 && gano; b++) {
										if(juego.getCelda(a,b).getNumero() != null) {
											if(juego.getCelda(a,b).getNumero() != lector.nextInt()) {
												gano = false;
											}
										} else {
											gano = false;
										}
										
									}
								}
								
								lector.close();
								
								if(gano) {
									// Mensaje de salida si el usuario gano el juego
									JOptionPane.showMessageDialog(panelCentral, "Felicitaciones, ¡has ganado!");
									System.exit(0);
								}
								
						}

						public void keyReleased(KeyEvent arg0) {
							// unimplemented metods
						}

						public void keyTyped(KeyEvent arg0) {
							// unimplemented metods
						}						
					});	
				}
				matrizBotones[i][j] = boton;								
			}
		}
	}
	
	// Ajusta la imagen para setear en el boton
	private void reDimensionar(JButton boton, ImageIcon imagen) {
		Image image = imagen.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(boton.getWidth(), boton.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			imagen.setImage(newimg);
			boton.repaint();
		}
	}
}