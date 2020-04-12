package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CrearEventoGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton botonCrearEvento;
	private JTextField textoDescripción;
	private JLabel labelDescripción;
	private JLabel lblNewLabel;
	private JLabel lblequipoaequipob;
	private JCalendar calendarioEventos;
	private JLabel lblFechaDelEvento;
	private JLabel labelError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearEventoGUI frame = new CrearEventoGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CrearEventoGUI() {
		setTitle("Crear evento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBotonCrearEvento());
		contentPane.add(getTextoDescripción());
		contentPane.add(getLabelDescripción());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblequipoaequipob());
		contentPane.add(getLblFechaDelEvento());
		contentPane.add(getCalendarioEventos());
		contentPane.add(getLabelError());
	}

	private JButton getBotonCrearEvento() {
		if (botonCrearEvento == null) {
			botonCrearEvento = new JButton("Crear evento");
			botonCrearEvento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BLFacade facade = Inicio.getBusinessLogic();
					String descripcion = textoDescripción.getText();
					Date fecha = new Date();
					boolean error = false;
					System.out.println(Calendar.getInstance().get(Calendar.YEAR));
					if(calendarioEventos.getYearChooser().getValue()< Calendar.getInstance().get(Calendar.YEAR) || calendarioEventos.getYearChooser().getValue() == 0) {
						labelError.setText("El año es incorrecto.");
						labelError.setForeground(Color.RED);
						error = true;
					}else if(calendarioEventos.getMonthChooser().getMonth()< Calendar.getInstance().get(Calendar.MONTH)) { 
						labelError.setText("El mes introducido no es valido"); 
						labelError.setForeground(Color.RED); 
						error = true; 
					}else if(calendarioEventos.getDayChooser().getDay()< Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) { 
						labelError.setText("El dia introducido no es valido"); 
						labelError.setForeground(Color.RED); 
						error = true; 
					}else{ 
						fecha = UtilDate.trim(new Date(calendarioEventos.getCalendar().getTime().getTime())); 
					} 
					if(!descripcion.equals("") && !error) {
						if(facade.anadirEvento(descripcion, fecha)) {
							labelError.setText("El evento se ha añadido correctamente.");
							labelError.setForeground(Color.GREEN);
						}else {
							labelError.setText("El evento no se ha podido añadir.");
							labelError.setForeground(Color.RED);
						}
					}else {
						if(!error) {
							labelError.setText("Rellene todos los campos (descripción y fecha).");
							labelError.setForeground(Color.RED);
						}
					}	
				}
			});
			botonCrearEvento.setBounds(12, 274, 126, 25);
		}
		return botonCrearEvento;
	}
	
	private JTextField getTextoDescripción() {
		if (textoDescripción == null) {
			textoDescripción = new JTextField();
			textoDescripción.setBounds(203, 47, 166, 22);
			textoDescripción.setColumns(10);
		}
		return textoDescripción;
	}
	
	private JLabel getLabelDescripción() {
		if (labelDescripción == null) {
			labelDescripción = new JLabel("Descripción del evento");
			labelDescripción.setBounds(12, 31, 133, 16);
		}
		return labelDescripción;
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("(Los dos equipos que juegan)");
			lblNewLabel.setBounds(12, 50, 179, 16);
		}
		return lblNewLabel;
	}
	
	private JLabel getLblequipoaequipob() {
		if (lblequipoaequipob == null) {
			lblequipoaequipob = new JLabel("(EquipoA-EquipoB)");
			lblequipoaequipob.setBounds(12, 70, 126, 16);
		}
		return lblequipoaequipob;
	}
	
	private JCalendar getCalendarioEventos() {
		if(calendarioEventos == null) {
			calendarioEventos = new JCalendar();
			calendarioEventos.setBounds(150, 99, 331, 166);
		}
		return calendarioEventos;
	}
	
	private JLabel getLblFechaDelEvento() {
		if (lblFechaDelEvento == null) {
			lblFechaDelEvento = new JLabel("Fecha del evento");
			lblFechaDelEvento.setBounds(28, 169, 117, 16);
		}
		return lblFechaDelEvento;
	}
	
	private JLabel getLabelError() {
		if (labelError == null) {
			labelError = new JLabel("");
			labelError.setBounds(160, 278, 321, 16);
		}
		return labelError;
	}
}
