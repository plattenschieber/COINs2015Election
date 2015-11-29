package org.coins.classifier.lang.counting;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import org.coins.classifier.lang.words.WordGroup;
import org.coins.classifier.lang.words.WordType;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by johannes on 11/28/15.
 */
public class OccurrenceCounter {
    private final CountingContext localCountingContext = new CountingContext();
    private final SetMultimap<String, WordGroup> wordToGroupMap = HashMultimap.create();
    private final Set<Countable> countables = Sets.newHashSet();

    public OccurrenceCounter(WordType... wordTypes) {
        for (WordType wordType : wordTypes) {
            countables.add(wordType);
            for (WordGroup wordGroup : wordType.getGroupSet()) {
                if (wordGroup instanceof Countable) {
                    countables.add((Countable) wordGroup);
                }
                for (String word : wordGroup.getWordSet()) {
                    wordToGroupMap.put(word, wordGroup);
                }
            }
        }
    }

    public Set<String> getWordsToCount() {
        return wordToGroupMap.keySet();
    }

    public void countOccurrences(Stream<String> stream, CountingContext countingContext) {
        stream.forEach((line) -> countOccurrences(line, countingContext));
    }

    public void countOccurrences(List<String> texts, CountingContext countingContext) {
        texts.forEach((text) -> countOccurrences(text, countingContext));
    }

    public void countOccurrences(String text, CountingContext countingContext) {
        if (countingContext == null) {
            countingContext = localCountingContext;
        }
        final String[] words = text.split("[^a-zA-Z']");
        for (String word : words) {
            final String keyWord = word.toLowerCase();
            if (getWordsToCount().contains(keyWord)) {
                for (WordGroup wordGroup : wordToGroupMap.get(keyWord)) {
                    countingContext.getCounter(wordGroup).increment();
                }
            }
        }
        countingContext.addWordsCounted(words.length);
    }

    public CountingContext getLocalCountingContext() {
        return localCountingContext;
    }

    public Set<Countable> getCountables() {
        return countables;
    }
}
