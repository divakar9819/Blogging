package com.blog.Blogging.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.Blogging.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		//fileName
		String fileName = file.getOriginalFilename();
		
		//
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(fileName.substring(fileName.lastIndexOf(".")));
		
		
		//filePath
		String filePath = path + File.separator + fileName1;
		
		//make dire if dire is not exits
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		Files.copy(file.getInputStream(),Paths.get(filePath));
		
		return fileName1;
		
		
	}

	@Override
	public InputStream getResources(String path, String fileName) throws FileNotFoundException {
		
		String filePath = path + File.separator + fileName;
		InputStream is = new FileInputStream(filePath);
		return is;
	}

}
