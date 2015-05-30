package com.jclolstorm.lolstorm.models;

public class Stat {
    private String label;
    private int value;
    private int indicatorColor;

    public Stat(String label, int value, int indicatorColor) {
        this.label = label;
        this.value = value;
        this.indicatorColor = indicatorColor;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

    public int getIndicatorColor () { return indicatorColor; }
}
