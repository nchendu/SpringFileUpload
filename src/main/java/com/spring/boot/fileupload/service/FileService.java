package com.spring.boot.fileupload.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.fileupload.bean.FileUploadResponse;
import com.spring.boot.fileupload.util.CSVValidatorUtil;

@Service
public class FileService {
	String PRIMARY_SOURCE = "D:\\OLD_FILES";
	String SECONDARY_SOURCE = "D:\\NEW_FILES";

	public List<FileUploadResponse> getFiles(MultipartFile[] files) {

		List<MultipartFile> multipartListFile = Arrays.asList(files);
		java.util.List<FileUploadResponse> fileUploadResp = new ArrayList<FileUploadResponse>();
		List<MultipartFile> tramsferFiles = new ArrayList<MultipartFile>();
		FileUploadResponse fileResp = null;
		for (MultipartFile mf : multipartListFile) {
			if (null != mf && mf.getSize() > 0
					&& "csv".equalsIgnoreCase(FilenameUtils.getExtension(mf.getOriginalFilename())))
				System.out.println(mf.getOriginalFilename() + "==" + mf.getSize());
			System.out.println(FilenameUtils.getExtension(mf.getOriginalFilename()) + "===New one=============>"
					+ System.currentTimeMillis());
			fileResp = CSVValidatorUtil.validCSV(mf);
			if (fileResp.isValid()) {
				tramsferFiles.add(mf);
			}
			fileUploadResp.add(fileResp);

		}
		try {
			copyFilesToSouceLocation(tramsferFiles, PRIMARY_SOURCE, SECONDARY_SOURCE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileUploadResp;
	}

	private void copyFilesToSouceLocation(List<MultipartFile> multipartListFile, String primaryLoc, String SecondaryLoc)
			throws Exception {
		File file = new File(primaryLoc);
		if (!file.exists()) {
			if (file.mkdir()) {
				file.setExecutable(true);
				file.setReadable(true);
				file.setWritable(true);
			}
		}
		if (file.exists()) {
			for (MultipartFile mf : multipartListFile) {
				System.out.println("**************>>>" + file.getAbsolutePath());
				FileUtils.copyFileToDirectory(convert(mf), file);
			}
		} else {
			System.out.println("Failed to create directory!");
		}
		StringBuffer filePath=new StringBuffer(SecondaryLoc);
		Date CurrentDate=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		filePath=filePath.append("\\").append(sdf.format(CurrentDate)); 
		File parentFileLocation=createDirectory(filePath.toString());
		System.out.println("parentFileLocation===>"+parentFileLocation);
		createFileNested(file,parentFileLocation.getAbsolutePath(),CurrentDate);
	}
public void createFileNested(File file,String parentFileLocation,Date CurrentDate) throws IOException{
	
	StringBuffer nestatedFile=new StringBuffer(parentFileLocation );
	SimpleDateFormat sdf = new SimpleDateFormat("hhmmssSSSS");
    nestatedFile=nestatedFile.append("\\").append(sdf.format(CurrentDate)); 
 FileUtils.moveDirectory(file, new File(nestatedFile.toString()) ) ;
	
}
	public File createDirectory(String location) {
		File file = new File(location);
		if (! new File(location).exists()) {
			if (file.mkdir()) {
				file.setExecutable(true);
				file.setReadable(true);
				file.setWritable(true);
			}
		}
		return file;
	}
	
	public File convert(MultipartFile file) throws Exception {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}
