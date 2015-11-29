package org.coins.classifier.lang.words;

import org.coins.classifier.lang.counting.Countable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by johannes on 11/28/15.
 */
public enum WordType implements Countable {
    PRONOUNS(PronounGroup.FIRST_SINGULAR, PronounGroup.SECOND_SINGULAR, PronounGroup.THIRD_SINGULAR,
            PronounGroup.FIRST_PLURAL, PronounGroup.SECOND_PLURAL, PronounGroup.THIRD_PLURAL),
    ARTICLES(new WordGroup("the", "a", "an")),
    AUXILIARY_VERBS(new WordGroup("do", "does", "did", "has", "have", "had", "is", "am", "are",  "was", "were",  "be",
            "being", "been", "may", "must", "might", "should", "could", "would", "shall", "will", "can")),
    PREPOSITIONS(new WordGroup("aboard", "about", "above", "across", "after", "against", "along", "amid", "among",
            "anti", "around", "as", "at", "before", "behind", "below", "beneath", "beside", "besides", "between",
            "beyond", "by", "despite", "during", "except",  "for", "from", "in", "inside", "into",   "near", "of",
            "off", "on", "onto",  "over", "past", "per", "since", "than", "through", "to", "toward", "towards", "under",
            "underneath", "until", "up", "upon", "versus", "via", "with", "within", "without")),
    CONJUNCTIONS(new WordGroup("for", "and", "but", "or", "yet", "so", "after", "although", "as", "because",
            "before", "if", "once", "since", "though", "unless", "until", "when", "where", "while")),
    NEGATIONS(new WordGroup("no", "not", "neither", "nor", "never")),
    QUANTIFIERS(new WordGroup("all", "any", "both", "each", "enough", "every", "few", "fewer", "little", "less",
            "lots", "many", "more", "none", "several", "some"));

    private final Set<WordGroup> groupSet;

    WordType(WordGroup ... groups) {
        this.groupSet = new HashSet<>();
        this.groupSet.addAll(Arrays.asList(groups));
        for (WordGroup group : groupSet) {
            group.setParentType(this);
        }
    }

    public Set<WordGroup> getGroupSet() {
        return groupSet;
    }

    @Override
    public String getName() {
        return name();
    }
}
