package com.luvina.phonpv.gui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.luvina.phonpv.gui.ianhienpanel.IAnHienPanel;
import com.luvina.phonpv.logic.Student;
import com.luvina.phonpv.logic.StudentManager;

public class PanelStudentManager extends BasePanel {
	private IAnHienPanel event;

	private static final int MARGIN_LEFT = 10;
	private static final int MARGIN_TOP = 10;
	private String[] HEADER;
	private StudentManager manager;
	private JTable jTableAllStudent;
	private DefaultTableModel tableAllStudentModel;
//	private JTable jTableFilter;
	private DefaultTableModel tableFilterModel;
	private JLabel lbTitle;
	private JTextField tfSearch;

	private JButton btAdd;
	private JButton btEdit;
	private JButton btDelete;
	private JButton btSearch;
	private JButton btSortByName;
	private JButton btSortByGpa;
	private PanelAddStudent panelAddStudent;

	public void addEventAnHienPanel(IAnHienPanel event) {
		this.event = event;
	}

	private void hienPanelAddStudent() {
		event.hienPanelAddStudent();
	}

	@Override
	public void init() {
		setLayout(null);
		setVisible(true);

	}

	@Override
	public void addComponent() {
		Font font = new Font("Arial", Font.BOLD, 18);
		FontMetrics fm = getFontMetrics(font);
		Font fontTitle = new Font("Tahoma", Font.BOLD, 25);
		FontMetrics fmTitle = getFontMetrics(fontTitle);

		lbTitle = new JLabel("QUẢN LÝ SINH VIÊN");
		lbTitle = (JLabel) setComponent(lbTitle, fontTitle, fmTitle.stringWidth(lbTitle.getText()) + 2,
				fmTitle.getHeight(), MARGIN_LEFT + 246, 20);
		add(lbTitle);

		tfSearch = new JTextField();
		tfSearch = (JTextField) setComponent(tfSearch, font, 600, fm.getHeight(), MARGIN_LEFT,
				lbTitle.getY() + lbTitle.getHeight() + MARGIN_TOP);
		add(tfSearch);

		btSearch = new JButton("Tìm Kiếm");
		btSearch = (JButton) setComponent(btSearch, font, 850 - tfSearch.getWidth() - 3 * MARGIN_LEFT,
				tfSearch.getHeight(), tfSearch.getX() + tfSearch.getWidth() + 10, tfSearch.getY());
		add(btSearch);

		HEADER = new String[] { "ID", "Tên", "Tuổi", "Địa Chỉ", "GPA" };
		manager = new StudentManager();
		jTableAllStudent = new JTable();
		jTableAllStudent.setShowGrid(true);
		jTableAllStudent.setBackground(Color.YELLOW);
		updateAllStudentModel();
		JScrollPane scr1 = new JScrollPane(jTableAllStudent);
		scr1.setSize(tfSearch.getWidth() + btSearch.getWidth() + MARGIN_LEFT,
				600 - (lbTitle.getHeight() + tfSearch.getHeight() + MARGIN_TOP + 100));
		scr1.setLocation(MARGIN_LEFT, tfSearch.getY() + tfSearch.getHeight() + MARGIN_TOP);
		add(scr1);
//		
//		jTableFilter = new JTable();
//		jTableFilter.setShowGrid(true);
//		jTableFilter.setBackground(Color.YELLOW);
//		updateFilterModel(tfSearch.getText());
//		JScrollPane scr2 = new JScrollPane(jTableFilter);
//		scr2.setSize(scr1.getWidth(), scr1.getHeight());
//		scr2.setLocation(scr1.getX(), scr1.getY());
//		add(scr2);

		btAdd = new JButton("Thêm");
		btAdd = (JButton) setComponent(btAdd, font, 150, btSearch.getHeight(), MARGIN_LEFT,
				scr1.getY() + scr1.getHeight() + MARGIN_TOP);
		add(btAdd);

		btEdit = new JButton("Sửa");
		btEdit = (JButton) setComponent(btEdit, font, 150, btSearch.getHeight(),
				MARGIN_LEFT + btAdd.getX() + btAdd.getWidth(), btAdd.getY());
		add(btEdit);

		btDelete = new JButton("Xóa");
		btDelete = (JButton) setComponent(btDelete, font, 150, btSearch.getHeight(),
				MARGIN_LEFT + btEdit.getX() + btEdit.getWidth(), btEdit.getY());
		add(btDelete);

		btSortByName = new JButton("Xếp theo tên");
		btSortByName = (JButton) setComponent(btSortByName, font, 170, btSearch.getHeight(),
				MARGIN_LEFT + btDelete.getX() + btDelete.getWidth(), btDelete.getY());
		add(btSortByName);

		btSortByGpa = new JButton("Xếp theo Gpa");
		btSortByGpa = (JButton) setComponent(btSortByGpa, font, 170, btSearch.getHeight(),
				MARGIN_LEFT + btSortByName.getX() + btSortByName.getWidth(), btSortByName.getY());
		add(btSortByGpa);

	}

