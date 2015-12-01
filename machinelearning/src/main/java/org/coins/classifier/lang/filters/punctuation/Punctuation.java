package org.coins.classifier.lang.filters.punctuation;

import org.coins.classifier.lang.counting.Countable;

/**
 * Created by johannes on 11/29/15.
 */
public enum Punctuation implements Countable {
    PERIOD('.'),
    COMMA(','),
    QUESTION('?'),
    EXCLAMATION('!'),
    COLON(':'),
    SEMICOLON(';');

    private final char character;

    Punctuation(char character) {
        this.character = character;
    }

    @Override
    public String getName() {
        return name();
    }

    public char getCharacter() {
        return character;
    }
}
