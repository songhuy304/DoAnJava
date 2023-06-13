package com.example.doanjava.constants;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public enum Provider {
    LOCAL("Local"),

    GITHUB("GitHub"),
    GOOGLE("Google");


    public final String value;
}
