package cn.com.util;

import java.util.List;

import cn.com.model.Point;
import cn.com.service.DBSCAN;

public class DataUtil {

	public static List<Point> normalization(List<Point> lists){
		for(Point point :lists){
			
			point.setX((point.getX() - TextUtil.minX) / (TextUtil.maxX - TextUtil.minX));
			point.setY((point.getY() - TextUtil.minY) / (TextUtil.maxY - TextUtil.minY));
		}
		TextUtil.maxDistance = 0;
		TextUtil.minDistance = Double.MAX_VALUE;
		for(int i = 0 ; i < lists.size() - 1; i ++)
			for(int j = i + 1 ; j < lists.size(); j ++){
				Point targetPoint = lists.get(i);
				Point sourcePoint = lists.get(j);
				double distance = DBSCAN.calculateDist(targetPoint, sourcePoint);
				if(distance > TextUtil.maxDistance)
					TextUtil.maxDistance = distance;
				if(distance < TextUtil.minDistance)
					TextUtil.minDistance = distance;
			}
		
        return lists;
	}
}
