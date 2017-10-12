package controller;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminGUI extends JFrame{
	private JPanel mainPanel = new JPanel();
	private JLabel nameLabel = new JLabel("Name");
	private JLabel urlLabel = new JLabel("URL");
	private JTextField nameInput = new JTextField();
	private JTextField urlInput = new JTextField();
	
	public AdminGUI() {
		add(mainPanel);
		mainPanel.setLayout(new GridLayout(2,2));
		mainPanel.add(nameLabel);
		mainPanel.add(urlLabel);
		mainPanel.add(nameInput);
		mainPanel.add(urlInput);
		urlInput.addKeyListener(new Listener());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	private class Listener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				System.out.println("It's working");
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
		
	}
}
