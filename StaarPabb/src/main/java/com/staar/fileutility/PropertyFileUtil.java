package com.staar.fileutility;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Ashutosh
 * this class helps extract configuration data fro mexcel file
 * 
 */
public class PropertyFileUtil {
	
	/**
	 * this method helps to get configuration data from property file
	 * @author Ashutosh
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	public String getDataFromProperty(String key) throws Throwable {
		FileInputStream fis=new FileInputStream("./testdata/configdata.properties");
		Properties p=new Properties();
		p.load(fis);
		return p.getProperty(key);
	}
}
