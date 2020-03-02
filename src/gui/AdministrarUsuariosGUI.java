package gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Usuario;

import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import javax.swing.JTable;

public class AdministrarUsuariosGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaUsuarios;

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
				String[] columnNames = {"Usuario", "Pass", "Dinero"};
				tablaUsuarios.getSelectedRow();
			    Object[][] data = {};
				Collection<Usuario> lista2 = facade.getAllUsers();
				DefaultTableModel modelo = new DefaultTableModel(data,columnNames);
				tablaUsuarios.setModel(modelo);
				for (Usuario u : lista2) {
					Object [] fila = new Object[3];
					fila[0] = u.getId();
					fila[1] = u.getPass();
					fila[2] = u.getMoney();
					modelo.addRow(fila);
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTablaUsuarios());
	}
	private JTable getTablaUsuarios() {
		if (tablaUsuarios == null) {
			tablaUsuarios = new JTable();
			tablaUsuarios.setBounds(12, 12, 408, 142);
		}
		return tablaUsuarios;
	}
}