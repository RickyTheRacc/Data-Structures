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

        // Only increment the sum if the record meets all three qualifications:
        // The flight is based out of CID
        // The flight ends at ORD
        // The month is 8 (august)

        for (FlightRecord record : input) {
            if (!record.ORIGIN.equals("CID")) continue;
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

        // Increment the sum for each flight with origin CID
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

        // Almost the same as query 1, but instead of making a number
        // we can use a set and just add the string for each flight.
        // Sets prevent duplicates so we ensure each destination
        // only occurs once

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

        // Essentially the same thing as query 2, the only change is
        // instead of returning the set itself we just return its size.

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
        // First create a map that will store a destionation, and
        // how many flights it's had that originated at CID

        Map<String, Integer> distinct = new HashMap<>();

        // Sort of the same principle as the last two queries. Only
        // take into account records that come out of CID. For each
        // flight, we get the number of flights that have gone to that
        // destination before from the map, with a default value of 0
        // if it's the first time, and then put the destination back
        // in the map with its value incremented by 1

        for (FlightRecord record: input) {
            if (!record.ORIGIN.equals("CID")) continue;

            int flights = distinct.getOrDefault(record.DEST, 0);
            distinct.put(record.DEST, flights + 1);
        }

        // Now we just create a new set, adding the destinations
        // together into strings. It doesn't really need to be a set
        // since we already know each dest will only appear once,
        // but they're performant :>

        Set<String> result = new HashSet<>();
        distinct.forEach((dest, num) -> result.add(dest + "=" + num));

        return result;
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return Which month had the most flights
     */
    public static String Query5(Iterable<FlightRecord> input) {
        // First we create a map that will contain each month as a
        // key and the number of flights it's had as a value

        Map<Integer, Integer> distinct = new HashMap<>();

        // For every record, we get the value for the month from the map
        // with a default of zero if it hasn't been added yet, and increment
        // it by 1

        input.forEach(record -> {
            int flights = distinct.getOrDefault(record.MONTH, 0);
            distinct.put(record.MONTH, flights + 1);
        });

        // Now we create a comparator to easily sort through the list. The way
        // a comparator works is you specify what type of object you want to sort,
        // and pass in a function to apply to each object before it's compared.
        // for example, if you just wanted to sort a regular list of numbers from
        // 1 to 10, you would use Comparators.comparingInt(Integer::intValue), which
        // just returns the integer as is

        // In this instance, we want to sort the months based on which has the highest
        // value, so our comparator function is going to be our map's get function
        
        Comparator<Integer> comparator = Comparator.comparingInt(distinct::get);

        // Stream API! This is a useful part of almost every collection in Java,
        // and allows you to quickly perform operations on them like sorting, filtering,
        // reducing, etc. Here, we're using it to stream through the keys in our map
        // and figure out which one has the most flights using the comparator

        int highestMonth = distinct.keySet().stream().max(comparator).get();
        return highestMonth + " had " + distinct.get(highestMonth) + " flights";
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return Which 2 states have the most flights between them in either direction
     */
    public static String Query6(Iterable<FlightRecord> input) {
        Map<String, Integer> distinct = new HashMap<>();

        // Pretty much the same principle as the last one. The keys in our
        // map will be formatted like (IA,IL) and our values will be the
        // number of flights each have. Loop through, increment by one.

        input.forEach(record -> {
            String key = "(" + record.ORIGIN_STATE_ABR + "," + record.DEST_STATE_ABR + ")";
            int flights = distinct.getOrDefault(key, 0);
            distinct.put(key, flights + 1);
        });

        // Same deal as before - we want to get the pair of states that has
        // the most flights, so we'll sort through the keys by using a comparator
        // to check which key has the highest value, and return that one.

        Comparator<String> comparator = Comparator.comparingInt(distinct::get);

        // Note: using .max() and other filtering methods on a stream return
        // the type Optional<T>, which means they may not always have a value,
        // in which case calling get() would return null. Normally it's good
        // practice to use .orElse() instead, where you pass in a default to
        // return if there is no actual value, like a map's getOrDefault function.

        // here, we already know that there will be at least one entry inside,
        // and it always has a value associated with it, so we don't need to
        // worry about get returning null.

        return distinct.keySet().stream().max(comparator).get();
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return Which states do not have a direct route to iowa
     */
    public static Iterable<String> Query7(Iterable<FlightRecord> input) {
        // Start by creating a map which will contain each state as a key,
        // and whether or not it's had a flight to Iowa as a value

        Map<String, Boolean> distinct = new HashMap<>();

        input.forEach(record -> {
            // First thing we check is if the state has already been
            // confirmed to have a flight directly to iowa, and if so
            // we just ignore all future flights in that state, assuming
            // that it hasn't by default.

            if (distinct.getOrDefault(record.DEST_STATE_ABR, false)) return;

            // Now we make sure that both the origin and destination
            // are NOT equal to Iowa

            boolean origin = record.ORIGIN_STATE_ABR.equals("IA");
            boolean dest = record.DEST_STATE_ABR.equals("IA");

            // Update the value for the state. If either the origin or
            // destination was Iowa, the value will now be true permanently,
            // and any further flights in this state will be ignored to
            // prevent it from being set back to false if another flight
            // in the state has a flight that doesn't go to Iowa

            distinct.put(record.DEST_STATE_ABR, origin || dest);
        });

        // More streams, this time using a filter! This is an easy way to
        // remove items from a list based off of a predicate, which is a function
        // that takes in the type of object in the list and returns a true or false
        // value as to whether that object should stay. For example, here my
        // predicate is (s -> !distinct.get(s)). Remember that the boolean is
        // true if there HAS been a flight to iowa, so only keys with a value of
        // false get to stay

        return distinct.keySet().stream().filter(s -> !distinct.get(s)).collect(Collectors.toSet());
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return The percentage of intra-state flights in each state
     */
    public static Iterable<String> Query8(Iterable<FlightRecord> input) {
        Map<String, Integer> totals = new HashMap<>();
        Map<String, Integer> actual = new HashMap<>();

        // Create two maps that have states as keys. The first will
        // contain the total number of flights to the state, and the
        // second will contain only the intra-state flights

        input.forEach(record -> {
            // Always increment the total amount by one

            int count = totals.getOrDefault(record.ORIGIN_STATE_ABR, 0);
            totals.put(record.ORIGIN_STATE_ABR, count + 1);

            // Now, we only continue past this point if the flight
            // is an intra-state flight, that is its origin state
            // is the same as the destination state

            if (!record.ORIGIN_STATE_ABR.equals(record.DEST_STATE_ABR)) return;

            // In this case, increment the actual number of flights
            // for the state by 1 as well

            count = actual.getOrDefault(record.ORIGIN_STATE_ABR, 0);
            actual.put(record.ORIGIN_STATE_ABR, count + 1);
        });

        DecimalFormat format = new DecimalFormat("#.000");
        List<String> result = new ArrayList<>();

        // Now we go through all the states that had intra-state flights
        // and add the percent in a string to a new list. Note - it is
        // important that we used actual.keySet() here instead of
        // total.keySet()! If we were to use total.keySet(), trying to
        // call get from actual on a state which didn't have any intra-state
        // flights would produce null

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
        // The first map we create here is called airlines, this will hold all
        // three values at once in the form of pairs like this: ("IA,LAX", 13)

        Map<String, Integer> airlines = new HashMap<>();

        // The second map will hold the actual results we want, with states as
        // the keys and the corresponding airlines as the values

        Map<String, String> maxAirlines = new HashMap<>();

        input.forEach(record -> {
            // First thing we do is construct the key for the airlines map
            // we also want to keep the state and airline separate for later

            String state = record.DEST_STATE_ABR;
            String airline = record.UNIQUE_CARRIER_NAME;
            String key = state + "," + airline;

            // We then increment the number of flights in airline by one

            airlines.put(key, airlines.getOrDefault(key, 0) + 1);

            // Now we actually update the best airline in each state. First thing
            // we do is check if the state has been assigned an airline yet, and
            // if not it's automatically set to the current airline

            maxAirlines.computeIfAbsent(state, k -> airline);

            // Next thing we do is get the count of the current best airline for the
            // state we're examining by reconstructing the key using maxAirlines
            int otherCount = airlines.getOrDefault(state + "," + maxAirlines.get(state), 0);

            // And finally, if the current airline now has more flights than the
            // previous best airline, we set it to the new one

            if (airlines.get(key) > otherCount) maxAirlines.put(state, airline);
        });

        List<String> result = new ArrayList<>();
        maxAirlines.forEach((state, maxAirline) -> result.add(state + "," + maxAirline));

        return result;
    }

    /**
     * @param input An iterable containing flight records to be queried
     * @return All possible routes from CID to LAX that consist of two flights
     */
    public static Iterable<String> Query10(Iterable<FlightRecord> input) {
        Set<String> routes = new HashSet<>();

        for (FlightRecord flight1: input) {

            // First thing we do is check whether the flight originated in
            // CID, if not we skip that record

            if (!flight1.ORIGIN.equals("CID")) continue;

            for (FlightRecord flight2: input) {

                // Now that we know the first flight started from CID, we check
                // if the second flight ends in LAX, if not we continue

                if (!flight2.DEST.equals("LAX")) continue;

                // Last, we make sure that the two are a layover by ensuring that
                // the origin of flight2 is the same as the dest of flight 1

                if (!flight2.ORIGIN.equals(flight1.DEST)) continue;

                // Since we already know the route will always start at CID and
                // end at LAX, we only really need to add flight1's dest. Not
                // really a performance improvement or anything, but looks cleaner :)

                routes.add("CID->" + flight1.DEST + "->LAX");
            }
        }

        return routes;
    }
}
