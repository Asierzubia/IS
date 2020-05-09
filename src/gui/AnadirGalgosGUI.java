package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AnadirGalgosGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEscogeLaCarrera;
	private JLabel lblEscogeElGalgo;
	private JLabel lblError;
	private JButton btnAnadir;
	private JComboBox<Carrera> comboBoxCarreras;
	private DefaultComboBoxModel<Carrera> modelCarrera = new DefaultComboBoxModel<Carrera>();
	
	private JComboBox<Galgo> comboBox;
	private DefaultComboBoxModel<Galgo> modelGalgos = new DefaultComboBoxModel<Galgo>();
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnadirGalgosGUI frame = new AnadirGalgosGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AnadirGalgosGUI(){
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				BLFacade facade = Inicio.getBusinessLogic();
				Collection<Carrera> lista = facade.getAllCarreras();
				Collection<Galgo> listaG = facade.getAllGalgos();
				for(Carrera e : lista) modelCarrera.addElement(e);
				for(Galgo g : listaG) modelGalgos.addElement(g);
			}
		});
		
		setTitle("Anadir Galgo a Carrera");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblEscogeLaCarrera());
		contentPane.add(getLblEscogeElGalgo());
		contentPane.add(getBtnAnadir());
		contentPane.add(getComboCarrera());
		contentPane.add(getComboBox());	
		contentPane.add(getLblError());
		
	}
	
	private JLabel getLblError() {
		if(lblError == null) {
			lblError = new JLabel("");
			lblError.setBounds(41, 200, 405, 15);
		}
		return lblError;		
	}
	private JComboBox<Carrera> getComboCarrera() {
		if (comboBoxCarreras == null) {
			comboBoxCarreras = new JComboBox<Carrera>();
			comboBoxCarreras.setBounds(263, 25, 166, 24);
			comboBoxCarreras.setModel(modelCarrera);
		}
		return comboBoxCarreras;
	}
	
	private JLabel getLblEscogeLaCarrera() {
		if (lblEscogeLaCarrera == null) {
			lblEscogeLaCarrera = new JLabel("Escoge la carrera");
			lblEscogeLaCarrera.setBounds(86, 30, 142, 15);
		}
		return lblEscogeLaCarrera;
	}
	
	private JLabel getLblEscogeElGalgo() {
		if (lblEscogeElGalgo == null) {
			lblEscogeElGalgo = new JLabel("Escoge el galgo");
			lblEscogeElGalgo.setBounds(86, 77, 142, 15);
		}
		return lblEscogeElGalgo;
	}
	
	private JButton getBtnAnadir() {
		if(btnAnadir == null) {
			btnAnadir = new JButton("Anadir");
			btnAnadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BLFacade facade = Inicio.getBusinessLogic();
					Carrera carrera = (Carrera) comboBoxCarreras.getSelectedItem();
					Galgo galgo = (Galgo) comboBox.getSelectedItem();
					if(galgo != null) {
						if(facade.actualizarCarreraGalgo(galgo,carrera)) {
							lblError.setText("El galgo se ha añadido correctamente a la carrera.");
							lblError.setForeground(Color.GREEN);
						}else {
							lblError.setText("El galgo no se ha podido añadir a la carrera.");
							lblError.setForeground(Color.RED);
						}
					}else {
							lblError.setText("Rellene todos los campos por favor");
							lblError.setForeground(Color.RED);
					}	
				}
			});
			btnAnadir.setBounds(175, 243, 117, 25);

		}
		return btnAnadir;
	}
	private JComboBox<Galgo> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<Galgo>();
			comboBox.setBounds(263, 72, 166, 24);
			comboBox.setModel(modelGalgos);
		}
		return comboBox;
	}
}
