/**
 * 
 */
package com.github.hanaokaiwa.puzzle.base;

import java.io.Serializable;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;

/**
 * フレームを構築するためのデータ
 */
public class FrameData implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;

	/** フレームの頂点の座標リスト */
	private final double[] points;
	/** フレームの中心点リスト */
	private final FrameCenterPointData[] frameCenterPointDatas;

	public FrameData(double[] points, FrameCenterPointData[] frameCenterPointDatas) {
		super();
		this.points = points;
		this.frameCenterPointDatas = frameCenterPointDatas;
	}

	public double[] getPoints() {
		return points;
	}

	public FrameCenterPointData[] getFrameCenterPointDatas() {
		return frameCenterPointDatas;
	}

	/**
	 * 中心点情報のデータ
	 */
	public static class FrameCenterPointData implements Serializable {
		/** */
		private static final long serialVersionUID = 1L;

		/** 中心点に対応する範囲 */
		private final BoundingBox area;
		/** 中心点 */
		private final Point2D centerPoint;

		public FrameCenterPointData(BoundingBox area, Point2D centerPoint) {
			super();
			this.area = area;
			this.centerPoint = centerPoint;
		}

		public BoundingBox getArea() {
			return area;
		}

		public Point2D getCenterPoint() {
			return centerPoint;
		}
	}
}
