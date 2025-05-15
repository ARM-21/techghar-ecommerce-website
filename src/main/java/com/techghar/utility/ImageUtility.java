package com.techghar.utility;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class ImageUtility {
	public static String fileWriter(HttpServletRequest request, HttpServletResponse response, Part image) {

		try {
			
			System.out.println(image);
			String fileName = image.getSubmittedFileName();
			System.out.println(fileName);

			String storePath = request.getServletContext().getRealPath("");
			String filePath = "assets" + File.separator + "images" + File.separator + fileName;

			System.out.println("storedpath" +storePath);
			System.out.println("filepath"+filePath);
			image.write(storePath + File.separator + filePath);

			System.out.println("File uploaded");
			return  filePath;
			// TODO: Write respective DAO process to store all attributes and filePath to
			// database

		} catch (Exception e) {
			System.out.println("File not uploaded");
			e.printStackTrace();
			return null;
		}
	}

}
