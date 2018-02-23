package chattingClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chattingClient.*;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.net.*;
import java.io.*;



public class login_page extends JFrame {

	private JPanel contentPane;
	private JTextField ID_TextField;
	private JPasswordField PW_TextField;
	private JLabel ID_Label;
	private JLabel PW_Label;


	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;
	
	
	/**
	 * Create the frame. 주석바꿔서 실험 2
	 */
	public login_page(Socket socket) {			// 소켓을 파라미터로 넘겨받음.
		this.socket = socket;					// 파라미터로 받은 소켓사용.
		try {									// 예외처리
			// Socket으로부터 InputStream과 OutputStream을 얻어와
			// BufferedReader 와 PrintWriter 형태로 변환시킴
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));	
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setVisible(true);
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
		Login_Button.addActionListener(new ActionListener()	 {
			public void actionPerformed(ActionEvent e) {
				String ID = ID_TextField.getText();
				String PW = String.copyValueOf(PW_TextField.getPassword());
				
				if(ID.trim().equals("") || PW.trim().equals(""))
				{
					JOptionPane.showMessageDialog(null, "모든 정보를 입력해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
					ID_TextField.setText("");
					PW_TextField.setText("");
				}
				else
				{
					String send = "logIn" + "|token|" + ID + "|token|" + PW;
					pw.println(send);
					pw.flush();
					try {
						String result = br.readLine();
						if(result.equals("CAN"))
						{
							//로그인 성공
							setVisible(false);
							// 서버로부터 로그인 정보가 맞다는 응답을 받으면, 방 접속창을 생성 이 때, ID를 생성자로 넘김
							new roomNum_page(socket); 
						}
						else
						{
							//로그인 실패
							JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 일치하지 않습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
						}
					} catch (IOException ee) {
						ee.printStackTrace();
					}
				}
			}
		});
		contentPane.add(Login_Button);
		
		JButton Signup_Button = new JButton("회원가입");
		Signup_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new signUp_page(socket);
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
			


	}
}
