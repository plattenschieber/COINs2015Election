package org.coins.classifier.lang.words;

import org.coins.classifier.lang.counting.Countable;

/**
 * Created by johannes on 11/28/15.
 */
public class PronounGroup extends WordGroup implements Countable {

    public final static PronounGroup FIRST_SINGULAR = new PronounGroup(PronounType.FIRST_SINGULAR,
            "i", "me", "mine", "myself", "my");
    public final static PronounGroup SECOND_SINGULAR = new PronounGroup(PronounType.SECOND_SINGULAR,
            "you", "your", "yours", "yourself");
    public final static PronounGroup THIRD_SINGULAR = new PronounGroup(PronounType.THIRD_SINGULAR,
            "he", "she", "him", "her", "his", "hers", "herself", "himself", "it", "its", "itself");
    public final static PronounGroup FIRST_PLURAL = new PronounGroup(PronounType.FIRST_PLURAL,
            "we", "our", "ours", "us", "ourselves");
    public final static PronounGroup SECOND_PLURAL = new PronounGroup(PronounType.SECOND_PLURAL,
            "you", "your", "yours", "yourselves");
    public final static PronounGroup THIRD_PLURAL = new PronounGroup(PronounType.THIRD_PLURAL,
            "they", "them", "their", "theirs", "themselves");

    private final PronounType type;

    public PronounGroup(PronounType type, String ... pronouns) {
        super(pronouns);
        this.type = type;
    }

    @Override
    public String getName() {
        return getParentType().getName() + " " + type.toString();
    }

    public enum PronounType {
        FIRST_SINGULAR,
        SECOND_SINGULAR,
        THIRD_SINGULAR,
        FIRST_PLURAL,
        SECOND_PLURAL,
        THIRD_PLURAL
    }
}
