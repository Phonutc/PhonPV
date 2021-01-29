package com.luvina.phonpv.gui.panel;

import javax.swing.JPanel;

import com.luvina.phonpv.gui.icommon.ICommon;

public abstract class BasePanel extends JPanel implements ICommon {

	public BasePanel() {
		init();
		addComponent();
		addEvent();
	}
}
