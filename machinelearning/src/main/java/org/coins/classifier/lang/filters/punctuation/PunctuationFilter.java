package org.coins.classifier.lang.filters.punctuation;

import com.google.common.collect.Lists;
import org.coins.classifier.lang.counting.Countable;
import org.coins.classifier.lang.filters.Filter;

import java.util.List;

/**
 * Created by johannes on 11/29/15.
 */
public class PunctuationFilter extends Filter {

    private static final PunctuationFilter INSTANCE = new PunctuationFilter();

    private Punctuation[] values = Punctuation.values();

    private PunctuationFilter() { }

    public static PunctuationFilter getInstance() {
        return INSTANCE;
    }

    @Override
    public String apply(String text) {
        for (Punctuation punctuationMark : values) {
            boolean escape = punctuationMark.getCharacter() == '.'
                    || punctuationMark.getCharacter() == '?';
            filter(text, (escape ? "\\" : "") + Character.toString(punctuationMark.getCharacter()), null);
        }
        return text;
    }

    @Override
    public List<Countable> getCountableChildren() {
        return Lists.newArrayList(values);
    }

    @Override
    public String getName() {
        return "Punctuation";
    }
}
