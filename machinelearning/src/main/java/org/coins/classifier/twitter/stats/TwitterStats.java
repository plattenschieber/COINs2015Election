package org.coins.classifier.twitter.stats;

import org.coins.classifier.lang.counting.Countable;

/**
 * Created by johannes on 1/10/16.
 */
public enum TwitterStats implements Countable {
    FOLLOWER,
    FOLLOWING,
    LISTED,
    FAVORITES,
    STATUSES;

    @Override
    public String getName() {
        return name();
    }
}
