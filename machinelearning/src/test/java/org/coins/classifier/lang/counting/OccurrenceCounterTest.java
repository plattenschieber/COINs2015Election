package org.coins.classifier.lang.counting;

import com.google.common.collect.Lists;
import org.coins.classifier.lang.filters.contraction.ContractionFilter;
import org.coins.classifier.lang.filters.punctuation.PunctuationFilter;
import org.coins.classifier.lang.words.PronounGroup;
import org.coins.classifier.lang.words.WordType;
import org.junit.Assert;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by johannes on 11/28/15.
 */
public class OccurrenceCounterTest {

    private static List<String> testStrings;

    @org.junit.BeforeClass
    public static void setUp() throws Exception {
        testStrings = Lists.newArrayList();
        try (Stream<String> lines = Files.lines(new File("src/main/resources/TestData.txt").toPath())) {
            lines.forEach((text) -> testStrings.add(text));
        }
    }

    @org.junit.Test
    public void testTokenize() {
        final String testString = "Here's a   fairly basic sentence - let's see how you'll split that. If it's right, that's good.";
        OccurrenceCounter counter = new OccurrenceCounter(WordType.ARTICLES);
        String[] result = counter.tokenize(testString);

        Assert.assertEquals("Token count wrong", 16, result.length);

    }

    @org.junit.Test
    public void testCountOccurrences_Pronouns() throws Exception {
        final Map<Countable, CountWrapper> counts = getCounts(WordType.PRONOUNS);

        assertCount(counts, 3, PronounGroup.FIRST_SINGULAR);
        assertCount(counts, 3, PronounGroup.SECOND_SINGULAR);
        assertCount(counts, 6, PronounGroup.THIRD_SINGULAR);
        assertCount(counts, 5, PronounGroup.FIRST_PLURAL);
        assertCount(counts, 3, PronounGroup.SECOND_PLURAL);
        assertCount(counts, 2, PronounGroup.THIRD_PLURAL);

        //second person singular and plural are counted twice in the total
        assertCount(counts, 22, WordType.PRONOUNS);
    }

    @org.junit.Test
    public void testCountOccurrences_Articles() throws Exception {
        final Map<Countable, CountWrapper> counts = getCounts(WordType.ARTICLES);
        assertCount(counts, 6, WordType.ARTICLES);
    }

    @org.junit.Test
    public void testCountOccurrences_Conjunctions() throws Exception {
        final Map<Countable, CountWrapper> counts = getCounts(WordType.CONJUNCTIONS);
        assertCount(counts, 5, WordType.CONJUNCTIONS);
    }

    @org.junit.Test
    public void testCountOccurrences_Prepositions() throws Exception {
        final Map<Countable, CountWrapper> counts = getCounts(WordType.PREPOSITIONS);
        assertCount(counts, 5, WordType.PREPOSITIONS);
    }

    @org.junit.Test
    public void testCountOccurrences_AuxiliaryVerbs() throws Exception {
        final Map<Countable, CountWrapper> counts = getCounts(WordType.AUXILIARY_VERBS);
        assertCount(counts, 7, WordType.AUXILIARY_VERBS);
    }

    @org.junit.Test
    public void testCountOccurrences_Quantifiers() throws Exception {
        final Map<Countable, CountWrapper> counts = getCounts(WordType.QUANTIFIERS);
        assertCount(counts, 3, WordType.QUANTIFIERS);
    }

    @org.junit.Test
    public void testCountOccurrences_Negations() throws Exception {
        final Map<Countable, CountWrapper> counts = getCounts(WordType.NEGATIONS);
        assertCount(counts, 2, WordType.NEGATIONS);
    }

    private Map<Countable, CountWrapper> getCounts(WordType wordType) {
        final OccurrenceCounter occurrenceCounter = new OccurrenceCounter(wordType);

        occurrenceCounter.countOccurrences(testStrings, null);
        return occurrenceCounter.getLocalCountingContext().getCountsByCountable();
    }

    @org.junit.Test
    public void testCountOccurrences_Filter_Contractions() throws Exception {
        final OccurrenceCounter occurrenceCounter = new OccurrenceCounter();
        occurrenceCounter.addFilter(ContractionFilter.getInstance());
        occurrenceCounter.countOccurrences(testStrings, null);
        final Map<Countable, CountWrapper> countsByCountable = occurrenceCounter.getLocalCountingContext().getCountsByCountable();
        assertCount(countsByCountable, 2, ContractionFilter.getInstance());
    }

    @org.junit.Test
    public void testCountOccurrences_Filter_Punctuation() throws Exception {
        final OccurrenceCounter occurrenceCounter = new OccurrenceCounter();
        occurrenceCounter.addFilter(PunctuationFilter.getInstance());
        occurrenceCounter.countOccurrences(testStrings, null);
        final Map<Countable, CountWrapper> countsByCountable = occurrenceCounter.getLocalCountingContext().getCountsByCountable();
        assertCount(countsByCountable, 13, PunctuationFilter.getInstance());
    }

    private void assertCount(Map<Countable, CountWrapper> counts, int expected, Countable countable) {
        final CountWrapper countWrapper = counts.get(countable);
        Assert.assertEquals(countable.getName() + " count wrong", expected, countWrapper == null ? 0 : countWrapper.getCount());
    }
}