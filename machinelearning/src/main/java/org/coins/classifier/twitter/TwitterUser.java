package org.coins.classifier.twitter;

import org.coins.classifier.lang.counting.CountWrapper;
import org.coins.classifier.lang.counting.Countable;
import org.coins.classifier.lang.counting.CountingContext;
import org.coins.classifier.lang.counting.OccurrenceCounter;
import org.coins.classifier.twitter.stats.Groupable;
import org.coins.classifier.twitter.stats.TwitterStats;
import twitter4j.Status;
import twitter4j.User;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by johannes on 11/29/15.
 */
public class TwitterUser implements Groupable, Serializable {

    private final String handle;
    private List<Status> statuses;
    private User user;
    private transient CountingContext defaultContext; //CountingContext is not serializable
    private transient Map<Countable, CountWrapper> counts; //we do not need to serialize this

    public TwitterUser(String handle) {
        this.handle = handle;
        defaultContext = new CountingContext();
    }

    public String getHandle() {
        return handle;
    }

    public List<Status> getStatuses() {
        if (statuses == null) {
            statuses = TwitterUtil.getUserTimeline(handle);
        }
        return statuses;
    }

    public User getUser() {
        if (user == null) {
            user = TwitterUtil.getUser(handle);
        }
        return user;
    }

    /**
     * Number of users that follow the user
     */
    public int getFollowerCount() {
        return getUser().getFollowersCount();
    }

    /**
     * Number of statuses the user posted
     */
    public int getStatusesCount() {
        return getUser().getStatusesCount();
    }

    /**
     * Number of users the user follows
     */
    public int getFollowingCount() {
        return getUser().getFriendsCount();
    }

    /**
     * Number of tweets the user has favorited
     */
    public int getFavoritesCount() {
        return getUser().getFavouritesCount();
    }

    /**
     * Number of lists the user is on
     */
    public int getListedCount() {
        return getUser().getListedCount();
    }

    @Override
    public double getFrequency(Countable countable, CountingContext context) {
        if (context == null) {
            context = defaultContext;
        }
        if (countable instanceof TwitterStats) {
            switch ((TwitterStats)countable) {
                case FOLLOWER:
                    return getFollowerCount();
                case FOLLOWING:
                    return getFollowingCount();
                case LISTED:
                    return getListedCount();
                case FAVORITES:
                    return getFavoritesCount();
                case STATUSES:
                    return getStatusesCount();
            }
        }
        final CountWrapper countWrapper = context.getCountsByCountable().get(countable);
        return countWrapper == null ? 0.0 : countWrapper.getCount() / (double) context.getWordsCounted();
    }

    @Override
    public void analyzeTweets(OccurrenceCounter counter, CountingContext context) {
        if (context == null) {
            context = defaultContext;
        }
        for (Status status : getStatuses()) {
            counter.countOccurrences(status.getText(), context);
        }
    }

    public void printToStream(PrintStream stream) {
        stream.println("\n=======================\n");
        stream.println("Word frequencies in tweets by @"+handle);
        defaultContext.printFrequencies(stream);
    }

    public void createDefaultContext() {
        if (defaultContext == null) {
            defaultContext = new CountingContext();
        }
    }

}
