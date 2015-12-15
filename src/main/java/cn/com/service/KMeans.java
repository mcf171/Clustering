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
	private List<List<Point>> cluster; //簇
	private List jc; //每个簇的簇内误差平方和
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
		jc = new ArrayList();
	}
	
	/*
	 * 
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
	
	public List<List<Point>> initCluster() {
		List<List<Point>> cluster = new ArrayList<List<Point>>();
		return cluster;
	}
}