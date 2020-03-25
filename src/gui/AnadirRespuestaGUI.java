package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
				Collection<Event> lista = facade.getAllEvents();
				for(Event e : lista) listaEventos.addElement(e);
			}
		});
		setTitle("Añadir Respuesta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 443, 244);
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
	}
	private JLabel getLblEligeElEvento() {
		if (lblEligeElEvento == null) {
			lblEligeElEvento = new JLabel("Elige el evento");
			lblEligeElEvento.setBounds(22, 31, 111, 16);
		}
		return lblEligeElEvento;
	}
	private JLabel getLblEligeLaQuestion() {
		if (lblEligeLaQuestion == null) {
			lblEligeLaQuestion = new JLabel("Elige la question");
			lblEligeLaQuestion.setBounds(22, 80, 111, 16);
		}
		return lblEligeLaQuestion;
	}
	private JLabel getLblIntroduceLaRespuesta() {
		if (lblIntroduceLaRespuesta == null) {
			lblIntroduceLaRespuesta = new JLabel("Introduce la respuesta");
			lblIntroduceLaRespuesta.setBounds(22, 132, 137, 16);
		}
		return lblIntroduceLaRespuesta;
	}
	private JButton getBotonAñadir() {
		if (botonAñadir == null) {
			botonAñadir = new JButton("Añadir respuesta");
			botonAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BLFacade facade = Inicio.getBusinessLogic();
					Question q = (Question) comboQuestion.getSelectedItem();
					String respuesta = textRespuesta.getText();
					if(!respuesta.equals("")) {
						if(q != null) {
							if(facade.anadirRespuesta(q, respuesta)) {
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
			botonAñadir.setBounds(12, 168, 151, 25);
		}
		return botonAñadir;
	}
	private JTextField getTextRespuesta() {
		if (textRespuesta == null) {
			textRespuesta = new JTextField();
			textRespuesta.setBounds(166, 129, 203, 22);
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
			comboEvento.setBounds(165, 28, 204, 22);
			comboEvento.setModel(listaEventos);
		}
		return comboEvento;
	}
	
	private JComboBox<Question> getComboQuestion() {
		if (comboQuestion == null) {
			comboQuestion = new JComboBox<Question>();
			comboQuestion.setBounds(165, 77, 204, 22);
			comboQuestion.setModel(listaQuestions);
		}
		return comboQuestion;
	}
	private JLabel getError() {
		if (error == null) {
			error = new JLabel("");
			error.setBounds(176, 172, 220, 16);
		}
		return error;
	}
}
