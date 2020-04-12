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

public class AnadirRespuestaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEligeElEvento;
	private JLabel lblEligeLaQuestion;
	private JLabel lblIntroduceLaRespuesta;
	private JButton botonAñadir;
	private JTextField textRespuesta;
	private JComboBox<Event> comboEvento;
	private JComboBox<Question> comboQuestion;
	private DefaultComboBoxModel<Event> listaEventos = new DefaultComboBoxModel<Event>();
	private DefaultComboBoxModel<Question> listaQuestions = new DefaultComboBoxModel<Question>();
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
					AnadirRespuestaGUI frame = new AnadirRespuestaGUI();
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
	public AnadirRespuestaGUI() {
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
		setBounds(100, 100, 690, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblEligeElEvento());
		contentPane.add(getLblEligeLaQuestion());
		contentPane.add(getLblIntroduceLaRespuesta());
		contentPane.add(getBotonAñadir());
		contentPane.add(getTextRespuesta());
		contentPane.add(getComboEvento());
		contentPane.add(getComboQuestion());
		contentPane.add(getError());
		contentPane.add(getCalendarioPartidos());
		contentPane.add(getLblEligeElDa());
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
			lblIntroduceLaRespuesta = new JLabel("Introduce la respuesta");
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
					if(!textRespuesta.getText().equals("")) {
						if(q != null) {
							Respuesta respuesta = new Respuesta(q,evento, textRespuesta.getText());
							if(facade.anadirRespuesta(respuesta)) {
								error.setText("Se ha añadido la respuesta.");
								error.setForeground(Color.GREEN);
							}else {
								error.setText("No se ha podido añadir la respuesta.");
								error.setForeground(Color.RED);
							}
						}
					}else {
						error.setText("Rellene todos los campos.");
						error.setForeground(Color.RED);
					}
				}
			});
			botonAñadir.setBounds(258, 185, 151, 25);
		}
		return botonAñadir;
	}
	private JTextField getTextRespuesta() {
		if (textRespuesta == null) {
			textRespuesta = new JTextField();
			textRespuesta.setBounds(412, 146, 203, 22);
			textRespuesta.setColumns(10);
		}
		return textRespuesta;
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
			comboQuestion.setBounds(411, 94, 204, 22);
			comboQuestion.setModel(listaQuestions);
		}
		return comboQuestion;
	}
	private JLabel getError() {
		if (error == null) {
			error = new JLabel("");
			error.setBounds(422, 189, 220, 16);
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
