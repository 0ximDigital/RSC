package hr.nullteam.rsc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {

    public static final String GSON_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String SHORT_DATE_FORMAT = "MMM d";
    public static final String TIME_FORMAT = "HH:mm";

    public boolean hasDatePassed(Date date) {
        if (date == null) {
            return true;
        }
        Date now = new Date();
        return now.after(date);
    }

    public boolean isNowInBetweenDates(Date startTime, Date endTime) {
        if (startTime == null || endTime == null) {
            return false;
        }
        Date now = new Date();
        return (now.after(startTime) && now.before(endTime));
    }

    public boolean isToday(Date dateSpecified) {
        if (dateSpecified == null) {
            return false;
        }
        Calendar now = Calendar.getInstance();
        Calendar other = Calendar.getInstance();
        other.setTime(dateSpecified);

        return isEqual(now, other);
    }

    public boolean isYesterday(Date dateSpecified) {
        if (dateSpecified == null) {
            return false;
        }
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_YEAR, -1);
        Calendar other = Calendar.getInstance();
        other.setTime(dateSpecified);

        return isEqual(now, other);
    }

    public boolean isEqual(Calendar now, Calendar other) {
        return (now.get(Calendar.DATE) == other.get(Calendar.DATE) &&
                now.get(Calendar.MONTH) == other.get(Calendar.MONTH) &&
                now.get(Calendar.YEAR) == other.get(Calendar.YEAR));
    }

    public boolean isSameDay(Date first, Date second) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(first);
        cal2.setTime(second);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public String getStringFormat(Date time, String format) {
        if (time == null) {
            return "-";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(time);
    }

}
