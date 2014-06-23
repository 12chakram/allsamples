package com.myeclipseide.examples.m4m;

import javax.swing.UIManager;


public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		new ManageContactFrame().setVisible(true);
	}
}