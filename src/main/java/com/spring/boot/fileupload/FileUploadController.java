package com.spring.boot.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

	@Autowired
	FileService fileService;

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {
		return "FileUpload";
	}

	@PostMapping("/")
	 @Consumes("multipart/mixed") 
	@Produces({MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile[] files,

			  RedirectAttributes redirectAttributes) {

		System.out.println("files length===>" + files.length);
		List<FileUploadResponse> fs=	fileService.getFiles(files);
		System.out.println("&*&*&**&&&**(");

		return  "SUCCESS";

	}

	@PostMapping("/fileUpload")
	public @ResponseBody List<FileUploadResponse> handleFileUploadResp(@RequestParam("file") MultipartFile[] files,
			RedirectAttributes redirectAttributes) {
		return fileService.getFiles(files);
	}
 
}
