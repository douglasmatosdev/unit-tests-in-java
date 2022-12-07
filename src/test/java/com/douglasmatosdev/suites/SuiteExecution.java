package com.douglasmatosdev.suites;

import com.douglasmatosdev.services.CalculatorTest;
import com.douglasmatosdev.services.RentalServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculatorTest.class,
        CalculatorTest.class,
        RentalServiceTest.class
})
public class SuiteExecution {
    // Remove if you can
}
