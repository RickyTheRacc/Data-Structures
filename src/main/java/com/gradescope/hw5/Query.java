package com.gradescope.hw5;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/*
/ HW 5
/ Name: Sam Yoder
*/

@SuppressWarnings("unused")
public class Query {
    /**
     * @param input An iterable containing flight records to be queried
     * @return The number of flights from Cedar Rapids (CID) to Chicago (ORD) in August
     */
    public static int exampleQuery(Iterable<FlightRecord> input) {
        int sum = 0;

        for (FlightRecord record : input) {
            if (!record.ORIGIN.equals("LAX")) continue;
            if (!record.DEST.equals("ORD")) continue;
            if (record.MONTH != 8) continue;

            sum++;
        }

        return sum;
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return The number of flights based out of Cedar Rapids (CID)
     */
    public static int Query1(Iterable<FlightRecord> input) {
        int sum = 0;

        for (FlightRecord record: input) {
            if (record.ORIGIN.equals("CID")) sum++;
        }

        return sum;
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return All unique destinations directly reachable from Cedar Rapids
     */
    public static Iterable<String> Query2(Iterable<FlightRecord> input) {
        Set<String> distinct = new HashSet<>();

        for (FlightRecord record: input) {
            if (!record.ORIGIN.equals("CID")) continue;
            distinct.add(record.DEST + ", " + record.DEST_STATE_ABR);
        }

        return distinct;
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return The number of unique destinations directly reachable from Cedar Rapids
     */
    public static int Query3(Iterable<FlightRecord> input) {
        Set<String> distinct = new HashSet<>();

        for (FlightRecord record: input) {
            if (!record.ORIGIN.equals("CID")) continue;
            distinct.add(record.DEST);
        }

        return distinct.size();
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return How many flights out of Cedar Rapids ended at each destination
     */
    public static Iterable<String> Query4(Iterable<FlightRecord> input) {
        Map<String, Integer> distinct = new HashMap<>();

        for (FlightRecord record: input) {
            if (!record.ORIGIN.equals("CID")) continue;

            int flights = distinct.getOrDefault(record.DEST, 0);
            distinct.put(record.DEST, flights + 1);
        }

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry: distinct.entrySet()) {
            result.add(entry.getKey() + "=" + entry.getValue());
        }

        return result;
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return Which month had the most flights
     */
    public static String Query5(Iterable<FlightRecord> input) {
        Map<Integer, Integer> distinct = new HashMap<>();

        input.forEach(record -> {
            int flights = distinct.getOrDefault(record.MONTH, 0);
            distinct.put(record.MONTH, flights + 1);
        });

        Comparator<Integer> comparator = Comparator.comparingInt(distinct::get);
        int highestMonth = distinct.keySet().stream().max(comparator).get();

        return highestMonth + " had " + distinct.get(highestMonth) + " flights";
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return Which 2 states have the most flights between them in either direction
     */
    public static String Query6(Iterable<FlightRecord> input) {
        Map<String, Integer> distinct = new HashMap<>();

        input.forEach(record -> {
            String key = "(" + record.ORIGIN_STATE_ABR + "," + record.DEST_STATE_ABR + ")";
            int flights = distinct.getOrDefault(key, 0);
            distinct.put(key, flights + 1);
        });

        Comparator<String> comparator = Comparator.comparingInt(distinct::get);
        return distinct.keySet().stream().max(comparator).get();
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return Which states do not have a direct route to iowa
     */
    public static Iterable<String> Query7(Iterable<FlightRecord> input) {
        Map<String, Boolean> distinct = new HashMap<>();

        input.forEach(record -> {
            if (!distinct.getOrDefault(record.DEST_STATE_ABR, true)) return;

            boolean origin = record.ORIGIN_STATE_ABR.equals("IA");
            boolean dest = record.DEST_STATE_ABR.equals("IA");

            distinct.put(record.DEST_STATE_ABR, !origin && !dest);
        });

        return distinct.keySet().stream().filter(distinct::get).collect(Collectors.toSet());
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return The percentage of intra-state flights in each state
     */
    public static Iterable<String> Query8(Iterable<FlightRecord> input) {
        Map<String, Integer> totals = new HashMap<>();
        Map<String, Integer> actual = new HashMap<>();

        input.forEach(record -> {
            int count = totals.getOrDefault(record.ORIGIN_STATE_ABR, 0);
            totals.put(record.ORIGIN_STATE_ABR, count + 1);

            if (!record.ORIGIN_STATE_ABR.equals(record.DEST_STATE_ABR)) return;

            count = actual.getOrDefault(record.ORIGIN_STATE_ABR, 0);
            actual.put(record.ORIGIN_STATE_ABR, count + 1);
        });

        DecimalFormat format = new DecimalFormat("#.000");
        List<String> result = new ArrayList<>();

        for (String state: actual.keySet()) {
            double percent = (double) actual.get(state) / totals.get(state);
            result.add(state + "=" + format.format(percent));
        }

        return result;
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return The airline with the most incoming flights into each state
     */
    public static Iterable<String> Query9(Iterable<FlightRecord> input) {
        Map<String, Integer> airlines = new HashMap<>();

        input.forEach(record -> {
            String key = record.DEST_STATE_ABR + "," + record.UNIQUE_CARRIER_NAME;
            int value = airlines.getOrDefault(key, 0);

            airlines.put(key, value + 1);
        });

        Map<String, String> maxAirlines = new HashMap<>();
        for (Map.Entry<String, Integer> entry: airlines.entrySet()) {
            String state = entry.getKey().split(",")[0];

            String newAirline = entry.getKey().split(",")[1];
            String currAirline = maxAirlines.get(state);

            if (currAirline == null) {
                maxAirlines.put(state, newAirline);
                continue;
            }

            int otherCount = airlines.getOrDefault(state + "," + currAirline, 0);
            if (otherCount < entry.getValue()) maxAirlines.put(state, newAirline);
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, String> entry : maxAirlines.entrySet()) {
            String state = entry.getKey();
            String maxAirline = entry.getValue();
            result.add(state + "," + maxAirline);
        }

        return result;
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return All possible routes from CID to LAX that consist of two flights
     */
    public static Iterable<String> Query10(Iterable<FlightRecord> input) {
        Set<String> routes = new HashSet<>();

        for (FlightRecord flight1: input) {
            if (!flight1.ORIGIN.equals("CID")) continue;

            for (FlightRecord flight2: input) {
                if (!flight2.DEST.equals("LAX")) continue;
                if (!flight2.ORIGIN.equals(flight1.DEST)) continue;

                routes.add("CID->" + flight1.DEST + "->LAX");
            }
        }

        return routes;
    }
}
