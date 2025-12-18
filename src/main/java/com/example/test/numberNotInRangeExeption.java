package com.example.test;

public class numberNotInRangeExeption extends RuntimeException {
    public numberNotInRangeExeption(String message) {
        super(message);
    }
}
