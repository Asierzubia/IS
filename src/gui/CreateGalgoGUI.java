package gui;

import java.text.DateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateGalgoGUI extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombreGalgo;
	private JLabel lblError;
	private JButton btnCrearGalgo; 
	private JLabel lblIndicaElNombre;
	private JLabel lblApuestaMinima;
	private JSpinner spinner;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateGalgoGUI frame = new CreateGalgoGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CreateGalgoGUI() {
		setTitle("Crear galgo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 487, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblError());
		contentPane.add(getLblIndicaElNombre());
		contentPane.add(getBtnCrearGalgo());
		contentPane.add(getTextNombreGalgo());
		contentPane.add(getLblApuestaMinima());
		contentPane.add(getSpinner());
	
		
	}
	private JTextField getTextNombreGalgo() {
		if(textNombreGalgo == null) {
			textNombreGalgo = new JTextField();
			textNombreGalgo.setBounds(218, 54, 235, 19);
			textNombreGalgo.setColumns(10);
		}
		return textNombreGalgo;
	}
	private JLabel getLblIndicaElNombre() {
		if (lblIndicaElNombre == null) {
			lblIndicaElNombre = new JLabel("Indica el nombre del galgo");
			lblIndicaElNombre.setBounds(27, 57, 206, 15);
		}
		return lblIndicaElNombre;
	}
	
	private JLabel getLblError() {
		if(lblError == null) {
			lblError = new JLabel("");
			lblError.setBounds(47, 117, 333, 15);
		}
		return lblError;
	}
	
	private JButton getBtnCrearGalgo() {
		if(btnCrearGalgo == null) {
			btnCrearGalgo = new JButton("Crear Galgo");
			btnCrearGalgo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BLFacade facade = Inicio.getBusinessLogic();
					String nombreGalgo = textNombreGalgo.getText();
					if (nombreGalgo.equals("")) {
						lblError.setText("Rellene todos los campos");
						lblError.setForeground(Color.RED);
					}else if(!nombreGalgo.equals("")) {
						if(facade.anadirGalgo(nombreGalgo, (float) spinner.getValue())) {
							lblError.setText("El galgo se ha añadido correctamente.");
							lblError.setForeground(Color.GREEN);
						}else {
							lblError.setText("El galgo no se ha podido añadir.");
							lblError.setForeground(Color.RED);
						}
					}	
				}
			});
			btnCrearGalgo.setBounds(138, 145, 132, 25);
		}
		return btnCrearGalgo;
	}
	private JLabel getLblApuestaMinima() {
		if (lblApuestaMinima == null) {
			lblApuestaMinima = new JLabel("Apuesta minima");
			lblApuestaMinima.setBounds(37, 85, 132, 15);
		}
		return lblApuestaMinima;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Float(1), new Float(1), null, new Float(1)));
			spinner.setBounds(218, 82, 93, 22);
		}
		return spinner;
	}
}
