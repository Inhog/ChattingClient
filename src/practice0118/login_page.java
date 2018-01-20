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

public class login_page extends JFrame {

	private JPanel contentPane;
	private JTextField Id_Field;
	private JTextField IDField;
	private JTextField PWField;
	private JPasswordField passwordField;
	
	/**
	 * Launch the application.
	 */
	static login_page frame;
	
	
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
	 * Create the frame. 주석바꿔서 실험
	 */
	public login_page() {
		setTitle("Log-in");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 482, 248);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Id_Field = new JTextField(); 
		Id_Field.setBounds(144, 24, 123, 36);
		Id_Field.setColumns(10);
		Id_Field.addKeyListener(new KeyAdapter() { //�Է¼� ����
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if(src.getText().length()>=15) ke.consume();
			}
		});
		contentPane.add(Id_Field);
		
		IDField = new JTextField();
		IDField.setEditable(false);
		IDField.setFont(new Font("����", Font.PLAIN, 18));
		IDField.setText("ID");
		IDField.setBackground(new Color(169, 169, 169));
		IDField.setBounds(24, 29, 106, 21);
		contentPane.add(IDField);
		IDField.setColumns(10);
		
		PWField = new JTextField();
		PWField.setEditable(false);
		PWField.setFont(new Font("����", Font.PLAIN, 18));
		PWField.setText("PW");
		PWField.setColumns(10);
		PWField.setBackground(new Color(169, 169, 169));
		PWField.setBounds(24, 112, 106, 21);
		contentPane.add(PWField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 107, 123, 36);
		passwordField.addKeyListener(new KeyAdapter() { //�Է¼� ����
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if(src.getText().length()>=15) ke.consume();
			}
		});
		contentPane.add(passwordField);
		
		JButton Login_Button = new JButton("\uB85C\uADF8\uC778");
		Login_Button.setFont(new Font("����", Font.PLAIN, 16));
		Login_Button.setBounds(314, 28, 109, 23);
		contentPane.add(Login_Button);
		
		JButton Signup_Button = new JButton("\uD68C\uC6D0\uAC00\uC785");
		Signup_Button.setFont(new Font("����", Font.PLAIN, 16));
		Signup_Button.setBounds(314, 103, 109, 23);
		contentPane.add(Signup_Button);
		
		
		
		Login_Button.addActionListener(new ActionListener()	 {
			public void actionPerformed(ActionEvent e) {
				String id_data = Id_Field.getText();
				String pw_data;
				char[] a = new char[30];
				a = passwordField.getPassword();
				
				System.out.println(a);
				roomNum_page rp;
				rp = new roomNum_page(id_data, frame);
				rp.setVisible(true);
			}
		});

	}
}
