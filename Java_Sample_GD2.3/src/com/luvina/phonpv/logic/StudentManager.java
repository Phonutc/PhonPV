package com.luvina.phonpv.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class StudentManager{
	private ArrayList<Student> listStudent;
	private static final String PATH = "src\\com\\luvina\\phonpv\\data\\data.txt";

	public StudentManager() {
		this.listStudent = convertFileToList();
	}
	
	public boolean addStudentTable(Student student) {
		if (!listStudent.contains(student)) {
			listStudent.add(student);
			convertListToFile(listStudent);
			return true;
		}
		return false;
	}
	public boolean editStudentTable(Student student) {
		int size = listStudent.size();
		for (int i = 0; i < size; i++) {
			if (listStudent.get(i).getiD().equals(student.getiD())) {
				listStudent.get(i).setName(student.getName());
				listStudent.get(i).setAge(student.getAge());
				listStudent.get(i).setAddress(student.getAddress());
				listStudent.get(i).setGpa(student.getGpa());
				convertListToFile(listStudent);
				return true;
			}
		}
		return false;
}
	
	public boolean deleteStudent() {
		for (Student student : listStudent) {
			listStudent.remove(student);
			convertListToFile(listStudent);
			return true;
		}
		return false;
	}
	public void sortStudentByGPA() {
        Collections.sort(listStudent, new SortByGPA());
        convertListToFile(listStudent);
    
	}
	
	public void sortStudentByName() {
	     Collections.sort(listStudent, new SortByName());
	     convertListToFile(listStudent);
	    }

	public ArrayList<Student> findByName(String name) {
		ArrayList<Student> result = new ArrayList<Student>();
		for (Student ele : listStudent) {
			if (ele.getName().contains(name)) {
				result.add(ele);
			}
		}
//		if (result.isEmpty()) {
//			return null;
//		}
		return result;
	}
	
	public ArrayList<Student> convertFileToList() {
		try {
			String data = docFileArr(PATH);
			String[] dataLine = data.split("\n");
			ArrayList<Student> listSt = new ArrayList<Student>();
			for (int i = 0; i < dataLine.length; i++) {
				String[] userInfor = dataLine[i].split("-");
				Student timeTable = new Student(userInfor[0], userInfor[1], userInfor[2], userInfor[3], userInfor[4]);
				listSt.add(timeTable);
			}
			return listSt;

		} catch (Exception e) {
			return new ArrayList<Student>();
		}
	}
	
	public ArrayList<Student> getListStudentTable() {
		return listStudent;
	}
	public void setListStudentTable(ArrayList<Student> listStudentTable) {
		this.listStudent = listStudentTable;
	}

	private void convertListToFile(ArrayList<Student> listStudent) {
		String data = "";
		for (Student ele : listStudent) {
			data += ele.toString();
		}
		ghiFileArr(data, PATH);
	}

	private String docFileArr(String path) {
		String data = "";
		try {
			// 1 trỏ vào file
			File file = new File(path);
			// 2. Kiểm tra tồn tại
			if (!file.exists()) {
//				System.out.println("File cần đọc không tồn tại!");
				return null;
			}
			// 3. Mở File để đọc
			FileInputStream fI = new FileInputStream(file);
			byte[] arrByte = new byte[1024];
			int length = fI.read(arrByte);
			while (length != -1) {
				data += new String(arrByte, 0, length);
				length = fI.read(arrByte);
			}
			fI.close();
		} catch (IOException e) {
			System.out.println("Lỗi Hệ Thống !!!");
		}
		return data;
	}

	private void ghiFileArr(String input, String path) {
		try {
			// 1 trỏ vào file
			File file = new File(path);
			// 2. Kiểm tra tồn tại
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
				System.out.println("Đã tạo một file mới để ghi thông tin");
			}
			// 3. Mở File để ghi
			FileOutputStream fO = new FileOutputStream(file);
			byte[] byteArr = input.getBytes();
			fO.write(byteArr);
			// close
			fO.close();
		} catch (IOException e) {
			System.out.println("Lỗi Hệ Thống !!!");
		}
	}

}
