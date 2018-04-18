package gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.*;

class Error{
	static void showErrorMessage(ErrorType error, Component parent) {
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
			
		case LINE_LENGTH_ERROR:
			JOptionPane.showMessageDialog(parent, "Error: Line length must contain a positive integer", "Error",  JOptionPane.ERROR_MESSAGE);
			break;
			
		case LINE_LENGTH_TOO_BIG:
			JOptionPane.showMessageDialog(parent, "The line length is too large", "Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case ERROR:
			JOptionPane.showMessageDialog(parent, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		case NONE: break; //Do nothing!
		}
	}

	static ErrorType hasFileErrorOutput(File file, String inputFile) {
		ErrorType errorType = ErrorType.NONE;
		String outputFile = "";
		String fileType = "";

		try {
			fileType = Files.probeContentType(file.toPath());
			if (fileType == null) {
				outputFile = file.getPath();
				int index = outputFile.lastIndexOf('.');
				fileType = (index == -1) ? "" : outputFile.substring(index + 1);
			}
		} catch (IOException e) {
			errorType = ErrorType.ERROR;
		}

		//If there was no exception above
		if (errorType == ErrorType.NONE) {
			if (file.exists() && !(file.canWrite() && (fileType.equals("text/plain")) || fileType.equals("txt"))) {
				errorType = ErrorType.WRITE;
			} else if (!file.exists()) {
				try {
					file.createNewFile();
					outputFile = file.getPath();
				} catch (IOException e) {
					errorType = ErrorType.ERROR;
					if (!file.exists()) errorType = ErrorType.WRITE;
				}
			} else {
				outputFile = file.getPath();
			}
		}

		if (!outputFile.isEmpty() && outputFile.equals(inputFile))
			errorType = ErrorType.SAME_INPUT_OUTPUT;

		return errorType;
	}
	
	static ErrorType hasFileErrorInput(File file, String outputFile) {
		ErrorType errorType = ErrorType.NONE;
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
			errorType = ErrorType.ERROR;
		}

		//If there was no exception above
		if (errorType == ErrorType.NONE) {
			if (!(file.canRead() && (fileType.equals("text/plain") || fileType.equals("txt"))))
				errorType = ErrorType.READ;
			else
				inputFile = file.getPath();
		}

		if (!inputFile.isEmpty() && inputFile.equals(outputFile))
			errorType = ErrorType.SAME_INPUT_OUTPUT;

		return errorType;
	}
	
	static ErrorType hasLineLengthError(String lineLength) {
		ErrorType error = ErrorType.NONE;
		if (!lineLength.matches("[1-9][0-9]*")) error = ErrorType.LINE_LENGTH_ERROR;
		else{
			try {
				Integer.parseInt(lineLength);
			}catch (NumberFormatException e) {
				error = ErrorType.LINE_LENGTH_TOO_BIG;
				//e.printStackTrace();
			}
		}
		
		return error;
	}
}
