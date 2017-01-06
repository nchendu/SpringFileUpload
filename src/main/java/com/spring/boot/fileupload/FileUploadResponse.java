package com.spring.boot.fileupload;

import org.springframework.stereotype.Component;

@Component
public class FileUploadResponse {
	private String fileName;
	private boolean valid;
	private String comments;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
