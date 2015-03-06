TANK COMMAND READ-ME

1) Building/executing
	Option 1:

	a) The source code for Tank Command should all be located within package TC.
	b) The images are located in the "resources/images" subdirectory
	c) The audio is located in the "resources/sound" subdirectory
	d) Create a jar file using the directory containing all the source code and resources
	e) Run the .jar file and hit "Start Game" to begin playing.
	
	Option 2:
	a) A build.xml file has been made after option 1 was finalized.
	b) At the command line, type in "ant" to compile all source files. Class files can be found in TankCommand/bin/TC.
	c) At the command line, type in "ant copy" to copy all data files (i.e. images and sound) from the src directory to the bin directory.
	d) At the command line, type in "java -cp TankCommand/bin TC.Window" to run the program.
	e) To create a .jar file, follow the instructions in option 1.

2) To add/modify data files
	Adding source code: Any source code that is modified/added should be kept within the same directory of the rest of the source files
	Adding resources: Images/audio should be kept in the "resources" subdirectory
	
3) Special information
	Bugs: 1) The pause button only freezes the background, not the tank or enemies
	      2) We do not have a way of resuming the game once paused. 
		  3) When you die, the button for restart functions properly, but fails to generate enemies
		  4) The buttons for "High Score" and "Music Control" do not function

3) How to play
	Movement: W or Up arrow key    = Jump/Use rockets
		  A or left arrow key  = Move left
		  D or right arrow key = Move right

	Shooting: Left-click

	Use power-up(superpower): Right-click

	Pause: Hit pause(top of frame)
	
	Link to highscore sample code: http://forum.codecall.net/topic/50071-making-a-simple-high-score-system/
