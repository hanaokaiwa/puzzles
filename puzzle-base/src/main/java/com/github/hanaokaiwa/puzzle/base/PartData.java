/**
 * 
 */
package com.github.hanaokaiwa.puzzle.base;

import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * パーツを作成するための必要なデータ
 */
public class PartData implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;

	private final double[] points;

	private final Color fileColor;

	private final String id;

	private final double layoutX;

	private final double layoutY;

	public PartData(double[] points, Color fileColor, String id, double layoutX, double layoutY) {
		super();
		this.points = points;
		this.fileColor = fileColor;
		this.id = id;
		this.layoutX = layoutX;
		this.layoutY = layoutY;
	}

	public double[] getPoints() {
		return points;
	}

	public Color getFileColor() {
		return fileColor;
	}

	public String getId() {
		return id;
	}

	public double getLayoutX() {
		return layoutX;
	}

	public double getLayoutY() {
		return layoutY;
	}
}
