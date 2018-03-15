package gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.*;

class FileError{
	static void showErrorMessage(FileErrorType error, Component parent) {
		switch(error) {
		case READ:
			JOptionPane.showMessageDialog(parent, "Invalid input file", "Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case WRITE:
			JOptionPane.showMessageDialog(parent, "Invalid output file", "Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case SAME_INPUT_OUTPUT:
			JOptionPane.showMessageDialog(parent, "Cannot overwrite input file", "Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case ERROR:
			JOptionPane.showMessageDialog(parent, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case NONE: break; //Do nothing!
		}
	}

	static FileErrorType hasFileErrorOutput(File file, String inputFile) {
		FileErrorType errorType = FileErrorType.NONE;
		String outputFile = "";
		String fileType = "";

		try {
			fileType = Files.probeContentType(file.toPath());
		} catch (IOException e) {
			errorType = FileErrorType.ERROR;
		}

		//If there was no exception above
		if (errorType == FileErrorType.NONE) {
			if (file.exists() && !(file.canWrite() && fileType.equals("text/plain"))) {
				errorType = FileErrorType.WRITE;
			} else if (!file.exists()) {
				try {
					file.createNewFile();
					outputFile = file.getPath();
				} catch (IOException e) {
					errorType = FileErrorType.ERROR;
					if (!file.exists()) errorType = FileErrorType.WRITE;
				}
			} else {
				outputFile = file.getPath();
			}
		}

		if (!outputFile.isEmpty() && outputFile.equals(inputFile))
			errorType = FileErrorType.SAME_INPUT_OUTPUT;

		return errorType;
	}
	
	static FileErrorType hasFileErrorInput(File file, String outputFile) {
		FileErrorType errorType = FileErrorType.NONE;
		String inputFile = "";
		String fileType = "";

		try {
			fileType = Files.probeContentType(file.toPath());
			if (fileType == null) {
				inputFile = file.getPath();
				int index = inputFile.lastIndexOf('.');
				fileType = (index == -1) ? "" : inputFile.substring(index + 1);
			}
		} catch (IOException e) {
			errorType = FileErrorType.ERROR;
		}

		//If there was no exception above
		if (errorType == FileErrorType.NONE) {
			if (!(file.canRead() && fileType.equals("text/plain")))
				errorType = FileErrorType.READ;
			else
				inputFile = file.getPath();
		}

		if (!inputFile.isEmpty() && inputFile.equals(outputFile))
			errorType = FileErrorType.SAME_INPUT_OUTPUT;

		return errorType;
	}
}
