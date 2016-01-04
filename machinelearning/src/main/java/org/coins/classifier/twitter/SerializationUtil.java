package org.coins.classifier.twitter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.coins.classifier.twitter.stats.UserGroup;

/**
 * Created by Christian on 02.01.2016.
 */
public class SerializationUtil {

    public static Object deserialize(String fileName) {

        return null;
    }

    public static void serialize(TwitterUser twitterUser) throws IOException {

        Properties properties = new Properties();
        InputStream inputProperties = SerializationUtil.class.getClassLoader().getResourceAsStream("TwitterUser.properties");
        properties.load(inputProperties);

        String dateForPath = Calendar.getInstance().get(Calendar.YEAR) + "_"
                          + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "_"
                          +  Calendar.getInstance().get(Calendar.DATE) + "_"
                          +  Calendar.getInstance().get(Calendar.HOUR) + "_"
                          +  Calendar.getInstance().get(Calendar.MINUTE);

        String fileName = twitterUser.getHandle() + "_" + dateForPath + ".twitteruser";
        String filePath = properties.getProperty("savePath") + "\\";

        Path path = Paths.get(filePath + fileName);

        try{

            if (Files.exists(path)){

                System.out.println(filePath + fileName + " already exists.");

            } else {

                FileOutputStream fileOutput = new FileOutputStream(filePath + fileName);
                ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                objectOutput.writeObject(twitterUser);
                objectOutput.close();
                System.out.println(filePath + fileName + " saved.");
            }

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
