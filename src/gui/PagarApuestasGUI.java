package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Apuesta;
import domain.ApuestaGalgo;
import domain.Carrera;
import domain.Event;
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
import com.toedter.calendar.JCalendar; 
import java.beans.PropertyChangeListener; 
import java.beans.PropertyChangeEvent; 

public class PagarApuestasGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton botonAñadir;
	private DefaultComboBoxModel<Event> listaEventos = new DefaultComboBoxModel<Event>();
	private JLabel error;
	private JCalendar calendarioPartidos;
	private JLabel lblEligeElDa;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PagarApuestasGUI frame = new PagarApuestasGUI();
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
	public PagarApuestasGUI() {
		setTitle("Pagar apuestas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 366, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBotonPagar());
		contentPane.add(getError());
		contentPane.add(getCalendarioPartidos());
		contentPane.add(getLblEligeElDa());
	}
	private JButton getBotonPagar() {
		if (botonAñadir == null) {
			botonAñadir = new JButton("Pagar apuestas");
			botonAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BLFacade facade = Inicio.getBusinessLogic();
					Date fecha = UtilDate.trim(new Date(calendarioPartidos.getCalendar().getTime().getTime()));
					Vector<Event> eventos = facade.eventosAnterioresAFecha(fecha);
					for(Event ev : eventos) {
						Vector<Question> listaQuest = ev.getQuestions();
						for(Question quest : listaQuest) {
							if (quest.getRespuestaCorrecta()!=null) {
								Vector<Apuesta> listaApuestas = facade.apuestasConQuestion(quest);
								for(Apuesta ap : listaApuestas) {
									if (!ap.getCobrada() && ap.getApostado().toString().equals(quest.getRespuestaCorrecta().getTextoRespuesta().toString())) {
										facade.setCobradaApuesta(ap);
										facade.incrementarSaldo(ap.getIdUsuario().getId(), ap.getGanancia());
									}
								}
							}
						}
					}
					Collection<Carrera> listaCarrera = facade.getAllCarreras();
					for(Carrera c : listaCarrera) {
						if(c.getRespuestaCorrecta()!=null) {
						Vector<ApuestaGalgo>listaApuestasGalgos = facade.apuestasCarrera(c);	
						for(ApuestaGalgo apg : listaApuestasGalgos) {
							if (!apg.getCobrada() && apg.getGalgo().getNombreGalgo().equals(c.getRespuestaCorrecta().getNombreGalgo())) {
								facade.setCobradaApuestaGalgos(apg);
								facade.incrementarSaldo(apg.getIdUsuario().getId(), apg.getGanancia());
							}
						}
						}
					}
				}
			});
			botonAñadir.setBounds(24, 236, 151, 25);
		}
		return botonAñadir;
	}
	private JLabel getError() {
		if (error == null) {
			error = new JLabel("");
			error.setBounds(187, 236, 220, 16);
		}
		return error;
	}
	private JCalendar getCalendarioPartidos() {
		if (calendarioPartidos == null) {
			calendarioPartidos = new JCalendar();
			calendarioPartidos.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					BLFacade facade = Inicio.getBusinessLogic();
					listaEventos.removeAllElements();
					Date fechaEvento = UtilDate.trim(new Date(calendarioPartidos.getCalendar().getTime().getTime()));				
					Collection<Event> lista = facade.getEvents(fechaEvento);
					for(Event e : lista) listaEventos.addElement(e);
				}
			});
			calendarioPartidos.setBounds(new Rectangle(40, 50, 225, 150));
			calendarioPartidos.setBounds(12, 48, 225, 150);
		}
		return calendarioPartidos;
	}
	private JLabel getLblEligeElDa() {
		if (lblEligeElDa == null) {
			lblEligeElDa = new JLabel("Elige el día a pagar (Se pagarán todas hasta ese día)");
			lblEligeElDa.setBounds(12, 19, 324, 16);
		}
		return lblEligeElDa;
	}
}
