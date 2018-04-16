package com.icloud.web.util;

import com.icloud.common.DateTools;

/**
 * @filename      : OrderUtil.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年9月17日 下午9:44:38   
 * @copyright     : zhumeng.com@hyzy-activities
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public class OrderUtil {

	/**
	 * 产生订单编号
	 * @return
	 */
	public static String bulidOrderNo(String lastString) {
		return "S" + DateTools.convertDateToString("yyyyMMdd") + lastString;
	}
	
	/**
	 * 产生订单编号
	 * @return
	 */
	public static String bulidRefunNo(String lastString) {
		return "R" + DateTools.convertDateToString("yyyyMMdd") + lastString;
	}
}
