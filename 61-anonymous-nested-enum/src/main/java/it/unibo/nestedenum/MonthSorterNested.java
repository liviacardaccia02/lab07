package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDays();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    private static class SortByMonthOrder implements Comparator<String> {

        @Override
        public int compare(final String s1, final String s2) {
            return Month.fromString(s1).compareTo(Month.fromString(s2));
        }
        
    }

    private static class SortByDays implements Comparator<String> {

        @Override
        public int compare(final String s1, final String s2) {
            final var m1 = Month.fromString(s1);
            final var m2 = Month.fromString(s2);
            return Integer.compare(m1.days, m2.days);
        }
        
    } 

    private enum Month { 
        January(31),
        February(28),
        March(31),
        April(30),
        May(31),
        June(30),
        July(31),
        August(31),
        September(30),
        October(31),
        November(30),
        December(31);

        private int days;

        private Month(final int days) {
            this.days = days;
        }

        public static Month fromString(final String name) {
            Objects.requireNonNull(name);
            Month real = null;
            int count = 0;
            try {
                return valueOf(name);
            } catch (IllegalArgumentException e) {
                for (Month month : values()) {
                    if (month.toString().toLowerCase().startsWith(name.toLowerCase())) {
                        real = month;
                        count++;
                    } 
                }
                if (count != 1) {
                    throw new IllegalArgumentException();
                }
                return real;
            }
        } 
    }
}

