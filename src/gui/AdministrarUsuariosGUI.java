package gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Usuario;

import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

public class AdministrarUsuariosGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Usuario> listaUsuarios;
	private DefaultListModel<Usuario> modeloUsuarios = new DefaultListModel<Usuario>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrarUsuariosGUI frame = new AdministrarUsuariosGUI();
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
	public AdministrarUsuariosGUI() {
		setTitle("AdministrarUsuarios");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				BLFacade facade = Inicio.getBusinessLogic();
				Collection<Usuario> lista = facade.getAllUsers();
				for (Usuario u : lista) {
					modeloUsuarios.addElement(u);
				}
				listaUsuarios.setModel(modeloUsuarios);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getListaUsuarios());
	}
	
	private JList<Usuario> getListaUsuarios() {
		if (listaUsuarios == null) {
			listaUsuarios = new JList<Usuario>();
			listaUsuarios.setBounds(44, 127, 343, 63);
			listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listaUsuarios;
	}
}