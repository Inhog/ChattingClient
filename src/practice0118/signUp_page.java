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
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.*;
import java.io.*;

public class signUp_page extends JFrame {

	private JPanel contentPane;
	private JTextField ID_TextField;
	private JTextField PW_TextField;
	private JButton Signup_Button;
	private JButton Close_Button;
	private JLabel ID_Label;
	private login_page lp;
	private JLabel PW_Label;
	
	
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;
	
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	
	public signUp_page(Socket socket) {
		this.socket = socket;
		try {
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		setVisible(true);
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){ //닫기버튼 설정
			@Override
			public void windowClosing(WindowEvent S_WE)
			{
				setVisible(false); // 방번호 창 닫기
				new login_page(socket);
			}
		});
		setBounds(100, 100, 325, 340);
		setAlwaysOnTop(true);
		
		
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
		Signup_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ID = ID_TextField.getText();
				String PW = PW_TextField.getText();
				
				if(ID.trim().equals("") || PW.trim().equals(""))		// trim : 문자열 앞뒤 공백제거
				{
					JOptionPane.showMessageDialog(null, "모든 정보를 입력해주세요.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
					ID_TextField.setText("");
					PW_TextField.setText("");
				}
				else
					{
					String send = "signUP|" + ID + "|" + PW;
					pw.println(send);
					pw.flush();
					
					try {
						System.out.println("여기까지 성공");
						String result = br.readLine();
						if(result.equals("CAN"))
						{
							//회원가입이 완료되었습니다. 로그인해주세요 라는 창 띄우기.
							JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다." + '\n' + "로그인 해주세요.", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
							new login_page(socket);
						}
						else
						{
							//이미 가입된 아이디입니다.
							System.out.println(result);
							JOptionPane.showMessageDialog(null, "이미 가입된 아이디입니다.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
					}
	
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		contentPane.add(Signup_Button);
		
		Close_Button = new JButton("\uCDE8\uC18C");
		Close_Button.setFont(new Font("����", Font.PLAIN, 16));
		Close_Button.setBounds(171, 174, 95, 23);
		Close_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);				// 프레임 닫고
				new login_page(socket);
			}
		});
		contentPane.add(Close_Button);
	
		
		ID_Label = new JLabel("ID");
		ID_Label.setBounds(40, 38, 95, 36);
		contentPane.add(ID_Label);
		
		PW_Label = new JLabel("PW");
		PW_Label.setBounds(40, 111, 95, 36);
		contentPane.add(PW_Label);
	}
}
