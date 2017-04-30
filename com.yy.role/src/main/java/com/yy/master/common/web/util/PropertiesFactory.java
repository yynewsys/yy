package com.yy.master.common.web.util;


import com.yy.master.common.web.Dto;
import com.yy.master.common.web.impl.BaseDto;

import java.io.InputStream;


/**
 * Properties文件静态工厂
 *
 * @author zhangyao
 * @since 2015-12-7
 */
public class PropertiesFactory {
	/**
	 * 属性文件实例容器
	 */
	private static Dto container = new BaseDto();

	static {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = PropertiesFactory.class.getClassLoader();
		}
		// 加载属性文件global.g4.properties
		try {
			InputStream is = classLoader.getResourceAsStream("global.g4.properties");
			PropertiesHelper ph = new PropertiesHelper(is);
			container.put(PropertiesFile.G4, ph);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 加载属性文件ExportProp.properties
		try {
			InputStream is = classLoader.getResourceAsStream("ExportProp.properties");
			PropertiesHelper ph = new PropertiesHelper(is);
			container.put(PropertiesFile.EP, ph);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 加载属性文件global.app.properties
		try {
			//InputStream is = classLoader.getResourceAsStream("g4server.properties");
			//PropertiesHelper ph = new PropertiesHelper(is);
			//container.put(PropertiesFile.G4SERVER, ph);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			InputStream is = classLoader.getResourceAsStream("num.properties");
			PropertiesHelper ph = new PropertiesHelper(is);
			container.put(PropertiesFile.NUM, ph);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 获取属性文件实例
	 * 
	 * @param pFile
	 *            文件类型
	 * @return 返回属性文件实例
	 */
	public static PropertiesHelper getPropertiesHelper(String pFile) {
		PropertiesHelper ph = (PropertiesHelper) container.get(pFile);
		return ph;
	}
}
