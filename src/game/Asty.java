//This file is part of my code masterpiece
//Ben Linde
//This code is well designed as both the first level and the parent class for all
//subsequent levels. It both creates a level and provides a good framework to create new 
//levels. The only refactoring here was to add the stopMovement() method, 
//which allowed me to easily stop movement in other levels by overriding that method.
package game;

import javafx.scene.Group;
import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Asty {
	private Rectangle tryAgainButton;
	private Rectangle nextLevelButton;
	private boolean won;
/**
 * 
 * @return whether the game is won
 */
	public boolean isWon() {
		return won;
	}

	/**
	 * 
	 * @return the next level button
	 */
	public Rectangle getNLButton() {
		return nextLevelButton;
	}
	
	/**
	 * 
	 * @return the try again button
	 */

	public Rectangle getTryAgainButton() {
		return tryAgainButton;
	}

	private Rectangle quitButton;
/**
 * 
 * @return the quit button
 */
	public Rectangle getQuitButton() {
		return quitButton;
	}

	public static final int SIZE = 600;
	public int KEY_INPUT_SPEED;
	private int BAD_GUY_SPEED;
	/**
	 * 
	 * @return speed of the enemies
	 */
	public int getBAD_GUY_SPEED() {
		return BAD_GUY_SPEED;
	}

	/**
	 * 
	 * @param bAD_GUY_SPEED
	 * @return change the enemies speed to bAD_GUY_SPEED
	 */
	public int setBAD_GUY_SPEED(int bAD_GUY_SPEED) {
		BAD_GUY_SPEED = bAD_GUY_SPEED;
		return BAD_GUY_SPEED;
	}

	private ImageView shipView;
/**
 * 
 * @return the ship
 */
	public ImageView getShipView() {
		return shipView;
	}

	private ImageView spaceImage;
	private Rectangle badGuy1;
	private Rectangle badGuy2;
	private int yDirection1;
	private int yDirection2;
	private Group root;
	private int numBullets = 10;
	private int bulletsLeft;
	private int currentBullet = 0;
	private ArrayList<Circle> bullets;
	private int badGuyCount = 2;

	/**
	 * 
	 * @return the list containing all the bullets
	 */
	public ArrayList<Circle> getBullets() {
		return bullets;
	}

	private Text bulletsLabel;
/**
 * Initialize the scene as a parent node, so the scene can be changed simply by calling setRoot()
 * @param width
 * @param height
 * @return the Parent node
 */
	public Group init(int width, int height) {
		BAD_GUY_SPEED = 25;
		KEY_INPUT_SPEED = 15;
		won = false;
		// create a scene graph to organize the scene
		root = new Group();
		// create a place to see the shapes
		bullets = new ArrayList<Circle>(10);
		generateBullets();
		// make some shapes and set their properties
		Image ship = new Image("file:ship.png", 171 / 2, 48, false, false);
		shipView = new ImageView(ship);
		Image space = new Image("file:space.png");
		spaceImage = new ImageView(space);
		// x and y represent the top left corner, so center it
		shipView.setX(0 + shipView.getBoundsInLocal().getWidth() / 2);
		shipView.setY(height / 2 - shipView.getBoundsInLocal().getHeight() / 2);
		badGuy1 = new Rectangle(width / 2 - 25, height / 2 + 50, 25, 25);
		badGuy1.setFill(Color.RED);
		badGuy2 = new Rectangle(3 * width / 4, height / 2 + 50, 25, 25);
		badGuy2.setFill(Color.RED);
		yDirection1 = 1;
		yDirection2 = 1;
		spaceImage.setX(0);
		spaceImage.setY(0);
		bulletsLeft = 10;
		bulletsLabel = new Text(20, 50, "Bullets Left: " + Integer.toString(bulletsLeft));
		bulletsLabel.setFill(Color.WHITE);
		for (Circle c : bullets) {
			c.setCenterX(0);
			c.setCenterY(0);
			c.setFill(Color.BLACK);
		}
		root.getChildren().add(spaceImage);
		root.getChildren().add(shipView);
		root.getChildren().add(badGuy1);
		root.getChildren().addAll(bullets);
		root.getChildren().add(bulletsLabel);
		root.getChildren().add(badGuy2);
		return root;
	}

	/**
	 * create the list of bullets
	 */
	public void generateBullets() {
		for (int i = 0; i < numBullets; i++) {
			bullets.add(new Circle(5, Color.BLUE));
		}
	}

	/**
	 * Change properties of shapes to animate them
	 * 
	 * 
	 */
	public void step(double elapsedTime) {
		badGuy1.setY(badGuy1.getY() + 2 * yDirection1 * BAD_GUY_SPEED * elapsedTime);
		if (badGuy1.getY() > SIZE - badGuy1.getHeight() || badGuy1.getY() < 0) {
			yDirection1 *= -1;
		}
		badGuy2.setY(badGuy2.getY() - 2 * yDirection2 * BAD_GUY_SPEED * elapsedTime);
		if (badGuy2.getY() > SIZE - badGuy2.getHeight() || badGuy2.getY() < 0) {
			yDirection2 *= -1;
		}
		intersectWithShip(badGuy1);
		intersectWithShip(badGuy2);
		for (Circle c : bullets) {
			updateMissle(elapsedTime, c);
		}

		if (badGuyCount == 0) {
			wonLevel();
		}

	}

	/**
	 * Move the bullets if they were fired. 
	 * @param elapsedTime
	 * @param c, the bullet
	 */
	public void updateMissle(double elapsedTime, Circle c) {
		if (c.getFill() == Color.AQUA) {
			c.setCenterX(c.getCenterX() + 200 * elapsedTime);
		}
		hitByMissle(c, badGuy1);
		hitByMissle(c, badGuy2);
	}

	/**
	 * Determine if a bullet hits a bad guy
	 * @param c, the bullet
	 * @param r, the bad guy
	 */
	public void hitByMissle(Circle c, Rectangle r) {
		Shape intersect = Shape.intersect(c, r);
		if (root.getChildren().contains(r) && root.getChildren().contains(c)) {
			if (intersect.getBoundsInLocal().getWidth() != -1) {
				root.getChildren().remove(c);
				root.getChildren().remove(r);
				badGuyCount--;
			}
		}
	}

	/**
	 * Determine if a bad guy runs into the ship
	 * @param r1
	 */
	public void intersectWithShip(Rectangle r1) {
		if (root.getChildren().contains(r1)) {
			if (r1.getBoundsInParent().intersects(shipView.getBoundsInParent())) {
				gameOver();
			}
		}
	}

	// What to do each time a key is pressed
	public void handleKeyInput(KeyCode code) {
		switch (code) {
		case RIGHT:
			shipView.setX(shipView.getX() + KEY_INPUT_SPEED);
			break;
		case LEFT:
			shipView.setX(shipView.getX() - KEY_INPUT_SPEED);
			break;
		case UP:
			shipView.setY(shipView.getY() - KEY_INPUT_SPEED);
			break;
		case DOWN:
			shipView.setY(shipView.getY() + KEY_INPUT_SPEED);
			break;
		case SPACE:
			if (currentBullet < numBullets)
				fire();
			else
				break;
		default:
			// do nothing
		}
	}


	protected void gameOver() {
		Text gameOver = new Text(SIZE / 2 - 125, SIZE / 2, "GAME OVER");
		gameOver.setFill(Color.RED);
		gameOver.setStyle("-fx-font: 48 arial;");
		stopMovement();
		tryAgainButton = new Rectangle(SIZE / 2 - 50, SIZE / 2 + 25, 100, 35);
		createButton(tryAgainButton);
		quitButton = new Rectangle(SIZE / 2 - 50, SIZE / 2 + 80, 100, 35);
		createButton(quitButton);
		Text tryAgainLabel = new Text(tryAgainButton.getX() + 10, tryAgainButton.getY() + 25, "Try Again");
		tryAgainLabel.setFill(Color.BLACK);
		tryAgainLabel.setStyle("-fx-font: 20 arial;");
		Text doneLabel = new Text(quitButton.getX() + 25, quitButton.getY() + 25, "Quit");
		doneLabel.setFill(Color.BLACK);
		doneLabel.setStyle("-fx-font: 20 arial;");
		root.getChildren().add(tryAgainButton);
		root.getChildren().add(tryAgainLabel);
		root.getChildren().add(quitButton);
		root.getChildren().add(doneLabel);
		root.getChildren().add(gameOver);
	}

	private void fire() {
		Circle bullet = bullets.get(currentBullet);
		bullet.setFill(Color.AQUA);
		bullet.setCenterX(shipView.getX() + shipView.getBoundsInLocal().getWidth());
		bullet.setCenterY(shipView.getY() + shipView.getBoundsInLocal().getHeight() / 2);
		currentBullet++;
		bulletsLeft--;
		bulletsLabel.setText("Bullets Left: " + Integer.toString(bulletsLeft));
	}

	protected void wonLevel() {
		Text winLabel = new Text(SIZE / 2 - 125, SIZE / 2, "Level Completed!");
		winLabel.setFill(Color.RED);
		winLabel.setStyle("-fx-font: 48 arial;");
		stopMovement();
		nextLevelButton = new Rectangle(SIZE / 2 - 50, SIZE / 2 + 25, 100, 35);
		createButton(nextLevelButton);
		quitButton = new Rectangle(SIZE / 2 - 50, SIZE / 2 + 80, 100, 35);
		createButton(quitButton);
		Text nextLevelLabel = new Text(nextLevelButton.getX() + 5, nextLevelButton.getY() + 25, "Next Level");
		nextLevelLabel.setFill(Color.BLACK);
		nextLevelLabel.setStyle("-fx-font: 20 arial;");
		Text doneLabel = new Text(quitButton.getX() + 25, quitButton.getY() + 25, "Quit");
		doneLabel.setFill(Color.BLACK);
		doneLabel.setStyle("-fx-font: 20 arial;");
		won = true;
		root.getChildren().add(nextLevelButton);
		root.getChildren().add(nextLevelLabel);
		root.getChildren().add(quitButton);
		root.getChildren().add(doneLabel);
		root.getChildren().add(winLabel);
	}

	protected void createButton(Rectangle rectangle) {
		rectangle.setFill(Color.WHITE);
		rectangle.setStroke(Color.AQUA);
		rectangle.setStrokeWidth(2);
	}
	
	protected void stopMovement(){
		yDirection1 = 0;
		yDirection2 = 0;
	}

}