	public void updateAllStudentModel() {
		manager = new StudentManager();
		ArrayList<Student> listStudentTable = manager.getListStudentTable();
		tableAllStudentModel = new DefaultTableModel(HEADER, 0);
		for (int i = 0; i < listStudentTable.size(); i++) {
			Student studentTable = listStudentTable.get(i);
			String[] datarow = new String[] { studentTable.getiD(), studentTable.getName(), studentTable.getAge(),
					studentTable.getAddress(), studentTable.getGpa() };
			tableAllStudentModel.addRow(datarow);
		}
		jTableAllStudent.setModel(tableAllStudentModel);

	}

	private void updateFilterModel(String name) {
		ArrayList<Student> listStudentTable = manager.findByName(name);
		tableFilterModel = new DefaultTableModel(HEADER, 0);
		for (int i = 0; i < listStudentTable.size(); i++) {
			Student studentTable = listStudentTable.get(i);
			String[] datarow = new String[] { studentTable.getiD(), studentTable.getName(), studentTable.getAge(),
					studentTable.getAddress(), studentTable.getGpa() };
			tableFilterModel.addRow(datarow);
		}
		jTableAllStudent.setModel(tableFilterModel);
	}

	private Component setComponent(Component com, Font font, int width, int height, int x, int y) {
		com.setFont(font);
		com.setSize(width, height);
		com.setLocation(x, y);
		add(com);
		return com;
	}

	@Override
	public void addEvent() {
		forCusTextField();
		btAdd.setName("Them");
		btEdit.setName("Sua");
		btDelete.setName("Xoa");
		btSortByName.setName("sortbyname");
		btSortByGpa.setName("sortbygpa");
		btSearch.setName("search");
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = ((JButton) e.getSource()).getName();
				switch (name) {
				case "Them":
					hienPanelAddStudent();
					break;
				case "Sua":
					editStudent();
					break;
				case "Xoa":
					delete();
					break;
				case "sortbyname":
					sortByName();
					break;
				case "sortbygpa":
					sortByGPA();
					break;
				case "search":
					findStudent();
					break;
				default:
				}
			}


		};
		btAdd.addActionListener(al);
		btEdit.addActionListener(al);
		btSortByName.addActionListener(al);
		btSortByGpa.addActionListener(al);
		btDelete.addActionListener(al);
		btSearch.addActionListener(al);

	}
	
	private void findStudent() {
		if (tfSearch.getText().equals("Nhập tên học sinh...")) {
			updateAllStudentModel();
		}else {
			updateFilterModel(tfSearch.getText());
		}
		
	}

	public void forCusTextField() {
		FocusListener focus = new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfSearch.getText().equals("")) {
					tfSearch.setForeground(Color.blue);
					tfSearch.setText("Nhập tên học sinh...");
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfSearch.getText().equals("Nhập tên học sinh...")) {
					tfSearch.setText("");
					tfSearch.setForeground(Color.black);
				}

			}
		};
		tfSearch.addFocusListener(focus);
	}

	private void editStudent() {
		int row = jTableAllStudent.getSelectedRow();
		System.out.println(row);
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn trường muốn sửa.Vui lòng chọn",
					"Thông báo", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String userID = (String)tableAllStudentModel.getValueAt(row, 0);
		System.out.println(userID);
		event.hienPanelAddStudent(userID);
	}

	private void delete() {
		int idx = jTableAllStudent.getSelectedRow();
//		System.out.println("Row" + idx);
		if (idx == -1) {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn trường muốn xóa.Vui lòng chọn",
					"Thông báo", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tableAllStudentModel.getValueAt(idx, 0);
		if (manager.deleteStudent()) {
			updateAllStudentModel();
		}

	}

	private void sortByName() {
		manager = new StudentManager();
		manager.sortStudentByName();
		updateAllStudentModel();

	}

	private void sortByGPA() {
		manager.sortStudentByGPA();
		manager = new StudentManager();
		updateAllStudentModel();
	}

}
