package cn.com.util;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cn.com.model.Point;

public class DrawPoints extends JPanel {
	
	private List<Point> lists;
	
	public void calculatePoint(){
		/*
		List<Point> list = TextUtil.getAllInformation("data/dataset1.dat");
		
		for(Point point : list){
			point.setX((point.getX()/1000));
			point.setY((point.getY()/1000));
			//point.setX((point.getX() - TextUtil.minX)/(TextUtil.maxX-TextUtil.minX));
			//point.setY((point.getY() - TextUtil.minY)/(TextUtil.maxY-TextUtil.minY));
		}
		
		lists = list;
		*/
		List<Point> list = TextUtil.getAllInformation("data/dataset2.dat");
		
		for(Point point : list){
			
			point.setX((point.getX() - TextUtil.minX)/(TextUtil.maxX-TextUtil.minX));
			point.setY((point.getY() - TextUtil.minY)/(TextUtil.maxY-TextUtil.minY));
		}
		
		lists = list;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("pdays");
		DrawPoints dp = new DrawPoints();
		dp.calculatePoint();
		frame.getContentPane().add(dp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 800);
		frame.setVisible(true);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Point point : lists){
			Double x = point.getX()*500;
			Double y = point.getX()*500;
			g.drawOval(x.intValue(), y.intValue(), 5, 5);
		}
		
	}
}