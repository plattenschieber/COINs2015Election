package org.coins.classifier.lang.filters.contraction;

import org.coins.classifier.lang.counting.Countable;
import org.coins.classifier.lang.filters.Filter;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by johannes on 11/29/15.
 */
public class ContractionFilter extends Filter {

    private static final ContractionFilter INSTANCE = new ContractionFilter();

    private ContractionFilter() { }

    public static ContractionFilter getInstance() {
        return INSTANCE;
    }

    private Contraction[] contractions = Contraction.values();

    @Override
    public String apply(String text) {
        for (Contraction contraction : contractions) {
            String regex;
            if (contraction.getAllowedPrefixes().isEmpty()) {
                 regex = "(\\w+)'" + contraction.getContracted();
            } else {
                StringBuilder sb = new StringBuilder("(\\b(");

                Iterator<String> iterator = contraction.getAllowedPrefixes().iterator();
                while (iterator.hasNext()) {
                    sb.append(iterator.next());
                    if (iterator.hasNext()) {
                        sb.append("|");
                    }
                }
                sb.append("))'");
                sb.append(contraction.getContracted());
                regex = sb.toString();
            }
            text = filter(text, regex, "$1 " + contraction.getExpanded());
        }
        return text;
    }

    @Override
    public List<Countable> getCountableChildren() {
        return Collections.emptyList();
    }

    @Override
    public String getName() {
        return "Contraction";
    }

}
