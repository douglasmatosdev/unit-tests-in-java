package com.douglasmatosdev.matchers;

import java.util.Calendar;

public class CustomMatchers {

    public static DayWeekMatcher willBe(Integer dayWeek) {
        return new DayWeekMatcher(dayWeek);
    }

    public static DayWeekMatcher willBeMonday() {
        return new DayWeekMatcher(Calendar.MONDAY);
    }
}
