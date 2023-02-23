package com.sulitsa.dev.accountant.model;

import java.util.List;

public enum Answer {
    A, B, C, D;

    public static List<Answer> getAll() {
        return List.of(A, B, C, D);
    }
}
