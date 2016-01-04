package org.coins.classifier;

import com.google.common.collect.Lists;
import com.sun.corba.se.impl.io.IIOPOutputStream;
import org.coins.classifier.lang.counting.OccurrenceCounter;
import org.coins.classifier.lang.words.WordType;
import org.coins.classifier.twitter.SerializationIterator;
import org.coins.classifier.twitter.SerializationUtil;
import org.coins.classifier.twitter.TwitterUser;
import org.coins.classifier.twitter.stats.UserGroup;
import twitter4j.TwitterException;

/**
 * Created by johannes on 11/28/15.
 */
public class Main {

    public static void main(String[] args) throws TwitterException {
        final OccurrenceCounter occurrenceCounter = new OccurrenceCounter(WordType.values());
        UserGroup userGroup = new UserGroup("Candidates");
        userGroup.addUsers(Lists.newArrayList(
                    new TwitterUser("berniesanders"),
                    new TwitterUser("HillaryClinton"),
                    new TwitterUser("realDonalTrump"),
                    new TwitterUser("marcorubio"),
                    new TwitterUser("JebBush")));
        userGroup.analyzeTweets(occurrenceCounter, null);
        userGroup.printToStream(System.out);

        SerializationIterator serIterator = new SerializationIterator();
        serIterator.iterate(userGroup);

    }
}
