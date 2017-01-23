package logic;

public class MyPoint{
	private int x;
	private int y;
	private static int ID_POINT;
	
	public MyPoint(int x,int y){
		
		ID_POINT++;
		setX(x);
		setY(y);
	}

	public int getDistans(MyPoint p){
	
	return (int) Math.sqrt(Math.pow(this.getX()-p.getX(),2)+Math.pow(this.getY()-p.getY(),2));		
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String toString(){
		return " "+getX()+ " " +getY();
	}
}
