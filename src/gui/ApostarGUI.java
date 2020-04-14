package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import domain.Respuesta;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import com.toedter.calendar.JCalendar;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ApostarGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEligeUnPartido;
	private JComboBox<Event> comboEventos;
	private JLabel lblEligeUnaPregunta;
	private JComboBox<Question> comboPreguntas;
	private JComboBox<Respuesta> comboRespuestas;
	private JLabel lblEligeUnaRespuesta;
	private JLabel lblImporteAApostar;
	private JButton botonApostar;
	private JSpinner spinnerDinero;
	private JLabel labelMensaje;
	private DefaultComboBoxModel<Event> modeloEventos = new DefaultComboBoxModel<Event>();
	private DefaultComboBoxModel<Question> modeloPreguntas = new DefaultComboBoxModel<Question>();
	private DefaultComboBoxModel<Respuesta> modeloRespuestas = new DefaultComboBoxModel<Respuesta>();
	private JCalendar calendarioPartidos;
	private JLabel label;
	private JLabel lblBonificacinPorEuro;
	private JLabel lblPosiblesGanancias;
	private JTextField bonificacionEuro;
	private JTextField posiblesBeneficios;

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
				Date fechaEvento = UtilDate.trim(new Date(calendarioPartidos.getCalendar().getTime().getTime()));				
				Collection<Event> lista = facade.getEvents(fechaEvento);
				for(Event e : lista) modeloEventos.addElement(e);
			}
		});
		setTitle("Apostar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 710, 352);
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
		contentPane.add(getLabel());
		contentPane.add(getLblBonificacinPorEuro());
		contentPane.add(getLblPosiblesGanancias());
		contentPane.add(getBonificacionEuro());
		contentPane.add(getPosiblesBeneficios());
		
		
	}

	private JLabel getLblEligeUnPartido() {
		if (lblEligeUnPartido == null) {
			lblEligeUnPartido = new JLabel("Elige un partido");
			lblEligeUnPartido.setBounds(301, 16, 99, 16);
		}
		return lblEligeUnPartido;
	}
	private JComboBox<Respuesta> getComboRespuestas(){
		if(comboRespuestas == null) {
			comboRespuestas = new JComboBox<Respuesta>();
			comboRespuestas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(modeloRespuestas.getSize() != 0) {
						Double boni = ((Respuesta) comboRespuestas.getSelectedItem()).getBonificacion();
						bonificacionEuro.setText(boni.toString());
						Double ganancias = boni * (Double) spinnerDinero.getValue();
						posiblesBeneficios.setText(ganancias.toString());
					}
				}
			});
			comboRespuestas.setBounds(424, 100, 214, 24);
			comboRespuestas.setModel(modeloRespuestas);
		}
		return comboRespuestas;
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
			comboEventos.setBounds(424, 13, 214, 22);
			comboEventos.setModel(modeloEventos);
		}
		return comboEventos;
	}
	private JLabel getLblEligeUnaPregunta() {
		if (lblEligeUnaPregunta == null) {
			lblEligeUnaPregunta = new JLabel("Elige una pregunta");
			lblEligeUnaPregunta.setBounds(301, 61, 116, 16);
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
						  Vector<Respuesta> respuestas = pregunta.getRespuestas();
						  if(respuestas != null) {
							  for(Respuesta r : respuestas) modeloRespuestas.addElement(r);
						  }
					  }
				  	} 
				  });
			comboPreguntas.setBounds(424, 58, 214, 22);
			comboPreguntas.setModel(modeloPreguntas);
		}
		return comboPreguntas;
	}
	
	private JLabel getLblEligeUnaRespuesta() {
		if (lblEligeUnaRespuesta == null) {
			lblEligeUnaRespuesta = new JLabel("Elige una respuesta");
			lblEligeUnaRespuesta.setBounds(301, 104, 116, 16);
		}
		return lblEligeUnaRespuesta;
	}
	
	private JLabel getLblImporteAApostar() {
		if (lblImporteAApostar == null) {
			lblImporteAApostar = new JLabel("Importe a apostar");
			lblImporteAApostar.setBounds(301, 143, 116, 16);
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
							 String respuesta = ((Respuesta) comboRespuestas.getSelectedItem()).getTextoRespuesta();
							 Double boni = ((Respuesta) comboRespuestas.getSelectedItem()).getBonificacion();
							 Double ganancias = boni * (Double) spinnerDinero.getValue();
							 if(Inicio.getBusinessLogic().generarApuesta(selectedQuestion, respuesta, (Double) spinnerDinero.getValue(), UsuarioGUI.getUsuario(), ganancias)) {
								 Inicio.getBusinessLogic().incrementarSaldo(UsuarioGUI.getUsuario().getId(), (-(Double) spinnerDinero.getValue()));
								 UsuarioGUI.setUsuario(Inicio.getBusinessLogic().tryUser(UsuarioGUI.getUsuario().getId(), UsuarioGUI.getUsuario().getPass()));
								 labelMensaje.setText("Gracias por apostar con nosotros");
								 labelMensaje.setForeground(Color.GREEN);
							 }else {
								 labelMensaje.setText("No se ha podido realizar la apuesta");
								 labelMensaje.setForeground(Color.GREEN);
							 }
						 }
					}
				}
			});
			botonApostar.setBounds(301, 267, 97, 25);
		}
		return botonApostar;
	}
	
	private JSpinner getSpinnerDinero() {
		if (spinnerDinero == null) {
			spinnerDinero = new JSpinner();
			spinnerDinero.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					Double boni = ((Respuesta) comboRespuestas.getSelectedItem()).getBonificacion();
					bonificacionEuro.setText(boni.toString());
					Double ganancias = boni * (Double) spinnerDinero.getValue();
					posiblesBeneficios.setText(ganancias.toString());
				}
			});
			spinnerDinero.setModel(new SpinnerNumberModel(new Double(1), new Double(1), null, new Double(1)));
			spinnerDinero.setBounds(421, 140, 79, 22);
		}
		return spinnerDinero;
	}
	
	private JLabel getLabelMensaje() {
		if (labelMensaje == null) {
			labelMensaje = new JLabel("");
			labelMensaje.setBounds(301, 249, 384, 16);
		}
		return labelMensaje;
	}
	private JCalendar getCalendarioPartidos() {
		if (calendarioPartidos == null) {
			calendarioPartidos = new JCalendar();
			calendarioPartidos.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					BLFacade facade = Inicio.getBusinessLogic();
					modeloEventos.removeAllElements();
					Date fechaEvento = UtilDate.trim(new Date(calendarioPartidos.getCalendar().getTime().getTime()));				
					Collection<Event> lista = facade.getEvents(fechaEvento);
					for(Event e : lista) modeloEventos.addElement(e);
				}
			});
			calendarioPartidos.setBounds(new Rectangle(40, 50, 225, 150));
			calendarioPartidos.setBounds(12, 38, 225, 150);
		}
		return calendarioPartidos;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Elige el día del partido");
			label.setBounds(12, 9, 203, 16);
		}
		return label;
	}
	private JLabel getLblBonificacinPorEuro() {
		if (lblBonificacinPorEuro == null) {
			lblBonificacinPorEuro = new JLabel("Bonificación por Euro");
			lblBonificacinPorEuro.setBounds(301, 183, 131, 16);
		}
		return lblBonificacinPorEuro;
	}
	private JLabel getLblPosiblesGanancias() {
		if (lblPosiblesGanancias == null) {
			lblPosiblesGanancias = new JLabel("Posibles beneficios");
			lblPosiblesGanancias.setBounds(524, 183, 143, 16);
		}
		return lblPosiblesGanancias;
	}
	private JTextField getBonificacionEuro() {
		if (bonificacionEuro == null) {
			bonificacionEuro = new JTextField();
			bonificacionEuro.setEditable(false);
			bonificacionEuro.setBounds(301, 212, 116, 22);
			bonificacionEuro.setColumns(10);
		}
		return bonificacionEuro;
	}
	private JTextField getPosiblesBeneficios() {
		if (posiblesBeneficios == null) {
			posiblesBeneficios = new JTextField();
			posiblesBeneficios.setEditable(false);
			posiblesBeneficios.setBounds(524, 212, 116, 22);
			posiblesBeneficios.setColumns(10);
		}
		return posiblesBeneficios;
	}
}
