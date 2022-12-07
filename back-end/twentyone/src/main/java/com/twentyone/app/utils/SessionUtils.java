package com.twentyone.app.utils;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public class SessionUtils {
	
	public static String saveProfileImage(MultipartFile file) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(System.getProperty("user.dir"));
		stringBuilder.append("\\src\\main\\resources\\static\\img\\");
		String dir = stringBuilder.toString();
		File newFile = new File(dir, System.currentTimeMillis() + file.getOriginalFilename());
		try {
			if(newFile.createNewFile()) {
				file.transferTo(newFile);
				return newFile.getName();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("[ KHONG THEM MOI DUOC FILE ]");
		return null;
	}

}
