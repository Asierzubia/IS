package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inicio extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblQuirolbets;
	private JButton botonIniciarSesion;
	private JButton botonRegistrarse;
	private static BLFacade appFacadeInterface;
	
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setTitle("Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblQuirolbets());
		contentPane.add(getBotonIniciarSesion());
		contentPane.add(getBotonRegistrarse());
	}
	private JLabel getLblQuirolbets() {
		if (lblQuirolbets == null) {
			lblQuirolbets = new JLabel("QuirolBets");
			lblQuirolbets.setFont(new Font("Lucida Bright", Font.PLAIN, 33));
			lblQuirolbets.setBounds(217, 13, 185, 95);
		}
		return lblQuirolbets;
	}
	private JButton getBotonIniciarSesion() {
		if (botonIniciarSesion == null) {
			botonIniciarSesion = new JButton("Iniciar sesión");
			botonIniciarSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Login a = new Login();
					Inicio.this.dispose();
					a.setVisible(true);
				}
			});
			botonIniciarSesion.setBounds(181, 108, 255, 25);
		}
		return botonIniciarSesion;
	}
	private JButton getBotonRegistrarse() {
		if (botonRegistrarse == null) {
			botonRegistrarse = new JButton("Registrarse");
			botonRegistrarse.setBounds(181, 161, 255, 25);
			botonRegistrarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Registro a = new Registro();
					Inicio.this.dispose();
					a.setVisible(true);
				}
			});
		}
		return botonRegistrarse;
	}
}
