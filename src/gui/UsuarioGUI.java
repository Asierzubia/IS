package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Usuario;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsuarioGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel mensajeEstandar1;
	private JLabel mensajeNombreUsuario;
	private JButton botonCerrarSesion;
	private JLabel mensajeEstandar2;
	private JLabel mensajeSaldoUsuario;
	private JButton botonApostar;
	private JButton botonVerApuestas;
	private JSpinner modificadorSaldo;
	private JButton botonAumentarSaldo;
	private static Usuario usuario;
	private JButton botonChangePassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usuario = null;
					UsuarioGUI frame = new UsuarioGUI();
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
	public UsuarioGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				if(usuario!=null) {
					mensajeNombreUsuario.setText(usuario.getId());
					mensajeSaldoUsuario.setText(Double.toString(usuario.getMoney()));
				}
			}
		});
		setTitle("Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getMensajeEstandar1());
		contentPane.add(getMensajeEstandar2());
		contentPane.add(getMensajeNombreUsuario());
		contentPane.add(getMensajeSaldoUsuario());
		contentPane.add(getBotonCerrarSesion());
		contentPane.add(getBotonApostar());
		contentPane.add(getBotonVerApuestas());
		contentPane.add(getModificadorSaldo());
		contentPane.add(getBotonAumentarSaldo());
		contentPane.add(getBotonChangePassword());
		contentPane.add(getBotonChangePassword());
	}
	
	private JLabel getMensajeEstandar1() {
		if (mensajeEstandar1 == null) {
			mensajeEstandar1 = new JLabel("Bienvenido al sistema, ");
			mensajeEstandar1.setBounds(11, 16, 132, 16);
		}
		return mensajeEstandar1;
	}
	
	private JLabel getMensajeNombreUsuario() {
		if (mensajeNombreUsuario == null) {
			mensajeNombreUsuario = new JLabel("");
			mensajeNombreUsuario.setBounds(143, 16, 259, 16);
		}
		return mensajeNombreUsuario;
	}
	
	private JButton getBotonCerrarSesion() {
		if (botonCerrarSesion == null) {
			botonCerrarSesion = new JButton("Cerrar Sesión");
			botonCerrarSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					UsuarioGUI.this.dispose();
					UsuarioGUI.setUsuario(null);
					Inicio a = new Inicio();
					a.setVisible(true);
				}
			});
			botonCerrarSesion.setBounds(284, 215, 136, 25);
		}
		return botonCerrarSesion;
	}
	
	private JLabel getMensajeEstandar2() {
		if (mensajeEstandar2 == null) {
			mensajeEstandar2 = new JLabel("Saldo disponible");
			mensajeEstandar2.setBounds(11, 39, 104, 16);
		}
		return mensajeEstandar2;
	}
	
	private JLabel getMensajeSaldoUsuario() {
		if (mensajeSaldoUsuario == null) {
			mensajeSaldoUsuario = new JLabel("");
			mensajeSaldoUsuario.setBounds(127, 40, 255, 16);
		}
		return mensajeSaldoUsuario;
	}
	
	private JButton getBotonApostar() {
		if (botonApostar == null) {
			botonApostar = new JButton("Apostar");
			botonApostar.setBounds(11, 146, 97, 25);
			botonApostar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {					
					ApostarGUI a = new ApostarGUI();
					a.setVisible(true);
				}
			});
		}
		return botonApostar;
	}
	
	private JButton getBotonVerApuestas() {
		if (botonVerApuestas == null) {
			botonVerApuestas = new JButton("Ver tus apuestas");
			botonVerApuestas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VerApuestasGUI a = new VerApuestasGUI(usuario.getId(),false);
					a.setVisible(true);
				}
			});
			botonVerApuestas.setBounds(127, 146, 150, 25);
		}
		return botonVerApuestas;
	}
	
	private JSpinner getModificadorSaldo() {
		if (modificadorSaldo == null) {
			modificadorSaldo = new JSpinner();
			modificadorSaldo.setModel(new SpinnerNumberModel(new Double(1), new Double(1), null, new Double(1)));
			modificadorSaldo.setBounds(10, 83, 127, 22);
		}
		return modificadorSaldo;
	}
	
	private JButton getBotonAumentarSaldo() {
		if (botonAumentarSaldo == null) {
			botonAumentarSaldo = new JButton("Aumentar saldo");
			botonAumentarSaldo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BLFacade facade = Inicio.getBusinessLogic();
					Double saldo = (Double) modificadorSaldo.getValue();
					facade.incrementarSaldo(usuario.getId(), saldo);
					UsuarioGUI.setUsuario(facade.tryUser(usuario.getId(), usuario.getPass()));
					mensajeSaldoUsuario.setText(Double.toString(usuario.getMoney()));
				}
			});
			botonAumentarSaldo.setBounds(175, 84, 150, 25);
		}
		return botonAumentarSaldo;
	}
	
	public static void setUsuario(Usuario pUsuario) {
		usuario = pUsuario;
	}
	
	public static Usuario getUsuario() {
		return usuario;
	}
	private JButton getBotonChangePassword() {
		if (botonChangePassword == null) {
			botonChangePassword = new JButton("Cambiar Contraseña");
			botonChangePassword.setBounds(105, 215, 172, 24);
			botonChangePassword.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg2) {
					CambiarContrasena a = new CambiarContrasena();
					a.setVisible(true);
				}
			});
		}
		return botonChangePassword;
	}
	
}