package com.github.hanaokaiwa.puzzle.base;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * プログラムで使用する定数
 */
public interface Constants {
	/** オブジェクトのサイズのベース */
	public final static double BASE = 100.0d;

	/** オブジェクトを移動する際の最小単位 */
	public final static int GRID = 5;

	/** 枠の太さ */
	public final static double STROKE_WIDTH = 2.0d;

	/** 枠の色 */
	public final static Color STROKE_COLOR = Color.BLACK;

	/** フレームのID */
	public final static String FRAME_ID = "FRAME";

	/** 結果のラベルのフォント */
	public final static Font RESULT_LABEL_FONT = new Font(20);

	/** 結果のラベルの色 */
	public final static Color RESULT_LABEL_COLOR = Color.RED;

	/** 回転のときの角度 */
	public final static double ROTATE_DEGREE = 45.0d;
}
