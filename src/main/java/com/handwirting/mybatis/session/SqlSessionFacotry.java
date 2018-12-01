package com.handwirting.mybatis.session;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.handwirting.mybatis.config.Configuration;
import com.handwirting.mybatis.config.MapperStatement;

/**
 * @author: tanglong
 * 
 * 1、初始化配置信息
 * 2、生产sqlSession
 */
public class SqlSessionFacotry {
	
	private Configuration config;
	
	public SqlSessionFacotry() {
		
		//1、加载数据库连接信息
		loadDbInfo();
		
		//2、加载maper.xml文件中信息
		loadMappersInfo();
	}
	
	//记录maper.xml文件的位置
	public static final String MAPPER_CONFIG_LOCATION = "mappers";
	//记录数据库连接信息文件的存放位置
	public static final String DB_CONFIG_FILE = "database.properties";
	
	//1、加载数据库配置信息
	private void loadDbInfo() {
		
		//加载数据库信息的配置文件
		InputStream dbInputStream = SqlSessionFacotry.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE);

		Properties p = new Properties();
		try {
			//将配置信息写入properties对象
			p.load(dbInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//将数据库配置信息写入config对象
		config = new Configuration();
		config.setJdbcDriver(p.get("jdbc.driver").toString());
		config.setJdbcUrl(p.get("jdbc.url").toString());
		config.setJdbcUserName(p.get("jdbc.username").toString());
		config.setJdbcPassword(p.get("jdbc.password").toString());	
	}
	
	//2、加载指定文件夹下的所有mapper.xml
	private void loadMappersInfo() {
		URL resources = null;
		resources = SqlSessionFacotry.class.getClassLoader().getResource(MAPPER_CONFIG_LOCATION);
		
		//获取指定文件夹信息
		File mappers = new File(resources.getFile());
		if(mappers.isDirectory()) {
			File[] listFiles = mappers.listFiles();
			//遍历文件夹下的所有的mapper.xml，解析信息，注册到config对象中
			for( File file:listFiles) {
				loadMapperInfo(file);
			}
		}
	}
	
	//加载指定的mapper.xml文件
	private void loadMapperInfo(File file) {
		
		//创建SaxReader 对象
		SAXReader reader = new SAXReader();
		reader.setEncoding("utf-8");
		//通过read方法读取一个文件转成document对象
		Document doucment = null;
		try {
			doucment = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//继续获取mapper跟节点元素对象 <mapper>
		Element root = doucment.getRootElement();
		
		//获取命名空间
		String namespace = root.attribute("namespace").getData().toString();
		
		//获取select子节点列表
		List<Element> selects = root.elements("select");
		
		//遍历select节点，将信息记录到MapperStatement对象，并登记到config对象中
		for(Element element:selects) {
			MapperStatement ms = new MapperStatement();
			
			//读取id属性
			String id = element.attribute("id").getData().toString();
			//读取resultType属性
			String resultType = element.attribute("resultType").getData().toString();
			//读取sql语句
			String sql = element.getData().toString();
			String resourceId = namespace+"."+id;
			
			//给MapperStatement 对象赋值
			ms.setNamespace(namespace);
			ms.setResultType(resultType);
			ms.setSourceId(resourceId);
			ms.setSql(sql);
			ms.setNamespace(namespace);
			
			//注册到config对象中
			config.getMapperStatements().put(resourceId, ms);
		}	
	}
	
	
	//生产sqlSession
	public SqlSession openSession() {
		return new DefaultSqlSession(config);
	}
}
