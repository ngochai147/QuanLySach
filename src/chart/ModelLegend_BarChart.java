package chart;

import java.awt.Color;

public class ModelLegend_BarChart {
//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ModelLegend_BarChart(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public ModelLegend_BarChart() {
    }

    private String name;
    private Color color;
}
