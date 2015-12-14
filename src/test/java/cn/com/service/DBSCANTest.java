package cn.com.service;

import java.util.ArrayList;
import java.util.List;

import cn.com.model.Point;
import cn.com.util.TextUtil;
import junit.framework.TestCase;

public class DBSCANTest extends TestCase {

	public void testAlgorithm() {
		
		
		List<Point> points = TextUtil.getAllInformation("data/dataset1.dat");
		points = TextUtil.markTheList("data/dataset1-label.dat", points);
		List<List<Integer>> Cs = DBSCAN.algorithm(points.toArray(new Point[points.size()]), 1263761, 2000,50);
		
		System.out.println("when the e is 1263761 and the MinPts is 2000 the number of C is : " + Cs.size());
		this.assertEquals(Cs.size(), 1);
		
		Cs = DBSCAN.algorithm(points.toArray(new Point[points.size()]), 10000, 4,50);
		System.out.println("when the e is 10000 and the MinPts is 4 the number of C is : " + Cs.size());
	}
/*
	public void testCalculateDist(){
		
		Point target = new Point();
		target.setX(32710);
		target.setY(70003);
		
		Point source = new Point();
		source.setX(942327);
		source.setY(947322);
		
		double distance = DBSCAN.calculateDist(target, source);
		System.out.println(distance);
	}
	*/
}
