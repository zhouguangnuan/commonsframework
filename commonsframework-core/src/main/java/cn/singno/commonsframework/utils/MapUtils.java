package cn.singno.commonsframework.utils;

/**
 * <p>File：MapUtils.java</p>
 * <p>Title: 经纬度工具类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-4 下午4:26:29</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@SuppressWarnings("all")
public class MapUtils
{
	/** 地球半径 */  							   
    private static final double EARTH_RADIUS = 6371000;  
    /** 范围半径 */  
    private double distance;  
    /** 范围（矩形）坐标点 */
    private BoundaryPoint boundaryPoint;
    /** 范围（矩形）坐标 */  
    private BoundaryCoord boundaryCoord;
    
    private MapUtils(double distance) {  
        this.distance = distance;  
    }  
  
    /**
     * @param lat	维度
     * @param lng	经度
     * @author 周光暖
     */
    private void setTarget(double lat, double lng) {  
    	
    	float delta = 111000; 
    	double lng1 = 0;
    	double lng2 = 0;
    	double lat1 = 0;
    	double lat2 = 0;
        if (lng != 0 && lat != 0) { 
            lng1 = lng - distance 
                    / Math.abs(Math.cos(Math.toRadians(lat)) * delta); 
            lng2 = lng + distance 
                    / Math.abs(Math.cos(Math.toRadians(lat)) * delta); 
            lat1 = lat - (distance / delta); 
            lat2 = lat + (distance / delta); 
            
        } else { 
            // TODO ZHCH 等于0时的计算公式 
            lng1 = lng - distance / delta; 
            lng2 = lng + distance / delta; 
            lat1 = lat - (distance / delta); 
            lat2 = lat + (distance / delta); 
        } 
        Location left_top = new Location(lat2, lng1);  
        Location right_top = new Location(lat2, lng2);  
        Location left_bottom = new Location(lat1, lng1);  
        Location right_bottom = new Location(lat1, lng2);  
        this.boundaryPoint = new BoundaryPoint(left_top, right_top, left_bottom, right_bottom);
        this.boundaryCoord = new BoundaryCoord(lng1, lng2, lat1, lat2);
        
    }  
  
    public static double hav(double theta) {  
        double s = Math.sin(theta / 2);  
        return s * s;  
    }  
  
    /**
     * 计算两点坐标的直线距离
     * @param lat0	维度
     * @param lng0	经度
     * @param lat1	维度
     * @param lng1	经度
     * @return
     * @author 周光暖
     */
    public static double getDistance(double lat0, double lng0, double lat1, double lng1) {  
        lat0 = Math.toRadians(lat0);  
        lat1 = Math.toRadians(lat1);  
        lng0 = Math.toRadians(lng0);  
        lng1 = Math.toRadians(lng1);  
  
        double dlng = Math.abs(lng0 - lng1);  
        double dlat = Math.abs(lat0 - lat1);  
        double h = hav(dlat) + Math.cos(lat0) * Math.cos(lat1) * hav(dlng);  
        double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(h));  
  
        return distance;  
    }  
    
    /**
     * 获得距离目标坐标指定距离的范围(矩形)坐标点
     * @param lat		维度
     * @param lng		经度
     * @param distance	距离（米）
     * @return
     * @author 周光暖
     */
    public static BoundaryPoint getBoundaryPoint(double lat, double lng, double distance) {  
        MapUtils llu = new MapUtils(distance);  
        llu.setTarget(lat, lng);  
        return llu.boundaryPoint;  
    }  
    
    /**
     * 获得距离目标坐标指定距离的范围(矩形)坐标
     * @param lat		维度
     * @param lng		经度
     * @param distance	距离（米）
     * @return
     * @author 周光暖
     */
    public static BoundaryCoord getBoundaryCoord(double lat, double lng, double distance)
    {
    	MapUtils llu = new MapUtils(distance);  
        llu.setTarget(lat, lng);  
    	return llu.boundaryCoord;
    }
    
    // ================================================================================================================
    
    /**
     * 坐标点
     */
    private class Location {  
        private double latitude;  
        private double longitude;  
      
        public Location(double latitude, double longitude) {  
            this.latitude = latitude;  
            this.longitude = longitude;  
        }  
        public double getLatitude() {  
            return latitude;  
        }  
        public void setLatitude(double latitude) {  
            this.latitude = latitude;  
        }  
        public double getLongitude() {  
            return longitude;  
        }  
        public void setLongitude(double longitude) {  
            this.longitude = longitude;  
        }  
    } 
    
    /**
     * 矩形范围（坐标点）
     */
    private class BoundaryPoint
    {
    	private Location[] locations;

		public BoundaryPoint(Location left_top, Location right_top, Location left_bottom, Location right_bottom)
		{
			this.locations = new Location[]{left_top, right_top, left_bottom, right_bottom};
		}
		
		public Location getLeftTopPoint()
		{
			return this.locations[0];
		}
		public Location getRightTopPoint()
		{
			return this.locations[1];
		}
		public Location getLeftBottomPoint()
		{
			return this.locations[2];
		}
		public Location getRightBottomPoint()
		{
			return this.locations[3];
		}
    }
    
    /**
     * 矩形范围（坐标）
     */
    public class BoundaryCoord
    {
    	private double minLng;// 最小经度
    	private double maxLng;// 最大经度
    	private double minLat;// 最小维度
    	private double maxLat;// 最大维度
    	
		public BoundaryCoord(double minLng, double maxLng, double minLat, double maxLat)
		{
			this.minLng = minLng;
			this.maxLng = maxLng;
			this.minLat = minLat;
			this.maxLat = maxLat;
		}
		
		public double getMinLng()
		{
			return minLng;
		}
		public void setMinLng(double minLng)
		{
			this.minLng = minLng;
		}
		public double getMaxLng()
		{
			return maxLng;
		}
		public void setMaxLng(double maxLng)
		{
			this.maxLng = maxLng;
		}
		public double getMinLat()
		{
			return minLat;
		}
		public void setMinLat(double minLat)
		{
			this.minLat = minLat;
		}
		public double getMaxLat()
		{
			return maxLat;
		}
		public void setMaxLat(double maxLat)
		{
			this.maxLat = maxLat;
		}
    }
    
    
    public static void main(String[] args) {  
    	
//    	map.put("longitude", 116.294891);
//    	map.put("latitude", 39.34767);
//    	map.put("distance", 10000);
    	
        double lat = 39.34767;
        double lng = 116.294891;  
        double distance = 10000;  
//        BoundaryPoint boundaryPoint = MapUtils.getBoundaryPoint(lat, lng, distance);  
//        System.out.println("latitude:" + boundaryPoint.getLeftTopPoint().getLatitude() + ", latitude:" + boundaryPoint.getLeftTopPoint().getLongitude() + "|: " + MapUtils.getDistance(lat, lng, boundaryPoint.getLeftTopPoint().getLatitude(), boundaryPoint.getLeftTopPoint().getLongitude()));  
//        System.out.println("latitude:" + boundaryPoint.getRightTopPoint().getLatitude() + ", latitude:" + boundaryPoint.getRightTopPoint().getLongitude() + "|: " + MapUtils.getDistance(lat, lng, boundaryPoint.getRightTopPoint().getLatitude(), boundaryPoint.getRightTopPoint().getLongitude()));  
//        System.out.println("latitude:" + boundaryPoint.getLeftBottomPoint().getLatitude() + ", latitude:" + boundaryPoint.getLeftBottomPoint().getLongitude() + "|: " + MapUtils.getDistance(lat, lng, boundaryPoint.getLeftBottomPoint().getLatitude(), boundaryPoint.getLeftBottomPoint().getLongitude()));  
//        System.out.println("latitude:" + boundaryPoint.getRightBottomPoint().getLatitude() + ", latitude:" + boundaryPoint.getRightBottomPoint().getLongitude() + "|: " + MapUtils.getDistance(lat, lng, boundaryPoint.getRightBottomPoint().getLatitude(), boundaryPoint.getRightBottomPoint().getLongitude()));  
        
//        latitude:39.437760090090094, latitude:116.17839212621963|: 14162.400568242796
//        latitude:39.437760090090094, latitude:116.41138987378038|: 14162.400568241252
//        latitude:39.25757990990991, latitude:116.17839212621963|: 14171.53228176471
//        latitude:39.25757990990991, latitude:116.41138987378038|: 14171.53228176316


        
        double y = 39.354812;
        double x = 116.281955;
        Double s = MapUtils.getDistance(lat, lng, y, x);
        System.out.println(s.intValue());// 1366

    }  
}  

