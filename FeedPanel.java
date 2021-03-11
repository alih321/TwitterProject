package twitter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class FeedPanel extends JPanel {
	
	public FeedPanel(User user) {

		DefineLayout();

		this.add(new JLabel("YOUR FEED"));
		this.add(new JSeparator());
		
		Tweet[] tweets = user.getTwitterFeed();
		
		for (int i = 0; i<tweets.length; i++) {
			this.add(new TweetPanel(tweets[i]));
		}
		
        this.setPreferredSize(this.getPreferredSize());

		
	}
	
	private void DefineLayout() {
				
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

}
