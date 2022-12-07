package com.douglasmatosdev.matchers;

import com.douglasmatosdev.utils.DateUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class DateDiffDayMatcher extends TypeSafeMatcher<Date> {
    private Integer amountDays;

    public DateDiffDayMatcher(Integer amountDays) {
        this.amountDays = amountDays;
    }

    @Override
    protected boolean matchesSafely(Date date) {
        return DateUtils.isSameDate(date, DateUtils.getDateWithDiffDays(amountDays));
    }

    @Override
    public void describeTo(Description description) {
        // TODO
    }
}
