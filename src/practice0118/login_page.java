package practice0118;

import practice0118.*; // 패키지 내부 다른 클래스 사용?
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

public class login_page extends JFrame {

	private JPanel contentPane;
	private JTextField ID_TextField;
	private JPasswordField PW_TextField;
	
	/**
	 * Launch the application.
	 */
	static login_page frame;
	private JLabel ID_Label;
	private JLabel PW_Label;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new login_page();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. 주석바꿔서 실험 2
	 */
	public login_page() {
		setTitle("로그인");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 482, 248);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ID_TextField = new JTextField(); 
		ID_TextField.setBounds(144, 24, 123, 36);
		ID_TextField.setColumns(10);
		ID_TextField.addKeyListener(new KeyAdapter() { //�Է¼� ����
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if(src.getText().length()>=15) ke.consume();
			}
		});
		contentPane.add(ID_TextField);
		
		PW_TextField = new JPasswordField();
		PW_TextField.setBounds(144, 107, 123, 36);
		PW_TextField.addKeyListener(new KeyAdapter() { //�Է¼� ����
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if(src.getText().length()>=15) ke.consume();
			}
		});
		contentPane.add(PW_TextField);
		
		JButton Login_Button = new JButton("로그인");
		Login_Button.setFont(new Font("굴림", Font.PLAIN, 16));
		Login_Button.setBounds(314, 28, 109, 23);
		contentPane.add(Login_Button);
		
		JButton Signup_Button = new JButton("회원가입");
		Signup_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				signUp_page sp;
				sp = new signUp_page(frame);
				sp.setVisible(true);
			}
		});
		Signup_Button.setFont(new Font("굴림", Font.PLAIN, 16));
		Signup_Button.setBounds(314, 112, 109, 23);
		contentPane.add(Signup_Button);
		
		ID_Label = new JLabel("ID");
		ID_Label.setFont(new Font("굴림", Font.PLAIN, 16));
		ID_Label.setBounds(30, 34, 66, 26);
		contentPane.add(ID_Label);
		
		PW_Label = new JLabel("PW");
		PW_Label.setFont(new Font("굴림", Font.PLAIN, 16));
		PW_Label.setBounds(30, 110, 66, 26);
		contentPane.add(PW_Label);
		
		
		
		Login_Button.addActionListener(new ActionListener()	 {
			public void actionPerformed(ActionEvent e) {
				String id_data = ID_TextField.getText();
				String pw_data;
				char[] a = new char[30];
				a = PW_TextField.getPassword();
				
				System.out.println(a);
				roomNum_page rp;
				rp = new roomNum_page(frame);
				rp.setVisible(true);
			}
		});

	}
}
