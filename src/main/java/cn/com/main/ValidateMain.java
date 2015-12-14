package cn.com.main;

import java.util.ArrayList;
import java.util.List;

import cn.com.model.Point;
import cn.com.service.DBSCAN;
import cn.com.util.EXCELUtil;
import cn.com.util.TextUtil;

public class ValidateMain {

	public static void main(String[] args) {
		
		//从文件中读取所有的点
		List<Point> points = TextUtil.getAllInformation("data/dataset1.dat");
		
		//文类标文件中给所有的点打上正确的类标
		points = TextUtil.markTheList("data/dataset1-label.dat", points);
		
		//List<List<Integer>> Cs = DBSCAN.algorithm(points.toArray(new Point[points.size()]), 1263761, 2000);
		
		//保存两种度量方式的结果
		List<Object[]> purityResults = new ArrayList<Object[]>();
		List<Object[]> FScoreResults = new ArrayList<Object[]>();
		
		//将List<Poit> 转换为数组
		Point[] pointsArray = points.toArray(new Point[points.size()]);

		//每次单独调整最小距离和最小包含个数也就是DBSCAN的两个变量
		for(int i = 1 ; i < TextUtil.maxDistance + 1; i = i + ((Double)(TextUtil.maxDistance / points.size())).intValue())
			for(int j = 1 ; j < points.size();){
				
				initPoint(pointsArray);
				//DBSCAN算法
				List<List<Integer>> Cs = DBSCAN.algorithm(pointsArray, i, j,50);
				
				//当DBSCAN返回有效的聚类并且聚类的个数至少为一个时进行据算purity和F-score
				if(Cs != null && Cs.size() >= 1){
					
					//计算purity
					double purity = purityValidate(pointsArray,Cs);
					
					//计算F-score
					double FScore = FScoreValidate(pointsArray,Cs);
					
					//数组前两位保存本次DBSCAN算法的两个参数e和MinPts的大小，最后一位保存的计算结果
					Object[] purityResult = new Object[3];
					Object[] FscoreResult = new Object[3];
					
					FscoreResult[0] = purityResult[0] = i;
					FscoreResult[1] = purityResult[1] = j;
					
					purityResult[2] = purity;
					FscoreResult[2] = FScore;
					
					purityResults.add(purityResult);
					FScoreResults.add(FscoreResult);
					System.out.println("when the e is " + i + " and the MinPts is  " + j +  "  the number of C is : " + Cs.size() + " the purity is :" + purity*100 + "%");
					System.out.println("when the e is " + i + " and the MinPts is  " + j +  "  the number of C is : " + Cs.size() + " the F-score is :" + FScore*100 + "%");
				}
				
				//考虑当MinPts在1-10变化时对于purity和F-score计算很敏感，所以在1-10时每次增加1，大于10时，每次增加20
				if(j<10)
					j = j+1;
				else
					j = j +20;
			}
		
			//将所有的结果保存为excel文件
			EXCELUtil.saveAsEXCEL(purityResults, 0, "purityResult", 0);
			EXCELUtil.saveAsEXCEL(FScoreResults, 0, "FScoreResult", 0);
		
	}
	
	public static void initPoint(Point[] points){
		
		for(Point point : points){
			point.setStatus("unvisited");
            point.setLabel("unknown");
		}
	}
	
	/**
	 * 计算purity
	 * @param points 所有有标的点
	 * @param clusters	聚类的结果
	 * @return
	 */
	public static double purityValidate(Point[] points, List<List<Integer>> clusters){
		
		double purity = 0;
		
		for(List<Integer> indexs : clusters){
			
			double[] countLabel = new double[20];
			for(int index : indexs){
				countLabel[Integer.parseInt(points[index].getTrueLabel())]++;
			}
			int maxLabelIndex = 0;
			for(int i = 0 ;  i< 20 ; i ++)
				if(countLabel[i] > countLabel[maxLabelIndex])
					maxLabelIndex = i; 
			purity += countLabel[maxLabelIndex]/points.length;
		}
		
		return purity;
	}

	/**
	 * 计算F-score
	 * @param points 所有有标的点
	 * @param clusters	聚类的结果
	 * @return
	 */
	public static double FScoreValidate(Point[] points,List<List<Integer>> clusters){
		
		double FScore = 0;
		
		double[] correctNumber = new double[20];
		for(Point point : points){
			
			correctNumber[Integer.parseInt(point.getTrueLabel())] ++;
		}
		
		for(List<Integer> indexs : clusters){
			
			double[] countLabel = new double[20];
			for(int index : indexs){
				countLabel[Integer.parseInt(points[index].getTrueLabel())]++;
			}
			int maxLabelIndex = 0;
			for(int i = 0 ;  i< 20 ; i ++)
				if(countLabel[i] > countLabel[maxLabelIndex])
					maxLabelIndex = i; 
			
			double r = countLabel[maxLabelIndex]/correctNumber[maxLabelIndex];
			double p = countLabel[maxLabelIndex]/indexs.size();
			
			double F_i = 2*(p*r)/(p+r);
			
			FScore += F_i;
		}
		
		return FScore/clusters.size();
	}
}
