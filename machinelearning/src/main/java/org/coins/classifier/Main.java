package org.coins.classifier;

import com.google.common.collect.Lists;
import org.coins.classifier.lang.counting.CountingContext;
import org.coins.classifier.lang.counting.OccurrenceCounter;
import org.coins.classifier.lang.words.WordType;
import org.coins.classifier.twitter.TwitterUser;
import org.coins.classifier.twitter.stats.UserGroup;
import twitter4j.TwitterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 * Created by johannes on 11/28/15.
 */
public class Main {

    public static void main(String[] args) throws TwitterException {
        final OccurrenceCounter occurrenceCounter = new OccurrenceCounter(WordType.values());
        if (args.length == 0) {
            UserGroup userGroup = new UserGroup("Candidates");
            userGroup.addUsers(Lists.newArrayList(
                    new TwitterUser("berniesanders"),
                    new TwitterUser("HillaryClinton"),
                    new TwitterUser("realDonaldTrump"),
                    new TwitterUser("RealBenCarson"),
                    new TwitterUser("marcorubio"),
                    new TwitterUser("JebBush")));
            userGroup.analyzeTweets(occurrenceCounter, null);
            userGroup.printToStream(System.out, null);
            userGroup.getUsers().get(0).printToStream(System.out);
            try {
                String fileName = System.getProperty("user.home")+"/candidates.csv";
                userGroup.printToFile(new PrintStream(new File(fileName)), null);
                System.out.println("Successfully created "+fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            for (String path : args) {
                try (Stream<String> lines = Files.lines(new File(path).toPath())) {
                    CountingContext context = new CountingContext();
                    UserGroup userGroup = new UserGroup(path);
                    lines.forEach((text) -> userGroup.addUser(new TwitterUser(text)));
                    userGroup.analyzeTweets(occurrenceCounter, context);
                    userGroup.printToStream(System.out, context);
                    try {
                        String fileName = path + "-results.csv";
                        userGroup.printToFile(new PrintStream(new File(fileName)), context);
                        System.out.println("Successfully created "+fileName);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    System.out.println("ERROR: file " + path + " cannot be opened");
                }
            }

        }
    }


}
