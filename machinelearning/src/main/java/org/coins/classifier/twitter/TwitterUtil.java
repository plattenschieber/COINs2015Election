package org.coins.classifier.twitter;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.List;

/**
 * Created by johannes on 11/28/15.
 */
public class TwitterUtil {

    private static Twitter twitter = TwitterFactory.getSingleton();;

    public static List<Status> getUserTimeline(String userHandle) {
        Paging paging = new Paging(1, 200);
        try {
            return twitter.getUserTimeline(userHandle, paging);
        } catch (TwitterException e) {
            throw new RuntimeException(e);
        }
    }

}
