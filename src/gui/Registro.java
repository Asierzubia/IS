package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registro extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField usuarioRegistro;
	private JPasswordField contraseñaRegistro1;
	private JButton btnVolverALogin;
	private JButton btnRegistrar;
	private JLabel lblRepetirContrasea;
	private JPasswordField contraseñaRegistro2;
	private JLabel mensajeRegistro;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param frame 
	 */
	public Registro() {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getUsuarioRegistro());
		contentPane.add(getContraseñaRegistro1());
		contentPane.add(getBtnVolverALogin());
		contentPane.add(getBtnRegistrar());
		contentPane.add(getLblRepetirContrasea());
		contentPane.add(getContraseñaRegistro2());
		contentPane.add(getMensajeRegistro());
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Usuario");
			lblNewLabel.setBounds(28, 41, 56, 16);
		}
		return lblNewLabel;
	}
	
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Contraseña");
			lblNewLabel_1.setBounds(28, 81, 97, 16);
		}
		return lblNewLabel_1;
	}
	
	private JTextField getUsuarioRegistro() {
		if (usuarioRegistro == null) {
			usuarioRegistro = new JTextField();
			usuarioRegistro.setBounds(187, 38, 187, 22);
			usuarioRegistro.setColumns(10);
		}
		return usuarioRegistro;
	}
	
	private JPasswordField getContraseñaRegistro1() {
		if (contraseñaRegistro1 == null) {
			contraseñaRegistro1 = new JPasswordField();
			contraseñaRegistro1.setBounds(187, 78, 187, 22);
		}
		return contraseñaRegistro1;
	}
	
	private JButton getBtnVolverALogin() {
		if (btnVolverALogin == null) {
			btnVolverALogin = new JButton("Volver a Login");
			btnVolverALogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new Login();
					a.setVisible(true);
					Registro.this.dispose();
				}
			});
			btnVolverALogin.setBounds(243, 164, 131, 25);
		}
		return btnVolverALogin;
	}
	
	private JButton getBtnRegistrar() {
		if (btnRegistrar == null) {
			btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					/* Aqui va el tema del registro */
					String usuario = usuarioRegistro.getText();
					String contraseña1 = new String(contraseñaRegistro1.getPassword());
					String contraseña2 = new String(contraseñaRegistro2.getPassword());
					
					BLFacade facade = Inicio.getBusinessLogic();
					
					if(usuario.equals("")|| contraseña1.equals("") || contraseña2.equals("") || !contraseña1.equals(contraseña2)) {
						mensajeRegistro.setText("Uno de los campos esta vacío o las contraseñas no coinciden.");
					}else {
						if(facade.registrarUsuario(usuario, contraseña1)) mensajeRegistro.setText("Se ha registrado el usuario.");
						else mensajeRegistro.setText("El usuario no se ha podido registrar.");
					}
				}
			});
			btnRegistrar.setBounds(28, 164, 97, 25);
		}
		return btnRegistrar;
	}
	
	private JLabel getLblRepetirContrasea() {
		if (lblRepetirContrasea == null) {
			lblRepetirContrasea = new JLabel("Repetir contraseña");
			lblRepetirContrasea.setBounds(28, 124, 116, 16);
		}
		return lblRepetirContrasea;
	}
	
	private JPasswordField getContraseñaRegistro2() {
		if (contraseñaRegistro2 == null) {
			contraseñaRegistro2 = new JPasswordField();
			contraseñaRegistro2.setBounds(187, 121, 187, 22);
		}
		return contraseñaRegistro2;
	}
	
	private JLabel getMensajeRegistro() {
		if (mensajeRegistro == null) {
			mensajeRegistro = new JLabel("");
			mensajeRegistro.setBounds(28, 202, 472, 16);
		}
		return mensajeRegistro;
	}
}