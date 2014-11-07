package ar.edu.itba.poo.bytemii.display;

import javafx.scene.paint.Color;

import java.util.Set;

public interface BasicGameDisplay {
	Set<Resolution> getValidResolutions();
	void setResolution(Resolution resolution);
	Resolution getResolution();
	void setBackColor(Color backColor);
	Color getBackColor();
	void setForeColor(Color foreColor);
	Color getForeColor();
}
