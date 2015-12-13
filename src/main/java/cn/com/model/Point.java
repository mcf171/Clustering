package cn.com.model;

public class Point {

	
	private double x;
	private double y;
	private String status;
	private String trueLabel;
	private String label;
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTrueLabel() {
		return trueLabel;
	}
	public void setTrueLabel(String trueLabel) {
		this.trueLabel = trueLabel;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(obj instanceof Point){
			
			Point compare = (Point)obj;
			
			flag = this.x == compare.getX() && this.y == compare.getY();
		}
		return flag;
	}
	
	
	
}
