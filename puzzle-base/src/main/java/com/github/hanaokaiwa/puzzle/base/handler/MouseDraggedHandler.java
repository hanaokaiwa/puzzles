package com.github.hanaokaiwa.puzzle.base.handler;

import static com.github.hanaokaiwa.puzzle.base.Constants.*;

import com.github.hanaokaiwa.puzzle.base.Global;
import com.github.hanaokaiwa.puzzle.base.util.PuzzleUtil;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MouseDraggedHandler implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent event) {
		Object source = event.getSource();
		if (source instanceof Node) {
			Node node = (Node) source;
			node.setLayoutX(node.getLayoutX() + ((int) (event.getX() - Global.start.getX()) / GRID) * GRID);
			node.setLayoutY(node.getLayoutY() + ((int) (event.getY() - Global.start.getY()) / GRID) * GRID);
//			if (node instanceof Polygon) {
//				Polygon polygon = (Polygon) node;
//				System.out.println(PuzzleUtil.toString(PuzzleUtil.getTransformedPoints(polygon)));
//			}
		}
		PuzzleUtil.checkResult(Global.currentStage.getParts(), Global.currentStage.getFrame(), Global.resultLabel);
	}
}
