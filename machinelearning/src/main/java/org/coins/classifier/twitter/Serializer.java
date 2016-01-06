package org.coins.classifier.twitter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.coins.classifier.twitter.stats.UserGroup;

/**
 * Created by Christian on 02.01.2016.
 */
public class Serializer {

    public static Object deserialize(List<String> fileNameList) {

        /*
         *Properties properties = new Properties();
         *InputStream inputProperties = Serializer.class.getClassLoader().getResourceAsStream("TwitterUser.properties");
         *properties.load(inputProperties);
        */

        Iterator<String> fileNameListIterator = fileNameList.iterator();

        while(fileNameListIterator.hasNext()) {

            String fileName = fileNameListIterator.next();

            //String filePath = properties.getProperty("savePath") + "\\";
            String filePath = System.getProperty("user.home");
            Path path = Paths.get(filePath + fileName);

            try {

                if (Files.exists(path)) {

                    /*
                     * To be continued...
                     */

                } else {

                    System.out.println(filePath + fileName + " does not exist.");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    public static void serialize(UserGroup userGroup) throws IOException {

        /*
         *Properties properties = new Properties();
         *InputStream inputProperties = Serializer.class.getClassLoader().getResourceAsStream("TwitterUser.properties");
         *properties.load(inputProperties);
        */

        List<TwitterUser> twitterUserList = userGroup.getUsers();
        Iterator<TwitterUser> twitterUserListIterator = twitterUserList.iterator();

        while(twitterUserListIterator.hasNext()) {

            TwitterUser twitterUser = twitterUserListIterator.next();

            String dateForPath = Calendar.getInstance().get(Calendar.YEAR) + "_"
                    + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "_"
                    + Calendar.getInstance().get(Calendar.DATE) + "_"
                    + Calendar.getInstance().get(Calendar.HOUR) + "_"
                    + Calendar.getInstance().get(Calendar.MINUTE);

            String fileName = "\\" + twitterUser.getHandle() + "_" + dateForPath + ".twitteruser";
            //String filePath = properties.getProperty("savePath") + "\\";
            String filePath = System.getProperty("user.home");
            Path path = Paths.get(filePath + fileName);

            try {

                if (Files.exists(path)) {

                    System.out.println(filePath + fileName + " already exists.");

                } else {

                    FileOutputStream fileOutput = new FileOutputStream(filePath + fileName);
                    ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                    objectOutput.writeObject(twitterUser);
                    objectOutput.close();
                    System.out.println(filePath + fileName + " saved.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
