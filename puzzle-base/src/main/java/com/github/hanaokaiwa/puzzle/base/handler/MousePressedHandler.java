package com.github.hanaokaiwa.puzzle.base.handler;

import static com.github.hanaokaiwa.puzzle.base.Constants.*;

import com.github.hanaokaiwa.puzzle.base.Global;
import com.github.hanaokaiwa.puzzle.base.util.PuzzleUtil;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

public class MousePressedHandler implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent event) {
		// スタート点を記録
		Global.start = new Point2D(event.getX(), event.getY());
		Object source = event.getSource();
		if (source instanceof Node) {
			Node node = (Node) source;
			Global.layoutStart = new Point2D(node.getLayoutX(), node.getLayoutY());
			node.setViewOrder(-1.0);
		}
		if (event.getClickCount() == 2) {
			// ダブルクリックの場合では回転を実施
			if (source instanceof Polygon) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					PuzzleUtil.rotatePart((Polygon) source, ROTATE_DEGREE);
				} else if (event.getButton().equals(MouseButton.SECONDARY)) {
					PuzzleUtil.flipPart((Polygon) source);
				}
				PuzzleUtil.checkResult(Global.currentStage.getParts(), Global.currentStage.getFrame(),
						Global.resultLabel);
			}
		}
	}
}
