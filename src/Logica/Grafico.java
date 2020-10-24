package Logica;

import javax.swing.ImageIcon;

/**
 * Clase Grafico que representa la imagen del numero una celda
 * @author Maslein Martin
 */
public class Grafico {
	
	/**
	 * Imagen que contiene la celda para representar al numero
	 */
	private ImageIcon imagen;
	
	/**
	 * Arreglo de String que contiene las rutas de cada imagen dentro de la carpeta del juego
	 */
	private String[] imagenes;
	
	/**
	 * Constructor vacio de Grafico, inicializa una imagen vacia y el arreglo con
	 * las rutas de cada imagen de cada numero
	 */
	public Grafico() {
		this.imagen = new ImageIcon();
		this.imagenes = new String[]{"/img/celdaVacia.png","/img/uno.png","/img/dos.png","/img/tres.png","/img/cuatro.png","/img/cinco.png",
				  "/img/seis.png","/img/siete.png","/img/ocho.png","/img/nueve.png"};
	}
	
	/**
	 * Actualiza la imagen de la celda
	 * @param indice al que tiene que acceder dentro de imagenes para actualizar la imagen
	 */
	public void actualizar(int indice) {
		if (indice < imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagenes[indice]));
			imagen.setImage(imageIcon.getImage());
		}
	}
	
	/**
	 * Retorna el String que indica la ruta de la imagen del numero i
	 * @param i Indice dentro del arreglo imagenes al que acceder
	 * @return salida String de la ruta a la que debe acceder para obtener su imagen
	 */
	public String getNumero(int i) {
		String salida;
		if(i < 10 && i > -1) {
			System.out.println("GET NUMERO CON INDICE "+i);
			salida = imagenes[i];
		} else
			salida = null;
		return salida;
	}
	
	/**
	 * Retorna imagen del numero de la celda
	 * @return imagen
	 */	
	public ImageIcon getImagen() {
		return this.imagen;
	}
	
	/**
	 * Setea imagen a la celda
	 * @param imagen 
	 */
	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}
	
	/**
	 * Retorna el arreglo de las rutas de las imagenes
	 * @return imagenes
	 */
	public String[] getImagenes() {
		return this.imagenes;
	}
	
	/**
	 * Setea arreglo de la ruta de las imagenes
	 * @param imagenes
	 */
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
}