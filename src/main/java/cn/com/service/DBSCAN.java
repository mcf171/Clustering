package cn.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.com.model.Point;

public class DBSCAN {

	/***
	 * 运行DBSCAN算法
	 * @param points 所有的点
	 * @param e	最短距离
	 * @param MinPts 最小集合数量
	 * @param maxClusterNumber 聚类最大数量
	 * @return	通过e和MinPts两个参数下的聚类结果，如果聚类数量大于maxClusterNumber则返回null
	 */
	public static List<List<Integer>> algorithm(Point[] points, double e, int MinPts,int maxClusterNumber){
		
		List<List<Integer>> Cs = new ArrayList<List<Integer>>();
	
		List<Integer> unvisitedList = new ArrayList<Integer>();
		
		for(int i = 0 ; i < points.length; i ++){
			unvisitedList.add(i);
		}
		
		int labelNumber = 0;
		int markCount = 0;
		
        while(true){
        	
        	Random random = new Random();
        	
            int index = random.nextInt(unvisitedList.size())%(unvisitedList.size()-0+1) + 0;
            int s = unvisitedList.get(index);

            if(points[s].getStatus().equals("unvisited")){
			    points[s].setStatus("visited");
			    unvisitedList.remove(index);
			    markCount ++;
			    List<Integer> N = getN(points, points[s], e, MinPts, s);
			    if(N.size() >= MinPts){
			    	List<Integer> C = new ArrayList<Integer>();
			    	C.add(s);
			    	points[s].setLabel("" + labelNumber);
			    	for(int i = 0 ; i < N.size() ; i ++){
			    		int indexP = N.get(i);
			    		if(points[indexP].getStatus().equals("unvisited")){
			    			unvisitedList.remove(unvisitedList.indexOf(indexP));
			    			markCount++;
			    			points[indexP].setStatus("visited");
			    			List<Integer> eNumber = getN(points, points[indexP], e, MinPts, indexP);
			    			if(eNumber.size() >= MinPts)
			    				N.addAll(eNumber);
			    			
			    		}
			    		if(points[indexP].getLabel().equals("unknown")){
			    			C.add(indexP);
			    			points[indexP].setLabel("" + labelNumber);
			    		}
			    	}
			    	Cs.add(C);
			    	labelNumber++;
			    }else{
			    	points[s].setLabel("noise");
			    }
			    if(markCount >= points.length)
			    	break;
			    /*
			    if(Cs.size() >= maxClusterNumber){
			    	Cs = null;
			    	break;
			    	
			    }
			    */
            }
		}
		return Cs;
	}
	
	private static List<Integer> getN(Point[] points, Point p, double e, int minPts,int s){
		
		List<Integer> lists = new ArrayList<Integer>();
		
		
		for(int i = 0 ; i < points.length ; i ++){
			
			Point point = points[i];
			
			if(calculateDist(point, p) <= e && i!=s){
				lists.add(i);
			}
		}
		
		return lists;
	}
	
	public static double calculateDist(Point target, Point source){
		
		double distance = Math.pow(target.getX() - source.getX(),2) + Math.pow(target.getY() - source.getY(),2);
		distance = Math.sqrt(distance);
		return distance;
	}
}
