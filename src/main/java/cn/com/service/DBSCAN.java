package cn.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.com.model.Point;

public class DBSCAN {

	public static List<List<Integer>> algorithm(Point[] points, double e, int MinPts){
		
		List<List<Integer>> Cs = new ArrayList<List<Integer>>();
		
		int labelNumber = 0;
		int markCount = 0;
        while(true){
        	Random random = new Random();
            int s = random.nextInt(points.length)%(points.length-0+1) + 0;
        
		    Point p = points[s];
		    p.setStatus("visited");
		    
		    markCount ++;
		    List<Integer> N = getN(points, p, e, MinPts);
		    if(N.size() >= MinPts){
		    	List<Integer> C = new ArrayList<Integer>();
		    	for(int i = 0 ; i < N.size() ; i ++){
		    		int indexP = N.get(i);
		    		if(points[indexP].getStatus().equals("unvisited")){
		    			markCount++;
		    			points[indexP].setStatus("visited");
		    			List<Integer> eNumber = getN(points, points[indexP], e, MinPts);
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
		    	p.setLabel("noise");
		    }
		    if(markCount >= points.length)
		    	break;
		}
		return Cs;
	}
	
	private static List<Integer> getN(Point[] points, Point p, double e, int minPts){
		
		List<Integer> lists = new ArrayList<Integer>();
		
		
		for(int i = 0 ; i < points.length ; i ++){
			
			Point point = points[i];
			
			if(calculateDist(point, p) <= e){
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
