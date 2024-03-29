package gui;

/**
 * @author Software Engineering teachers
 */

import javax.swing.*;

import domain.Event;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	private JButton botonCerrarSesion;

	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton jButtonAdministrarUsuarios;
	private JButton botonCrearEvento;
	private JButton botonAñadirRespuesta;
	private JButton botonResponderApuesta;
	private JButton btnCrearCarrera;
	private JButton btnCrearGalgo;
	private JButton btnAnadirGalgoA;
	private JButton btnCrearCarrera_2;
	
	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(854, 407);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());
			jContentPane.add(getJButtonAdministrarUsuarios());
			jContentPane.add(getBotonCrearEvento());
			jContentPane.add(getBotonAñadirRespuesta());
			jContentPane.add(getPanel());
			jContentPane.add(getBotonCerrarSesion());
			jContentPane.add(getBotonResponderApuesta());
			jContentPane.add(getBtnCrearCarrera());
			jContentPane.add(getBtnCrearGalgo());
			jContentPane.add(getBtnAnadirGalgoA());
			
			JButton btnCrearCarrera_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnCrearCarrera_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCrearCarrera_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PagarApuestasGUI a=new PagarApuestasGUI();
					a.setVisible(true);
				}
			});
			btnCrearCarrera_1.setBounds(60, 171, 243, 52);
			jContentPane.add(btnCrearCarrera_1);
			jContentPane.add(getBtnCrearCarrera_2());

		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setBounds(330, 33, 165, 60);
			jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//BLFacade facade=Inicio.getBusinessLogic();
					//Vector<Event> events=facade.getAllEvents();
					JFrame a = new CreateQuestionGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateQuery;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(0, 33, 165, 60);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new FindQuestionsGUI();
					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	
	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(151, -13, 477, 60);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.setBounds(231, 5, 69, 25);
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.setBounds(61, 5, 73, 25);
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.setBounds(140, 5, 87, 25);
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 283, 319, 36);
			panel.setLayout(null);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	
	private JButton getJButtonAdministrarUsuarios() {
		if (jButtonAdministrarUsuarios == null) {
			jButtonAdministrarUsuarios = new JButton();
			jButtonAdministrarUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AdministrarUsuariosGUI a=new AdministrarUsuariosGUI();
					a.setVisible(true);
				}
			});
			jButtonAdministrarUsuarios.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.jButtonAdministrarUsuarios.text")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonAdministrarUsuarios.setBounds(433, 171, 243, 52);
		}
		return jButtonAdministrarUsuarios;
	}
	
	private JButton getBotonCerrarSesion() {
		if (botonCerrarSesion == null) {
			botonCerrarSesion = new JButton("Cerrar Sesión");
			botonCerrarSesion.setBounds(462, 283, 145, 25);
			botonCerrarSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainGUI.this.dispose();
					Inicio a = new Inicio();
					a.setVisible(true);
				}
			});
		}
		return botonCerrarSesion;
	}
	private JButton getBotonCrearEvento() {
		if (botonCrearEvento == null) {
			botonCrearEvento = new JButton();
			botonCrearEvento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CrearEventoGUI a = new CrearEventoGUI();
					a.setVisible(true);
				}
			});
			botonCrearEvento.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.botonCrearEvento.text")); //$NON-NLS-1$ //$NON-NLS-2$
			botonCrearEvento.setBounds(165, 33, 165, 60);
		}
		return botonCrearEvento;
	}
	private JButton getBotonAñadirRespuesta() {
		if (botonAñadirRespuesta == null) {
			botonAñadirRespuesta = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
			botonAñadirRespuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AnadirRespuestaGUI a = new AnadirRespuestaGUI();
					a.setVisible(true);
				}
			});
			botonAñadirRespuesta.setBounds(495, 33, 165, 60);
		}
		return botonAñadirRespuesta;
	}
	
	private JButton getBotonResponderApuesta() {
		if (botonResponderApuesta == null) {
			botonResponderApuesta = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnResponderApuesta.text")); //$NON-NLS-1$ //$NON-NLS-2$
			botonResponderApuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ResponderApuestaGUI a = new ResponderApuestaGUI();
					a.setVisible(true);
				}
			});
			botonResponderApuesta.setBounds(660, 33, 165, 60);
		}
		return botonResponderApuesta;
	}
	private JButton getBtnCrearCarrera() {
		if (btnCrearCarrera == null) {
			btnCrearCarrera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnCrearCarrera.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCrearCarrera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CreateCarreraGUI a = new CreateCarreraGUI();
					a.setVisible(true);
				}
			});
			btnCrearCarrera.setBounds(10, 106, 160, 52);
		}
		return btnCrearCarrera;
	}
	private JButton getBtnCrearGalgo() {
		if (btnCrearGalgo == null) {
			btnCrearGalgo = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnCrearGalgo.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCrearGalgo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CreateGalgoGUI a = new CreateGalgoGUI();
					a.setVisible(true);
				}
			});
			btnCrearGalgo.setBounds(175, 106, 160, 52);
		}
		return btnCrearGalgo;
	}
	private JButton getBtnAnadirGalgoA() {
		if (btnAnadirGalgoA == null) {
			btnAnadirGalgoA = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnAnadirGalgoA.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnAnadirGalgoA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AnadirGalgosGUI a = new AnadirGalgosGUI();
					a.setVisible(true);
				}
			});
			btnAnadirGalgoA.setBounds(347, 106, 180, 52);
		}
		return btnAnadirGalgoA;
	}
	private JButton getBtnCrearCarrera_2() {
		if (btnCrearCarrera_2 == null) {
			btnCrearCarrera_2 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnCrearCarrera_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCrearCarrera_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ResponderApuestaGalgosGUI a = new ResponderApuestaGalgosGUI();
					a.setVisible(true);
				}
			});
			btnCrearCarrera_2.setBounds(539, 106, 243, 52);
		}
		return btnCrearCarrera_2;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

