package cn.com.service;

import java.util.ArrayList;
import java.util.List;

import cn.com.model.Point;
import cn.com.util.TextUtil;
import junit.framework.TestCase;

public class DBSCANTest extends TestCase {

	public void testAlgorithm() {
		
		
		List<Point> points = TextUtil.getAllInformation("data/dataset1.dat");
		
		List<List<Integer>> Cs = DBSCAN.algorithm(points.toArray(new Point[points.size()]), 1263761, 2000);
		
		this.assertEquals(Cs.size(), 1);
	}

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
}
