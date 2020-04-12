package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Question;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ApostarGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEligeUnPartido;
	private JComboBox<Event> comboEventos;
	private JLabel lblEligeUnaPregunta;
	private JComboBox<Question> comboPreguntas;
	private JLabel lblEligeUnaRespuesta;
	private JComboBox<String> comboRespuestas;
	private JLabel lblImporteAApostar;
	private JButton botonApostar;
	private JSpinner spinnerDinero;
	private JLabel labelMensaje;
	private DefaultComboBoxModel<Event> modeloEventos = new DefaultComboBoxModel<Event>();
	private DefaultComboBoxModel<Question> modeloPreguntas = new DefaultComboBoxModel<Question>();
	private DefaultComboBoxModel<String> modeloRespuestas = new DefaultComboBoxModel<String>();
	private JCalendar calendarioPartidos;
	private JLabel lblEligeUnDa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostarGUI frame = new ApostarGUI();
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
	public ApostarGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				BLFacade facade = Inicio.getBusinessLogic();
				Date fecha = UtilDate.trim(new Date(calendarioPartidos.getCalendar().getTime().getTime()));
				Collection<Event> eventos = facade.getEvents(fecha);
				for(Event evento : eventos) modeloEventos.addElement(evento);
			}
		});
		setTitle("Apostar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 774, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblEligeUnPartido());
		contentPane.add(getComboEventos());
		contentPane.add(getLblEligeUnaPregunta());
		contentPane.add(getComboPreguntas());
		contentPane.add(getLblEligeUnaRespuesta());
		contentPane.add(getComboRespuestas());
		contentPane.add(getLblImporteAApostar());
		contentPane.add(getBotonApostar());
		contentPane.add(getSpinnerDinero());
		contentPane.add(getLabelMensaje());
		contentPane.add(getCalendarioPartidos());
		contentPane.add(getLblEligeUnDa());
	}

	private JLabel getLblEligeUnPartido() {
		if (lblEligeUnPartido == null) {
			lblEligeUnPartido = new JLabel("Elige un partido");
			lblEligeUnPartido.setBounds(388, 16, 99, 16);
		}
		return lblEligeUnPartido;
	}
	private JComboBox<Event> getComboEventos() {
		if (comboEventos == null) {
			comboEventos = new JComboBox<Event>();
			comboEventos.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			        modeloPreguntas.removeAllElements();
			        Event evento = (Event) comboEventos.getSelectedItem();
			        if(evento != null) {
			        	Vector<Question> preguntas = evento.getQuestions();
			        	for(Question pregunta : preguntas) modeloPreguntas.addElement(pregunta);
			        }
			    }
			});
			comboEventos.setBounds(511, 13, 214, 22);
			comboEventos.setModel(modeloEventos);
		}
		return comboEventos;
	}
	private JLabel getLblEligeUnaPregunta() {
		if (lblEligeUnaPregunta == null) {
			lblEligeUnaPregunta = new JLabel("Elige una pregunta");
			lblEligeUnaPregunta.setBounds(388, 61, 116, 16);
		}
		return lblEligeUnaPregunta;
	}
	private JComboBox<Question> getComboPreguntas() {
		if (comboPreguntas == null) {
			comboPreguntas = new JComboBox<Question>();
			comboPreguntas.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			        modeloRespuestas.removeAllElements();
			        Question pregunta = (Question) comboPreguntas.getSelectedItem();
			        if(pregunta != null) {
			        	Vector<String> respuestas = pregunta.getRespuestas();
			        	for(String respuesta : respuestas) modeloRespuestas.addElement(respuesta);
			        }
			    }
			});
			comboPreguntas.setBounds(511, 58, 214, 22);
			comboPreguntas.setModel(modeloPreguntas);
		}
		return comboPreguntas;
	}
	
	private JLabel getLblEligeUnaRespuesta() {
		if (lblEligeUnaRespuesta == null) {
			lblEligeUnaRespuesta = new JLabel("Elige una respuesta");
			lblEligeUnaRespuesta.setBounds(388, 104, 116, 16);
		}
		return lblEligeUnaRespuesta;
	}
	
	private JComboBox<String> getComboRespuestas() {
		if (comboRespuestas == null) {
			comboRespuestas = new JComboBox<String>();
			comboRespuestas.setBounds(511, 101, 214, 22);
			comboRespuestas.setModel(modeloRespuestas);
		}
		return comboRespuestas;
	}
	
	private JLabel getLblImporteAApostar() {
		if (lblImporteAApostar == null) {
			lblImporteAApostar = new JLabel("Importe a apostar");
			lblImporteAApostar.setBounds(388, 143, 116, 16);
		}
		return lblImporteAApostar;
	}
	
	private JButton getBotonApostar() {
		if (botonApostar == null) {
			botonApostar = new JButton("Apostar");
			botonApostar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Boolean error = false;
					if(comboRespuestas.getSelectedItem() == null ||modeloPreguntas.getSize() == 0) {
						labelMensaje.setText("Faltan campos por rellenar");
						labelMensaje.setForeground(Color.RED);
						error = true;
					}
					else if(UsuarioGUI.getUsuario().getMoney()< (Double) spinnerDinero.getValue()){
						labelMensaje.setText("No tienes dinero suficiente");
						labelMensaje.setForeground(Color.RED);
					}
					else if(!error && comboPreguntas.getItemCount()!=0) {
						 Question selectedQuestion = (Question) comboPreguntas.getSelectedItem();
						 if (selectedQuestion.getBetMinimum()> (Double) spinnerDinero.getValue()) {
							 labelMensaje.setText("Sube la apuesta al menos a " + selectedQuestion.getBetMinimum());
							 labelMensaje.setForeground(Color.RED);
						 }
						 else {
							 String respuesta = (String) comboRespuestas.getSelectedItem();
							 Inicio.getBusinessLogic().generarApuesta(selectedQuestion, respuesta, (Double) spinnerDinero.getValue(), UsuarioGUI.getUsuario().getId());
							 Inicio.getBusinessLogic().incrementarSaldo(UsuarioGUI.getUsuario().getId(), (-(Double) spinnerDinero.getValue()));
							 UsuarioGUI.setUsuario(Inicio.getBusinessLogic().tryUser(UsuarioGUI.getUsuario().getId(), UsuarioGUI.getUsuario().getPass()));
							 labelMensaje.setText("Gracias por apostar con nosotros");
							 labelMensaje.setForeground(Color.GREEN);
						 }
					}
				}
			});
			botonApostar.setBounds(388, 190, 97, 25);
		}
		return botonApostar;
	}
	
	private JSpinner getSpinnerDinero() {
		if (spinnerDinero == null) {
			spinnerDinero = new JSpinner();
			spinnerDinero.setModel(new SpinnerNumberModel(new Double(1), new Double(1), null, new Double(1)));
			spinnerDinero.setBounds(508, 140, 107, 22);
		}
		return spinnerDinero;
	}
	
	private JLabel getLabelMensaje() {
		if (labelMensaje == null) {
			labelMensaje = new JLabel("");
			labelMensaje.setBounds(388, 172, 384, 16);
		}
		return labelMensaje;
	}
	private JCalendar getCalendarioPartidos() {
		if (calendarioPartidos == null) {
			calendarioPartidos = new JCalendar();
			calendarioPartidos.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					BLFacade facade = Inicio.getBusinessLogic();
					Date fecha = UtilDate.trim(new Date(calendarioPartidos.getCalendar().getTime().getTime()));
					modeloEventos.removeAllElements();
					Collection<Event> eventos = facade.getEvents(fecha);
					for(Event evento : eventos) modeloEventos.addElement(evento);
				}
			});
			calendarioPartidos.setBounds(30, 49, 331, 166);
		}
		return calendarioPartidos;
	}
	private JLabel getLblEligeUnDa() {
		if (lblEligeUnDa == null) {
			lblEligeUnDa = new JLabel("Elige un día para ver los partidos disponibles");
			lblEligeUnDa.setBounds(30, 16, 299, 16);
		}
		return lblEligeUnDa;
	}
}
