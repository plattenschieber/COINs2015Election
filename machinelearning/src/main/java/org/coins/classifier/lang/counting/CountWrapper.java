package org.coins.classifier.lang.counting;

/**
 * Created by johannes on 11/28/15.
 */
public class CountWrapper implements Comparable<CountWrapper> {
    private final Countable countable;
    private int count = 0;
    private CountWrapper parent;

    public CountWrapper(Countable countable) {
        this.countable = countable;
    }

    public CountWrapper(Countable countable, CountWrapper parent) {
        this(countable);
        this.parent = parent;
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        count += 1;
        if (parent != null) {
            parent.increment();
        }
    }

    public String getName() {
        return countable.getName();
    }

    public Countable getCountable() {
        return countable;
    }

    @Override
    public int compareTo(CountWrapper o) {
        return getName().compareTo(o.getName());
    }
}
