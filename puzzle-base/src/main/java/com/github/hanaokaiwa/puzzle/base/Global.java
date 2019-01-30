package com.github.hanaokaiwa.puzzle.base;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;

/**
 * グローバル変数を扱うクラス
 */
public class Global {
	/** マウスクリックされたときのスタート点 */
	public static Point2D start;

	/** 現在のステージ */
	public static PuzzleStage currentStage;

	public static Point2D layoutStart;

	/** 結果を表示するLabel */
	public static final Label resultLabel = new Label("");

	/** パズルのステージ一覧 */
	public static final List<PuzzleStage> stages = new ArrayList<>();
}
