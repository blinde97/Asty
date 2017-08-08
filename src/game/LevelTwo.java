//This entire file is part of my masterpiece
//Ben Linde
//The purpose of this code is to create the Parent node for the second level. 
//I think it is well designed because it shows what I have learned about inheritance - 
//because I do not need to redo all the code that was done in the first level.
//I refactored the code by taking out the handleKeyInput method, because that method 
//was simply inherited from Asty. 
package game;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;


public class LevelTwo extends Asty{
	private Rectangle badGuy3;
	private Group root;
	private int yDirection3;
	private int xDirection3;
	private int badGuyCount;
	private Rectangle quitButton;
	private Rectangle tryAgainButton;
	public Rectangle getQuitButton() {
		return quitButton;
	}
	

	public Rectangle getTryAgainButton() {
		return tryAgainButton;
	}

	public Rectangle getNextLevelButton() {
		return nextLevelButton;
	}
	private boolean won;
	private Rectangle nextLevelButton;

	public Group getRoot() {
		return root;
	}

	public Group init(int width, int height){
		badGuyCount = 3;
		root = super.init(SIZE, SIZE);
		badGuy3 = new Rectangle(width / 2 - 25, height / 2 + 100, 25, 25);
		badGuy3.setFill(Color.RED);
		xDirection3 = 1;
		yDirection3 = 1;
		root.getChildren().add(badGuy3);
		return root;
	}
	
	public void step(double elapsedTime){
		super.step(elapsedTime);
		//badGuy3 should follow the ship
		badGuy3.setX(badGuy3.getX() + (super.getShipView().getX() - badGuy3.getX())/2*elapsedTime*xDirection3*.8);
		if (badGuy3.getY() > SIZE - badGuy3.getHeight() || badGuy3.getY() < 0) {
			yDirection3 *= -1;
		}
		badGuy3.setY(badGuy3.getY() + (super.getShipView().getY() - badGuy3.getY())/2*elapsedTime*yDirection3*.8);
		if (badGuy3.getX() > SIZE - badGuy3.getWidth() || badGuy3.getX() < 0) {
			xDirection3 *= -1;
		}
		super.intersectWithShip(badGuy3);
		for(Circle c: super.getBullets()){
			updateMissle(elapsedTime, c);
		}
		if(badGuyCount==0){
			wonLevel();
		}
	}
	
	public boolean isWon(){
		return won;
	}
	
	protected void wonLevel() {
		stopMovement();
		Text winLabel = new Text(SIZE / 2 - 125, SIZE / 2, "YOU WIN!");
		winLabel.setFill(Color.RED);
		winLabel.setStyle("-fx-font: 48 arial;");
		nextLevelButton = new Rectangle(SIZE / 2 - 50, SIZE / 2 + 25, 100, 35);
		super.createButton(nextLevelButton);
		quitButton = new Rectangle(SIZE / 2 - 50, SIZE / 2 + 80, 100, 35);
		super.createButton(quitButton);
		Text nextLevelLabel = new Text(nextLevelButton.getX() + 5, nextLevelButton.getY() + 25, "Play Again");
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
	
	protected void gameOver(){
		stopMovement();
		Text gameOver = new Text(SIZE / 2 - 125, SIZE / 2, "GAME OVER");
		gameOver.setFill(Color.RED);
		gameOver.setStyle("-fx-font: 48 arial;");
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
	
	
	public void updateMissle(double elapsedTime, Circle c){
		super.updateMissle(elapsedTime, c);
		hitByMissle(c, badGuy3);
	}
	
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
	
	protected void stopMovement(){
		super.stopMovement();
		xDirection3 = 0;
		yDirection3 = 0;
	}
}
