import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

 public class KeyboardListener extends KeyAdapter{
     int mode;

     public KeyboardListener(int mode) {
         this.mode = mode;
     }

        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case 39:	// -> Right
                    //if it's not the opposite direction
                    if(ThreadsController.directionSnake!=2)
                    ThreadsController.directionSnake=1;
                    break;

                case 38:	// -> Top
                    if(ThreadsController.directionSnake!=4)
                        ThreadsController.directionSnake=3;
                    break;

                case 37: 	// -> Left
                    if(ThreadsController.directionSnake!=1)
                        ThreadsController.directionSnake=2;
                    break;

                case 40:	// -> Bottom
                    if(ThreadsController.directionSnake!=3)
                        ThreadsController.directionSnake=4;
                    break;

                case 27:   // ESC key (Pause game)
                    ThreadsController.pauseState = !ThreadsController.pauseState;

                default: 	break;
            }
            // w = 87
            // a = 65
            // s = 83
            // d = 68
            if (mode == 2) {
                switch(e.getKeyCode()) {
                    case 68: // right
                        if (ThreadsController.badDir != 2) ThreadsController.badDir = 1;
                        break;
                    case 87: // top
                        if (ThreadsController.badDir != 4) ThreadsController.badDir = 3;
                        break;
                    case 65: // left
                        if (ThreadsController.badDir != 1) ThreadsController.badDir = 2;
                        break;
                    case 83: //down
                        if (ThreadsController.badDir != 3) ThreadsController.badDir = 4;
                    default:
                        break;
                }
            }
        }
 	
 }
