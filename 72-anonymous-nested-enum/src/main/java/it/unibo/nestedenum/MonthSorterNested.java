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
public static final int VENTOTTO = 28;
public static final int TRENTA = 30;
public static final int TRENTUNO = 31;


    public enum Month{
        JANUARY(31),
        FEBRUARY(28), 
        MARCH(31), 
        APRIL(30), 
        MAY(31), 
        JUNE(30), 
        JULY(31), 
        AUGUST(31), 
        SEPTEMBER(30), 
        OCTOBER(31), 
        NOVEMBER(30), 
        DECEMBER(31);

        private final int days;

        private Month(final int days){
            
            if(days != VENTOTTO && days != TRENTA && days != TRENTUNO){
                throw new IllegalArgumentException("Numero non valido");
            }

            this.days=days;
        }

        public int getDays(){
            return this.days;
        }

        public static Month fromString (String name){
            if(name == null){
                throw new NullPointerException();
            }
            String conf = name.toUpperCase().trim();
            List<Month> found = new ArrayList<>();
            for (Month month : Month.values()) {
                if(month.toString().startsWith(conf)){
                    found.add(month);
                }
            }

            if (found.size() == 1){
                return found.get(0);
            }
            else{
                throw new IllegalArgumentException();
            }
        }
    }

    
    static class SortByDate implements Comparator<String>{
        public int compare(final String month1, final String month2){
            return Integer.compare(Month.fromString(month1).getDays(), Month.fromString(month2).getDays());
        }
    }

    static class SortByMonthOrder implements Comparator<String>{
        public int compare(final String month1, final String month2){
            return Month.fromString(month1).compareTo(Month.fromString(month2));
        }
    }


    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }


}
