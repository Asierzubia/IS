package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class AnadirRespuestaGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblEligeElEvento;
	private JLabel lblEligeLaQuestion;
	private JLabel lblIntroduceLaRespuesta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnadirRespuestaGUI frame = new AnadirRespuestaGUI();
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
	public AnadirRespuestaGUI() {
		setTitle("Añadir Respuesta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblEligeElEvento());
		contentPane.add(getLblEligeLaQuestion());
		contentPane.add(getLblIntroduceLaRespuesta());
	}
	private JLabel getLblEligeElEvento() {
		if (lblEligeElEvento == null) {
			lblEligeElEvento = new JLabel("Elige el evento");
			lblEligeElEvento.setBounds(22, 31, 111, 16);
		}
		return lblEligeElEvento;
	}
	private JLabel getLblEligeLaQuestion() {
		if (lblEligeLaQuestion == null) {
			lblEligeLaQuestion = new JLabel("Elige la question");
			lblEligeLaQuestion.setBounds(22, 80, 111, 16);
		}
		return lblEligeLaQuestion;
	}
	private JLabel getLblIntroduceLaRespuesta() {
		if (lblIntroduceLaRespuesta == null) {
			lblIntroduceLaRespuesta = new JLabel("Introduce la respuesta");
			lblIntroduceLaRespuesta.setBounds(22, 135, 137, 16);
		}
		return lblIntroduceLaRespuesta;
	}
}
