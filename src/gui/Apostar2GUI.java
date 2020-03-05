package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Apostar2GUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblEligeUnPartido;
	private JComboBox<Event> comboEventos;
	private JLabel lblEligeUnaPregunta;
	private JComboBox<Question> comboPreguntas;
	private JLabel lblEligeUnaRespuesta;
	private JComboBox<String> comboRespuestas;
	private JLabel lblImporteAApostar;
	private JButton botonApostar;
	private JSpinner spinner;
	private JLabel labelMensaje;
	private DefaultComboBoxModel<Event> modeloEventos = new DefaultComboBoxModel<Event>();
	private DefaultComboBoxModel<Question> modeloPreguntas = new DefaultComboBoxModel<Question>();
	private DefaultComboBoxModel<String> modeloRespuestas = new DefaultComboBoxModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apostar2GUI frame = new Apostar2GUI();
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
	public Apostar2GUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				BLFacade facade = Inicio.getBusinessLogic();
				Collection<Event> eventos = facade.getAllEvents();
				for(Event evento : eventos) modeloEventos.addElement(evento);
			}
		});
		setTitle("Apostar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 422, 270);
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
		contentPane.add(getSpinner());
		contentPane.add(getLabelMensaje());
	}

	private JLabel getLblEligeUnPartido() {
		if (lblEligeUnPartido == null) {
			lblEligeUnPartido = new JLabel("Elige un partido");
			lblEligeUnPartido.setBounds(12, 13, 99, 16);
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
			comboEventos.setBounds(135, 10, 172, 22);
			comboEventos.setModel(modeloEventos);
		}
		return comboEventos;
	}
	private JLabel getLblEligeUnaPregunta() {
		if (lblEligeUnaPregunta == null) {
			lblEligeUnaPregunta = new JLabel("Elige una pregunta");
			lblEligeUnaPregunta.setBounds(12, 58, 116, 16);
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
			        	//Vector<String> respuestas = pregunta.getRespuestas();
			        	//for(String respuesta : respuestas) modeloRespuestas.addElement(respuesta);
			        	modeloRespuestas.addElement(pregunta.getRespuestas());
			        }
			    }
			});
			comboPreguntas.setBounds(135, 55, 172, 22);
			comboPreguntas.setModel(modeloPreguntas);
		}
		return comboPreguntas;
	}
	
	private JLabel getLblEligeUnaRespuesta() {
		if (lblEligeUnaRespuesta == null) {
			lblEligeUnaRespuesta = new JLabel("Elige una respuesta");
			lblEligeUnaRespuesta.setBounds(12, 101, 116, 16);
		}
		return lblEligeUnaRespuesta;
	}
	
	private JComboBox<String> getComboRespuestas() {
		if (comboRespuestas == null) {
			comboRespuestas = new JComboBox<String>();
			comboRespuestas.setBounds(135, 98, 172, 22);
			comboRespuestas.setModel(modeloRespuestas);
		}
		return comboRespuestas;
	}
	
	private JLabel getLblImporteAApostar() {
		if (lblImporteAApostar == null) {
			lblImporteAApostar = new JLabel("Importe a apostar");
			lblImporteAApostar.setBounds(12, 140, 116, 16);
		}
		return lblImporteAApostar;
	}
	
	private JButton getBotonApostar() {
		if (botonApostar == null) {
			botonApostar = new JButton("Apostar");
			botonApostar.setBounds(12, 187, 97, 25);
		}
		return botonApostar;
	}
	
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Double(1), new Double(1), null, new Double(1)));
			spinner.setBounds(132, 137, 88, 22);
		}
		return spinner;
	}
	
	private JLabel getLabelMensaje() {
		if (labelMensaje == null) {
			labelMensaje = new JLabel("");
			labelMensaje.setBounds(12, 169, 384, 16);
		}
		return labelMensaje;
	}
}
