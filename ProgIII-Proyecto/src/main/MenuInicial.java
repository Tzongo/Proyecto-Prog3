package main;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utilidades.JButtonTransparente;
import utilidades.JPanelIMG;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

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
		setContentPane(contentPane);
		
		JButtonTransparente buttonTransparente = new JButtonTransparente();
		buttonTransparente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		contentPane.setLayout(new BorderLayout(0, 0));
		buttonTransparente.setBounds(327, 134, 347, 34);
		buttonTransparente.setOpaque();
		buttonTransparente.setText("Jugar");
		buttonTransparente.setPreferredSize(new Dimension(395, 52));
		buttonTransparente.setMinimumSize(new Dimension(395, 52));
		
		JPanelIMG panelIMG = new JPanelIMG();
		panelIMG.setBackground("img/MenuPrincipal.jpg");
		panelIMG.add(buttonTransparente);
		panelIMG.setLayout(null);
		contentPane.add(panelIMG);
	}

}
