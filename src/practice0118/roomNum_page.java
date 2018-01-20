package practice0118;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class roomNum_page extends JFrame {

	private JPanel contentPane;
	private JTextField RoomNum_Field;
	private JTextField textField;
	private JTextField user_name;
	
	/**
	 * Create the frame.
	 */
	public roomNum_page(String name, login_page lp) { // ID�� �α���â�� �Ķ���ͷ� ����
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){ //�ݱ��ư ����
			@Override
			public void windowClosing(WindowEvent WE)
			{
				lp.setEnabled(true); // �α���â ���� ����
				setVisible(false); // ���ȣ â �ݱ�
			}
		});
		setBounds(100, 100, 320, 209);
		setAlwaysOnTop(true); // �׻� �� ����
		lp.setEnabled(false); // �α���â ���� ����
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		RoomNum_Field = new JTextField();
		RoomNum_Field.setFont(new Font("����", Font.PLAIN, 12));
		RoomNum_Field.setBounds(156, 65, 123, 36);
		contentPane.add(RoomNum_Field);
		RoomNum_Field.setColumns(10);
		
		textField = new JTextField();
		textField.setFont(new Font("����", Font.PLAIN, 16));
		textField.setText("\uCC44\uD305\uBC29\uBC88\uD638");
		textField.setBackground(UIManager.getColor("Label.background"));
		textField.setBounds(12, 72, 106, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton Join_Button = new JButton("\uC811\uC18D");
		Join_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lp.setVisible(false);
				setVisible(false);
				chatting_page cp;
				cp = new chatting_page();
				cp.setVisible(true);
			}
		});
		Join_Button.setFont(new Font("����", Font.PLAIN, 16));
		Join_Button.setBounds(96, 139, 106, 21);
		contentPane.add(Join_Button);
		
		user_name = new JTextField();
		user_name.setBounds(96, 10, 106, 35);
		contentPane.add(user_name);
		user_name.setColumns(10);
		user_name.setText(name);
	}
}
