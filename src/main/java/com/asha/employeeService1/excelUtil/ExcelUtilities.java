package com.asha.employeeService1.excelUtil;
//package com.asha.employeeService.excelUtil;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import com.asha.employeeService.entity.Employee;
//
////import org.apache.poi.xssf.usermodel.XSSFSheet;
////import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//
//import com.asha.quizService.entity.Option;
//import com.asha.quizService.entity.Question;
//import com.asha.quizService.entity.Quiz;
//
//
//public class ExcelUtilities {
//	public static Integer quiz_timeLimit = 0;
//	public static Integer question_description = 1;
//	public static Integer question_correctAnswerDescription = 2;
//	public static Integer question_correctOption= 3;
//	public static Integer question_position = 4;
//	public static Integer question_positivePoints = 5;
//	public static Integer question_negativePoints =6;
//	public static Integer question_answerType = 7;
//	public static Integer question_timeLimit= 8;	
//	public static Integer question_options = 9;
//	public static String add_quiz_file = "C:\\Users\\ASHA\\OneDrive\\Desktop\\add_quizzes1.xlsx";
//	
//	public static Integer employeeCode=0;
//	public static Integer firstName= 1;
//	public static Integer lastName = 2;
//	public static Integer emailId = 3 ;
//	public static Integer phoneNumber=4;
//	public static Integer address = 5;
//	public static Integer designation = 6;
//	public static Integer subordinateCount=7;
//	public static Integer manager = 8;
//	
//	
//	
//	public static String add_employees_file = "C:\\Users\\ASHA\\OneDrive\\Desktop\\add_edit_employees.xlsx";
////	public static List<Employee> getEmployeesToAdd() throws IOException{
////		XSSFWorkbook workbook = new XSSFWorkbook(add_employees_file);
////		XSSFSheet sheet = workbook.getSheetAt(0);
////		Integer rowIndex = 1;
////		List<Employee> employeesToAddUpdate = new ArrayList<Employee>();
////		while(true) {
////			if(rowIndex.equals(sheet.getPhysicalNumberOfRows())) {
////				break;
////			}
////			
////			
////			
////			
////			
////			
////			
////			
////			
////		}
////		
////	}
////	
//	public static List<Quiz> getQuizzesToAdd() throws IOException {
//		Integer sheetIndex = 0;
//		XSSFWorkbook workbook = new XSSFWorkbook(add_quiz_file);
//		List<Quiz> quizzesToAdd = new ArrayList<Quiz>();
//		Integer numberOfSheets = workbook.getNumberOfSheets();
//		while (true) {
//			//get sheet using index
//			//if index sheet is starting with "Sheet" then break;
//			if(sheetIndex.equals(numberOfSheets)) {
//				break;
//			}
//			XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
//			if(sheet == null || sheet.getSheetName().startsWith("Sheet")) {
//				break;
//			}
//			
//			Quiz quiz = new Quiz();
//			quiz.setQuizCode(sheet.getSheetName());
//			LocalDateTime now = LocalDateTime.now();
//			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//			 String formatDateTime = now.format(formatter);
//			 quiz.setTimeQuizEditted(formatDateTime);
//			//create Quiz object -  set 4 properties - quizcode from sheet name, timeEditted = current time, question count = getRowCount -1, time limit
//			quiz.setQuestionCount(sheet.getPhysicalNumberOfRows() -1);
//			
//			Integer quizTimeLimit = (int) sheet.getRow(1).getCell(quiz_timeLimit).getNumericCellValue();
//			quiz.setTimeLimit(quizTimeLimit); 
//
//			
//			for(int questionOrder = 1; questionOrder < sheet.getPhysicalNumberOfRows(); questionOrder++ ) {
//				
//				String question_Description = sheet.getRow(questionOrder).getCell(question_description).getStringCellValue();
//				String correctAnswerDescription = sheet.getRow(questionOrder).getCell(question_correctAnswerDescription).getStringCellValue();
//				String correctOption = sheet.getRow(questionOrder).getCell(question_correctOption).getRawValue();
//				Integer position = (int) sheet.getRow(questionOrder).getCell(question_position).getNumericCellValue();
//				Integer positivePoints = (int) sheet.getRow(questionOrder).getCell(question_positivePoints).getNumericCellValue();
//				Integer negativePoints = (int) sheet.getRow(questionOrder).getCell(question_negativePoints).getNumericCellValue();
//				String answerType = sheet.getRow(questionOrder).getCell(question_answerType).getStringCellValue();
//				Integer timeLimit = (int) sheet.getRow(questionOrder).getCell(question_timeLimit).getNumericCellValue();
//				List<Option> options = new ArrayList<Option>();
//				Integer column = question_options;
//				Integer optionOrder = 1;
//				while(true) {
//					if(sheet.getRow(questionOrder).getCell(column) == null || sheet.getRow(questionOrder).getCell(column).getStringCellValue().equals("")){
//						break;
//					}
//					Option option = new Option();
//					option.setPosition(optionOrder);
//					option.setDescription(sheet.getRow(questionOrder).getCell(column).getStringCellValue());
//					options.add(option);
//					
//					column++;
//					optionOrder ++;
//				}
//				
//				 Question question = new Question(question_Description, correctAnswerDescription, correctOption, position,
//						positivePoints , negativePoints, answerType, timeLimit,options,
//						 quiz);
//				 quiz.getQuestions().add(question);				
//				
// 			}
//			quizzesToAdd.add(quiz);
//			sheetIndex++;
//		}
//		workbook.close();
//		return quizzesToAdd;
//	}
//
//}
