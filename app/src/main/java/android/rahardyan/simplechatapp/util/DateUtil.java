package android.rahardyan.simplechatapp.util;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by rahardyan on 16/05/17.
 */

/*
 * DateUtil: Utility class for Date-related functionalities.
 */
public final class DateUtil {
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "SUNDAY";

    /**
     * Gets the Buisiness day of the week in {@link String} format
     *
     * @return - String day, one of {@link DateUtil#MONDAY} {@link DateUtil#TUESDAY} {@link DateUtil#WEDNESDAY}
     * {@link DateUtil#THURSDAY} {@link DateUtil#FRIDAY} {@link DateUtil#SATURDAY} {@link DateUtil#SUNDAY}
     */
    @NonNull
    public static String getBusinessDayOfTheWeek(){
        Calendar calendar = Calendar.getInstance();
        switch (calendar.get(Calendar.DAY_OF_WEEK)){
            case Calendar.MONDAY:
                return MONDAY;
            case Calendar.TUESDAY:
                return TUESDAY;
            case Calendar.WEDNESDAY:
                return WEDNESDAY;
            case Calendar.THURSDAY:
                return THURSDAY;
            case Calendar.FRIDAY:
                return FRIDAY;
            case Calendar.SATURDAY:
                return SATURDAY;
            case Calendar.SUNDAY:
                return SUNDAY;
        }
        return "";
    }

    /**
     * get string date with custom format
     *
     * @param locale - expected locale
     * @param expectedFormat - expected format
     * @param dateTime - timestamp dateTime
     *
     * @return customized date format
     */
    @NonNull
    public static String getDateWithCustomFormat(@NonNull Locale locale, @NonNull String expectedFormat, long dateTime){
        String date = new SimpleDateFormat("dd MMM yyyy HH:mm")
                .format(new Date(dateTime));
        SimpleDateFormat dateFormatView = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault());
        try {
            Date newDate = dateFormatView.parse(date);
            dateFormatView = new SimpleDateFormat(expectedFormat, locale);
            date = dateFormatView.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * get string date with custom format
     *
     * @param expectedFormat - expected format
     * @param dateTime - dateTime in string
     *
     * @return customized date format
     */
    @NonNull
    public static String getDateWithCustomFormat(@NonNull String expectedFormat, String dateTime){
        String date = "";
        if (!dateTime.equals("0")) {
            date = dateTime.substring(6, 8);
            date = date + " " + dateTime.substring(4, 6);
            date = date + " " + dateTime.substring(0, 4);
        }

        return date;
    }
}
