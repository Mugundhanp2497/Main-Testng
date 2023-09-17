package org.alphind.alphamcs.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.alphind.alphamcs.base.CommonFunctions;
import org.apache.commons.io.FileUtils;

;

public class FileUtil extends CommonFunctions {

	public FileUtil() {

	}

	public void copyFile(String sourceFileName, String destinationFileName) {

		File source = new File(sourceFileName);
		File dest = new File(destinationFileName);
		try {
			FileUtils.copyFile(source, dest);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void moveFile(String sourceFileName, String destinationFileName) {

		File source = new File(sourceFileName);
		File dest = new File(destinationFileName);
		try {
			FileUtils.moveFile(source, dest);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String findFile(String partialFileName, String folderName, long currentTime) {
		File[] listFiles = new File(folderName).listFiles(file->file.lastModified() > currentTime);
		//Arrays.sort(listFiles, Comparator.comparingLong(File::lastModified).reversed());
		Arrays.sort(listFiles, Comparator.comparingLong(File::lastModified)); //Modified from above line as the above line is picking up the old file.
		String fullFileName = "File Not Found";
		for (int i = 0; i < listFiles.length; i++) {

			if (listFiles[i].isFile()) {
				String fileName = listFiles[i].getName();
				// if (fileName.startsWith(partialFileName) && fileName.endsWith(".txt"))
				if (fileName.contains(partialFileName)) {
					System.out.println("found file" + " " + fileName);
					fullFileName = fileName;
				}
			}
		}
		return fullFileName;
	}

	public String findFile(String partialFileName, String folderName) {
		File[] listFiles = new File(folderName).listFiles();
		//Arrays.sort(listFiles, Comparator.comparingLong(File::lastModified).reversed());
		Arrays.sort(listFiles, Comparator.comparingLong(File::lastModified)); //Modified from above line as the above line is picking up the old file.
		String fullFileName = "File Not Found";
		for (int i = 0; i < listFiles.length; i++) {

			if (listFiles[i].isFile()) {
				String fileName = listFiles[i].getName();
				// if (fileName.startsWith(partialFileName) && fileName.endsWith(".txt"))
				if (fileName.contains(partialFileName)) {
					System.out.println("found file" + " " + fileName);
					fullFileName = fileName;
				}
			}
		}
		return fullFileName;
	}
	
	
	public boolean isFileProcessed(String partialFileName, String folderName, int maxMinute) {
		System.out.println("waiting for max " + maxMinute + " min for file to be processed");
		long startTime = System.currentTimeMillis();
		long endTime;
		do {
			putStaticWait(10);
			endTime = System.currentTimeMillis();
			if ((endTime - startTime) / 60000 > maxMinute) {
				System.out.println("waited for max " + maxMinute + " min for file to be processed");
				return false;
			}
		} while (!(findFile(partialFileName, folderName, startTime) == "File Not Found"));
		putStaticWait(10);
		return true;
	}

	public String findFile(String partialFileName, String folderName, int maxWaitMinute) {
		long startTime = System.currentTimeMillis();
		long endTime;
		String fileName = "File Not Found";
		do {
			putStaticWait(10);
			fileName = findFile(partialFileName, folderName, startTime);
			endTime = System.currentTimeMillis();
			if ((endTime - startTime) / 60000 > maxWaitMinute) {
				System.out.println("waited for max " + maxWaitMinute + " min for file to appear");
				return "File Not Found";
			}
		} while (fileName == "File Not Found");
		return fileName;
	}

	/**
	 * Use this method to copy file from one folder to other with username and password authentication.
	 * 
	 * @param sourceFilePath
	 * This is the path of the file including filename and extension.
	 * @param destinationFolderPath
	 * This the folder path where file needs to be copied.
	 * @param username
	 * Username for network authentication.
	 * @param password
	 * Password for the user account with the username.
	 */
	public void copyFile(String sourceFilePath, String destinationFolderPath, String username, String password) {

		Map<String, String> env = new HashMap<>();
		env.put("smb4j.auth.username", username);
		env.put("smb4j.auth.password", password);

		try {
			Path sourcePath = Paths.get(sourceFilePath);
			Path destinationPath = Paths.get(destinationFolderPath).resolve(sourcePath.getFileName());

			Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

			System.out.println("File copied successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error copying the file.");
		}
	}

	public void replaceStringAndCreateNewFile(String sourceFilePath, String destinationFilePath, String searchString,
			String replacementString) {

		try {
			// Read the content of the source file into a list of strings
			Path sourcePath = Paths.get(sourceFilePath);
			List<String> lines = Files.readAllLines(sourcePath, StandardCharsets.UTF_8);

			// Replace the old string with the new string
			List<String> modifiedLines = lines.stream().map(line -> line.replace(searchString, replacementString))
					.collect(Collectors.toList());

			// Write the modified content to the destination file
			Path destinationPath = Paths.get(destinationFilePath);
			Files.write(destinationPath, modifiedLines, StandardCharsets.UTF_8);

			System.out.println("Modified file created successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error creating the modified file.");
		}
	}

}
