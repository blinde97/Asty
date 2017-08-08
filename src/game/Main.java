package game;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static final int SIZE = 600;
	private Game game;
	private Scene myScene;
	private Stage stage;


	@Override
	/**
	 * start the game
	 */
	public void start(Stage s) throws Exception {
		stage = s;
		// create your own game here
		game = new Game();
		myScene = game.init(SIZE, SIZE);
		stage.setTitle("Asty's Rescue");
		stage.setScene(myScene);
		stage.show();
	}
/**
 * launch game
 * @param args
 */
	public static void main(String[] args) {
		launch(args);
	}

}
