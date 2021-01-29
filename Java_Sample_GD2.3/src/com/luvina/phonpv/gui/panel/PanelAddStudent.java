package com.luvina.phonpv.gui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.luvina.phonpv.gui.ianhienpanel.IAnHienPanel;
import com.luvina.phonpv.logic.Student;
import com.luvina.phonpv.logic.StudentManager;

public class PanelAddStudent extends BasePanel {
//	private static final long serialVersionUID = 1L;
	private IAnHienPanel event;
	private StudentManager studentManager;
//	private PanelStudentManager panelStudentManager;

	private static final int MARGIN_LEFT = 20;
	private static final int MARGIN_TOP = 10;

	private JLabel lbTitle;
	private JLabel lbID;
	private JTextField tfID;

	private JLabel lbName;
	private JTextField tfName;

	private JLabel lbAge;
	private JTextField tfAge;

	private JLabel lbAddress;
	private JTextArea taAddress;

	private JLabel lbGpa;
	private JTextField tfGpa;

	private JButton btAdd;
	private JButton btSave;
	private JButton btQuayLai;

	public void addEventAnHienPanel(IAnHienPanel event) {
		this.event = event;
	}

	private void hienPanelStudentManager() {
		event.hienPanelStudentManager();
	}

	@Override
	public void init() {
		studentManager = new StudentManager();
		setBackground(new Color(230, 230, 230));
		setVisible(true);
		setLayout(null);
	}

	@Override
	public void addComponent() {
		Font font = new Font("Tahoma", Font.PLAIN, 16);
		FontMetrics fm = getFontMetrics(font);
		Font fontTitle = new Font("Tahoma", Font.BOLD, 25);
		FontMetrics fmTitle = getFontMetrics(fontTitle);

		lbTitle = new JLabel("THÊM SINH VIÊN");
		lbTitle = (JLabel) setComponent(lbTitle, fontTitle, fmTitle.stringWidth(lbTitle.getText()) + 2,
				fmTitle.getHeight(), MARGIN_LEFT + 60, MARGIN_TOP);
		add(lbTitle);

		lbID = new JLabel("ID:");
		lbID = (JLabel) setComponent(lbID, font, fm.stringWidth(lbID.getText()) + 2, fm.getHeight(), MARGIN_LEFT,
				lbTitle.getY() + lbTitle.getHeight() + MARGIN_TOP);
		add(lbID);

		tfID = new JTextField();
		tfID = (JTextField) setComponent(tfID, font, 80, fm.getHeight(), lbID.getX() + 4 * MARGIN_LEFT, lbID.getY());
		add(tfID);

		lbName = new JLabel("Tên:");
		lbName = (JLabel) setComponent(lbName, font, fm.stringWidth(lbName.getText()) + 2, fm.getHeight(), MARGIN_LEFT,
				lbID.getY() + lbID.getHeight() + MARGIN_TOP);
		add(lbName);

		tfName = new JTextField();
		tfName = (JTextField) setComponent(tfName, font, 3 * tfID.getWidth(), tfID.getHeight(), tfID.getX(),
				lbName.getY());
		add(tfName);

		lbAge = new JLabel("Tuổi:");
		lbAge = (JLabel) setComponent(lbAge, font, fm.stringWidth(lbAge.getText()) + 2, fm.getHeight(), MARGIN_LEFT,
				lbName.getY() + lbName.getHeight() + MARGIN_TOP);
		add(lbAge);

		tfAge = new JTextField();
		tfAge = (JTextField) setComponent(tfAge, font, tfID.getWidth(), tfID.getHeight(), tfID.getX(), lbAge.getY());
		add(tfAge);

		lbAddress = new JLabel("Địa chỉ:");
		lbAddress = (JLabel) setComponent(lbAddress, font, fm.stringWidth(lbAddress.getText()) + 2, fm.getHeight(),
				MARGIN_LEFT, lbAge.getY() + lbAge.getHeight() + MARGIN_TOP);
		add(lbAddress);

		taAddress = new JTextArea();
		taAddress = (JTextArea) setComponent(taAddress, font, 3 * tfID.getWidth(), 100, tfID.getX(), lbAddress.getY());
		add(taAddress);

		lbGpa = new JLabel("Gpa:");
		lbGpa = (JLabel) setComponent(lbGpa, font, fm.stringWidth(lbGpa.getText()) + 2, fm.getHeight(), MARGIN_LEFT,
				taAddress.getY() + taAddress.getHeight() + MARGIN_TOP);
		add(lbGpa);

		tfGpa = new JTextField();
		tfGpa = (JTextField) setComponent(tfGpa, font, 2 * tfID.getWidth(), tfID.getHeight(), tfID.getX(),
				lbGpa.getY());
		add(tfGpa);

		btAdd = new JButton("Thêm");
		btAdd = (JButton) setComponent(btAdd, font, 100, 30, lbID.getX(),
				lbGpa.getY() + lbGpa.getHeight() + 2 * MARGIN_TOP);
		add(btAdd);

		btSave = new JButton("Lưu");
		btSave = (JButton) setComponent(btSave, font, btAdd.getWidth(), btAdd.getHeight(),
				btAdd.getX() + btAdd.getWidth() + MARGIN_LEFT / 2, btAdd.getY());
		add(btSave);

		btQuayLai = new JButton("Quay Lại");
		btQuayLai = (JButton) setComponent(btQuayLai, font, btAdd.getWidth(), btAdd.getHeight(),
				btSave.getX() + btSave.getWidth() + MARGIN_LEFT / 2, btSave.getY());
		add(btQuayLai);

	}

