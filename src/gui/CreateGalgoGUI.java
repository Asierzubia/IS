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
	private JTextField textFieldPrice;
	
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
		setBounds(100, 100, 512, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblError());
		contentPane.add(getLblIndicaElNombre());
		contentPane.add(getBtnCrearGalgo());
		contentPane.add(getTextNombreGalgo());
		contentPane.add(getLblApuestaMinima());
		contentPane.add(getTextFieldPrice());
	
		
	}
	private JTextField getTextNombreGalgo() {
		if(textNombreGalgo == null) {
			textNombreGalgo = new JTextField();
			textNombreGalgo.setBounds(129, 85, 235, 19);
			textNombreGalgo.setColumns(10);
		}
		return textNombreGalgo;
	}
	private JLabel getLblIndicaElNombre() {
		if (lblIndicaElNombre == null) {
			lblIndicaElNombre = new JLabel("Indica el nombre del galgo");
			lblIndicaElNombre.setBounds(148, 58, 206, 15);
		}
		return lblIndicaElNombre;
	}
	
	private JLabel getLblError() {
		if(lblError == null) {
			lblError = new JLabel("");
			lblError.setBounds(55, 191, 333, 15);
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
					float inputPrice = Float.parseFloat(textFieldPrice.getText());
					if (textFieldPrice.getText().equals("") || nombreGalgo.equals("")) {
						lblError.setText("Rellene todos los campos");
						lblError.setForeground(Color.RED);
					}else if(!nombreGalgo.equals("")) {
						if(facade.anadirGalgo(nombreGalgo,inputPrice)) {
							lblError.setText("El galgo se ha añadido correctamente.");
							lblError.setForeground(Color.GREEN);
						}else {
							lblError.setText("El galgo no se ha podido añadir.");
							lblError.setForeground(Color.RED);
						}
					}else if(inputPrice <= 0){
							lblError.setText("Introduzca una cantidad mayor a 0.");
							lblError.setForeground(Color.RED);
					}	
				}
			});
			btnCrearGalgo.setBounds(175, 247, 132, 25);
		}
		return btnCrearGalgo;
	}
	private JLabel getLblApuestaMinima() {
		if (lblApuestaMinima == null) {
			lblApuestaMinima = new JLabel("Apuesta minima");
			lblApuestaMinima.setBounds(175, 116, 132, 15);
		}
		return lblApuestaMinima;
	}
	private JTextField getTextFieldPrice() {
		if (textFieldPrice == null) {
			textFieldPrice = new JTextField();
			textFieldPrice.setBounds(174, 143, 114, 19);
			textFieldPrice.setColumns(10);
		}
		return textFieldPrice;
	}
}
