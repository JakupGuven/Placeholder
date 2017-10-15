package controller;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.DatabaseConnector;

public class AdminGUI extends JFrame{
	private JPanel mainPanel = new JPanel();
	private JLabel nameLabel = new JLabel("Name");
	private JLabel urlLabel = new JLabel("URL");
	private JTextField nameInput = new JTextField();
	private JTextField urlInput = new JTextField();
	private JLabel label = new JLabel("Välj din databas som ska finnas i undermappen db i projektmappen.");
	private JFileChooser fileChooser = new JFileChooser();
	
	public AdminGUI() {
		add(mainPanel);
		mainPanel.setLayout(new GridLayout(3,2));
		mainPanel.add(nameLabel);
		mainPanel.add(urlLabel);
		mainPanel.add(nameInput);
		mainPanel.add(urlInput);
		mainPanel.add(label);
		mainPanel.add(fileChooser);
		urlInput.addKeyListener(new Listener());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		AdminGUI gui = new AdminGUI();
	}
	
	private class Listener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				DatabaseConnector.addScheduale(nameInput.getText(), urlInput.getText());
				nameInput.setText("");
				urlInput.setText("");
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
		
	}
}