	@Override
	public void addEvent() {
		btQuayLai.setName("Quay Lại");
		btAdd.setName("Thêm");
		btSave.setName("Lưu");
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = ((JButton) e.getSource()).getName();
				switch (name) {
				case "Quay Lại":
					reset();
					hienPanelStudentManager();
					break;
				case "Thêm":
					addStudent();
					break;
				case "Lưu":
					save();
					break;
				default:
				}
			}

		};
		btAdd.addActionListener(al);
		btSave.addActionListener(al);
		btQuayLai.addActionListener(al);

	}
	private void reset() {
		tfID.setText("");
		tfName.setText("");
		tfAge.setText("");
		taAddress.setText("");
		tfGpa.setText("");
	}
	public void setStudentInfor(String id) {
		
		ArrayList<Student> lisStudents = studentManager.getListStudentTable();
		for (Student student : lisStudents) {
			if (id.equals(student.getiD())) {
				tfID.setText(student.getiD());
				tfName.setText(student.getName());
				tfAge.setText(student.getAge());
				taAddress.setText(student.getAddress());
				tfGpa.setText(student.getGpa());
			}
		}
		tfID.setEditable(false);
		
		
	}

	private Component setComponent(Component com, Font font, int width, int height, int x, int y) {
		com.setFont(font);
		com.setSize(width, height);
		com.setLocation(x, y);
		add(com);
		return com;
	}

	private void addStudent() {
		studentManager = new StudentManager();
		if (tfID.getText().matches("")||tfName.getText().matches("")||tfAge.getText().matches("")||taAddress.getText().matches("")||tfGpa.getText().matches("")) {
			JOptionPane.showMessageDialog(this, "Error!\nCó trường bạn chưa điền. Vui lòng kiểm tra và điền đầy đủ thông tin",
					"Thông báo", JOptionPane.OK_OPTION);
			return;
		}
		String iD = tfID.getText();
		if (!tfID.getText().matches("\\d*")) {
			JOptionPane.showMessageDialog(this, "Error!\nID chỉ bao gồm các số. Vui lòng kiểm tra lại", "Thông báo",
					JOptionPane.OK_OPTION);
			return;
		} else {
			iD = tfID.getText();
		}

		String name = tfName.getText();
		if (!tfName.getText().matches("^[a-z A-Z]{1,50}$")) {
			JOptionPane.showMessageDialog(this, "Error!\nTên phải là chữ viết hoa đầu mỗi chữ cái. Vui lòng kiểm tra lại",
					"Thông báo", JOptionPane.OK_OPTION);
			return;
		} else {
			name = tfName.getText();
		}

		String age = tfAge.getText();
		if (!tfAge.getText().matches("\\d*")) {
			JOptionPane.showMessageDialog(this, "Error!\nTuổi chỉ bao gồm các số. Vui lòng kiểm tra lại", "Thông báo",
					JOptionPane.OK_OPTION);
			return;
		} else {
			age = tfAge.getText();
		}

		String address = taAddress.getText();
		if (!taAddress.getText().matches("[a-z A-Z]{1,50}$")) {
			JOptionPane.showMessageDialog(this, "Error!\nBạn phải nhập địa chỉ đúng quy định", "Thông báo", JOptionPane.OK_OPTION);
			return;
		} else {
			address = taAddress.getText();
		}

		String gpa = tfGpa.getText();
		if (!tfGpa.getText().matches("^([0-9]{1,2})\\.[0-9]$")) {
			JOptionPane.showMessageDialog(this, "Error!\nTuổi chỉ bao gồm các số. Vui lòng kiểm tra lại", "Thông báo",
					JOptionPane.OK_OPTION);
			return;
		} else {
			age = tfAge.getText();
		}

		Student student = new Student(iD, name, age, address, gpa);
		boolean checkTonTai = studentManager.addStudentTable(student);
		if (checkTonTai) {
			JOptionPane.showMessageDialog(this, "Thêm học sinh thành công", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			tfID.setText("");
			tfName.setText("");
			tfAge.setText("");
			taAddress.setText("");
			tfGpa.setText("");
			hienPanelStudentManager();
		} else {
			JOptionPane.showMessageDialog(this, "Error!\\nHọc sinh đã tồn tại.Vui lòng kiểm tra lại.", "Thông báo",
					JOptionPane.OK_OPTION);
			tfID.setText("");
			tfName.setText("");
			tfAge.setText("");
			taAddress.setText("");
			tfGpa.setText("");
		}
	}

	private void save() {
		studentManager = new StudentManager();
		String iD = tfID.getText();
//		tfID.setEditable(false);
		String name = tfName.getText();
		if (!tfName.getText().matches("^[a-z A-Z]{1,50}$")) {
			JOptionPane.showMessageDialog(this, "Error!\nTên phải là chữ viết hoa đầu mỗi chữ cái. Vui lòng kiểm tra lại",
					"Thông báo", JOptionPane.OK_OPTION);
			return;
		} else {
			name = tfName.getText();
		}

		String age = tfAge.getText();
		if (!tfAge.getText().matches("\\d*")) {
			JOptionPane.showMessageDialog(this, "Error!\nTuổi chỉ bao gồm các số. Vui lòng kiểm tra lại", "Thông báo",
					JOptionPane.OK_OPTION);
			return;
		} else {
			age = tfAge.getText();
		}

		String address = taAddress.getText();
		if (!taAddress.getText().matches("[a-z A-Z]{1,50}$")) {
			JOptionPane.showMessageDialog(this, "Error!\nBạn phải nhập địa chỉ đúng quy định", "Thông báo", JOptionPane.OK_OPTION);
			return;
		} else {
			address = taAddress.getText();
		}

		String gpa = tfGpa.getText();
		if (!tfGpa.getText().matches("^([0-9]{1,2})\\.[0-9]$")) {
			JOptionPane.showMessageDialog(this, "Error!\nTuổi chỉ bao gồm các số. Vui lòng kiểm tra lại", "Thông báo",
					JOptionPane.OK_OPTION);
			return;
		} else {
			age = tfAge.getText();
		}
		
		Student student = new Student(iD, name, age, address, gpa);
		boolean checkThanhCong = studentManager.editStudentTable(student);
		if (checkThanhCong) {
			JOptionPane.showMessageDialog(this, "Sửa học sinh thành công.", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			tfID.setText("");
			tfName.setText("");
			tfAge.setText("");
			taAddress.setText("");
			tfGpa.setText("");
			hienPanelStudentManager();
		}

	}
}
