package ar.edu.itba.poo.bytemii.display;

import ar.edu.itba.poo.bytemii.emulator.hardware.Display;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.Memory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO: Documentation
 */

public class CanvasDisplay extends Canvas implements Display, BasicGameDisplay {
	public static final Resolution MIN_RES = new Resolution(WIDTH * 5, HEIGHT * 5);
	public static final Resolution MAX_RES = new Resolution(WIDTH * 15, HEIGHT * 15);
	public static final Resolution DEFAULT_RES = new Resolution(WIDTH * 10, HEIGHT * 10);

	private static final Color FORE_COLOR = Color.WHITE;
	private static final Color BACK_COLOR = Color.valueOf("336699"); // Dark Blue

	private Resolution resolution;
	private Color backColor;
	private Color foreColor;

	private Set<Resolution> resolutionList;

	public CanvasDisplay(double width, double height) {
		super(width, height);
		resolutionList = new HashSet<>();
		resolution = new Resolution(DEFAULT_RES.getWidth(), DEFAULT_RES.getHeight());
		backColor = BACK_COLOR;
		foreColor = FORE_COLOR;

		resolutionList.add(MIN_RES);
		resolutionList.add(DEFAULT_RES);
		resolutionList.add(MAX_RES);
	}

	@Override
	public Resolution getResolution() {
		return resolution;
	}

	@Override
	public Set<Resolution> getValidResolutions() {
		return resolutionList;
	}

	@Override
	public void setResolution(Resolution resolution) {
		if(resolution.getWidth() % WIDTH != 0 || resolution.getHeight() % HEIGHT != 0)
			throw new IllegalArgumentException();
		if(resolution.getWidth() < MIN_RES.getWidth() || resolution.getHeight() < MIN_RES.getHeight())
			throw new IllegalArgumentException();
		if(resolution.getWidth() > MAX_RES.getWidth() || resolution.getHeight() > MAX_RES.getHeight())
			throw new IllegalArgumentException();
		this.resolution = resolution;
		this.setWidth(resolution.getWidth());
		this.setHeight(resolution.getHeight());
	}

	@Override
	public Color getBackColor() {
		return backColor;
	}

	@Override
	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}

	@Override
	public Color getForeColor() {
		return foreColor;
	}


	public void setForeColor(Color foreColor) {
		this.foreColor = foreColor;
	}

	@Override
	public void paint(Memory memory) {
		if(memory == null || memory.size() != WIDTH * HEIGHT)
			throw new IllegalArgumentException();

		int pixelSize = resolution.getWidth() / WIDTH;
		GraphicsContext gc = this.getGraphicsContext2D();

		for(int i = 0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				if(memory.get(i * WIDTH + j) == PIXEL_ON)
					gc.setFill(foreColor);
				else
					gc.setFill(backColor);
				gc.fillRect(j * pixelSize, i * pixelSize, pixelSize, pixelSize);
			}
		}
	}
}
