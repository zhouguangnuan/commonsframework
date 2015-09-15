/*
 * @(#)RadarModel.java 2014-9-10 下午1:40:38
 * Copyright 2014 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.singno.commonsframework.bean;

import org.springframework.util.Assert;

import cn.singno.commonsframework.utils.MapUtils;
import cn.singno.commonsframework.utils.MapUtils.BoundaryCoord;


/**
 * <p>File：RadarModel.java</p>
 * <p>Title: 雷达模型</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-9-10 下午2:52:06</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class RadarModel
{
	private int					range;							// 扫描半径（单位米）
	private double				targetGpsX;						// 目标经度
	private double				targetGpsY;						// 目标维度

	// 雷达扫描范围边界坐标
	private Double				minGpsX;							// 最小经度
	private Double				maxGpsX;						// 最大经度
	private Double				minGpsY;							// 最小维度
	private Double				maxGpsY;						// 最大维度

	private RadarModel(Double gpsX, Double gpsY, Integer range)
	{
		Assert.noNullElements(new Object[]{gpsX, gpsY, range}, "参数不能为空!");
		BoundaryCoord boundaryCoord = MapUtils.getBoundaryCoord(gpsY, gpsX, range);
		this.range = range;
		this.targetGpsX = gpsX;
		this.targetGpsY = gpsY;
		this.minGpsX = boundaryCoord.getMinLng();
		this.maxGpsX = boundaryCoord.getMaxLng();
		this.minGpsY = boundaryCoord.getMinLat();
		this.maxGpsY = boundaryCoord.getMaxLat();
	}
	
	private RadarModel(Double gpsX, Double gpsY)
	{
		this.targetGpsX = gpsX;
		this.targetGpsY = gpsY;
	}
	
	/**
	 * 根据坐标和半径，初始化雷达信息
	 * 
	 * @param gpsX 	目标经度
	 * @param gpsY	目标维度
	 * @param range	扫描半径（单位米）
	 */
	public static RadarModel initialize(Double gpsX, Double gpsY, Integer range)
	{
		if ((null==gpsX || null==gpsY) && null==range)
		{
			return null;
		}
		else if (!(null==gpsX || null==gpsY) &&  null==range)
		{
			return new RadarModel(gpsX, gpsY);
		}
		return new RadarModel(gpsX, gpsY, range);
	}

	/**
	 * 判断雷达扫描范围是否包含目标坐标
	 * 
	 * @param gpsX
	 * @param gpsY
	 * @return
	 * @author 周光暖
	 */
	public boolean containTarget(Double gpsX, Double gpsY)
	{
		return this.getDistance(gpsX, gpsY) < this.range;
	}
	
	/**
	 * 计算目标坐标距离雷达中心坐标距离
	 * @param gpsX
	 * @param gpsY
	 * @return
	 * @author 周光暖
	 */
	public double getDistance(Double gpsX, Double gpsY)
	{
		Assert.noNullElements(new Object[]{gpsX, gpsY}, "参数不能为空!");
		return MapUtils.getDistance(this.targetGpsY, this.targetGpsX, gpsY, gpsX);
	}

	public boolean containTarget(String gpsX, String gpsY)
	{
		try
		{
			return this.containTarget(Double.valueOf(gpsX), Double.valueOf(gpsY));
		} catch (NumberFormatException e)
		{
			return false;
		}
	}

	public String getMinGpsX()
	{
		return minGpsX.toString();
	}

	public String getMaxGpsX()
	{
		return maxGpsX.toString();
	}

	public String getMinGpsY()
	{
		return minGpsY.toString();
	}

	public String getMaxGpsY()
	{
		return maxGpsY.toString();
	}

	public double getTargetGpsX()
	{
		return targetGpsX;
	}

	public void setTargetGpsX(double targetGpsX)
	{
		this.targetGpsX = targetGpsX;
	}

	public double getTargetGpsY()
	{
		return targetGpsY;
	}

	public void setTargetGpsY(double targetGpsY)
	{
		this.targetGpsY = targetGpsY;
	}

	/**
	 * 判断雷达模型是否被设置过扫描范围
	 * @return
	 * @author 周光暖
	 */
	public boolean setedRange()
	{
		return this.range<=0 ? false : true;
	}
}
