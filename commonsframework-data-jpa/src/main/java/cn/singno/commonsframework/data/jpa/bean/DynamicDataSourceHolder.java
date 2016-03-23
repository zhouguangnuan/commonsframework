package cn.singno.commonsframework.data.jpa.bean;

public class DynamicDataSourceHolder {
        
        
        public enum DataSourceType {
                /**
                 * 写
                 */
                WRITE,
                
                /**
                 * 读
                 */
                READ,
                
                /**
                 * 默认
                 */
                DEFAULT
                ;
        }

        private static final ThreadLocal<DataSourceType> holder = new ThreadLocal<DataSourceType>();

        
        public static void setWrite(){
                holder.set(DataSourceType.WRITE);
        }
        
        public static void setRead(){
                holder.set(DataSourceType.READ);
        }
        
        public static void clear(){
                holder.set(null);
        }
        
        public static boolean isNull(){
                return holder.get() == null;
        }
        
        /**
         * 
         * <p>描述：写?</p>
         * <pre>
         *    
         * </pre>
         * @return
         */
        public static boolean isWrite(){
                return holder.get() == DataSourceType.WRITE;
        }
        
        /**
         * 
         * <p>描述：读?</p>
         * <pre>
         *    
         * </pre>
         * @return
         */
        public static boolean isRead(){
                return holder.get() == DataSourceType.READ;
        }
        
        
        /**
         * 
         * <p>描述：获取数据源的类型</p>
         * <pre>
         *    获取数据源的类型，如果为空则返回类型默认
         * </pre>
         * @return
         */
        public static DataSourceType getDataSourceType(){
                DataSourceType dataType = holder.get();
                return dataType == null ? DataSourceType.DEFAULT : dataType;
        }
        
}
