import java.util.HashSet;
import java.util.Set;

/*
A data structure created to hold all of the positions of the snake
Uses a linked list to add and remove the head and tail of the snake
 as well as a HashSet which holds four digit values of the snake's positions
 to allow for O(1) checks for collisions
 */

public class SnakePositions {
    // current length of snake
    int size;
    // Node for head of snake
    Node head;
    // Node for tail of snake
    Node tail;
    // count of how many food pellets have been consumed
    int grubs;

    // set of all current positions of snake
    Set<Integer> snakePos;

    int score;

    // constructor method
    public SnakePositions(int size) {
        this.size = size;
        this.snakePos = new HashSet<>();
        this.head = null;
        this.score = 0;
    }

    // adds the Tuple pos to head of the snake
    // O(1)
    public void addPos(Tuple pos) {
        Node newPos = new Node(pos);
        if (this.head != null) {
            newPos.next = this.head;
            this.head.prev = newPos;
        } else {
            this.tail = newPos;
        }
        int posXY = tupToXY(pos);
        this.snakePos.add(posXY);
        this.head = newPos;
    }

    //Moves the head of the snake and refreshes the positions in the arraylist
    //1:right 2:left 3:top 4:bottom 0:nothing
    public Tuple moveInterne(int dir) {

        Tuple newHead = this.getHeadPos();

        switch (dir) {
            case 4:
                newHead = newHead.above();
                break;
            case 3:
                newHead = newHead.below();
                break;
            case 2:
                newHead = newHead.toLeft();
                break;
            case 1:
                newHead = newHead.toRight();
                break;
        }
        return newHead;
    }
    //returns true if moving in the given direction runs back into the snake's own body
    public boolean danger(int dir)
    {
        Tuple move = this.moveInterne(dir);
        return this.inSnake(move);
    }
    //given a destination and a direction, this function returns true if moving the head position of this snake in that
// direction brings it closer to the destination.
    public boolean closer(int dir, Tuple dest) {
        Tuple head = this.getHeadPos();
        Tuple move = this.moveInterne(dir);

        int fromHere = head.dist(dest);
        int fromThere = move.dist(dest);

        return (fromThere < fromHere);
    }


    public int moveAIGeneral(int difficulty, int dir, Tuple food){
        int move = moveAI(dir);
        int selectRNG;
        //hard mode Snake searches for food but sometimes crashes into itself
        if(difficulty ==1){
            selectRNG= (int)(Math.random() *100);
            //50% chance the AI is careful
            if ((0<=selectRNG) && (selectRNG<20)){
                move=directAI(dir,food);
            }
        }
        return move;
    }

    public int moveAI(int currentDir){
        int select=randomAI();

        switch(currentDir) {
            case 4:    // currently moving up
                //not permitted to move down (opposite direction)
                while ( (select == 3) || (this.danger(select) )){
                    select = randomAI();
                }
                break;

            case 3:    //down
                while ( (select == 4) || (this.danger(select) )){
                    select = randomAI();
                }
                break;

            case 2: //left
                while ( (select == 1) || (this.danger(select) )){
                    select = randomAI();
                }
                break;

            case 1:    // right
                while ( (select == 2) || (this.danger(select) )){
                    select = randomAI();
                }
                break;
        }


        return select;
    }

    //RNG 1,2,3,or 4
    public int randomAI(){
        return 1 + (int) (Math.random() * 4);
    }
    //AI strat to go directly toward food
    //doesn't consider collisions
    public int directAI(int curdir, Tuple food){
        int choice=0;

        //does moving to the right get me closer?
        if ( (this.closer(1,food) && (curdir != 2) )){
            choice=1;
        }else if ( (this.closer(4,food) && (curdir != 3) )){
            choice=4;
        }else if ( (this.closer(2,food) && (curdir != 1) )){
            choice=2;
        }else{
            choice=3;
        }


        return choice;
    }


    //removes the tailPos from the snake
    // removes the tailPos from the snake
    // O(1)
    public Tuple removeTail() {
        Tuple tailPos = this.tail.position;
        Node newTail = this.tail.prev;
        newTail.next = null;
        this.tail = newTail;
        int tailXY = tupToXY(tailPos);
        this.snakePos.remove(tailXY);
        return tailPos;
    }

    // returns true if Tuple pos is in the snake BUT NOT THE HEAD
    // O(1)
    public boolean hitSelf(Tuple pos) {
        int posXY = tupToXY(pos);
        int headXY = tupToXY(this.head.position);
        if (posXY == headXY) {
            return false;
        }
        return this.inSnake(pos);
    }

    // returns true if Tuple pos is in the snake
    // O(1)
    public boolean inSnake(Tuple pos) {
        int posXY = tupToXY(pos);
        return this.snakePos.contains(posXY);
    }

    public Tuple getHeadPos() {
        return this.head.position;
    }

    public Tuple getTailPos() {
        return this.tail.position;
    }

    public void increaseSize() {
        this.size++;
    }

    public void increaseGrubbed(){
        this.grubs++;
    }


    //converts tuple to 4 digit int value for set
    // converts tuple to 4 digit int value for set
    private int tupToXY(Tuple pos) {
        return pos.getX() * 100 + pos.getY();
    }

    // private node object
    private static class Node {
        Tuple position;
        Node next;
        Node prev;

        public Node(Tuple pos) {
            this.position = pos;
        }
    }

    /*
     * Sets score base on player eating food within two minutes
     * 
     * @param addToScore Time difference from food eaten minus when food was spawn
     * 
     * @return score Return Calculated score if player has eaten within two minutes
     * else return unchanged score
     */
    public int setScore(int addToScore) {
        System.out.printf("Adding: %d \n", addToScore);
        if (addToScore < 122) {
            return score = score + (121 - addToScore);
        }
        return score;
    }

    public int getScore() {
        return score;
    }
/*
    private void changeSnakeSpeed(int snakeLength) {
        if (snakeLength % 10 == 0 && speed != 40) {
            speed = (long) (speed * 0.5);
        }
    }
*/
}
