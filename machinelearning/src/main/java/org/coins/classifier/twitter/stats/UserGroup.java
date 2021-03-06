package org.coins.classifier.twitter.stats;

import com.google.common.collect.Lists;
import org.coins.classifier.lang.counting.Countable;
import org.coins.classifier.lang.counting.CountingContext;
import org.coins.classifier.twitter.TwitterUser;

import java.io.PrintStream;
import java.util.List;

/**
 * Created by johannes on 11/29/15.
 */
public class UserGroup extends ComparisonGroup implements Groupable {

    private final String name;
    private List<TwitterUser> users = Lists.newArrayList();

    public UserGroup(String name) {
        super("user group");
        this.name = name;
    }

    public void addUser(TwitterUser user) {
        users.add(user);
    }

    public void addUsers(List<TwitterUser> users) {
        this.users.addAll(users);
    }


    public List<TwitterUser> getUsers() {
        return users;
    }

    @Override
    public List<? extends Groupable> getMembers() {
        return users;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getFrequency(Countable countable, CountingContext context) {
        return getMean(countable);
    }

    public void printUsersToFile(PrintStream stream, String supports) {
        stream.print("NAME,SUPPORTS");
        for (Countable countable : countables) {
            stream.print(","+countable.getName());
        }
        stream.println();

        for (TwitterUser user : users) {
            stream.print(user.getHandle());
            stream.print(","+supports);
            for (Countable countable : countables) {
                stream.print(","+user.getFrequency(countable, null));
            }
            stream.println();
        }
    }
}
