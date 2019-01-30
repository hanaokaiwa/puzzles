package com.github.hanaokaiwa.puzzles.tpuzzle;

import static com.github.hanaokaiwa.puzzles.tpuzzle.TPuzzleConstants.*;

import com.github.hanaokaiwa.puzzle.base.Global;
import com.github.hanaokaiwa.puzzle.base.util.PuzzleUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TPuzzle extends Application {
	// private int currentStageNo;

	@Override
	public void start(Stage stage) throws Exception {
		// タイトルを設定
		stage.setTitle("T Puzzle");

		Scene scene = initStages();
		PuzzleUtil.initResultLabel(scene, RESULT_LABEL_LAYOUT_X, RESULT_LABEL_LAYOUT_Y);
		stage.setScene(scene);

		stage.setWidth(WINDOW_WIDTH);
		stage.setHeight(WINDOW_HEIGHT);
		stage.show();
	}

	/** ステージの初期化 */
	private Scene initStages() {
		PuzzleUtil.initPuzzles(FRAME_DATAS, PART_DATAS, WINDOW_WIDTH, WINDOW_HEIGHT);
		Global.currentStage = Global.stages.get(0);
		return Global.currentStage.getScene();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
