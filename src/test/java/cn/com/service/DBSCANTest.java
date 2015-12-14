package cn.com.service;

import java.util.ArrayList;
import java.util.List;

import cn.com.main.ValidateMain;
import cn.com.model.Point;
import cn.com.util.TextUtil;
import junit.framework.TestCase;

public class DBSCANTest extends TestCase {

	public void testAlgorithm() {
		
		
		List<Point> points = TextUtil.getAllInformation("data/dataset1.dat");
		points = TextUtil.markTheList("data/dataset1-label.dat", points);
		List<List<Integer>> Cs;
		/*
		List<List<Integer>> Cs = DBSCAN.algorithm(points.toArray(new Point[points.size()]), 1263761, 2000,50);
		
		System.out.println("when the e is 1263761 and the MinPts is 2000 the number of C is : " + Cs.size());
		this.assertEquals(Cs.size(), 1);
		*/
		points = TextUtil.getAllInformation("data/testdata1.txt");
		points = TextUtil.markTheList("data/testdata1-label.txt", points);
		Point[] pointsArray = points.toArray(new Point[points.size()]);
		Cs = DBSCAN.algorithm(pointsArray, 1, 1,50);
		
		System.out.println("when the e is 1 and the MinPts is 1 the number of C is : " + Cs.size());
		double purity = ValidateMain.purityValidate(pointsArray,Cs);
		
		//计算F-score
		double FScore = ValidateMain.FScoreValidate(pointsArray,Cs);
		
		//数组前两位保存本次DBSCAN算法的两个参数e和MinPts的大小，最后一位保存的计算结果
		
		System.out.println("when the e is " + 1 + " and the MinPts is  " + 1 +  "  the number of C is : " + Cs.size() + " the purity is :" + purity*100 + "%");
		System.out.println("when the e is " + 1 + " and the MinPts is  " + 1 +  "  the number of C is : " + Cs.size() + " the F-score is :" + FScore*100 + "%");
		this.assertEquals(Cs.size(), 3);
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
