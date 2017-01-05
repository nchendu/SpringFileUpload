package com.spring.boot.fileupload;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	public List<FileUploadResponse> getFiles(MultipartFile[] files) {
		java.util.List<FileUploadResponse> fileUploadResp=new ArrayList<FileUploadResponse>();
		for (int i = 0; i < files.length; i++) {
			if (null != files[i] && files[i].getSize() > 0&&"csv".equalsIgnoreCase(FilenameUtils.getExtension(files[i].getOriginalFilename()))){
				System.out.println(files[i].getOriginalFilename() + "==" + files[i].getSize());
			System.out.println(FilenameUtils.getExtension(files[i].getOriginalFilename())+"===New one=============>" + System.currentTimeMillis());
			fileUploadResp.add(CSVValidatorUtil.validCSV(files[i]));
			}
		}
		return fileUploadResp;
	}
}
