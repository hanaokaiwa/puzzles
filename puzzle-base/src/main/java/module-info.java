module com.github.hanaokaiwa.puzzle.base {

	requires transitive javafx.base;
	requires transitive javafx.graphics;
	requires transitive javafx.controls;

	exports com.github.hanaokaiwa.puzzle.base;
	exports com.github.hanaokaiwa.puzzle.base.handler;
	exports com.github.hanaokaiwa.puzzle.base.util;
}
