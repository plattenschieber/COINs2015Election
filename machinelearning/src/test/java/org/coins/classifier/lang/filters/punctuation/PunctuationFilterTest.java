package org.coins.classifier.lang.filters.punctuation;

import org.coins.classifier.lang.counting.CountWrapper;
import org.coins.classifier.lang.filters.contraction.ContractionFilter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by johannes on 11/29/15.
 */
public class PunctuationFilterTest {

    @Test
    public void testApply_NoReplacement() throws Exception {
        final String inputString = "This, is; a. test: Is! it?";
        PunctuationFilter punctuationFilter = PunctuationFilter.getInstance();
        final String result = punctuationFilter.apply(inputString);
        Assert.assertEquals("Filter removed characters", inputString, result);
    }

    @Test
    public void testApply_FilterListener() throws Exception {
        final String inputString = "This, is; a. test: Is! it?";
        PunctuationFilter punctuationFilter = PunctuationFilter.getInstance();
        final CountWrapper countWrapper = new CountWrapper(punctuationFilter);
        punctuationFilter.setListener(countWrapper::increment);
        punctuationFilter.apply(inputString);
        punctuationFilter.unsetListener();
        Assert.assertEquals("Contractions not counted correctly", 6, countWrapper.getCount());
    }
}