package org.coins.classifier.lang.filters.contraction;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by johannes on 11/29/15.
 */
enum Contraction {
    HAVE("ve", "have"),
    AM("m", "am"),
    WILL("ll", "will"),
    IS("s", "is", "here", "that"),
    HAS_IS("s", "", "he", "she", "it"),
    US("s", "us", "let"),
    HAD_WOULD("d", "");

    private final String contracted;
    private final String expanded;
    private final Set<String> allowedPrefixes;

    Contraction(String contracted, String expanded) {
        this.contracted = contracted;
        this.expanded = expanded;
        allowedPrefixes = Sets.newHashSet();
    }

    Contraction(String contracted, String expanded, String... allowedPrefixes) {
        this.contracted = contracted;
        this.expanded = expanded;
        this.allowedPrefixes = Sets.newHashSet(allowedPrefixes);
    }

    public String getContracted() {
        return contracted;
    }

    public String getExpanded() {
        return expanded;
    }

    public Set<String> getAllowedPrefixes() {
        return allowedPrefixes;
    }
}
