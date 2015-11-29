package org.coins.classifier.lang.words;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by johannes on 11/28/15.
 */
public class WordGroup {

    private final Set<String> wordSet;
    private WordType parentType;

    public WordGroup(String ... words) {
        this.wordSet = Sets.newHashSet(words);
    }

    public Set<String> getWordSet() {
        return wordSet;
    }

    public WordType getParentType() {
        return parentType;
    }

    public void setParentType(WordType parentType) {
        this.parentType = parentType;
    }
}
