package com.gyxsh.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordUtil {
	
	public static void createWord(Map dataMap,String templateName,String filePath,String fileName){
		try {
			//创建实例配置
			Configuration configuration=new Configuration();
			
			//设置编码
			configuration.setDefaultEncoding("utf-8");
			
			//ftl模板文件统一放至com.gyxsh.template包下面
			configuration.setClassForTemplateLoading(WordUtil.class, "/com/gyxsh/template/");
			
			//获取模板
			Template template=configuration.getTemplate(templateName);
			
			//输出文件
			File outFile=new File(filePath+File.separator+fileName);
			
			//如果存在目标文件夹则删除 包括其中的文件
			//如果输出目标文件夹不存在，则创建
			if(!outFile.getParentFile().exists()){
				outFile.getParentFile().mkdirs();
			}
			
			//将模板和数据模型合并生成文件
			Writer out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
			
			//生成文件
			template.process(dataMap, out);
			
			//关闭流
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 递归删除子目录及文件
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			//递归删除目录中的子目录下
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
	            }
	        }
	  }
		// 目录此时为空，可以删除
		return dir.delete();
    }
}
