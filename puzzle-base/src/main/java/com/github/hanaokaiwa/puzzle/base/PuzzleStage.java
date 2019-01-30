package com.github.hanaokaiwa.puzzle.base;

import java.io.Serializable;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.shape.Polygon;

public class PuzzleStage implements Serializable {
	/** */
	private static final long serialVersionUID = 1L;

	public PuzzleStage(Polygon frame, List<Polygon> parts, Scene scene) {
		super();
		this.frame = frame;
		this.parts = parts;
		this.scene = scene;
	}

	private Polygon frame;

	private List<Polygon> parts;

	private Scene scene;

	public Polygon getFrame() {
		return frame;
	}

	public List<Polygon> getParts() {
		return parts;
	}

	public Scene getScene() {
		return scene;
	}
}
