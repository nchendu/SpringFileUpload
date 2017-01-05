package com.spring.boot.fileupload;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public @ResponseBody List<FileUploadResponse> handleFileUpload(@RequestParam("file") MultipartFile[] files,
                                   RedirectAttributes redirectAttributes) {
    	fileService.getFiles(files);
    	
     //   return "redirect:/";
    	return           fileService.getFiles(files);

    }

    @PostMapping("/fileUpload")
    public @ResponseBody List<FileUploadResponse> handleFileUploadResp(@RequestParam("file") MultipartFile[] files,
                                   RedirectAttributes redirectAttributes) {
        return fileService.getFiles(files);
    }


}
