package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Carrera;
import domain.Event;
import domain.Galgo;
import domain.Question;
import domain.Respuesta;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import java.util.Date; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Vector;
import java.beans.PropertyChangeListener; 
import java.beans.PropertyChangeEvent; 

public class ResponderApuestaGalgosGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEligeElEvento;
	private JLabel lblEligeLaQuestion;
	private JButton botonAñadir;
	private JComboBox<Carrera> comboCarrera;
	private JComboBox<Galgo> comboGalgos;
	private DefaultComboBoxModel<Carrera> listaCarreras = new DefaultComboBoxModel<Carrera>();
	private DefaultComboBoxModel<Galgo> listaGalgos = new DefaultComboBoxModel<Galgo>();
	private JLabel error;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResponderApuestaGalgosGUI frame = new ResponderApuestaGalgosGUI();
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
	public ResponderApuestaGalgosGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				BLFacade facade = Inicio.getBusinessLogic();
			
				Collection<Carrera> lista = facade.getAllCarreras();
				for(Carrera c : lista) listaCarreras.addElement(c);
			}
		});
		setTitle("Responder Apuesta Galgos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblEligeElEvento());
		contentPane.add(getLblEligeLaQuestion());
		contentPane.add(getBotonAñadir());
		contentPane.add(getComboCarrera());
		contentPane.add(getComboGalgos());

		contentPane.add(getError());	
		contentPane.add(comboGalgos);

	}
	private JLabel getLblEligeElEvento() {
		if (lblEligeElEvento == null) {
			lblEligeElEvento = new JLabel("Elige la carrera");
			lblEligeElEvento.setBounds(99, 48, 111, 16);
		}
		return lblEligeElEvento;
	}
	private JLabel getLblEligeLaQuestion() {
		if (lblEligeLaQuestion == null) {
			lblEligeLaQuestion = new JLabel("Elige el galgo");
			lblEligeLaQuestion.setBounds(99, 97, 111, 16);
		}
		return lblEligeLaQuestion;
	}
	private JButton getBotonAñadir() {
		if (botonAñadir == null) {
			botonAñadir = new JButton("Responder apuesta");
			botonAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BLFacade facade = Inicio.getBusinessLogic();
					Carrera carrera = (Carrera) comboCarrera.getSelectedItem();
					Galgo g = (Galgo) comboGalgos.getSelectedItem();
					if (carrera!=null) {
						if(g!=null) {
							if(carrera.getRespuestaCorrecta()==null) {
								error.setText("Se ha añadido la respuesta.");
								error.setForeground(Color.GREEN);
								facade.ResponderApuestaGalgos(carrera,g);
							}else {
								error.setText("No se ha podido añadir la respuesta.");
								error.setForeground(Color.RED);
							}
						}
					}
					else {
						error.setText("Rellene todos los campos.");
						error.setForeground(Color.RED);
					}
				}
			});
			botonAñadir.setBounds(238, 143, 151, 25);
		}
		return botonAñadir;
	}
	
	private JComboBox<Carrera> getComboCarrera() {
		if (comboCarrera == null) {
			comboCarrera = new JComboBox<Carrera>();
			comboCarrera.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	listaGalgos.removeAllElements();
			    	Carrera carrera = (Carrera) comboCarrera.getSelectedItem();
					if(carrera != null) {
						Vector<Galgo> lista = carrera.getGalgos();
						for(Galgo g : lista) listaGalgos.addElement(g);
					}
			    }
			});
			comboCarrera.setBounds(301, 45, 204, 22);
			comboCarrera.setModel(listaCarreras);
		}
		return comboCarrera;
	}
	
	private JComboBox<Galgo> getComboGalgos() {
		if (comboGalgos == null) {
			comboGalgos = new JComboBox<Galgo>();
			comboGalgos.setBounds(301, 94, 204, 22);
			comboGalgos.setModel(listaGalgos);
		}
		return comboGalgos;
	}
	
	

	private JLabel getError() {
		if (error == null) {
			error = new JLabel("");
			error.setBounds(418, 175, 220, 16);
		}
		return error;
	}
}
