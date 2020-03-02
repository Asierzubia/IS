package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Admin;
import domain.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel labelUsuario;
	private JLabel labelContraseña;
	private JTextField usuarioLogin;
	private JPasswordField contraseñaLogin;
	private JButton botonLogin;
	private JButton botonRegistro;
	private JLabel labelRegistro;
	private JLabel mensajeLogin;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLabelUsuario());
		contentPane.add(getLabelContraseña());
		contentPane.add(getUsuarioLogin());
		contentPane.add(getContraseñaLogin());
		contentPane.add(getBotonLogin());
		contentPane.add(getBotonRegistro());
		contentPane.add(getLabelRegistro());
		contentPane.add(getMensajeLogin());
	}


	private JLabel getLabelUsuario() {
		if (labelUsuario == null) {
			labelUsuario = new JLabel("Usuario");
			labelUsuario.setBounds(55, 37, 56, 16);
		}
		return labelUsuario;
	}
	
	private JLabel getLabelContraseña() {
		if (labelContraseña == null) {
			labelContraseña = new JLabel("Contraseña");
			labelContraseña.setBounds(40, 80, 87, 16);
		}
		return labelContraseña;
	}
	
	private JTextField getUsuarioLogin() {
		if (usuarioLogin == null) {
			usuarioLogin = new JTextField();
			usuarioLogin.setBounds(124, 34, 178, 22);
			usuarioLogin.setColumns(10);
		}
		return usuarioLogin;
	}
	
	private JPasswordField getContraseñaLogin() {
		if (contraseñaLogin == null) {
			contraseñaLogin = new JPasswordField();
			contraseñaLogin.setBounds(124, 77, 178, 22);
		}
		return contraseñaLogin;
	}
	private JButton getBotonLogin() {
		if (botonLogin == null) {
			botonLogin = new JButton("Acceder");
			botonLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					/* Aqui va el tema LogIn */

					String usuario = usuarioLogin.getText();
					String contraseña = new String(contraseñaLogin.getPassword());
					
					BLFacade facade = Inicio.getBusinessLogic();
					
					Admin admin = facade.tryAdmin(usuario, contraseña);
					Usuario user = facade.tryUser(usuario, contraseña);
					
					if(admin == null && user == null) {
						mensajeLogin.setText("No existe un usuario o administrador con esos datos de acceso.");
					}else if(admin != null) {
						MainGUI a = new MainGUI();
						a.setVisible(true);
						Login.this.dispose();
					}else if(user != null) {						
						UsuarioGUI b = new UsuarioGUI();
						UsuarioGUI.setUsuario(facade.tryUser(usuario, contraseña));
						b.setVisible(true);
						Login.this.dispose();
					}
				}
			});
			botonLogin.setBounds(314, 53, 97, 25);
		}
		return botonLogin;
	}
	
	private JButton getBotonRegistro() {
		if (botonRegistro == null) {
			botonRegistro = new JButton("Registrarse");
			botonRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new Registro();
					a.setVisible(true);
					Login.this.dispose();
				}
			});
			botonRegistro.setBounds(224, 130, 127, 25);
		}
		return botonRegistro;
	}
	
	private JLabel getLabelRegistro() {
		if (labelRegistro == null) {
			labelRegistro = new JLabel("¿No tienes cuenta? Registrate");
			labelRegistro.setBounds(36, 134, 176, 16);
		}
		return labelRegistro;
	}
	
	private JLabel getMensajeLogin() {
		if (mensajeLogin == null) {
			mensajeLogin = new JLabel("");
			mensajeLogin.setBounds(21, 182, 399, 16);
		}
		return mensajeLogin;
	}
}