package org.coins.classifier.lang.counting;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.coins.classifier.lang.words.WordGroup;
import org.coins.classifier.lang.words.WordType;

import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by johannes on 11/28/15.
 *
 * This class provides an interchangeable state for the the OccurrenceCounter.
 * It allows reusing the same OccurrenceCounter instance for counting many difference texts.
 */
public class CountingContext {

    private final Map<WordType,CountWrapper> typeCounts = new HashMap<>();
    private final Map<WordGroup, CountWrapper> groupCounts = new HashMap<>();
    private int wordsCounted = 0;

    public Set<CountWrapper> getCounts() {
        Set<CountWrapper> counts = Sets.newHashSet(typeCounts.values());
        counts.addAll(groupCounts.values());
        return counts;
    }

    public List<CountWrapper> getSortedCounts() {
        final List<CountWrapper> counts = Lists.newArrayList(getCounts());
        Collections.sort(counts);
        return counts;
    }

    public Map<Countable, CountWrapper> getCountsByCountable() {
        Map<Countable, CountWrapper> countsByCountable = new HashMap<>();
        for (CountWrapper countWrapper : getCounts()) {
            countsByCountable.put(countWrapper.getCountable(), countWrapper);
        }
        return countsByCountable;
    }

    public CountWrapper getCounter(WordType wordType) {
        CountWrapper countWrapper = typeCounts.get(wordType);
        if (countWrapper == null) {
            countWrapper = new CountWrapper(wordType);
            typeCounts.put(wordType, countWrapper);
        }
        return countWrapper;
    }

    public CountWrapper getCounter(WordGroup wordGroup) {
        CountWrapper countWrapper;
        if (wordGroup instanceof Countable) {
            countWrapper = groupCounts.get(wordGroup);
            if (countWrapper == null) {
                countWrapper = new CountWrapper((Countable)wordGroup, getCounter(wordGroup.getParentType()));
                groupCounts.put(wordGroup, countWrapper);
            }
        } else {
            countWrapper = getCounter(wordGroup.getParentType());
        }
        return countWrapper;
    }

    public void clear() {
        typeCounts.clear();
        groupCounts.clear();
        wordsCounted = 0;
    }

    public void addWordsCounted(int wordsCounted) {
        this.wordsCounted += wordsCounted;
    }

    public int getWordsCounted() {
        return wordsCounted;
    }

    public void printFrequencies(PrintStream stream) {
        final List<CountWrapper> sortedCounts = getSortedCounts();
        for (CountWrapper count : sortedCounts) {
            stream.println(String.format("%s: %.2f%%", count.getName(), count.getCount() * 100/(double)getWordsCounted()));
        }
    }
}
