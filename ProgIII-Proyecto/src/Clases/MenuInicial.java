package Clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Utilidades.JPanelIMG;
import javax.swing.JButton;
import Utilidades.JButtonTransparente;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuInicial frame = new MenuInicial();
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
	public MenuInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanelIMG panelIMG = new JPanelIMG();
		panelIMG.setBackground("img/Menu.jpg");
		contentPane.add(panelIMG, BorderLayout.CENTER);
		
		JButtonTransparente btntrnsprntAdventure = new JButtonTransparente();
		btntrnsprntAdventure.setBounds(263, 71, 395, 52);
		btntrnsprntAdventure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btntrnsprntAdventure.setText("Jugar");
		btntrnsprntAdventure.setOpaque();
		panelIMG.setLayout(null);
		panelIMG.add(btntrnsprntAdventure);
	}

}
