package logic;

public class MyPoint{
	private int x;
	private int y;
	private static int ID_POINT;
	private int id_p;
	
	public MyPoint(int x,int y){
		
		setID_POINT(getID_POINT() + 1);
		id_p=getID_POINT();
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

	public static int getID_POINT() {
		return ID_POINT;
	}

	public static void setID_POINT(int iD_POINT) {
		ID_POINT = iD_POINT;
	}

	public int getId_p() {
		return id_p;
	}

	public void setId_p(int id_p) {
		this.id_p = id_p;
	}
}
