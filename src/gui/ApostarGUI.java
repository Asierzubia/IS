package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Event;
import domain.Question;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ApostarGUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_respuesta;
	private JLabel coments;
	private JComboBox<Event> comboBox;
	private JComboBox<Question> comboPreguntas;
	private Vector<Event> model;
	private Vector<Question> model_preguntas;
	private Question selectedQuestion;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ApostarGUI dialog = new ApostarGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ApostarGUI() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Apuesta Básica");
		setBounds(100, 100, 554, 349);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		model = new Vector<Event>();
		model_preguntas = new Vector<Question>();
		Collection<Event> auxmodel = Inicio.getBusinessLogic().getAllEvents();		
		for (Event ev : auxmodel)  model.addElement(ev);
		comboBox = new JComboBox<Event>(model);
		//model_preguntas = model.get(0).getQuestions();
		comboPreguntas = new JComboBox<Question>(model_preguntas);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboPreguntas.removeAllItems();
			}
		});
		
		JLabel lblEligeElTipo = new JLabel("Elige un partido");
		JLabel lblNewLabel = new JLabel("Elige una pregunta");
		
		JButton btnBuscarPreguntas = new JButton("Buscar Preguntas ");
		btnBuscarPreguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboPreguntas.removeAllItems();
				Event aux = (Event) comboBox.getSelectedItem();
				for (Question q : aux.getQuestions())  model_preguntas.addElement(q);
				comboPreguntas.updateUI();
				if (model_preguntas.size()!=0) {
					comboPreguntas.setSelectedIndex(0);
				}
			}
		});
		selectedQuestion = null;
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Double(1), new Double(1), null, new Double(1)));
		coments = new JLabel("Por favor, rellene todos los campos");
		coments.setForeground(Color.RED);
		
		JLabel lblEscribeTuRespuesta = new JLabel("Escribe tu respuesta");
		
		txt_respuesta = new JTextField();
		txt_respuesta.setColumns(10);
		
		JLabel lblImporteAApostar = new JLabel("Importe a Apostar");
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBuscarPreguntas)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblImporteAApostar, Alignment.LEADING)
								.addComponent(lblEligeElTipo, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEscribeTuRespuesta, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
										.addComponent(txt_respuesta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(coments, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
								.addComponent(comboPreguntas, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEligeElTipo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(60)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboPreguntas, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblEscribeTuRespuesta, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
										.addComponent(txt_respuesta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblImporteAApostar)
										.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(18)
									.addComponent(coments, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(57)
							.addComponent(btnBuscarPreguntas)))
					.addGap(24))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Atras");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JFrame a = new UsuarioGUI();
						a.setVisible(true);
						ApostarGUI.this.dispose();
					}
				});
				JButton btnNewButton = new JButton("APOSTAR");
				buttonPane.add(btnNewButton);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Boolean error = false;
						if(txt_respuesta.getText().equals("")||model_preguntas.isEmpty()) {
							coments.setText("Faltan campos por rellenar");
							error = true;
						}
						else if(UsuarioGUI.getUsuario().getMoney()< (Double) spinner.getValue()){
							coments.setText("No tienes dinero suficiente");
						}
						else if(!error && comboPreguntas.getItemCount()!=0) {
							 selectedQuestion = (Question) comboPreguntas.getSelectedItem();
							 if (selectedQuestion.getBetMinimum()> (Double) spinner.getValue()) {
								 coments.setText("Sube la apuesta al menos a" + selectedQuestion.getBetMinimum());
							 }
							 else {
								 Inicio.getBusinessLogic().generarApuesta(selectedQuestion, txt_respuesta.getText(), (Double) spinner.getValue(), UsuarioGUI.getUsuario());
								 Inicio.getBusinessLogic().incrementarSaldo(UsuarioGUI.getUsuario().getId(), (-(Double) spinner.getValue()));
								 UsuarioGUI.setUsuario(Inicio.getBusinessLogic().tryUser(UsuarioGUI.getUsuario().getId(), UsuarioGUI.getUsuario().getPass()));
								 coments.setText("Gracias por apostar con nosotros");
								 txt_respuesta.setText("");
							 }
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
