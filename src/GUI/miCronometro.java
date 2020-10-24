package GUI;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Clase miCronometro que diseña un cronometro con botones para controlarlo
 * @author Maslein Martín
 */
@SuppressWarnings("serial")
public class miCronometro extends JFrame{
	
	public static void main(String [] args) {
		
	} 
	
	/**
	 * Boton que pausa el cronometro
	 */
	private JButton btnPause;
	
	/**
	 * Boton que inicia el cronometro
	 */
    private JButton btnStart;
    
    /**
     * Boton que detiene el cronometro
     */
    private JButton btnStop;
    
    // Label que modela el numero --->  (0)0:00
    private JLabel minuto1;
    // Label que modela el numero --->  0(0):00
    private JLabel minuto2;
    // Label que modela el parentesis --->  00(:)00
    private JLabel dosPuntos;
    // Label que modela el numero --->  00:(0)0
    private JLabel segundo1;
    // Label que modela el numero --->  00:0(0)
    private JLabel segundo2;
    
    /**
     * Imagenes que contienen las rutas de las imagenes que representaran los numeros
     * del 0 al 9 para diseñar el cronometro
     */
    private ImageIcon[] imagenes;
    
    /**
     * Objeto de tipo Timer que representaran los tics para establecer el tiempo transcurrido
     */
    private Timer t;
    
    /**
     * Minutos, segundos y milisegundos
     */
    private int m, s, cs;
    
    /**
     * Comportamiento que tendran los minutos, segundos y milisegundos
     */
    private ActionListener acciones = new ActionListener(){
    
    	// A los 100 milisegundos, aumenta 1 segundo
    	// A los 60 segundos aumenta 1 minuto
        public void actionPerformed(ActionEvent ae) {
            cs++; 
            if(cs == 100){
                cs = 0;
                s++;
            }
            if(s == 60) {
                s = 0;
                m++;
            }
            // Actualiza los label del tiempo
            actualizarTiempo();
        }
    };
	
    /**
     * Actualiza los label segundo1/2 y minuto1/2 para que se corresponda con el tiempo transcurrido
     */
    private void actualizarTiempo() {
    	
    	if(s < 10) {
    		segundo1.setIcon(imagenes[s]);
    		segundo2.setIcon(imagenes[0]);
    	} else {
    		segundo1.setIcon(imagenes[s%10]);
    		segundo2.setIcon(imagenes[s/10]);
    	}
    	
		if(m < 10) {
			minuto1.setIcon(imagenes[m]);
    		minuto2.setIcon(imagenes[0]);
		} else {
			minuto1.setIcon(imagenes[m%10]);
    		minuto2.setIcon(imagenes[m/10]);
		}
    }
    
    /**
     * Evento que se ejecuta al clickear el btnStart e inicia el cronometro
     * @param evt
     */
    private void btnStartActionPerformed(ActionEvent evt) {
        t.start();
        btnStart.setEnabled(false);
        btnStart.setText("Reanudar");
        btnPause.setEnabled(true);
        btnStop.setEnabled(true);
    }
    
    /**
     * Evento que se ejecuta al clickear el btnPause y pausa el cronometro
     * @param evt
     */
    private void btnPauseActionPerformed(ActionEvent evt) {
        t.stop();
        btnStart.setEnabled(true);
        btnPause.setEnabled(false);
    }

    /**
     * Evento que se ejecuta al clickear el btnStop y restablecer el cronometro en 00:00
     * @param evt
     */
    private void btnStopActionPerformed(ActionEvent evt) {
        if(t.isRunning()) {
            t.stop();
            btnStart.setEnabled(true);
        }
        btnStart.setText("Iniciar");
        btnPause.setEnabled(false);
        btnStop.setEnabled(false);
        m=0; s=0; cs=0;
        actualizarTiempo();
    }
    
    /**
     * Constructor del cronometro
     * @param panelInferior Panel donde se copiara graficamente el cronometro 
     */
	public miCronometro(JPanel panelInferior) {
		initComponents(panelInferior);
        setLocationRelativeTo(null);
        t = new Timer(10, acciones);
        this.setEnabled(true);
	}
	
	/**
	 * Retorna el timer del cronometro
	 * @return t
	 */
	public Timer getTimer() {
		return t;
	}
	
	/**
	 * Dado el String ruta, redimensiona la imagen que alli se encuentra para que se
	 * ajuste al tamaño del label
	 * @param ruta Ruta de acceso a la imagen
	 * @return image Imagen redimensionada
	 */
	private ImageIcon redimensionar(String ruta) {
		ImageIcon image = new ImageIcon(this.getClass().getResource(ruta));
    	Image imgEscalada = image.getImage().getScaledInstance(minuto1.getWidth(), minuto1.getHeight(), Image.SCALE_SMOOTH);
    	image = new ImageIcon(imgEscalada);
    	return image;
    }

	/**
	 * Inicializa todos los componentes del cronometro
	 * @param panelInferior Panel donde se copiaran graficamente los componentes
	 */
	private void initComponents(JPanel panelInferior) {
		
		// Inicializo arreglo que contendra las imagenes de los numeros que representan el tiempo
		imagenes = new ImageIcon[10];
		
		// Inicializo los botones
        btnStart = new JButton();
        btnPause = new JButton();
        btnStop = new JButton();

        // Seteo el listener al boton Iniciar
        btnStart.setText("Iniciar");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        
        // Seteo el listener al boton Pausar
        btnPause.setText("Pausar");
        btnPause.setEnabled(false);
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        // Seteo el listener al boton Detener
        btnStop.setText("Detener");
        btnStop.setEnabled(false);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        
        // Inicializo los label del tiempo
        minuto1 = new JLabel();
        minuto2 = new JLabel();
        segundo1 = new JLabel();
        segundo2 = new JLabel();
        dosPuntos = new JLabel();
        
        // Seteo el tamaño de los label
        minuto1.setSize(25, 25);
        minuto2.setSize(25, 25);
        segundo1.setSize(25, 25);
        segundo2.setSize(25, 25);
        dosPuntos.setSize(25, 25);
        
        // Redimensiono la imagen inicial al tamaño de los label
        minuto1.setIcon(redimensionar("/img/0.png"));
        minuto2.setIcon(redimensionar("/img/0.png"));
        segundo1.setIcon(redimensionar("/img/0.png"));
        segundo2.setIcon(redimensionar("/img/0.png"));
        dosPuntos.setIcon(redimensionar("/img/dosPuntos.png"));
        
        // Inserto en el arreglo cada una de las imagenes de los numeros que representan el tiempo
        imagenes [0] = redimensionar("/img/0.png");
        imagenes [1] = redimensionar("/img/1.png");
        imagenes [2] = redimensionar("/img/2.png");
        imagenes [3] = redimensionar("/img/3.png");
        imagenes [4] = redimensionar("/img/4.png");
        imagenes [5] = redimensionar("/img/5.png");
        imagenes [6] = redimensionar("/img/6.png");
        imagenes [7] = redimensionar("/img/7.png");
        imagenes [8] = redimensionar("/img/8.png");
        imagenes [9] = redimensionar("/img/9.png");
        
        // Añado al panel inferior del programa principal, los componentes del cronometro
        panelInferior.add(btnStart);
        panelInferior.add(btnPause);
        panelInferior.add(btnStop);
        panelInferior.add(minuto2);
        panelInferior.add(minuto1);
        panelInferior.add(dosPuntos);
        panelInferior.add(segundo2);
        panelInferior.add(segundo1);
	}
}