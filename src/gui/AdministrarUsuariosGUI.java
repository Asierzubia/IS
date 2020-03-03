package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Usuario;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class AdministrarUsuariosGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaUsuarios;
	private JButton botonBorrarUsuario;
	private JScrollPane scrollPane;

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
				recargarTabla();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBotonBorrarUsuario());
		contentPane.add(getScrollPane());
	}
	
	private JTable getTablaUsuarios() {
		if (tablaUsuarios == null) {
			tablaUsuarios = new JTable();
		}
		return tablaUsuarios;
	}
	
	private JButton getBotonBorrarUsuario() {
		if (botonBorrarUsuario == null) {
			botonBorrarUsuario = new JButton("Borrar Usuario");
			botonBorrarUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					BLFacade facade = Inicio.getBusinessLogic();
					ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) facade.getAllUsers();
					facade.deleteUser(listaUsuarios.get(tablaUsuarios.getSelectedRow()));
					recargarTabla();
				}
			});
			botonBorrarUsuario.setBounds(12, 192, 138, 25);
		}
		return botonBorrarUsuario;
	}
	
	private void recargarTabla() {
		BLFacade facade = Inicio.getBusinessLogic();
		String[] columnNames = {"Usuario", "Pass", "Dinero"};
		tablaUsuarios.getSelectedRow();
	    Object[][] data = {};
		Collection<Usuario> lista2 = facade.getAllUsers();
		DefaultTableModel modelo = new DefaultTableModel(data, columnNames);
		tablaUsuarios.setModel(modelo);
		for (Usuario u : lista2) {
			Object [] fila = new Object[3];
			fila[0] = u.getId();
			fila[1] = u.getPass();
			fila[2] = u.getMoney();
			modelo.addRow(fila);
		}
	}
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 13, 408, 166);
			scrollPane.setViewportView(getTablaUsuarios());
		}
		return scrollPane;
	}
}