package application;

import java.util.ArrayList;
import java.util.Hashtable;

public class FeedAnalyzer {

	
	public static String getTrendingTagsFromFeed(Tweet[] feed) {
		
		ArrayList<String> AllTags = new ArrayList<>();
		
		for (Tweet tweet : feed) 
			AllTags.addAll(tweet.getHashtags());
		
		if (AllTags.size() == 0) return null;
		
		Hashtable<String, Integer> tagCount = new Hashtable<>();
		
		for (String tag: AllTags) {
			if (tagCount.contains(tag)) 
				tagCount.put(tag, tagCount.get(tag)+1);
			else 
				tagCount.put(tag, 1);
		}
		
		String topTrending = AllTags.get(0);
	
		for (int i = 0; i<AllTags.size(); i++) {
			
			String tempTag = AllTags.get(i);
			int tempTagCount = tagCount.get(tempTag);
			
			if (tempTagCount > tagCount.get(topTrending)) topTrending = tempTag;
			
			
		}
		
		System.out.println("TEST - TopTrending: " + topTrending);
		return topTrending;
	}
	
}
