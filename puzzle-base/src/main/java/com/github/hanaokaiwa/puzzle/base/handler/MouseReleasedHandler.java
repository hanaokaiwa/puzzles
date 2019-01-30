package com.github.hanaokaiwa.puzzle.base.handler;

import com.github.hanaokaiwa.puzzle.base.Global;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MouseReleasedHandler implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent event) {
		// スタート点をリリース
		Global.start = null;
		Object source = event.getSource();
		if (source instanceof Node) {
			Node node = (Node) source;
			node.setViewOrder(-0.0);
		}
	}
}
