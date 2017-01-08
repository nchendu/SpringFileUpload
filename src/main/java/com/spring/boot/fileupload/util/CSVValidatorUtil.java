package com.spring.boot.fileupload.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.fileupload.bean.FileUploadResponse;

public class CSVValidatorUtil {
	private static final String[] FILE_HEADER_MAPPING = { "LABEL", "segmentId", "PROMOTION", "name", "description",
			"pathvar" };

	public static FileUploadResponse validCSV(MultipartFile csvFile) {
		boolean isValidCSV = true;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		int recordLength = 0;
		FileUploadResponse fileUploadResponse = null;
		try {
			fileUploadResponse = new FileUploadResponse();
			fileUploadResponse.setFileName(csvFile.getOriginalFilename());
			fileReader = new FileReader(multipartToFile(csvFile));
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			List csvRecords = csvFileParser.getRecords();
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord record = (CSVRecord) csvRecords.get(i);
				System.out.println(csvFile.getName() + "<===record.size()===>" + record.size());
				if (record.size() != 6) {
					recordLength = record.size();
					isValidCSV = false;
					break;
				}
				if (isValidCSV) {
					fileUploadResponse.setComments("is ValidFile");
					fileUploadResponse.setValid(true);
				}
			}
		} catch (Exception e) {
			fileUploadResponse.setComments("Not a ValidFile because of column lenth is==>" + recordLength);
			fileUploadResponse.setValid(false);
		}
		return fileUploadResponse;
	}

	public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}
}
