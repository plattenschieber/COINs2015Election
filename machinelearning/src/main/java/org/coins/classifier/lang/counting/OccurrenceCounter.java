package org.coins.classifier.lang.counting;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import org.coins.classifier.lang.filters.Filter;
import org.coins.classifier.lang.words.WordGroup;
import org.coins.classifier.lang.words.WordType;

import java.util.List;
import java.util.Set;

/**
 * Created by johannes on 11/28/15.
 */
public class OccurrenceCounter {
    private final CountingContext localCountingContext = new CountingContext();
    private final SetMultimap<String, WordGroup> wordToGroupMap = HashMultimap.create();
    private final Set<Countable> countables = Sets.newHashSet();

    private final List<Filter> filters = Lists.newArrayList();

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

    public void addFilter(Filter filter) {
        filters.add(filter);

    }

    public Set<String> getWordsToCount() {
        return wordToGroupMap.keySet();
    }

    public void countOccurrences(List<String> texts, CountingContext countingContext) {
        texts.forEach((text) -> countOccurrences(text, countingContext));
    }

    public void countOccurrences(String text, CountingContext countingContext) {
        if (countingContext == null) {
            countingContext = localCountingContext;
        }
        for (Filter filter : filters) {
            final CountWrapper countWrapper = countingContext.getCounter(filter);
            filter.setListener(countWrapper::increment);
            text = filter.apply(text);
            filter.unsetListener();
        }
        final String[] words = tokenize(text);
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

    public String[] tokenize(String text) {
        return text.split("[^a-zA-Z']+");
    }

    public CountingContext getLocalCountingContext() {
        return localCountingContext;
    }

    public Set<Countable> getCountables() {
        return countables;
    }
}
