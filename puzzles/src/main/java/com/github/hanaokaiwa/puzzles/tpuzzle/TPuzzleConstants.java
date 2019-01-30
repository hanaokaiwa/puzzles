package com.github.hanaokaiwa.puzzles.tpuzzle;

import static com.github.hanaokaiwa.puzzle.base.Constants.*;

import com.github.hanaokaiwa.puzzle.base.FrameData;
import com.github.hanaokaiwa.puzzle.base.FrameData.FrameCenterPointData;
import com.github.hanaokaiwa.puzzle.base.PartData;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public interface TPuzzleConstants {

	/** すべてのフレームの頂点データ */
//	final static double[][] FRAME_POINTSS = { { 0.5 * BASE - STROKE_WIDTH, 0.5 * BASE - STROKE_WIDTH,
//			3.5 * BASE + STROKE_WIDTH, 0.5 * BASE - STROKE_WIDTH, 3.5 * BASE + STROKE_WIDTH, 1.5 * BASE + STROKE_WIDTH,
//			2.5 * BASE + STROKE_WIDTH, 1.5 * BASE + STROKE_WIDTH, 2.5 * BASE + STROKE_WIDTH, 4.5 * BASE + STROKE_WIDTH,
//			1.5 * BASE - STROKE_WIDTH, 4.5 * BASE + STROKE_WIDTH, 1.5 * BASE - STROKE_WIDTH, 1.5 * BASE + STROKE_WIDTH,
//			0.5 * BASE - STROKE_WIDTH, 1.5 * BASE + STROKE_WIDTH } };
	final static double[][] FRAME_POINTSS = { { 0.0, 0.0, 3.0 * BASE, 0.0, 3.0 * BASE, 1.0 * BASE, 2.0 * BASE,
			1.0 * BASE, 2.0 * BASE, 4.0 * BASE, 1.0 * BASE, 4.0 * BASE, 1.0 * BASE, 1.0 * BASE, 0.0, 1.0 * BASE } };
	final static FrameCenterPointData[][] FRAME_CENTER_POINT_DATASS = {
			{ new FrameCenterPointData(new BoundingBox(0.0, 0.0, 3.0 * BASE, 4.0 * BASE),
					new Point2D(1.5 * BASE, 0.5 * BASE)) } };
	/** フレームのデータ */
	final static FrameData[] FRAME_DATAS = { new FrameData(FRAME_POINTSS[0], FRAME_CENTER_POINT_DATASS[0]) };

	/** すべてのパーツの頂点データ */
//	final static double[][] PART_POINTSS = {
//			{ BASE, BASE, 2.5 * BASE, BASE, 3.5 * BASE, 2 * BASE, 3 * BASE, 2 * BASE, 3 * BASE, 3 * BASE },
//			{ 0.0, 0.0, 0.0, BASE, BASE, BASE }, { 0.0, BASE, 0.0, 4 * BASE, BASE, 4 * BASE, BASE, 2 * BASE },
//			{ 2.5 * BASE, 3 * BASE, 4 * BASE, 3 * BASE, 4 * BASE, 4 * BASE, 3.5 * BASE, 4 * BASE } };
	final static double[][] PART_POINTSS = {
			{ 0.0, 0.0, 1.5 * BASE, 0.0, 2.5 * BASE, 1.0 * BASE, 2.0 * BASE, 1.0 * BASE, 2.0 * BASE, 2.0 * BASE },
			{ 0.0, 0.0, 0.0, BASE, BASE, BASE }, { 0.0, 0.0, 0.0, 3.0 * BASE, BASE, 3.0 * BASE, BASE, 1.0 * BASE },
			{ 0.0, 0.0, 1.5 * BASE, 0.0, 1.5 * BASE, 1.0 * BASE, 1.0 * BASE, 1.0 * BASE } };

	/** すべてのパーツの塗りつぶし色 */
	final static Color[] PART_FILL_COLORS = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };

	/** すべてのパーツのID */
	final static String[] PART_IDS = { "RED", "GREEN", "BLUE", "YELLOW" };

	/** すべてのパーツデータ */
	final static PartData[] PART_DATAS = { new PartData(PART_POINTSS[0], Color.RED, "RED", BASE, BASE),
			new PartData(PART_POINTSS[1], Color.GREEN, "GREEN", 0.0, 0.0),
			new PartData(PART_POINTSS[2], Color.BLUE, "BLUE", 0.0, 3.0 * BASE),
			new PartData(PART_POINTSS[3], Color.YELLOW, "YELLOW", 2.5 * BASE, 3.0 * BASE) };

	/** 結果ラベルのX座標 */
	final double RESULT_LABEL_LAYOUT_X = 5.5 * BASE;

	/** 結果ラベルのY座標 */
	final double RESULT_LABEL_LAYOUT_Y = 10.0d;

	/** ウィンドウサイズの幅 */
	final double WINDOW_WIDTH = 6.0 * BASE;

	/** ウィンドウサイズの高さ */
	final double WINDOW_HEIGHT = 6.0 * BASE;
}
