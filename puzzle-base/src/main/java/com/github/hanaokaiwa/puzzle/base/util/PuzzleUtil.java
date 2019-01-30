package com.github.hanaokaiwa.puzzle.base.util;

import static com.github.hanaokaiwa.puzzle.base.Constants.*;

import java.util.ArrayList;
import java.util.List;

import com.github.hanaokaiwa.puzzle.base.FrameData;
import com.github.hanaokaiwa.puzzle.base.FrameData.FrameCenterPointData;
import com.github.hanaokaiwa.puzzle.base.Global;
import com.github.hanaokaiwa.puzzle.base.PartData;
import com.github.hanaokaiwa.puzzle.base.PuzzleStage;
import com.github.hanaokaiwa.puzzle.base.handler.MouseDraggedHandler;
import com.github.hanaokaiwa.puzzle.base.handler.MousePressedHandler;
import com.github.hanaokaiwa.puzzle.base.handler.MouseReleasedHandler;

import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class PuzzleUtil {

	/**
	 * パズルの初期化
	 * 
	 * @param frameDatas   すべてのステージのフレームのデータ
	 * @param partDatas    すべてのパーツの初期化データ
	 * @param windowWidth  ウィンドウの幅
	 * @param windowHeight ウィンドウの高さ
	 */
	public static void initPuzzles(FrameData[] frameDatas, PartData[] partDatas, double windowWidth,
			double windowHeight) {
		EventHandler<MouseEvent> mousePressedHandler = new MousePressedHandler();
		EventHandler<MouseEvent> mouseReleasedHandler = new MouseReleasedHandler();
		EventHandler<MouseEvent> mouseDraggedHandler = new MouseDraggedHandler();

		for (FrameData frameData : frameDatas) {
			Group root = new Group();
			Scene scene = new Scene(root);

			List<Polygon> parts = new ArrayList<Polygon>();

			Polygon frame = new Polygon(initFramePoints(frameData, windowWidth, windowHeight));
			frame.setStroke(STROKE_COLOR);
			frame.strokeProperty();
			frame.setFill(Color.TRANSPARENT);
			frame.setStrokeWidth(STROKE_WIDTH);
			frame.fillProperty();
			frame.toFront();
			frame.setMouseTransparent(true);
			frame.setId(FRAME_ID);
			root.getChildren().add(frame);

			for (PartData partData : partDatas) {
				Polygon part = new Polygon(partData.getPoints());
				part.setFill(partData.getFileColor());
				part.setStroke(STROKE_COLOR);
				part.setStrokeWidth(STROKE_WIDTH);
				part.fillProperty();
				part.strokeProperty();
				part.setOnMousePressed(mousePressedHandler);
				part.setOnMouseReleased(mouseReleasedHandler);
				part.setOnMouseDragged(mouseDraggedHandler);
				part.setId(partData.getId());
				part.setLayoutX(partData.getLayoutX());
				part.setLayoutY(partData.getLayoutY());
				root.getChildren().add(part);
				parts.add(part);
			}
			PuzzleStage puzzleStage = new PuzzleStage(frame, parts, scene);
			Global.stages.add(puzzleStage);
		}
	}

	/**
	 * フレームの初期化を実施
	 * 
	 * @param frameData    フレームデータ
	 * @param windowWidth  ウィンドウの幅
	 * @param windowHeight ウィンドウの高さ
	 * @return 中間へ遷移したフレームの座標
	 */
	private static double[] initFramePoints(FrameData frameData, double windowWidth, double windowHeight) {

		double[] points = frameData.getPoints();
		Point2D centerPoint = getCenterPoint(points);

		for (int i = 0; i < points.length; i += 2) {
			double x = points[i];
			double y = points[i + 1];
			for (FrameCenterPointData frameCenterPointData : frameData.getFrameCenterPointDatas()) {
				if (frameCenterPointData.getArea().contains(x, y)) {
					if (x < frameCenterPointData.getCenterPoint().getX()) {
						points[i] -= STROKE_WIDTH;
					} else {
						points[i] += STROKE_WIDTH;
					}
					if (y < frameCenterPointData.getCenterPoint().getY()) {
						points[i + 1] -= STROKE_WIDTH;
					} else {
						points[i + 1] += STROKE_WIDTH;
					}
				}
			}
		}
		double layoutX = windowWidth / 2.0 - centerPoint.getX();
		double layoutY = windowHeight / 2.0 - centerPoint.getY();
		System.out.println("layoutX=" + layoutX + ",layoutY=" + layoutY);
		Translate translate = new Translate(layoutX, layoutY);
		translate.transform2DPoints(points, 0, points, 0, points.length / 2);

		return points;
	}

	/**
	 * 座標で示す図形の中心点を返す
	 * 
	 * @param points 座標リスト
	 * @return 中心点
	 */
	private static Point2D getCenterPoint(double[] points) {
		double maxX = Double.MIN_VALUE, minX = Double.MAX_VALUE;
		double maxY = Double.MIN_VALUE, minY = Double.MAX_VALUE;
		for (int i = 0; i < points.length; i += 2) {
			double x = points[i];
			double y = points[i + 1];
			if (x > maxX) {
				maxX = x;
			}
			if (x < minX) {
				minX = x;
			}
			if (y > maxY) {
				maxY = y;
			}
			if (y < minY) {
				minY = y;
			}
		}
		return new Point2D((maxX + minX) / 2.0, (maxY + minY) / 2.0);
	}

	/**
	 * 結果を表すラベルを初期化
	 * 
	 * @param scene   Scene
	 * @param layoutX X座標
	 * @param layoutY Y座標
	 */
	public static void initResultLabel(Scene scene, double layoutX, double layoutY) {
		Global.resultLabel.setFont(RESULT_LABEL_FONT);
		Global.resultLabel.setTextFill(Color.RED);
		Global.resultLabel.setLayoutX(layoutX);
		Global.resultLabel.setLayoutY(layoutY);
		((Group) scene.getRoot()).getChildren().add(Global.resultLabel);
	}

	/**
	 * 結果をチェックする
	 */
	public static void checkResult(List<Polygon> parts, Polygon frame, Label label) {
		List<Polygon> transformedParts = getTransformedParts(parts);
		List<Polygon> checkedParts = new ArrayList<>();
		boolean ok = true;
		check: for (Polygon part : transformedParts) {
			if (!containsAllInArea(part, frame)) {
				ok = false;
				break;
			}
			for (Polygon checkedPart : checkedParts) {
				if (containsPartInArea(part, checkedPart, true) || containsPartInArea(checkedPart, part, true)) {
					ok = false;
					break check;
				}
			}
			checkedParts.add(part);
		}
		if (ok) {
			label.setText("OK");
		} else {
			label.setText("");
		}
	}

	/**
	 * Transformをした結果で新しいPolygonのリストを返す
	 * 
	 * @return Transformをした結果で新しいPolygonのリスト
	 */
	private static List<Polygon> getTransformedParts(List<Polygon> parts) {
		List<Polygon> transformedParts = new ArrayList<>();
		for (Polygon part : parts) {
			double[] points = getTransformedPoints(part);
			Polygon transformedPart = new Polygon(points);
			transformedPart.setId(part.getId());
			// strokeを設定することですべての頂点containsの不具合対応
			transformedPart.setStroke(Color.WHITE);
			transformedParts.add(transformedPart);
		}
		return transformedParts;
	}

	/**
	 * パーツの変換後の頂点データを取得
	 * 
	 * @param part
	 * @return パーツの変換後の頂点データ
	 */
	public static double[] getTransformedPoints(Polygon part) {
		double[] points = part.getPoints().stream().mapToDouble(Number::doubleValue).toArray();
		double orientation = 1.0;
		if (NodeOrientation.RIGHT_TO_LEFT == part.getNodeOrientation()) {
			orientation = -1.0;
		}
		Scale scale = new Scale(orientation * part.getScaleX(), part.getScaleY());
		scale.transform2DPoints(points, 0, points, 0, points.length / 2);
		Translate translate = new Translate(part.getLayoutX(), part.getLayoutY());
		translate.transform2DPoints(points, 0, points, 0, points.length / 2);
		Rotate rotate = new Rotate(part.getRotate());
		rotate.transform2DPoints(points, 0, points, 0, points.length / 2);
		return points;
	}

	/**
	 * part1のすべての頂点がpart2に含まれているかどうかを返す
	 * 
	 * @param part1
	 * @param part2
	 * @return part1のすべての頂点がpart2に含まれているかどうか
	 */
	private static boolean containsAllInArea(Polygon part1, Polygon part2) {
		List<Double> points = part1.getPoints();
		for (int i = 0; i < points.size(); i += 2) {
			double x = points.get(i);
			double y = points.get(i + 1);
			if (!part2.contains(x, y)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * part1の一つの頂点がpart2に含まれているかどうかを返す
	 * 
	 * @param part1
	 * @param part2
	 * @param pointInLineOk 頂点がLINEにあることを許すかどうか
	 * @return part1の一つ頂点がpart2に含まれているかどうか
	 */
	private static boolean containsPartInArea(Polygon part1, Polygon part2, boolean pointInLineOk) {
		List<Double> points = part1.getPoints();
		List<Point2D> onLinePoints = new ArrayList<>();
		for (int i = 0; i < points.size(); i += 2) {
			double x = points.get(i);
			double y = points.get(i + 1);
			if (part2.contains(x, y)) {
				// 頂点がLINEにあることを許さない か 頂点がLINEにない場合
				if (!pointInLineOk || !pointOnLine(x, y, part2, true, onLinePoints)) {
					return true;
				}
			}
		}
		// 辺の上にあるポイントが2つ以上ある場合、その中点が辺の上にあるかどうかをチェック
		if (onLinePoints.size() > 1) {
			Point2D prePoint = null;
			for (int i = 0; i < onLinePoints.size(); i++) {
				Point2D point = onLinePoints.get(i);
				if (0 == i) {
					Point2D lastPoint = onLinePoints.get(onLinePoints.size() - 1);
					if (!pointOnLine((point.getX() + lastPoint.getX()) / 2.0, (point.getY() + lastPoint.getY()) / 2.0,
							part2, false, null)) {
						return true;
					}
				} else {
					if (!pointOnLine((point.getX() + prePoint.getX()) / 2.0, (point.getY() + prePoint.getY()) / 2.0,
							part2, false, null)) {
						return true;
					}
				}
				prePoint = point;
			}
		}

		return false;
	}

	/**
	 * x,yで示したポイントがpartの辺の上にあるかどうかを返す
	 * 
	 * @param x
	 * @param y
	 * @param part
	 * @param onLinePoints 辺の上にあるポイントのリスト
	 * @return x,yで示したポイントがpartの辺の上にあるかどうか
	 */
	private static boolean pointOnLine(double x, double y, Polygon part, boolean addPointToList,
			List<Point2D> onLinePoints) {
		boolean result = false;
		// 最初の頂点
		double firstX = 0.0, firstY = 0.0;
		// 直前の頂点
		double preX = 0.0, preY = 0.0;
		List<Double> points = part.getPoints();
		for (int i = 0; i < points.size(); i += 2) {
			double nowX = points.get(i);
			double nowY = points.get(i + 1);
			if (0 == i) {
				// 最初の頂点
				firstX = nowX;
				firstY = nowY;
			} else {
				// 一つの辺の上にポイントがあるかどうかを判定
				Line line = new Line(preX, preY, nowX, nowY);
				if (line.contains(x, y)) {
					result = true;
					break;
				}
			}
			preX = nowX;
			preY = nowY;
		}
		if (!result) {
			// 最後の頂点と最初の頂点の辺の上にポイントがあるかどうかを判定
			Line line = new Line(preX, preY, firstX, firstY);
			result = line.contains(x, y);
		}

		if (result && addPointToList) {
			onLinePoints.add(new Point2D(x, y));
		}
		return result;
	}

	/**
	 * パーツの反転を実施
	 * 
	 * @param part パーツ
	 */
	public static void flipPart(Polygon part) {
		List<Double> polygonPoints = part.getPoints();
		double[] points = polygonPoints.stream().mapToDouble(Number::doubleValue).toArray();
		double[] xPoints = new double[points.length / 2];
		for (int i = 0; i < points.length / 2; i++) {
			xPoints[i] = points[2 * i];
		}
		flipX(xPoints);
		for (int i = 0; i < xPoints.length; i++) {
			polygonPoints.set(2 * i, xPoints[i]);
		}
	}

	/**
	 * X座標の反転を実施
	 * 
	 * @param xPoints X座標
	 */
	private static void flipX(double[] xPoints) {
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (double point : xPoints) {
			if (point < min) {
				min = point;
			}
			if (point > max) {
				max = point;
			}
		}
		for (int i = 0; i < xPoints.length; i++) {
			xPoints[i] = max + min - xPoints[i];
		}
	}

	/**
	 * パーツの回転を実施
	 * 
	 * @param part  パーツ
	 * @param angle 角度
	 */
	public static void rotatePart(Polygon part, double angle) {
		List<Double> polygonPoints = part.getPoints();
		double[] points = polygonPoints.stream().mapToDouble(Number::doubleValue).toArray();
		Point2D centerPoint = getCenterPoint(points);
		System.out.println("Step1:" + toString(points));
//		Translate translate = new Translate(part.getLayoutX(), part.getLayoutY());
//		translate.transform2DPoints(points, 0, points, 0, points.length / 2);
//		System.out.println("Step2:" + toString(points));
		Rotate rotate = new Rotate(angle, centerPoint.getX(), centerPoint.getY());
		rotate.transform2DPoints(points, 0, points, 0, points.length / 2);
//		System.out.println("Step3:" + toString(points));
//		try {
//			translate.inverseTransform2DPoints(points, 0, points, 0, points.length / 2);
//		} catch (NonInvertibleTransformException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Step4:" + toString(points));
//		roundPoints(points);
		System.out.println("Step5:" + toString(points));
		for (int i = 0; i < polygonPoints.size(); i++) {
			polygonPoints.set(i, points[i]);
		}
	}

//	/**
//	 * すべての座標を小数点1桁で四捨五入する
//	 * 
//	 * @param points 座標リスト
//	 */
//	private static void roundPoints(double[] points) {
//		for (int i = 0; i < points.length; i++) {
//			points[i] = Math.round(points[i] * 10.0) / 10.0;
//		}
//	}

	/**
	 * DEBUGのためdouble[]の座標リストを文字列へ変換
	 * 
	 * @param points double[]の座標リスト
	 * @return 変換された文字列
	 */
	public static String toString(double[] points) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < points.length; i += 2) {
			sb.append("(");
			sb.append(points[i]);
			sb.append(",");
			sb.append(points[i + 1]);
			sb.append("),");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}

}
