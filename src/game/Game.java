package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Game {
	public static final int SIZE = 600;
	public static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private Asty asty;
	private SplashScreen splash;
	private Instructions instr;
	private Scene mainScene;
	private LevelTwo l2;
	private String whichRoot;

	/**
	 * Initialize the scene to SplashScreen
	 * @param width
	 * @param height
	 * @return the scene
	 */
	public Scene init(int width, int height) {
		asty = new Asty();
		instr = new Instructions();
		splash = new SplashScreen();
		l2 = new LevelTwo();
		mainScene = new Scene(splash.init(SIZE, SIZE), width, height, Color.BLACK);
		whichRoot = "splash";

		mainScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));

		return mainScene;
	}

	/**
	 * Scene control
	 * @param x
	 * @param y
	 */
	private void handleMouseInput(double x, double y) {
		if (whichRoot.equals("splash")) {
			if (splash.getStartButton().contains(x, y)) {
				mainScene.setRoot(asty.init(SIZE, SIZE));
				whichRoot = "asty";
				mainScene.setOnKeyPressed(e -> asty.handleKeyInput(e.getCode()));
				// attach game to the stage and display it
				// sets the game's loop
				KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> asty.step(SECOND_DELAY));
				Timeline animation = new Timeline();
				animation.setCycleCount(Timeline.INDEFINITE);
				animation.getKeyFrames().add(frame);
				animation.play();
			} else if (splash.getCheatButton().contains(x,y)){
				asty.setBAD_GUY_SPEED(asty.getBAD_GUY_SPEED()/2);
			}
				else if (splash.getInstructionButton().contains(x, y)) {
			
				mainScene.setRoot(instr.init(SIZE, SIZE));
				whichRoot = "instr";
			} else if (splash.getSkipToL2Button().contains(x, y)) {
				mainScene.setRoot(l2.init(SIZE, SIZE));
				whichRoot = "l2";
				KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> l2.step(SECOND_DELAY));
				mainScene.setOnKeyPressed(e -> l2.handleKeyInput(e.getCode()));
				Timeline animation = new Timeline();
				animation.setCycleCount(Timeline.INDEFINITE);
				animation.getKeyFrames().add(frame);
				animation.play();
			}
		} else if (whichRoot.equals("asty")) {
			if (asty.getQuitButton().contains(x, y)) {
				mainScene.setRoot(splash.init(SIZE, SIZE));
				whichRoot = "splash";
			}
			if (asty.isWon()) {
				if (asty.getNLButton().contains(x, y)) {
					mainScene.setRoot(l2.init(SIZE, SIZE));
					whichRoot = "l2";
					KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> l2.step(SECOND_DELAY));
					mainScene.setOnKeyPressed(e -> l2.handleKeyInput(e.getCode()));
					Timeline animation = new Timeline();
					animation.setCycleCount(Timeline.INDEFINITE);
					animation.getKeyFrames().add(frame);
					animation.play();
				}
			} else {
				if (asty.getTryAgainButton().contains(x, y)) {
					mainScene.setRoot(asty.init(SIZE, SIZE));
				}
			}

		} else if (whichRoot.equals("instr")) {
			if (instr.getBackButton().contains(x, y)) {
				mainScene.setRoot(splash.init(SIZE, SIZE));
				whichRoot = "splash";
			}
		} else if (whichRoot.equals("l2")) {
			if (l2.getQuitButton().contains(x, y)) {
				mainScene.setRoot(splash.init(SIZE, SIZE));
				whichRoot = "splash";
			}
			if (l2.isWon()) {
				if (l2.getNextLevelButton().contains(x, y)) {
					mainScene.setRoot(l2.init(SIZE, SIZE));
					whichRoot = "l2";
				}
			} else if (l2.getTryAgainButton().contains(x, y)) {
				mainScene.setRoot(l2.init(SIZE, SIZE));

			}
		}

	}

	public Scene getScene() {
		return mainScene;
	}
}
