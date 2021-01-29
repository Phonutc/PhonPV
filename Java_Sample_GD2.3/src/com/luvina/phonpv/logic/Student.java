package com.luvina.phonpv.logic;

public class Student {
	private String iD;
	private String name;
	private String age;
	private String address;
	private String gpa;

	public Student() {

	}

	public Student(String iD, String name, String age, String address, String gpa) {
		this.iD = iD;
		this.name = name;
		this.age = age;
		this.address = address;
		this.gpa = gpa;
	}



	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGpa() {
		return gpa;
	}

	public void setGpa(String gpa) {
		this.gpa = gpa;
	}

	@Override
	public String toString() {
		return iD + "-" + name + "-" + age + "-" + address + "-" + gpa + "\n";
	}

	@Override
	public boolean equals(Object obj) {
		Student student = (Student) obj;
		return iD.equals(student.getiD());
	}

}
