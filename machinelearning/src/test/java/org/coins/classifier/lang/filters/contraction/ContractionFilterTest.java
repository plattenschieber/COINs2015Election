package org.coins.classifier.lang.filters.contraction;

import org.coins.classifier.lang.counting.CountWrapper;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by johannes on 11/29/15.
 */
public class ContractionFilterTest {

    @Test
    public void testApply_AM() throws Exception {
        testApplyWithText("I'm a test. I'm here!", "I am a test. I am here!");
    }

    @Test
    public void testApply_HAVE() throws Exception {
        testApplyWithText("I've got a test. A test I've got.", "I have got a test. A test I have got.");
    }

    @Test
    public void testApply_WILL() throws Exception {
        testApplyWithText("I'll test this. I'll test this real good.", "I will test this. I will test this real good.");
    }

    @Test
    public void testApply_IS() throws Exception {
        testApplyWithText("Here's a test and that's good.",
                "Here is a test and that is good.");
    }

    @Test
    public void testApply_HAS_IS() throws Exception {
        //we don't know what the contraction was for -> no replacement
        testApplyWithText("He's tested this. It's tested. Kermit's test has gone well.",
                "He  tested this. It  tested. Kermit's test has gone well.");
    }

    @Test
    public void testApply_US() throws Exception {
        testApplyWithText("Let's test this! This test's string is nothing interesting.",
                "Let us test this! This test's string is nothing interesting.");
    }

    @Test
    public void testApply_HAD_WOULD() throws Exception {
        //we don't know what the contraction was for -> no replacement
        testApplyWithText("If he'd test this, we'd have tested it.",
                "If he  test this, we  have tested it.");
    }

    private void testApplyWithText(String testString, String expectedResult) {
        ContractionFilter contractionFilter = ContractionFilter.getInstance();
        final String result = contractionFilter.apply(testString);
        Assert.assertEquals("Contractions not expanded correctly", expectedResult, result);
    }

    @Test
    public void testApply_FilterListener() throws Exception {
        final String inputString = "I'm a test. You'll like me.";
        ContractionFilter contractionFilter = ContractionFilter.getInstance();
        final CountWrapper countWrapper = new CountWrapper(contractionFilter);
        contractionFilter.setListener(countWrapper::increment);
        contractionFilter.apply(inputString);
        contractionFilter.unsetListener();
        Assert.assertEquals("Contractions not counted correctly", 2, countWrapper.getCount());
    }
}