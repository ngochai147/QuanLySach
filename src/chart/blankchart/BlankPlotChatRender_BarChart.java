package chart.blankchart;

import java.awt.Graphics2D;

public abstract class BlankPlotChatRender_BarChart {

    public abstract String getLabelText(int index);

    public abstract void renderSeries(BlankPlotChart_BarChart chart, Graphics2D g2, SeriesSize size, int index);
}
