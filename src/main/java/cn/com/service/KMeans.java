package cn.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.com.model.Point;

public class KMeans {
	
	private int k; //簇的数量
	private int m; //迭代次数
	private int dataSetLength; //数据元素个数，即数据集的长度
	private List<Point> dataSet; //数据集
	private List<Point> center; //簇中心集合
	private List<List<Integer>> cluster; //簇
	private List<Double> jc; //每个簇的簇内误差平方和
	private Random random;
	
	public KMeans(int k, List<Point> dataSet) {
		if(k<=0) {
			k=1;
		}
		this.k = k;
		this.dataSet = dataSet;
	}
	
	
	public void init() {
		m = 0;
		random = new Random();
		dataSetLength = dataSet.size();
		if(k>dataSetLength) {
			k = dataSetLength;
		}
		center = initCenter();
		cluster = initCluster();
		jc = new ArrayList<Double>();
	}
	
	/*
	 * 初始化k个空簇
	 */
	public List<Point> initCenter() {
		List<Point> center = new ArrayList<Point>();
		int[] randoms = new int[k];
		boolean flag;
		int temp = random.nextInt(dataSetLength);
		randoms[0]=temp;
		for(int i=0; i<k; i++) {
			flag = true;
			while(flag) {
				temp = random.nextInt(dataSetLength);
				int j=0;
				while(j<i) {
					if(temp == randoms[j]) {
						break;
					}
					j++;
				}
				if(j == i) {
					flag = false;
				}
			}
			randoms[i] = temp;
		}
		for(int i=0; i<k; i++) {
			center.add(dataSet.get(randoms[i]));
		}
		return center;
	}
	
	/**
	 * 初始化k个空簇
	 */
	public List<List<Integer>> initCluster() {
		List<List<Integer>> cluster = new ArrayList<List<Integer>>();
		for (int i = 0; i < k; i++) {  
            cluster.add(new ArrayList<Integer>());  
        } 
		return cluster;
	}
	
	/** 
     * 计算两个点之间的距离 
     */  
    public double distance(Point p1, Point p2) {  
        double distance = 0.0;  
        double x = p1.getX() - p2.getX();
        double y = p1.getY() - p2.getY();
        double z = x * x + y * y;  
        distance = Math.sqrt(z);   
        return distance;  
    }  
    
    /** 
     * 获取距离集合中最小距离的位置 
     */  
    public int minDistance(double[] distances) {  
        double minDistance = distances[0];  
        int minLocation = 0;  
        for (int i = 1; i < distances.length; i++) {  
            if (distances[i] < minDistance) {  
                minDistance = distances[i];  
                minLocation = i;  
            } else if (distances[i] == minDistance) // 如果相等，随机返回一个位置  
            {  
                if (random.nextInt(10) < 5) {  
                    minLocation = i;  
                }  
            }  
        }  
        return minLocation;  
    }  
   
    /** 
     * 核心，将当前元素放到最小距离中心相关的簇中 
     */  
    public void clusterSet() {  
        double[] distances = new double[k];  
        for (int i = 0; i < dataSetLength; i++) {  
            for (int j = 0; j < k; j++) {  
                distances[j] = distance(dataSet.get(i), center.get(j));  
            }  
            int minLocation = minDistance(distances);  
            cluster.get(minLocation).add(i);// 核心，将当前元素放到最小距离中心相关的簇中  
        }  
    }  
    
    /** 
     * 求两点误差平方的方法 
     * @return 误差平方 
     */  
    public double errorSquare(Point p1, Point p2) {  
        double x = p1.getX() - p2.getX(); 
        double y = p1.getY() - p2.getY(); 
  
        double errSquare = x * x + y * y;  
  
        return errSquare;  
    }  
    
    /** 
     * 计算误差平方和
     */  
    public void countRule() {  
        double jcF = 0;  
        for (int i = 0; i < cluster.size(); i++) {  
            for (int j = 0; j < cluster.get(i).size(); j++) {  
                jcF += errorSquare(dataSet.get(cluster.get(i).get(j)), center.get(i));  
  
            }  
        }  
        jc.add(jcF);  
    }  
  
    /** 
     * 设置新的簇中心方法 
     */  
    public void setNewCenter() {  
        for (int i = 0; i < k; i++) {  
            int n = cluster.get(i).size();  
            if (n != 0) {  
                Point newCenter = new Point();  
                double x=0.0;
                double y=0.0;
                for (int j = 0; j < n; j++) {  
                    x += dataSet.get(cluster.get(i).get(j)).getX();  
                    y += dataSet.get(cluster.get(i).get(j)).getY();
                }  
                // 设置一个平均值  
                newCenter.setX(x/n);  
                newCenter.setY(y/n);
                center.set(i, newCenter);  
            }  
        }  
    } 
    
    /** 
     * Kmeans算法核心过程方法 
     */  
    public List<List<Integer>> kmeans() {  
        init();  
        // 循环分组，直到误差不变为止  
        while (true) {  
            clusterSet();  
            countRule();  
            if (m != 0) {  
                if (jc.get(m) - jc.get(m - 1) == 0) {  
                    break;  
                }  
            }  
  
            setNewCenter();  
            m++;  
            cluster.clear();  
            cluster = initCluster();  
        }  
        return cluster;
    } 
    
}
