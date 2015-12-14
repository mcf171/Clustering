package cn.com.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import cn.com.model.Point;

public class TextUtil {

	public static double minX = Double.MAX_VALUE, maxX = 0, avgX = 0, minY = Double.MAX_VALUE, maxY = 0,avgY = 0;
	public static double maxDistance = 0;
	public static List<Point> getAllInformation(String fileName){
		
		List<Point> lists = new ArrayList<Point>();
		FileReader fr;
		
		try {
			fr = new FileReader(fileName);
			BufferedReader br=new BufferedReader(fr);
	        String line="";
	        String[] arrs=null;
	        
	        while ((line=br.readLine())!=null) {
	        	line = line.trim();
	            arrs=line.split("\\s+");
	            Point point = new Point();
	            
	            point.setX(Double.parseDouble(arrs[0]));
	            point.setY(Double.parseDouble(arrs[1]));
	            point.setStatus("unvisited");
	            point.setLabel("unknown");
	            
	            if(point.getX() < minX)
	            	minX = point.getX();
	            else if(point.getX() > maxX)
	            	maxX = point.getX();
	            
	            avgX += point.getX();
	            
	            if(point.getY() < minY)
	            	minY = point.getY();
	            else if(point.getY() > maxY)
	            	maxY = point.getY();
	            
	            avgY += point.getY();
	            lists.add(point);
	        }
	        avgX /= lists.size();
	        avgY /= lists.size();
	        maxDistance = Math.sqrt(Math.pow(maxX-minX, 2) + Math.pow(maxY - minY, 2));
	        System.out.println(" point x min value is :" + minX + ", max value is :" + maxX + ", avg value is :" + avgX);
	        System.out.println(" point y min value is :" + minY + ", max value is :" + maxY + ", avg value is :" + avgY);
	        
	        br.close();
	        fr.close();
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
		
		return lists;
	}
	
	public static List<Point> markTheList(String fileName,List<Point> sourceList){
		
		FileReader fr;
		
		try {
			fr = new FileReader(fileName);
			BufferedReader br=new BufferedReader(fr);
	        String line="";
	        int index = 0;
	        
	        while ((line=br.readLine())!=null) {
	        	line = line.trim();
	        	sourceList.get(index).setTrueLabel(line);
	        	index++;
	        }
	        
	        br.close();
	        fr.close();
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
		
		return sourceList;
	}
}
