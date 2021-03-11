package twitter;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TweetPanel extends JPanel {

	
	public TweetPanel(Tweet tweet) {
		
		DefineLayout();

		JLabel user = new JLabel(tweet.username);
		
		JLabel tweetContent = new JLabel(tweet.content);
		JLabel date = new JLabel(tweet.getDateString());
		
		this.add(user);
		this.add(Box.createVerticalStrut(10));
		this.add(tweetContent);
		this.add(Box.createVerticalStrut(10));
		this.add(date);
		this.add(new JSeparator());
		
        this.setPreferredSize(this.getPreferredSize());

		
	}
	
	private void DefineLayout() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		
	}

	
}
