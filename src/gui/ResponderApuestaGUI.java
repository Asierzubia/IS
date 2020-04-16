package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
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

public class ResponderApuestaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEligeElEvento;
	private JLabel lblEligeLaQuestion;
	private JLabel lblIntroduceLaRespuesta;
	private JButton botonAñadir;
	private JComboBox<Event> comboEvento;
	private JComboBox<Question> comboQuestion;
	private JComboBox<Respuesta> comboRespuestas;
	private DefaultComboBoxModel<Event> listaEventos = new DefaultComboBoxModel<Event>();
	private DefaultComboBoxModel<Question> listaQuestions = new DefaultComboBoxModel<Question>();
	private DefaultComboBoxModel<Respuesta> listaRespuestas = new DefaultComboBoxModel<Respuesta>();
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
					ResponderApuestaGUI frame = new ResponderApuestaGUI();
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
	public ResponderApuestaGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				BLFacade facade = Inicio.getBusinessLogic();
				Date fechaEvento = UtilDate.trim(new Date(calendarioPartidos.getCalendar().getTime().getTime()));				
				Collection<Event> lista = facade.getEvents(fechaEvento);
				for(Event e : lista) listaEventos.addElement(e);
			}
		});
		setTitle("Añadir Respuesta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblEligeElEvento());
		contentPane.add(getLblEligeLaQuestion());
		contentPane.add(getLblIntroduceLaRespuesta());
		contentPane.add(getBotonAñadir());
		contentPane.add(getComboEvento());
		contentPane.add(getComboQuestion());
		contentPane.add(getComboRespuestas());
		contentPane.add(getError());
		contentPane.add(getCalendarioPartidos());
		contentPane.add(getLblEligeElDa());
		
		JComboBox<Question> comboRespuestas = new JComboBox<Question>();
	
		contentPane.add(comboRespuestas);
	}
	private JLabel getLblEligeElEvento() {
		if (lblEligeElEvento == null) {
			lblEligeElEvento = new JLabel("Elige el partido");
			lblEligeElEvento.setBounds(268, 48, 111, 16);
		}
		return lblEligeElEvento;
	}
	private JLabel getLblEligeLaQuestion() {
		if (lblEligeLaQuestion == null) {
			lblEligeLaQuestion = new JLabel("Elige la pregunta");
			lblEligeLaQuestion.setBounds(268, 97, 111, 16);
		}
		return lblEligeLaQuestion;
	}
	private JLabel getLblIntroduceLaRespuesta() {
		if (lblIntroduceLaRespuesta == null) {
			lblIntroduceLaRespuesta = new JLabel("Elige la correcta");
			lblIntroduceLaRespuesta.setBounds(268, 149, 137, 16);
		}
		return lblIntroduceLaRespuesta;
	}
	private JButton getBotonAñadir() {
		if (botonAñadir == null) {
			botonAñadir = new JButton("Añadir respuesta");
			botonAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BLFacade facade = Inicio.getBusinessLogic();
					Event evento = (Event) comboEvento.getSelectedItem();
					Question q = (Question) comboQuestion.getSelectedItem();
					Respuesta r = (Respuesta)  comboRespuestas.getSelectedItem();
					if (evento!=null) {
						if(r!=null) {
							if(q != null) {
								if(facade.ResponderApuesta(q,r)!=null) {
									error.setText("Se ha añadido la respuesta.");
									error.setForeground(Color.GREEN);
								}else {
									error.setText("No se ha podido añadir la respuesta.");
									error.setForeground(Color.RED);
								}
							}
						}
					}
					else {
						error.setText("Rellene todos los campos.");
						error.setForeground(Color.RED);
					}
				}
			});
			botonAñadir.setBounds(268, 253, 151, 25);
		}
		return botonAñadir;
	}
	
	private JComboBox<Event> getComboEvento() {
		if (comboEvento == null) {
			comboEvento = new JComboBox<Event>();
			comboEvento.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	listaQuestions.removeAllElements();
			    	Event evento = (Event) comboEvento.getSelectedItem();
					if(evento != null) {
						Vector<Question> lista = evento.getQuestions();
						for(Question q : lista) listaQuestions.addElement(q);;
					}
			    }
			});
			comboEvento.setBounds(411, 45, 204, 22);
			comboEvento.setModel(listaEventos);
		}
		return comboEvento;
	}
	
	private JComboBox<Question> getComboQuestion() {
		if (comboQuestion == null) {
			comboQuestion = new JComboBox<Question>();
			comboQuestion.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	listaRespuestas.removeAllElements();
			    	Question quest = (Question) comboQuestion.getSelectedItem();
					if(quest != null) {
						Vector<Respuesta> lista = quest.getRespuestas();
						for(Respuesta r : lista) listaRespuestas.addElement(r);;
					}
			    }
			});
			comboQuestion.setBounds(411, 94, 204, 22);
			comboQuestion.setModel(listaQuestions);
		}
		return comboQuestion;
	}
	
	private JComboBox<Respuesta> getComboRespuestas() {
		if (comboRespuestas == null) {
			comboRespuestas = new JComboBox<Respuesta>();
			comboRespuestas.setBounds(411, 146, 204, 22);
			comboRespuestas.setModel(listaRespuestas);
		}
		return comboRespuestas;
	}
	

	private JLabel getError() {
		if (error == null) {
			error = new JLabel("");
			error.setBounds(433, 263, 220, 16);
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
			lblEligeElDa = new JLabel("Elige el día del partido");
			lblEligeElDa.setBounds(12, 19, 203, 16);
		}
		return lblEligeElDa;
	}
}
