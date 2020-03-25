package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import businessLogic.BLFacade;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;


public class CambiarContrasena extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton botonConfirmar;
	private JPanel panel_1;
	private JLabel labelContrasenaActual;
	private JLabel labelContrasenaNueva;
	private JLabel labelRepetirContrasena;
	private JLabel labelMensajes;
	private JPasswordField contraActual;
	private JPasswordField contraNueva1;
	private JPasswordField contraNueva2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CambiarContrasena frame = new CambiarContrasena();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CambiarContrasena() {
		
		setTitle("Cambiar Contraseña");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 207);
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel_1);
		panel_1.setLayout(null);
		panel_1.add(getLabelContrasenaActual());
		panel_1.add(getLabelContrasenaNueva());
		panel_1.add(getLabelRepetirContrasena());
		panel_1.add(getLabelMensajes());
		panel_1.add(getBotonConfirmar());
		panel_1.add(getLabelContrasenaActual());
		panel_1.add(getLabelContrasenaNueva());
		panel_1.add(getLabelRepetirContrasena());
		panel_1.add(getLabelMensajes());
		panel_1.add(getContraActual());
		panel_1.add(getContraNueva1());
		panel_1.add(getContraNueva2());
		
	}
	 
	
	
	 private JButton getBotonConfirmar() {
		 if(botonConfirmar == null) {
			 botonConfirmar = new JButton("Confirmar");
			 botonConfirmar.setBounds(25, 117, 91, 24);
			 botonConfirmar.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent arg1) {
					 String actual = new String(contraActual.getPassword());
					 String nueva = new String(contraNueva1.getPassword());
					 String repetida = new String(contraNueva2.getPassword());
					 if(!actual.equals("") && !nueva.equals("") && !repetida.equals("")) {
						 if(actual.equals(UsuarioGUI.getUsuario().getPass())) {
							 if(nueva.equals(repetida)) {
								BLFacade facade = Inicio.getBusinessLogic();
								labelMensajes.setText(UsuarioGUI.getUsuario().getId());
								facade.cambiarContrasena(UsuarioGUI.getUsuario(), nueva);
								labelMensajes.setText("La contraseña se ha modificado con éxito.");
								labelMensajes.setForeground(Color.GREEN);
							 }else {
								 labelMensajes.setText("Las contraseñas no coinciden.");
								 labelMensajes.setForeground(Color.RED);
							 }
						 }else {
							 labelMensajes.setText("La contraseña actual no es correcta.");
							 labelMensajes.setForeground(Color.RED);
						 }
					 }else {
						 labelMensajes.setText("Hay campos vacíos.");
						 labelMensajes.setForeground(Color.RED);
					 }
				 }
			 });
		 }
		 return botonConfirmar;
	 }
 
	private JLabel getLabelContrasenaActual() {
		if (labelContrasenaActual == null) {
			labelContrasenaActual = new JLabel("Contraseña Actual");
			labelContrasenaActual.setBounds(92, 29, 127, 14);
		}
		return labelContrasenaActual;
	}
	private JLabel getLabelContrasenaNueva() {
		if (labelContrasenaNueva == null) {
			labelContrasenaNueva = new JLabel("Contraseña Nueva");
			labelContrasenaNueva.setBounds(92, 59, 127, 14);
		}
		return labelContrasenaNueva;
	}
	private JLabel getLabelRepetirContrasena() {
		if (labelRepetirContrasena == null) {
			labelRepetirContrasena = new JLabel("Repetir Contraseña");
			labelRepetirContrasena.setBounds(92, 90, 127, 14);
		}
		return labelRepetirContrasena;
	}
	private JLabel getLabelMensajes() {
		if (labelMensajes == null) {
			labelMensajes = new JLabel("");
			labelMensajes.setBounds(128, 127, 299, 14);
		}
		return labelMensajes;
	}
	private JPasswordField getContraActual() {
		if (contraActual == null) {
			contraActual = new JPasswordField();
			contraActual.setBounds(215, 25, 127, 22);
		}
		return contraActual;
	}
	private JPasswordField getContraNueva1() {
		if (contraNueva1 == null) {
			contraNueva1 = new JPasswordField();
			contraNueva1.setBounds(215, 56, 127, 22);
		}
		return contraNueva1;
	}
	private JPasswordField getContraNueva2() {
		if (contraNueva2 == null) {
			contraNueva2 = new JPasswordField();
			contraNueva2.setBounds(215, 86, 127, 22);
		}
		return contraNueva2;
	}
}
