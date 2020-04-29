package gameResearch;

import java.util.List;

public class StatisticProperties {

    public static double average(List<Double> numberList) {

        return numberList.stream()
                .mapToDouble(val -> (double) val)
                .average().orElse(0.0);
    }

    public static double varianceDouble(List<Double> numberList) {
        double average = average(numberList);
        return numberList.stream()
                .mapToDouble(val -> (double) val)
                .map(val -> Math.pow(val - average, 2))
                .average().orElse(0.0);
    }

    public static double standardDeviationDouble(List<Double> numberList) {
        return Math.sqrt(varianceDouble(numberList));
    }
}
