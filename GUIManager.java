package twitter;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUIManager {
	
	static User user;
	
		public static void createSignInPanel() {
			
			JFrame f = new JFrame("Twitter Sign In");
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	        f.setPreferredSize(new Dimension(350, 250));
	        f.setMaximumSize(new Dimension(350,250));
			f.setLocation(500,200);
			
			JPanel mainPanel = new JPanel();
			JPanel topPanel = new JPanel();
			JPanel midPanel = new JPanel();
			
			topPanel.setSize(100, 100);
			midPanel.setSize(100,100);
			
			f.getContentPane().add(mainPanel);
			
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
			midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.X_AXIS));

			JTextField usernameArea = new JTextField();
			JTextField passArea = new JTextField();

			JButton attemptLogin = new JButton("Sign Up/In");
			JLabel usernameLabel = new JLabel("Username: ", SwingConstants.CENTER);
			JLabel passLabel = new JLabel("Password: ", SwingConstants.CENTER);
			JLabel messageLabel = new JLabel("");

			
			attemptLogin.setSize(100, 100);
			
			
			topPanel.add(usernameLabel);
			topPanel.add(usernameArea);

			
			midPanel.add(passLabel);
			midPanel.add(passArea);
			
			mainPanel.add(new JLabel("Welcome to Twitter"));			
			mainPanel.add(Box.createVerticalStrut(20));
			mainPanel.add(topPanel);
			mainPanel.add(Box.createVerticalStrut(20));
			mainPanel.add(midPanel);
			mainPanel.add(Box.createVerticalStrut(20));
			mainPanel.add(messageLabel);
			mainPanel.add(Box.createVerticalStrut(20));
			mainPanel.add(attemptLogin);
			
			mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			
			attemptLogin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String username = usernameArea.getText();
					String password = passArea.getText();
					
					System.out.println("Attempting to login...");
					
					if (AuthManager.attemptLogin(username, password) == true) {
						
						user = new User(username, true);

						f.setVisible(false);
						createMainFeedPanel();
					} else {
						messageLabel.setText("Username or Password was incorrect");
					}
				}
			});
			
			
			f.pack();
			
			f.setVisible(true);
		}
		
		
		public static void createMainFeedPanel() {
			
			JFrame f = new JFrame("Main Frame");
			
			f.setPreferredSize(new Dimension(1000,1000));
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			
			JPanel topPanel = new JPanel();
			JPanel midPanel = getCorrectPanel();
			JScrollPane scrollFrame = new JScrollPane(midPanel);
			JPanel bottomPanel = new JPanel();
			
			topPanel.setSize(100, 100);
			scrollFrame.setSize(100,100);
			
			bottomPanel.setSize(100,100);
			midPanel.setAutoscrolls(true);

			
			JButton[] buttons = {new JButton("My Feed"), new JButton("My Tweets"), new JButton("My Friends")};
			
			
			topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
			
			for (int i = 0; i<buttons.length; i++) {	
				topPanel.add(buttons[i]);
			}
			

			
			f.getContentPane().add(BorderLayout.NORTH, topPanel);
			f.getContentPane().add(BorderLayout.CENTER, scrollFrame);
			f.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
			
			f.pack();
			
			f.setVisible(true);
			
			
		}

		private static JPanel getCorrectPanel() { 
			

			return new FeedPanel(user);
			
		}

	}



