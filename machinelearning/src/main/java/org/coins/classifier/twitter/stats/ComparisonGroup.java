package org.coins.classifier.twitter.stats;

import com.google.common.collect.Lists;
import org.apache.commons.math3.stat.StatUtils;
import org.coins.classifier.lang.counting.Countable;
import org.coins.classifier.lang.counting.CountingContext;
import org.coins.classifier.lang.counting.OccurrenceCounter;

import java.io.PrintStream;
import java.util.List;
import java.util.Set;

/**
 * Created by johannes on 11/29/15.
 */
public abstract class ComparisonGroup {

    private final String groupType;
    private Set<Countable> countables;

    public ComparisonGroup(String groupType) {
        this.groupType = groupType;
    }

    public abstract List<? extends Groupable> getMembers();
    public abstract String getName();

    public double getVariance(Countable countable) {
        return StatUtils.variance(getFrequencies(countable));
    }

    public double getMean(Countable countable) {
        return StatUtils.mean(getFrequencies(countable));
    }

    public double getGeometricMean(Countable countable) {
        return StatUtils.geometricMean(getFrequencies(countable));
    }

    public double getMedian(Countable countable) {
        return StatUtils.percentile(getFrequencies(countable), 50);
    }

    public double[] getFrequencies(Countable countable) {
        final List<Double> frequencyList = Lists.transform(getMembers(), groupable -> groupable.getFrequency(countable, null));
        final double[] frequencyArray = new double[frequencyList.size()];
        for (int i = 0; i < frequencyList.size(); i++) {
            double frequency = frequencyList.get(i);
            frequencyArray[i] = frequency;
        }
        return frequencyArray;
    }

    public void analyzeTweets(OccurrenceCounter counter, CountingContext context) {
        countables = counter.getCountables();
        for (Groupable groupable : getMembers()) {
            groupable.analyzeTweets(counter, context);
        }
    }

    public void printToStream(PrintStream stream) {
        stream.println("\n=======================\n");
        stream.println("Word frequencies in " + groupType + ": " + getName());
        for (Countable countable : countables) {
            stream.println(String.format("%s: mean %.2f%%, median: %.2f%%, variance %.2f", countable.getName(),
                    getMean(countable) * 100, getMedian(countable) * 100, getVariance(countable)));
        }
    }
}
