package com.techghar.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * ImageUtility provides methods for handling image uploads and processing.
 * This utility class manages saving uploaded images to the server and related operations.
 */
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
			 return  filePath.replace("\\", "/");


		} catch (Exception e) {
			System.out.println("File not uploaded");
			e.printStackTrace();
			return null;
		}
	}
}
