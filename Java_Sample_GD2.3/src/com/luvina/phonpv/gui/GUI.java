package com.luvina.phonpv.gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.luvina.phonpv.gui.ianhienpanel.IAnHienPanel;
import com.luvina.phonpv.gui.icommon.ICommon;
import com.luvina.phonpv.gui.panel.PanelAddStudent;
import com.luvina.phonpv.gui.panel.PanelStudentManager;

public class GUI extends JFrame implements ICommon, IAnHienPanel {

	private static final int WIDTH = 866;
	private static final int HEIGHT = 639;
	private PanelStudentManager panelStudentManager;
	private PanelAddStudent panelAddStudnet;

	public GUI() {
		init();
		addComponent();
		addEvent();
	}

	@Override
	public void init() {
		setTitle("Ứng dụng quản lý sinh viên");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new CardLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void addComponent() {
		panelStudentManager = new PanelStudentManager();
		panelStudentManager.addEventAnHienPanel(this);
		panelAddStudnet = new PanelAddStudent();
		panelAddStudnet.addEventAnHienPanel(this);
		add(panelStudentManager);
		add(panelAddStudnet);
	}

	@Override
	public void addEvent() {
		WindowAdapter wa = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Bạn đã chắc chắn chưa?");
				if (result == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		};
		addWindowListener(wa);

	}

	@Override
	public void hienPanelStudentManager() {
		panelStudentManager.setVisible(true);
		panelStudentManager.updateAllStudentModel();
		panelAddStudnet.setVisible(false);
		this.setSize(WIDTH, HEIGHT);

	}

	@Override
	public void hienPanelAddStudent() {
		panelAddStudnet.setVisible(true);
		panelStudentManager.setVisible(false);
		this.setSize(WIDTH - 496, HEIGHT - 239);

	}

	@Override
	public void hienPanelAddStudent(String id) {
		panelAddStudnet.setVisible(true);
		panelStudentManager.setVisible(false);
		panelAddStudnet.setStudentInfor(id);
		this.setSize(WIDTH - 496, HEIGHT - 239);
		
	}

}
