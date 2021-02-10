import java.util.ArrayList;
import java.awt.Color;

public class DataOfSquare {

	
	//ArrayList that'll contain the colors
	ArrayList<Color> C =new ArrayList<Color>();
	int color; //0: empty, 1:food, 2: Player 1 snake, 3: 2nd Snake
	SquarePanel square;
	public DataOfSquare(int col){
		
		//Lets add the color to the arrayList
		C.add(Color.white);//0
		C.add(Color.green);    //1
		C.add(Color.orange);   //2 first snake
		C.add(Color.red);		//opponent snake
		color=col;
		square = new SquarePanel(C.get(color));
	}
	public void lightMeUp(int c){
		square.ChangeColor(C.get(c));
	}
}
