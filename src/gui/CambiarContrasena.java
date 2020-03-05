package gui;

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


public class CambiarContrasena extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textContrasenaActual;
	private JTextField textNuevaContrasena;
	private JTextField textRepetirContrasena;
	private JButton botonVolverAtras;
	private JButton botonConfirmar;
	private JPanel panel_1;
	private JLabel labelContrasenaActual;
	private JLabel labelContrasenaNueva;
	private JLabel labelRepetirContrasena;
	private JLabel labelAvisoPantalla;
	
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
		setBounds(100, 100, 449, 266);
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel_1);
		panel_1.setLayout(null);
		panel_1.add(getLabelContrasenaActual());
		panel_1.add(getLabelContrasenaNueva());
		panel_1.add(getLabelRepetirContrasena());
		panel_1.add(getLabelAvisoPantalla());
		panel_1.add(getTextContrasenaActual());
		panel_1.add(getTextNuevaContrasena());
		panel_1.add(getTextRepetirContrasena());
		panel_1.add(getBotonConfirmar());
		panel_1.add(getBotonVolverAtras());
		panel_1.add(getLabelContrasenaActual());
		panel_1.add(getLabelContrasenaNueva());
		panel_1.add(getLabelRepetirContrasena());
		panel_1.add(getLabelAvisoPantalla());
		
	}
	
	private JTextField getTextContrasenaActual() {
		if(textContrasenaActual == null) {
			textContrasenaActual = new JTextField();
			textContrasenaActual.setBounds(232, 27, 114, 18);
			textContrasenaActual.setColumns(10);
		}
		return textContrasenaActual;
	}

	private JTextField getTextNuevaContrasena() {
		if(textNuevaContrasena == null) {
			textNuevaContrasena = new JTextField();
			textNuevaContrasena.setBounds(231, 57, 114, 18);
			textNuevaContrasena.setColumns(10);
		}
		return textNuevaContrasena;
	}
	

	private JTextField getTextRepetirContrasena() {
		if(textRepetirContrasena == null) {
			textRepetirContrasena = new JTextField();
			textRepetirContrasena.setBounds(232, 88, 114, 18);
			textRepetirContrasena.setColumns(10);
		}
		return textRepetirContrasena;
	}
	

	 private JButton getBotonVolverAtras() { 
		 if( botonVolverAtras == null) {
			 botonVolverAtras = new JButton("Volver Atras");
			 botonVolverAtras.setBounds(242, 153, 104, 24);
			 botonVolverAtras.addActionListener(new ActionListener() { 
				 public void actionPerformed(ActionEvent arg0) { 
					 JFrame a = new UsuarioGUI();
					 a.setVisible(true);
					 CambiarContrasena.this.dispose();
				 }
			 }); 
		}
		return botonVolverAtras;
	}
	 
	
	
	 private JButton getBotonConfirmar() {
		 if(botonConfirmar == null) {
			 botonConfirmar = new JButton("Confirmar");
			 botonConfirmar.setBounds(123, 153, 91, 24);
			 botonConfirmar.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent arg1) {
					 if(textContrasenaActual.getText().equals(UsuarioGUI.getUsuario().getPass())) {
						 String nueva = textNuevaContrasena.getText();
						 String repetida = textRepetirContrasena.getText();
						 if(nueva.equals(repetida)) {
							BLFacade facade = Inicio.getBusinessLogic();
							labelAvisoPantalla.setText(UsuarioGUI.getUsuario().getId());
							facade.cambiarContrasena(UsuarioGUI.getUsuario(),textNuevaContrasena.getText());
							CambiarContrasena.this.dispose();
						 }else {
							//getLabelAvisoPantalla().setText("Falla segundo if");
							
						 }
					 }else {
						// getLabelAvisoPantalla().setText("falla aqui");
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
	private JLabel getLabelAvisoPantalla() {
		if (labelAvisoPantalla == null) {
			labelAvisoPantalla = new JLabel("");
			labelAvisoPantalla.setBounds(12, 127, 415, 14);
		}
		return labelAvisoPantalla;
	}
}
