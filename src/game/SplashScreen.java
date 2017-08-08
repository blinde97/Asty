package game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class SplashScreen {
	private Rectangle startButton;
	
	public Rectangle getStartButton() {
		return startButton;
	}



	private Text welcome;
	private Rectangle instructionButton;
	public Rectangle getInstructionButton() {
		return instructionButton;
	}


	private Rectangle cheatButton;
	public Rectangle getCheatButton(){
		return cheatButton;
	}
	private Text instructionLabel;
	private Text startLabel;
	private Group root;
	private Rectangle skipToL2Button;
	
	
	public Rectangle getSkipToL2Button() {
		return skipToL2Button;
	}


	public Group init(double width, double height){
		root = new Group();
		welcome = new Text(width / 6, height/4, "Welcome to Asty's Rescue");
		welcome.setStyle("-fx-font: 36 arial;");
		welcome.setFill(Color.RED);
		cheatButton = new Rectangle(100,100,100,35);
		cheatButton.setFill(Color.BLACK);
		startButton = new Rectangle(width/3, height/2, 200, 35);
		startButton.setFill(Color.WHITE);
		startButton.setStroke(Color.AQUA);
		startButton.setStrokeWidth(2);
		startLabel = new Text(startButton.getX()+startButton.getWidth()/2-25, 
				startButton.getY()+ startButton.getHeight()/2+7, "Start");
		startLabel.setFill(Color.BLACK);
		startLabel.setStyle("-fx-font: 28 arial;");
		instructionButton = new Rectangle(width/3, 2*height/3, 200, 35);
		instructionButton.setFill(Color.WHITE);
		instructionButton.setStroke(Color.AQUA);
		instructionButton.setStrokeWidth(2);
		instructionLabel = new Text(instructionButton.getX()+instructionButton.getWidth()/2-70,
				instructionButton.getY()+instructionButton.getHeight()/2+7, "Instructions");
		instructionLabel.setFill(Color.BLACK);
		instructionLabel.setStyle("-fx-font: 28 arial;");
		skipToL2Button = new Rectangle(width - 200, height - 35, 200, 35);
		skipToL2Button.setFill(Color.BLACK);
		root.getChildren().add(cheatButton);
		root.getChildren().add(skipToL2Button);
		root.getChildren().add(welcome);
		root.getChildren().add(startButton);
		root.getChildren().add(startLabel);
		root.getChildren().add(instructionButton);
		root.getChildren().add(instructionLabel);

		return root;
	}
	
	
	
}
