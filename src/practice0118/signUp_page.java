package practice0118;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class signUp_page extends JFrame {

	private JPanel contentPane;
	private JTextField ID_TextField;
	private JTextField PW_TextField;
	private JButton Signup_Button;
	private JButton Close_Button;
	private JLabel ID_Label;
	private login_page lp;
	private JLabel PW_Label;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public signUp_page(login_page lp) {
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){ //닫기버튼 설정
			@Override
			public void windowClosing(WindowEvent S_WE)
			{
				lp.setEnabled(true); // 로그인창 수정 가능
				setVisible(false); // 방번호 창 닫기
			}
		});
		setBounds(100, 100, 325, 340);
		setAlwaysOnTop(true);
		lp.setEnabled(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ID_TextField = new JTextField();
		ID_TextField.setColumns(10);
		ID_TextField.setBounds(160, 38, 123, 36);
		contentPane.add(ID_TextField);
		
		PW_TextField = new JTextField();
		PW_TextField.setColumns(10);
		PW_TextField.setBounds(160, 111, 123, 36);
		contentPane.add(PW_TextField);
		
		Signup_Button = new JButton("\uAC00\uC785");
		Signup_Button.setFont(new Font("����", Font.PLAIN, 16));
		Signup_Button.setBounds(40, 174, 95, 23);
		contentPane.add(Signup_Button);
		
		Close_Button = new JButton("\uCDE8\uC18C");
		Close_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Close_Button.setFont(new Font("����", Font.PLAIN, 16));
		Close_Button.setBounds(171, 174, 95, 23);
		contentPane.add(Close_Button);
		
		ID_Label = new JLabel("ID");
		ID_Label.setBounds(40, 38, 95, 36);

		contentPane.add(ID_Label);
		
		PW_Label = new JLabel("PW");
		PW_Label.setBounds(40, 111, 95, 36);
		contentPane.add(PW_Label);
	}
}
