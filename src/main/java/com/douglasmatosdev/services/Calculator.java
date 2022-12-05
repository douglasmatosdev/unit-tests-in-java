package com.douglasmatosdev.services;

import com.douglasmatosdev.exceptions.NotDivideForZero;

public class Calculator {
    public int sum(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int divide(int a, int b) throws NotDivideForZero {
        if (b == 0) {
            throw new NotDivideForZero();
        }
        return a / b;
    }
}
