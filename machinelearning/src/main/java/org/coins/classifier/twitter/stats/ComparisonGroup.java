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

    public double getVariance(Countable countable, CountingContext context) {
        return StatUtils.variance(getFrequencies(countable, context));
    }

    public double getMean(Countable countable, CountingContext context) {
        return StatUtils.mean(getFrequencies(countable, context));
    }

    public double getGeometricMean(Countable countable, CountingContext context) {
        return StatUtils.geometricMean(getFrequencies(countable, context));
    }

    public double getMedian(Countable countable, CountingContext context) {
        return getPercentile(countable, context, 50);
    }

    public double getPercentile(Countable countable, CountingContext context, double percentile) {
        return StatUtils.percentile(getFrequencies(countable, context), percentile);
    }

    public double getMax(Countable countable, CountingContext context) {
        return StatUtils.max(getFrequencies(countable, context));
    }

    public double getMin(Countable countable, CountingContext context) {
        return StatUtils.min(getFrequencies(countable, context));
    }

    public double[] getFrequencies(Countable countable, CountingContext context) {
        final List<Double> frequencyList = Lists.transform(getMembers(), groupable -> groupable.getFrequency(countable, context));
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

    public void printToStream(PrintStream stream, CountingContext context) {
        stream.println("\n=======================\n");
        stream.println("Word frequencies in " + groupType + ": " + getName());
        for (Countable countable : countables) {
            stream.println(String.format("%s: \tmean %.2f%%,\tmedian: %.2f%%,\tstandard deviation %.3f%%,\tmax: %.2f%%,\tmin: %.2f%%", countable.getName(),
                    getMean(countable, context) * 100, getMedian(countable, context) * 100, Math.sqrt(getVariance(countable, context))*100, getMax(countable, context)*100, getMin(countable, context)*100));
        }
    }
    public void printToFile(PrintStream stream, CountingContext context) {
        stream.println("TYPE,MEAN,FIRST_QUARTILE,MEDIAN,THIRD_QUARTILE,STANDARD DEVIATION,MAX,MIN\n");
        for (Countable countable : countables) {
            stream.println(String.format("%s,%.2f%%,%.2f%%,%.2f%%,%.2f%%,%.3f%%,%.2f%%,%.2f%%", countable.getName(),
                    getMean(countable, context) * 100, getPercentile(countable, context, 25) * 100, getMedian(countable, context) * 100, getPercentile(countable, context, 75) * 100, Math.sqrt(getVariance(countable, context))*100, getMax(countable, context)*100, getMin(countable, context)*100));
        }
    }
}
