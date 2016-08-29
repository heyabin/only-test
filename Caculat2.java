package lianxi2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Caculat2 extends JFrame {
	private JTextField dataTextField1;
	private JTextField dataTextField2;
	private JTextField result;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JButton button;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	
	
	public Caculat2() {
		this.setTitle("计算器");
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		int width =(int)ds.width;
		int height =(int)ds.height;
		this.setBounds((width-400)/2, (height-300)/2, 400, 300);
		this.setVisible(true);
		//this.setResizable(false);//设置窗体的大小不可变
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.dataTextField1 = new JTextField(20);
		this.dataTextField2 = new JTextField(20);
		this.result = new JTextField(20);
		this.label1 = new JLabel("第一个操作数");
		this.label2 = new JLabel("第二个操作数");
		this.label3 = new JLabel("计算结果");
		
		this.button = new JButton("计算");
		this.radioButton1 = new JRadioButton("+");
		this.radioButton2 = new JRadioButton("-");
		this.radioButton3 = new JRadioButton("*");
		this.radioButton4 = new JRadioButton("/");
		this.setLayout(new GridLayout(5,1));
		
		//处理按钮
		
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton1);
		group.add(radioButton2);
		group.add(radioButton3);
		group.add(radioButton4);
		
		JPanel panel1 = new JPanel(new GridLayout(1,2));
		panel1.add(this.label1);
		panel1.add(dataTextField1);
		
		JPanel panel2 = new JPanel(new GridLayout(1,4));
		panel2.add(this.radioButton1);
		panel2.add(this.radioButton2);
		panel2.add(this.radioButton3);
		panel2.add(this.radioButton4);
		
		JPanel panel3 = new JPanel(new GridLayout(1,2));
		panel3.add(this.label2);
		panel3.add(dataTextField2);
		
		JPanel panel4 = new JPanel(new GridLayout(1,2));
		panel4.add(this.label3);
		panel4.add(result);
		JPanel panel5 = new JPanel(new GridLayout(1,1));
		panel5.add(button);
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		
		this.setVisible(true);
		//button设置事件监听(事件源实例化以后，必须授权。注册该类事件的监听器，使用addxxxListener(xxxListener)方法来注册监听器)
		button.addActionListener(new ButtonListener());
	}
	//内部类，定义相应的事件处理类，同时实现相应的接口(定义事件处理类，并且实现监听器接口)
		class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//保存第一个操作数
			String data1 = dataTextField1.getText();
			//保存第二个操作数
			String data2 = dataTextField2.getText();
			//保存加减乘除的
			String option = "";
			if (radioButton1.isSelected()) {
				option = radioButton1.getText();
			}else if (radioButton2.isSelected()) {
				option = radioButton2.getText();
			}else if (radioButton3.isSelected()) {
				option = radioButton3.getText();
			}else if(radioButton4.isSelected()){
				option = radioButton4.getText();
			}
			//得到数据之后判断数据的合法性
			if (option=="") {
				JOptionPane.showMessageDialog(null, "请选择运算符");
			}
			//开始进行计算
			double d =caculator(data1, data2, option);
			//结果显示在文本框中
			result.setText(String.valueOf(d));
		}
		
		public double caculator(String data1,String data2,String option){
			double result = Double.MAX_VALUE;
			
			double d1 =0.0;
			double d2 =0.0;
			try {
				d1 =Double.parseDouble(data1);
				d2 =Double.parseDouble(data2);	
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "输入数字有错");
			}
			 
			if (option == "+") {
				result = d1+d2;
			}else if(option == "-"){
				result = d1-d2;
			}else if(option == "*"){
				result = d1*d2;
			}else if(option == "/"){
				if (d2!=0) {
					result = d1/d2;
				}else{
				JOptionPane.showMessageDialog(null, "除数不能为0");
				}
				
			}
			return result;
		}
	}
	public static void main(String[] args) {
		Caculat2 caculat = new Caculat2();
		
	}
	
}
