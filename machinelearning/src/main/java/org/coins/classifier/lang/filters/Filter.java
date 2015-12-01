package org.coins.classifier.lang.filters;

import org.coins.classifier.lang.counting.Countable;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by johannes on 11/29/15.
 */
public abstract class Filter implements Countable {

    private static final FilterListener BLANK_FILTER_LISTENER = () -> { };

    public abstract String apply(String text);
    public abstract List<Countable> getCountableChildren();

    protected FilterListener listener = BLANK_FILTER_LISTENER;

    public void setListener(FilterListener listener) {
        this.listener = listener;
    }

    public void unsetListener() {
        this.listener = BLANK_FILTER_LISTENER;
    }

    protected String filter(String text, String regex, String replacement) {
        final Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(text);
        if (replacement == null) {
            while (matcher.find()) {
                listener.filterApplied();
            }
            return text;
        }
        if (matcher.find()) {
            StringBuffer sb = new StringBuffer();
            do {
                listener.filterApplied();
                matcher.appendReplacement(sb, replacement);
            } while (matcher.find());
            matcher.appendTail(sb);
            return sb.toString();
        }
        return text;
    }
}
