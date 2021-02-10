public class Tuple { 
	public  int x;
	public  int y;
	public int xf;
	public int yf;

	public Tuple(int x, int y) {
		this.x = x;
		this.y = y;
	}

	//horizontal distance between any two positions
	public int distX(Tuple destination){
		return (Math.abs(this.x - destination.x));
	}
	public int distY( Tuple destination){
		return (Math.abs(this.y - destination.y));
	}
	//represents how many vertical or horizontal moves must be made to reach a given position
	public int dist(Tuple des) {
		return (this.distX(des) + this.distY(des));
	}


	public boolean eat(Tuple food){
		return ( this.getY() == food.getX() ) && ( this.getX() == food.getY() );
	}

	//direction 1
	public Tuple toRight(){
		return new Tuple(((this.x + 1) % 20), this.y);
	}


	//direction 2
	public Tuple toLeft(){
		if (this.x - 1 < 0) {
			return new Tuple(19, this.y);
		} else {
			return new Tuple(((this.x - 1) % 20), this.y);
		}
	}

	//direction 3
	public Tuple below(){
		if (this.y - 1 < 0 ) {
			return new Tuple(this.x, 19);
		} else {
			return new Tuple(this.x, ((this.y - 1) % 20));
		}
	}

	//direction 4
	public Tuple above(){
		return new Tuple(this.x, ((this.y + 1) % 20));
	}
	  public void ChangeData(int x, int y){
		    this.x = x; 
		    this.y = y; 
	  }
	  public int getX(){
		  return x;
	  }
	  public int getY(){
		  return y;
	  }
	  public int getXf(){
		  return xf;
	  }
	  public int getYf(){ return yf; }
} 