package com.douglasmatosdev.matchers;

import com.douglasmatosdev.utils.DateUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DayWeekMatcher extends TypeSafeMatcher<Date> {
    private Integer dayWeek;

    public DayWeekMatcher(Integer dayWeek) {
        this.dayWeek = dayWeek;
    }

    @Override
    public void describeTo(Description description) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_WEEK, dayWeek);
        String strDate = date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt", "BR"));
        description.appendText(strDate);
    }

    @Override
    protected boolean matchesSafely(Date date) {
        return DateUtils.verifyDayWeek(date, dayWeek);
    }
}
