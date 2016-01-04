package org.coins.classifier.twitter;

import org.coins.classifier.twitter.stats.UserGroup;

/**
 * Created by Christian on 04.01.2016.
 */
public class SerializationIterator {

    public void iterate(UserGroup userGroup) {

        SerializationUtil serUtil = new SerializationUtil();

        for (int i = 0; i < userGroup.getUsers().size(); i++) {

             try {

                serUtil.serialize(userGroup.getUsers().get(i));

            } catch (java.io.IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
