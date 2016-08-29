package lianxi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Caculator extends JFrame {
	private Container container;
	private JTextField displayField;// 计算结果显示区
	private String option;// 保存+,-,*,/,=命令
	private double result;// 保存计算结果
	private boolean start;// 判断是否为数字的开始

	public Caculator() {
		container = (JPanel) this.getContentPane();
		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		start = true;
		result = 0;
		option = "=";
		displayField = new JTextField(20);
		displayField.setHorizontalAlignment(JTextField.RIGHT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 100;
		gbc.weighty = 100;
		container.add(displayField, gbc);
		ActionListener insert = new InsertAction();
		ActionListener command = new CommandAction();
		//调用addButton方法
		addButton("7", 0, 2, 1, 1, insert);
		addButton("8", 1, 2, 1, 1, insert);
		addButton("9", 2, 2, 1, 1, insert);
		addButton("/", 3, 2, 1, 1, command);
		addButton("4", 0, 3, 1, 1, insert);
		addButton("5", 1, 3, 1, 1, insert);
		addButton("6", 2, 3, 1, 1, insert);
		addButton("*", 3, 3, 1, 1, command);
		addButton("1", 0, 4, 1, 1, insert);
		addButton("2", 1, 4, 1, 1, insert);
		addButton("3", 2, 4, 1, 1, insert);
		addButton("-", 3, 4, 1, 1, command);
		addButton("0", 0, 5, 1, 1, insert);
		addButton(".", 2, 5, 1, 1, insert);
		addButton("+", 3, 5, 1, 1, command);
		addButton("=", 0, 6, 4, 1, command);
		setBounds(100, 100, 180, 210);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void addButton(String label, int row, int column, int with,
			int height, ActionListener listener) {
		GridBagConstraints constraints = new GridBagConstraints();
		JButton button = new JButton(label);
		constraints.gridx = row;
		constraints.gridy = column;
		constraints.gridwidth = with;
		constraints.gridheight = height;
		constraints.fill = GridBagConstraints.BOTH;
		button.addActionListener(listener);
		container.add(button, constraints);

	}

	public void calculate(double x) {
		if (option.equals("+"))
			result += x;
		else if (option.equals("-"))
			result -= x;
		else if (option.equals("*"))
			result *= x;
		else if (option.equals("/"))
			result /= x;
		else if (option.equals("="))
			result = x;
		displayField.setText("" + result);
	}

	// 匿名内部类判断输入的数据是否合法
	class InsertAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			String input = event.getActionCommand();
			if (start) {
				displayField.setText("");
				start = false;
				if (input.equals("+/-"))
					displayField.setText(displayField.getText() + "-");
			}

			if (!input.equals("+/-")) {
				if (input.equals("Backspace")) {
					String str = displayField.getText();
					if (str.length() > 0)
						displayField.setText(str.substring(0, str.length() - 1));
				} else if (input.equals("CE") || input.equals("C")) {
					displayField.setText("0");
					start = true;
				} else
					displayField.setText(displayField.getText() + input);
			}
		}
	}

	// 匿名内部类
	class CommandAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			String command = evt.getActionCommand();
			if (start) {
				option = command;
			} else {
				calculate(Double.parseDouble(displayField.getText()));
				option = command;
				start = true;
			}
		}
	}

	public static void main(String[] args) {
		Caculator calculator = new Caculator();
	}

}