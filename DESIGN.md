## Design Goals:
The main goal of this project was to create a game that had multiple levels that differed algorithmically, rather than just visually. For my project, I chose to create a simple 2d shooter game, set in space. The first level has two moving "enemies", that simply move straight up and down. The second level has three enemies, the same two as the first level, but with a third enemy that follows the space ship's movements. 

## How to add new features:
In order to add a new level, a new class would have to be created that extends the Asty class, which is the superclass for the game levels. Additionally, you would have to edit the Game class and LevelTwo class to properly handle the button labels and scene control. For another enemy to be created, it would have to be initialized in the init method of the new class, and its movements would be coded in the step method of the class. 

## Major Design Choices:
The main design choice I made was to have a Game class that deals with all the scene control. To accomplish this, I needed to have all the init methods of the individual scenes return a Group, so that I would only have to create one scene, and simply call setRoot in order to change the Scene. This worked well because I didn't have to create a different Scene for every class, which made it significantly easier to change the scene in my main class. However, in some cases where a scene was initialized, then changed, then reverted back, the scenes did not change back to their original state. Another decision was to have the first level class, Asty.java, be the parent of the second level class, LevelTwo.java, rather than creating a level parent class that both levels extended. This was good, because it allowed me to write one less class, however it removed some flexibility in the design of LevelTwo.

 

 