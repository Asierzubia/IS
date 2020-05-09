package gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.*;

import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VerApuestasGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaApuestas;
	private JScrollPane scrollPane;
	private String id;
	private boolean tipo;
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
	public VerApuestasGUI(String pId, boolean pTipo) {
		this.id = pId;
		this.tipo = pTipo;
		setTitle("VerApuestas");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				hacerCosas();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 652, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
	}
	
	private void hacerCosas() {
		if(this.tipo == true) {
			recargarTablaAdmin();
		}else {
			recargarTabla(id);
		}
	}
	
	private JTable getTablaApuestas() {
		if (tablaApuestas == null) {
			tablaApuestas = new JTable();
		}
		return tablaApuestas;
	}
	
	private void recargarTabla(String pId) {
		BLFacade facade = Inicio.getBusinessLogic();
		String[] columnNames = {"Usuario", "Question", "Apostado", "Dinero apostado"};
	    Object[][] data = {};
		Collection<Apuesta> listaApuestas = facade.getApuestasUser(pId);
		Collection<ApuestaGalgo> listaApuestaGalgos = facade.getApuestasGalgosUser(pId);
		DefaultTableModel modelo = new DefaultTableModel(data, columnNames);
		tablaApuestas.setModel(modelo);
		for (Apuesta a : listaApuestas) {
			Object [] fila = new Object[4];
			fila[0] = a.getIdUsuario();
			fila[1] = a.getQuestion().getQuestion();
			fila[2] = a.getApostado();
			fila[3] = a.getDineroApostado();
			modelo.addRow(fila);
		}
		for (ApuestaGalgo b : listaApuestaGalgos) {
			Object [] fila = new Object[4];
			fila[0] = b.getIdUsuario();
			fila[1] = b.getGalgo().getCarrera();
			fila[2] = b.getGalgo().getNombreGalgo();
			fila[3] = b.getDineroApostado();
			modelo.addRow(fila);
		}
	}
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 13, 610, 283);
			scrollPane.setViewportView(getTablaApuestas());
		}
		return scrollPane;
	}
	
	private void recargarTablaAdmin() {
		BLFacade facade = Inicio.getBusinessLogic();
		String[] columnNames = {"Usuario", "Question", "Apostado", "Dinero apostado"};
	    Object[][] data = {};
		Collection<Usuario> listaUsuarios = facade.getAllUsers();
		DefaultTableModel modelo = new DefaultTableModel(data, columnNames);
		tablaApuestas.setModel(modelo);
		for (Usuario usu : listaUsuarios){
			Collection<Apuesta> listaApuestas = facade.getApuestasUser(usu.getId());
			Collection<ApuestaGalgo> listaApuestaGalgos = facade.getApuestasGalgosUser(usu.getId());
			for (Apuesta a : listaApuestas) {
				Object [] fila = new Object[4];
				fila[0] = a.getIdUsuario();
				fila[1] = a.getQuestion().getQuestion();
				fila[2] = a.getApostado();
				fila[3] = a.getDineroApostado();
				modelo.addRow(fila);
			}
			for (ApuestaGalgo b : listaApuestaGalgos) {
				Object [] fila = new Object[4];
				fila[0] = b.getIdUsuario();
				fila[1] = b.getGalgo().getCarrera();
				fila[2] = b.getGalgo().getNombreGalgo();
				fila[3] = b.getDineroApostado();
				modelo.addRow(fila);
			}
		}	
	}
}