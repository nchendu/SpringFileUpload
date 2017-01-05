package com.spring.boot.fileupload;

import java.util.Iterator;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.XmlAwareFormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUploadTest {
	public static void main(String[] args) {
		 RestTemplate restTemplate = new RestTemplate();
//		 List<HttpMessageConverter<?>> mc = restTemplate.getMessageConverters();
//		 for (Iterator<HttpMessageConverter<?>> iterator = mc.iterator(); iterator.hasNext();) {
//		     HttpMessageConverter httpMessageConverter = (HttpMessageConverter<?>) iterator
//		           .next();
//		     if (httpMessageConverter instanceof XmlAwareFormHttpMessageConverter) iterator.remove();
//		 }
//		 mc.add(mhmc);
//		 restTemplate.setMessageConverters(mc);
//		 ResponseEntity<MultiValueMap<String, CommonsMultipartFile>> eresp = null;
//		 MultiValueMap<String, HttpEntity<FileSystemResource>> parts = new LinkedMultiValueMap<String, HttpEntity<FileSystemResource>>();
		 HttpHeaders fileHeaders = new HttpHeaders();
		 fileHeaders.add("Content-type",
		      MediaType.APPLICATION_OCTET_STREAM_VALUE);
		 FileSystemResource r = new FileSystemResource("a.txt");
		 HttpEntity<FileSystemResource> sample_file = new HttpEntity<FileSystemResource>(
		      r, fileHeaders);
		 parts.add("file1", sample_file);
		 HttpHeaders reqheaders = new HttpHeaders();
		 // Set any custom HTTP request headers you need here
		 reqheaders.set("Custom-App-Header", "custome-value");
		 HttpEntity<MultiValueMap<String, HttpEntity<FileSystemResource>>> ereq = new HttpEntity<MultiValueMap<String, HttpEntity<FileSystemResource>>>(
		                 parts, reqheaders)
	}
}
 