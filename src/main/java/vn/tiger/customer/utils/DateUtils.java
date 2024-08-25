package vn.tiger.customer.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class DateUtils {

    public static String localDateToString(LocalDate localDate, String formatDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDate);
        return localDate.format(formatter);
    }

    public static String localDateToYYYYMMDD(LocalDate localDate) {
        String format = "yyyy-MM-dd";
        return localDateToString(localDate, format);
    }
}
