package com.example.caculator;

public enum CalculatorOperator {
    CLEAR,
    NEGATE ,
    POINTER ,
    SIN,
    COS,
    ROOT,
    Factorial,
    DIVISION {
        @Override
        public char getOperator() {
            return '÷';
        }
    },
    MULTIPLICATION {
        @Override
        public char getOperator() {
            return '×';
        }
    },
    SUBTRACTION {
        @Override
        public char getOperator() {
            return '-';
        }
    },
    ADDITION {
        @Override
        public char getOperator() {
            return '+';
        }
    },
    EQUAlS;


    public char getOperator() {
        return 0;
    }
}
