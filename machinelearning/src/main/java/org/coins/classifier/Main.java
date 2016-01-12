package org.coins.classifier;

import com.google.common.collect.Lists;
import org.coins.classifier.lang.counting.OccurrenceCounter;
import org.coins.classifier.lang.words.WordType;
import org.coins.classifier.twitter.Serializer;
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

    public static void main(String[] args) throws TwitterException, IOException {
        final OccurrenceCounter occurrenceCounter = new OccurrenceCounter(WordType.values());

        if (args.length == 0) {
            UserGroup userGroup = new UserGroup("Candidates");
            userGroup.addUsers(Lists.newArrayList(
                    new TwitterUser("berniesanders"),
                    new TwitterUser("HillaryClinton"),
                    new TwitterUser("realDonaldTrump"),
                    new TwitterUser("RealBenCarson"),
                    new TwitterUser("marcorubio"),
                    new TwitterUser("JebBush")
            ));
            userGroup.analyzeTweets(occurrenceCounter, null);
            userGroup.printToStream(System.out, null);
            userGroup.getUsers().get(0).printToStream(System.out);

            /*
             * serialization of fetched twitter user
             * The folder where you want to save the twitter users must exist!
             */
            Serializer.serialize(userGroup,"\\twitter user\\republicans");

            /*
             * deserialisation if twitter user
             * do only enter the filename, like berniesanders_2016_01_06_12_15.twitteruser
             */
//            List<String> fileNameList = Lists.newArrayList(
//                    "filename",
//                    "filename",
//                    "filename",
//                    "filename",
//                    "filename",
//                    "filename");
//
//            UserGroup deserializedTwitterUserGroup = Serializer.deserialize(fileNameList, "\\\twitter user\\sanders" , "UserGroupName");

            try {
                String fileName = System.getProperty("user.home")+"/Republicans.csv";
                userGroup.printToFile(new PrintStream(new File(fileName)), null);
                System.out.println("Successfully created "+fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            for (String path : args) {
                try (Stream<String> lines = Files.lines(new File(path).toPath())) {
                    UserGroup userGroup = new UserGroup(path);
                    lines.forEach((text) -> userGroup.addUser(new TwitterUser(text)));
                    userGroup.analyzeTweets(occurrenceCounter, null);
                    userGroup.printToStream(System.out, null);
                    try {
                        String fileName = path + "-results.csv";
                        userGroup.printToFile(new PrintStream(new File(fileName)), null);
                        System.out.println("Successfully created "+fileName);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    //print individual user file
                    try {
                        String fileName = path + "-users.csv";
                        userGroup.printUsersToFile(new PrintStream(new File(fileName)), path);
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
