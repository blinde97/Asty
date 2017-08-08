package game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Instructions {
	private ImageView ship;
	private Text shipInstructions;
	private Text label;
	private Rectangle backButton;
	private Circle bulletExample;
	private Rectangle badGuyExample;

	public Rectangle getBackButton() {
		return backButton;
	}

	private Text backLabel;

	public Group init(double width, double height) {
		Group root = new Group();
		Image shipImage = new Image("file:ship.png", 171 / 2, 48, false, false);
		ship = new ImageView(shipImage);
		ship.setX(width / 3 - 100);
		ship.setY(height / 2 + 50);
		bulletExample = new Circle(5, Color.AQUA);
		bulletExample.setCenterX(ship.getX() + 175);
		bulletExample.setCenterY(ship.getY());
		badGuyExample = new Rectangle(ship.getX() + 350, ship.getY(), 25, 25);
		badGuyExample.setFill(Color.RED);
		label = new Text(width / 3, height / 4, "How To Play");
		label.setStyle("-fx-font: 36 arial;");
		label.setFill(Color.RED);
		shipInstructions = new Text(0, height / 2 - 100,
				"Ship movement: " + "To move the ship, simply move the arrow keys. \n Press Space to fire at"
						+ " the bad guys (You only have 10 bullets). \n Hit all bad guys to advance.");
		shipInstructions.setWrappingWidth(width);
		shipInstructions.setStyle("-fx-font: 24 arial;");
		shipInstructions.setFill(Color.RED);
		backButton = new Rectangle(10, 25, 100, 30);
		backButton.setFill(Color.WHITE);
		backButton.setStroke(Color.AQUA);
		backButton.setStrokeWidth(2);
		backLabel = new Text(20, 50, "Back");
		backLabel.setStyle("-fx-font: 24 arial;");
		backLabel.setFill(Color.BLACK);
		root.getChildren().add(badGuyExample);
		root.getChildren().add(bulletExample);
		root.getChildren().add(label);
		root.getChildren().add(shipInstructions);
		root.getChildren().add(backButton);
		root.getChildren().add(backLabel);
		root.getChildren().add(ship);
		return root;
	}
	
	

}
