package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static LocalDate getTheDateRelativeToTodayPlusTheNumberOfDay(int increment) {
        return LocalDate.now().plusDays(increment);
    }

    public static String getFormattedDateRelativeToTodayPlusTheNumberOfDay(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern)).toLowerCase().replace(".", "");
    }
}