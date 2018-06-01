package oa.cfg;
/**
 * 对应的配置对象（defalut.properties）
 * @author slj
 *
 */
public class Configuration{
	private static int pageSize;
    static{
    	pageSize=3;
    }
	public static int getPageSize() {
		return pageSize;
	}

	
}
