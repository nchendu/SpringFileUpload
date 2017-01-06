package com.spring.boot.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	String PRIMARY_SOURCE="D:\\A";
	String SECONDARY_SOURCE="D:\\B";
	public List<FileUploadResponse> getFiles(MultipartFile[] files)   {
		System.out.println("&&&&&&&&&&&&&&&&&&&&===>"+files.length);
		
		List<MultipartFile> multipartListFile = Arrays.asList(files);  
		java.util.List<FileUploadResponse> fileUploadResp=new ArrayList<FileUploadResponse>();
		List<MultipartFile> tramsferFiles=new ArrayList<MultipartFile>() ;
		FileUploadResponse fileResp=null;
		for(MultipartFile mf:multipartListFile){
			if(null!=mf&&mf.getSize()>0&&"csv".equalsIgnoreCase(FilenameUtils.getExtension(mf.getOriginalFilename())))
				System.out.println(mf.getOriginalFilename() + "==" + mf.getSize());
			System.out.println(FilenameUtils.getExtension(mf.getOriginalFilename())+"===New one=============>" + System.currentTimeMillis());
			fileResp=CSVValidatorUtil.validCSV(mf);
			if(fileResp.isValid()){
				tramsferFiles.add(mf);
			}
			fileUploadResp.add(fileResp);
			
			}
		try{
		copyFilesToSouceLocation(tramsferFiles,PRIMARY_SOURCE,SECONDARY_SOURCE);
		}catch(Exception e){
			e.printStackTrace();
		}
//		for (int i = 0; i < files.length; i++) {
//			if (null != files[i] && files[i].getSize() > 0&&"csv".equalsIgnoreCase(FilenameUtils.getExtension(files[i].getOriginalFilename()))){
//				System.out.println(files[i].getOriginalFilename() + "==" + files[i].getSize());
//			System.out.println(FilenameUtils.getExtension(files[i].getOriginalFilename())+"===New one=============>" + System.currentTimeMillis());
//			fileResp=CSVValidatorUtil.validCSV(files[i]);
//			if(fileResp.isValid()){
//			}
//			fileUploadResp.add(fileResp);
//			}
//		}
		
		return fileUploadResp;
	}
	private void copyFilesToSouceLocation(List<MultipartFile> multipartListFile,String primaryLoc,String SecondaryLoc) throws Exception{
		 File file = new File(primaryLoc);
	        if (!file.exists()) {
	            if (file.mkdir()) {
	            	file.setExecutable(true);
	      	      file.setReadable(true);
	      	      file.setWritable(true);
	            }}
	        if(file.exists()){
	            	for(MultipartFile mf:multipartListFile){
	            		System.out.println("**************>>>"+file.getAbsolutePath());
	            	 FileUtils.copyFileToDirectory(convert(mf), file);
	            	}
	            } else {
	                System.out.println("Failed to create directory!");
	            }
	        FileUtils.copyDirectory(file, createDirectory(SecondaryLoc));
	}
	public File convert(MultipartFile file) throws Exception
	{    
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile(); 
	    FileOutputStream fos = new FileOutputStream(convFile); 
	    fos.write(file.getBytes());
	    fos.close(); 
	    return convFile;
	}
	public File createDirectory(String location){
		File file = new File(location);
        if (!file.exists()) {
            if (file.mkdir()) {
            	file.setExecutable(true);
      	      file.setReadable(true);
      	      file.setWritable(true);
            }}
		return file;
	}
}
