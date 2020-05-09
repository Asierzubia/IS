package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.*;
import domain.Event;

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

public class ApostarGalgosGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEligeUnaCarrera;
	private JLabel lblEligeUnGalgo;
	private JLabel labelMensaje;
	private JComboBox<Galgo> comboBoxGalgo;
	private JComboBox<Carrera> comboBoxCarrera;
	private JLabel lblImporteAApostar;
	private JButton botonApostar;
	private JSpinner spinnerDinero;
	private DefaultComboBoxModel<Galgo> modeloGalgo = new DefaultComboBoxModel<Galgo>();
	private DefaultComboBoxModel<Carrera> modeloCarreras = new DefaultComboBoxModel<Carrera>();
	private JLabel lblPosiblesGanancias;
	private JTextField posiblesBeneficios;
	private JButton btnVolverAtras;
	private JLabel lblAcertarElGalgo;
	private JLabel labelMen;


	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApostarGalgosGUI frame = new ApostarGalgosGUI();
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
	public ApostarGalgosGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				BLFacade facade = Inicio.getBusinessLogic();
				//Date fechaEvento = UtilDate.trim(new Date(calendarioPartidos.getCalendar().getTime().getTime()));				
				Collection<Carrera> lista = facade.getAllCarreras();
				for(Carrera car : lista) modeloCarreras.addElement(car);
			}
		});
		setTitle("Apostar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 710, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblEligeUnaCarrera());

		contentPane.add(getLblImporteAApostar());
		contentPane.add(getBotonApostar());
		contentPane.add(getSpinnerDinero());
		contentPane.add(getLblPosiblesGanancias());
		contentPane.add(getPosiblesBeneficios());
		
		contentPane.add(getBtnVolverAtras());
		contentPane.add(getComboCarreras());
		contentPane.add(getComboGalgos());
		contentPane.add(getLblEligeUnGalgo());
		contentPane.add(getLblElAcertarGalgo());
		contentPane.add(getLabelMen());
		}
	
	private JLabel getLblElAcertarGalgo() {
		if(lblAcertarElGalgo == null) {
			lblAcertarElGalgo = new JLabel("***Acertar el galgo ganador multiplica tus ganancias por 2.5***");
			lblAcertarElGalgo.setBounds(150, 140, 508, 15);
		}
		return lblAcertarElGalgo;
	}
	private JComboBox<Galgo> getComboGalgos(){
		if(comboBoxGalgo == null) {
			comboBoxGalgo = new JComboBox<Galgo>();				  
			comboBoxGalgo.setBounds(436, 63, 116, 24);
			comboBoxGalgo.setModel(modeloGalgo);
		}
		return comboBoxGalgo;
	}
	private JLabel getLblEligeUnaCarrera() {
		if (lblEligeUnaCarrera == null) {
			lblEligeUnaCarrera = new JLabel("Elige una carrera");
			lblEligeUnaCarrera.setBounds(150, 28, 133, 16);
		}
		return lblEligeUnaCarrera;
	}
	private JComboBox<Carrera> getComboCarreras() {
		if (comboBoxCarrera == null) {
			comboBoxCarrera = new JComboBox<Carrera>();	
			comboBoxCarrera.addActionListener (new ActionListener () { 
				  public void actionPerformed(ActionEvent e) { 
					  modeloGalgo.removeAllElements();
					  Carrera carrera = (Carrera) comboBoxCarrera.getSelectedItem();
					  if(carrera != null) {
						  Vector<Galgo> listaGalgos = carrera.getGalgos();
						  if(listaGalgos != null) {
							  for(Galgo gal : listaGalgos) modeloGalgo.addElement(gal);
						  }
					  }
				  	} 
				  });
			comboBoxCarrera.setBounds(436, 24, 116, 24);
			comboBoxCarrera.setModel(modeloCarreras);
		}
		return comboBoxCarrera;
	}
	
	private JLabel getLblEligeUnGalgo() {
		if (lblEligeUnGalgo == null) {
			lblEligeUnGalgo = new JLabel("Elige una galgo");
			lblEligeUnGalgo.setBounds(150, 67, 169, 16);
		}
		return lblEligeUnGalgo;
	}
	
	private JLabel getLblImporteAApostar() {
		if (lblImporteAApostar == null) {
			lblImporteAApostar = new JLabel("Importe a apostar");
			lblImporteAApostar.setBounds(150, 101, 153, 16);
		}
		return lblImporteAApostar;
	}
	
	private JButton getBotonApostar() {
		if (botonApostar == null) {
			botonApostar = new JButton("Apostar");
			botonApostar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Boolean error = false;
					if(comboBoxGalgo.getSelectedItem() == null ||modeloGalgo.getSize() == 0) {
						labelMen.setText("Faltan campos por rellenar");
						labelMen.setForeground(Color.RED);
						error = true;
					}
					else if(UsuarioGUI.getUsuario().getMoney()< (Double) spinnerDinero.getValue()){
						labelMen.setText("No tienes dinero suficiente");
						labelMen.setForeground(Color.RED);
					}
					else if(!error && comboBoxCarrera.getItemCount()!=0) {
						 Galgo selectedGalgo = (Galgo) comboBoxGalgo.getSelectedItem();
						 if (selectedGalgo.getBetMinimum()> (Double) spinnerDinero.getValue()) {
							 labelMen.setText("Sube la apuesta al menos a " + selectedGalgo.getBetMinimum());
							 labelMen.setForeground(Color.RED);
						 }
						 else {
							 Double ganancias = 2.5 * (Double) spinnerDinero.getValue();
							 if(Inicio.getBusinessLogic().generarApuestaGalgo(selectedGalgo,(Double) spinnerDinero.getValue(), UsuarioGUI.getUsuario(), ganancias)) {
								 Inicio.getBusinessLogic().incrementarSaldo(UsuarioGUI.getUsuario().getId(), (-(Double) spinnerDinero.getValue()));
								 UsuarioGUI.setUsuario(Inicio.getBusinessLogic().tryUser(UsuarioGUI.getUsuario().getId(), UsuarioGUI.getUsuario().getPass()));
								 labelMen.setText("Gracias por apostar con nosotros");
								 labelMen.setForeground(Color.GREEN);
							 }else {
								 labelMen.setText("No se ha podido realizar la apuesta");
								 labelMen.setForeground(Color.GREEN);
							 }
						 }
					}
				}
			});
			botonApostar.setBounds(261, 267, 97, 25);
		}
		return botonApostar;
	}
	
	private JSpinner getSpinnerDinero() {
		if (spinnerDinero == null) {
			spinnerDinero = new JSpinner();
			spinnerDinero.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					Double boni = 2.5;
					Double ganancias = boni * (Double) spinnerDinero.getValue();
					posiblesBeneficios.setText(ganancias.toString());
				}
			});
			spinnerDinero.setModel(new SpinnerNumberModel(new Double(1), new Double(1), null, new Double(1)));
			spinnerDinero.setBounds(467, 99, 85, 22);
		}
		return spinnerDinero;
	}
	private JLabel getLblPosiblesGanancias() {
		if (lblPosiblesGanancias == null) {
			lblPosiblesGanancias = new JLabel("Posibles beneficios");
			lblPosiblesGanancias.setBounds(150, 181, 143, 16);
		}
		return lblPosiblesGanancias;
	}
	private JTextField getPosiblesBeneficios() {
		if (posiblesBeneficios == null) {
			posiblesBeneficios = new JTextField();
			posiblesBeneficios.setEditable(false);
			posiblesBeneficios.setBounds(150, 209, 116, 22);
			posiblesBeneficios.setColumns(10);
		}
		return posiblesBeneficios;
	}
	private JButton getBtnVolverAtras() {
		if (btnVolverAtras == null) {
			btnVolverAtras = new JButton("Volver Atras");
			btnVolverAtras.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					jButton2_actionPerformed(e);
				}
			});
		}
		btnVolverAtras.setBounds(395, 267, 143, 25);
		return btnVolverAtras;
	}
	
	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	private JLabel getLabelMen() {
		if (labelMen == null) {
			labelMen = new JLabel("New label");
			labelMen.setBounds(392, 212, 266, 15);
		}
		return labelMen;
	}
}
