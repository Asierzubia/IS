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

public class CreateCarreraGUI extends JFrame{
	
	private JCalendar calendarioEventos;
	private JPanel contentPane;
	private JLabel lblTextoFinal;
	private JLabel lblEjemplo;
	private JTextField textCarrera;
	private JLabel lblDescripcion; 
	private JButton btnCrear;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCarreraGUI frame = new CreateCarreraGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CreateCarreraGUI() {
		setTitle("Crear carrera");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 517, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCalendarioEventos());
		contentPane.add(getLblTextoFinal());
		contentPane.add(getLblEjemplo());
		contentPane.add(getTextCarrera());
		contentPane.add(getLblDescripcion());
		contentPane.add(getBotonCrear());
	
	}
	
	private JCalendar getCalendarioEventos() {
		if(calendarioEventos == null) {
			calendarioEventos = new JCalendar();
			calendarioEventos.setBounds(150, 99, 331, 166);
		}
		return calendarioEventos;
	}
	private JLabel getLblTextoFinal() {
		if (lblTextoFinal == null) {
			lblTextoFinal = new JLabel("");
			lblTextoFinal.setBounds(12, 288, 313, 15);
		}
		return lblTextoFinal;
	}
	private JLabel getLblEjemplo() {
		if (lblEjemplo == null) {
			lblEjemplo = new JLabel("Por ejemplo : 100 metros");
			lblEjemplo.setBounds(12, 72, 228, 15);
		}
		return lblEjemplo;
	}
	private JLabel getLblDescripcion() {
		if (lblDescripcion == null) {
			lblDescripcion = new JLabel("Descripcion de la nueva carrera");
			lblDescripcion.setBounds(12, 52, 228, 15);		}
		return lblDescripcion;
	}
	private JTextField getTextCarrera() {
		if (textCarrera == null) {
			textCarrera = new JTextField();
			textCarrera.setBounds(258, 50, 223, 19);
			textCarrera.setColumns(10);
		}
		return textCarrera;
	}
	
	private JButton getBotonCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Crear");
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BLFacade facade = Inicio.getBusinessLogic();
					String descripcion = textCarrera.getText();
					Date fecha = new Date();
					boolean error = false;
					System.out.println(Calendar.getInstance().get(Calendar.YEAR));
					if(calendarioEventos.getYearChooser().getValue()< Calendar.getInstance().get(Calendar.YEAR) || calendarioEventos.getYearChooser().getValue() == 0) {
						lblTextoFinal.setText("El año es incorrecto.");
						lblTextoFinal.setForeground(Color.RED);
						error = true;
					}else if(calendarioEventos.getMonthChooser().getMonth()< Calendar.getInstance().get(Calendar.MONTH)) { 
						lblTextoFinal.setText("El mes introducido no es valido"); 
						lblTextoFinal.setForeground(Color.RED); 
						error = true; 
					}else if(calendarioEventos.getDayChooser().getDay()< Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) { 
						lblTextoFinal.setText("El dia introducido no es valido"); 
						lblTextoFinal.setForeground(Color.RED); 
						error = true; 
					}else{ 
						fecha = UtilDate.trim(new Date(calendarioEventos.getCalendar().getTime().getTime())); 
					} 
					if(!descripcion.equals("") && !error) {
						if(facade.anadirCarrera(descripcion, fecha)) {
							lblTextoFinal.setText("La carrera se ha añadido correctamente.");
							lblTextoFinal.setForeground(Color.GREEN);
						}else {
							lblTextoFinal.setText("La carrera no se ha podido añadir.");
							lblTextoFinal.setForeground(Color.RED);
						}
					}else {
						if(!error) {
							lblTextoFinal.setText("Rellene todos los campos (descripción y fecha).");
							lblTextoFinal.setForeground(Color.RED);
						}
					}	
				}
			});
			btnCrear.setBounds(364, 289, 117, 25);
		}
		return btnCrear;
	}
		
}
