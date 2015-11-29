package org.coins.classifier.twitter.stats;

import org.coins.classifier.lang.counting.Countable;
import org.coins.classifier.lang.counting.CountingContext;
import org.coins.classifier.lang.counting.OccurrenceCounter;

/**
 * Created by johannes on 11/29/15.
 */
public interface Groupable {
    double getFrequency(Countable countable, CountingContext context);
    void analyzeTweets(OccurrenceCounter counter, CountingContext context);
}
