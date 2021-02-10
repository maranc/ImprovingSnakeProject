import java.util.ArrayList;

/*
	TODO
 */

//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread {
	ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<ArrayList<DataOfSquare>>();
	ScoreboardWindow scoreboardWindow = new ScoreboardWindow();
	//positions of head and tail of snakes
	Tuple goodHead,badHead, goodPrevTail, badPrevTail;


	int sizeGood, sizeBad;

	int foodSpawnTime, foodAteTime;

	int mode, ai;
	// higher number = slower snake
	// lower input latency at high numbers
	long speed;
	public static int directionSnake;
	public static int badDir;
	public static boolean pauseState = false;

	SnakePositions goodSnake, badSnake;
	Tuple goodEgg, badEgg, foodPosition;

	// Constructor of ControllerThread
	ThreadsController(Tuple positionDepart, ArrayList<Integer> settings, int mode) {
		
		// Get all the threads
		Squares = Window.Grid;

		this.mode = mode;
		this.ai=ai;

		sizeGood = settings.get(0);
		sizeBad= sizeGood;



		speed = settings.get(1);

		System.out.printf("Snake size: %d\n", sizeGood);
		System.out.printf("Game speed: %d\n", speed);


		goodEgg= positionDepart;
		badEgg= new Tuple(14,14);

		//spawns snakes given game mode
		spawnGeneral(mode);
		


		directionSnake = 1;
		badDir = 1;

		foodPosition = new Tuple(Window.height - 2, Window.width - 2);
		spawnFood(foodPosition);
		foodSpawnTime = (int) System.currentTimeMillis() / 1000;
	}

	

	// Important part :
	public void run() {
		while (true) {
			moveInterneGeneral(mode, ai);
			checkCollisionGeneral(mode);
			moveExterneGeneral(mode);
			pauser();
			resumeGame();
			pauseGame();
		}
	}

	// delay between each move of the snake
	private void pauser() {
		try {
			sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void spawnGeneral(int mode){
		//create snake object for player 1
		//mode= 0 is the default
		System.out.printf("Mode: %d\n", mode);
		// fill positions with the corresponding beginning snake length and color accordingly
		goodSnake= spawnSnake(sizeGood,2, goodEgg);
		goodPrevTail = goodSnake.getTailPos();

		//spawn second snake if playing versus mode
		if(mode == 1 || mode == 2){
			//opponent snake
			badSnake= spawnSnake(sizeBad, 3, badEgg);
			badHead = badSnake.getHeadPos();
			badPrevTail= badSnake.getTailPos();
		}
	}

	private SnakePositions spawnSnake(int size, int skin, Tuple hatchSpot){
		SnakePositions snake = new SnakePositions(size);

		for(int i=0; i<size; i++){
			Tuple newPos = hatchSpot.toRight();
			snake.addPos(newPos);
			Squares.get(newPos.getY()).get(newPos.getX()).lightMeUp(skin);
			hatchSpot=newPos;
		}
		return snake;
	}

	private void moveInterneGeneral(int mode, int ai){
		goodHead= goodSnake.moveInterne(directionSnake);
		//if playing versus mode, load next position for second snake
		if (mode == 1){
			badDir=badSnake.moveAIGeneral(ai,badDir, foodPosition);

			badHead= badSnake.moveInterne(badDir);
			//local multiplayer
		} else if (mode == 2) {
			badHead = badSnake.moveInterne(badDir);
		}
	}



	private void checkCollisionGeneral(int mode) {
        if (mode == 0) {
            checkCollision();
        } else if (mode == 1 || mode == 2) {
            checkCollisionVS(goodHead, badHead);
        }
    }

	private void checkCollision() {
		// stop game if snake collides with itself
		if (goodSnake.hitSelf(goodHead)) {
			System.out.println("COLLISION!\n");
			scoreboardWindow.setScore(sizeGood-3,-1,"");
			MainMenu.OpenScoreBoard();
			stopTheGame();
		}

		boolean goodEating = goodHead.eat(foodPosition);
		if (goodEating) {
			goodSnake.increaseGrubbed();
			sizeGood++;
			foodAteTime = (int) System.currentTimeMillis() / 1000;
			System.out.printf("Food eaten: %d\nSnake size: %d\n", goodSnake.grubs, sizeGood);
			goodSnake.setScore(foodAteTime - foodSpawnTime);
			System.out.printf("P1 score: %d\n", goodSnake.getScore());
			foodPosition = getValAleaNotInSnake();
			spawnFood(foodPosition);
			foodSpawnTime = (int) System.currentTimeMillis() / 1000;
		}

	}

	// Checking for collisions under conditions of versus mode
	// The game ends when either snake hits the body of either snake
	private void checkCollisionVS(Tuple headPlayer, Tuple headAI) {
		// if the player-controlled snake collides with its or the other snake's body.
		boolean crashedPlayer = goodSnake.hitSelf(headPlayer) || badSnake.inSnake(headPlayer);
		boolean crashedAI = badSnake.hitSelf(headAI) || goodSnake.inSnake(headAI);

		if (crashedPlayer && crashedAI) {
			System.out.println("IT'S A DRAW! \n");
			scoreboardWindow.setScore(sizeGood-3,sizeBad-3, "Draw!");
			MainMenu.OpenScoreBoard();
			stopTheGame();
		} else if (crashedPlayer) {
			System.out.println("YOU LOSE! \n");
			System.out.println("sizebad"+sizeBad);
			scoreboardWindow.setScore(sizeGood-3,sizeBad-3, "Player 2 Won!");
			MainMenu.OpenScoreBoard();
			stopTheGame();
		}else if (crashedAI){
			System.out.println("YOU WON! \n");
			System.out.println("sizebad"+sizeBad);
			scoreboardWindow.setScore(sizeGood-3,sizeBad-3, "Player 1 Won!");
			MainMenu.OpenScoreBoard();
			stopTheGame();
		}

		// has either snake just eaten the pellet?
		boolean goodEating = goodHead.eat(foodPosition);
		boolean badEating = badHead.eat(foodPosition);

		if (goodEating) {
			goodSnake.increaseGrubbed();
			sizeGood++;
			foodAteTime = (int) System.currentTimeMillis() / 1000;
			System.out.printf("Food eaten: %d\nSnake size: %d\n", goodSnake.grubs, sizeGood);
			goodSnake.setScore(foodAteTime - foodSpawnTime);
			System.out.printf("Good snake Score: %d\n", goodSnake.getScore());
		}
		if (badEating) {
			badSnake.increaseGrubbed();
			sizeBad++;
			foodAteTime = (int) System.currentTimeMillis() / 1000;
			System.out.printf("Food eaten by RED: %d\nSnake size: %d\n", badSnake.grubs, sizeBad);
			badSnake.setScore(foodAteTime - foodSpawnTime);
			System.out.printf("Bad snake Score: %d\n", badSnake.getScore());
		}
		if(goodEating || badEating){
			foodPosition =getValVS();
			spawnFood(foodPosition);
			foodSpawnTime= (int) System.currentTimeMillis() / 1000;
		}
	}

	// Stops The Game
	private void stopTheGame() {
		while (true) {
			pauser();
		}
	}



	// Put food in a position and displays it
	private void spawnFood(Tuple foodPositionIn) {
		Squares.get(foodPositionIn.x).get(foodPositionIn.y).lightMeUp(1);
	}

	// return a position not occupied by the snake
	private Tuple getValAleaNotInSnake() {
		Tuple p;
		int ranX = (int) (Math.random() * 19);
		int ranY = (int) (Math.random() * 19);
		p = new Tuple(ranX, ranY);
		// o(n)
		// generate new coordinates for p while
		// current ones are in the snake
		boolean inSnake = goodSnake.inSnake(p) || (goodPrevTail.eat(foodPosition));
		while (inSnake) {
			ranX = (int) (Math.random() * 19);
			ranY = (int) (Math.random() * 19);
			p.ChangeData(ranX, ranY);
			inSnake = goodSnake.inSnake(p) || (goodHead.eat(p));
		}
		return p;
	}
	// return a position not occupied by the snake
	private Tuple getValVS() {
		Tuple p;
		int ranX = (int) (Math.random() * 19);
		int ranY = (int) (Math.random() * 19);
		p = new Tuple(ranX, ranY);
		// o(n)
		// generate new coordinates for p while
		// current ones are in the snake
		boolean inGood = goodSnake.inSnake(p) || (goodPrevTail.eat(foodPosition));
		boolean inBad = badSnake.inSnake(p) || (badPrevTail.eat(foodPosition));
		while (inGood || inBad) {
			ranX = (int) (Math.random() * 19);
			ranY = (int) (Math.random() * 19);
			p.ChangeData(ranX, ranY);


			inGood = goodSnake.inSnake(p) || (goodHead.eat(p));
			inBad = badSnake.inSnake(p) || (badHead.eat(p));
		}
		return p;
	}

	private void moveExterneGeneral(int mode)
	{
		//single player
		goodSnake.addPos(goodHead);
		paintAfterBite(0);
		//Playing versus mode; accounts for second snake.
		if (mode == 1 || mode == 2){
			badSnake.addPos(badHead);
			paintAfterBite(1);
		}
	}
	//Passed an integer representing the snake in question, this function colors the squares in the list
	// depending on of this snake just ate a pellet (increased length).
	private void paintAfterBite(int player) {
		int x;
		int y;
		if (player == 0) {
			y = goodHead.getX();
			x = goodHead.getY();
			Squares.get(x).get(y).lightMeUp(2);

			if (goodSnake.size == sizeGood) {
				goodPrevTail = goodSnake.removeTail();
				y = goodPrevTail.getX();
				x = goodPrevTail.getY();
				Squares.get(x).get(y).lightMeUp(0);
			} else { //just ate
				goodSnake.increaseSize();
			}
		} else { // 1 refers to second snake
			y = badHead.getX();
			x = badHead.getY();
			Squares.get(x).get(y).lightMeUp(3);

			if (badSnake.size == sizeBad) {
				badPrevTail = badSnake.removeTail();
				y = badPrevTail.getX();
				x = badPrevTail.getY();
				Squares.get(x).get(y).lightMeUp(0);
			} else {
				badSnake.increaseSize();
			}
		}
	}


	// Moves the head of the snake and refreshes the positions in the arraylist
	// 1:right 2:left 3:top 4:bottom 0:nothing
	// Refresh the squares that needs to be
	private void moveExterne() {
		// add new head position to positions
		goodSnake.addPos(new Tuple(goodHead.x, goodHead.y));
		// change new head position square color
		int y = goodHead.getX();
		int x = goodHead.getY();
		Squares.get(x).get(y).lightMeUp(2);
		// Haven't eaten
		if (goodSnake.size == sizeGood) {
			// remove tail square and change color to white
			goodPrevTail = goodSnake.removeTail();
			y = goodPrevTail.getX();
			x = goodPrevTail.getY();
			Squares.get(x).get(y).lightMeUp(0);
		} else {
			goodSnake.increaseSize();
		}
	}
	private void resumeGame(){
		if (this.getState() != Thread.State.RUNNABLE && !pauseState)
			interrupt();
	}

	private void pauseGame() {
		while (pauseState) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}

	public int setMode() {
		return 1;
	}
}
