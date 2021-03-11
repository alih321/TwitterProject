package twitter;

import javax.swing.*;

public class NewTweetPanel extends JPanel {

	public NewTweetPanel() {
		
		defineLayout();
	
		JTextArea TweetBox = new JTextArea(50,50);
		
		this.add(Box.createVerticalStrut(20));
		this.add(new JLabel("NEW TWEET"));
		this.add(new JSeparator());
		this.add(TweetBox);

		this.add(new JSeparator(50));
		
		this.add(new JButton("Post Tweet"));
		
		this.setPreferredSize(this.getPreferredSize());
	}
	
	private void defineLayout() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		
	}
	
	
}
