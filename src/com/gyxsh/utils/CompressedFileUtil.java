package com.gyxsh.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class CompressedFileUtil {
	/*
	 * 生成压缩文件
	 */
	public static void compressedFile(String resourcesPath,String targetPath,String zipOnlyName){
		try{
			File resourcesFile=new File(resourcesPath);//源文件
			
			File targetFile=new File(targetPath);//目的
			
			//如果目的文件不存在，则新建
			if(!targetFile.exists()){
				targetFile.mkdirs();
			}
			
			FileOutputStream outputStream=new FileOutputStream(targetFile+File.separator+zipOnlyName);
			ZipOutputStream out =new ZipOutputStream(new BufferedOutputStream(outputStream));
			
			createCompressedFile(out,resourcesFile,"");
			 
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/*
	 * 生成压缩文件
	 * 	 如果是文件夹，则使用递归，进行文件遍历、压缩
     * 	 如果是文件，直接压缩
	 */
	public static void createCompressedFile(ZipOutputStream out, File file, String dir) throws Exception {
		 //如果当前的是文件夹，则进行进一步处理
        if(file.isDirectory()){
            //得到文件列表信息
            File[] files = file.listFiles();
            //将文件夹添加到下一级打包目录
            out.putNextEntry(new ZipEntry(dir+File.separator));

            dir = dir.length() == 0 ? "" : dir +File.separator;

            //循环将文件夹中的文件打包
            for(int i = 0 ; i < files.length ; i++){
                createCompressedFile(out, files[i], dir + files[i].getName()); //递归处理
            }
        }
        else{//当前的是文件，打包处理
            //文件输入流
            FileInputStream fis = new FileInputStream(file);

            out.putNextEntry(new ZipEntry(dir));
            //进行写操作
            int j =  0;
            byte[] buffer = new byte[1024];
            while((j = fis.read(buffer)) > 0){
                out.write(buffer,0,j);
            }
            //关闭输入流
            fis.close();
        }
	}
}
