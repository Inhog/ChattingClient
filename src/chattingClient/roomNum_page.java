package chattingClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chattingClient.*;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.*;
import java.net.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class roomNum_page extends JFrame {

	private JPanel contentPane;
	private JTextField RoomName_TextField;
	private JTextField PW_TextField;
	
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;
	
	/**
	 * Create the frame.
	 */
	public roomNum_page(Socket socket) {
		this.socket = socket;
		try {
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
				
		setResizable(false);
		setVisible(true);
		setTitle("방 생성/참가"); 
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){ //닫기버튼 설정
			@Override
			public void windowClosing(WindowEvent R_WE)
			{
				new login_page(socket);
				setVisible(false); // 방번호 창 닫기
			}
		});
		
		setBounds(100, 100, 391, 297);
		//setAlwaysOnTop(true); // 항상 맨 위로 설정!!
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel PW_Label = new JLabel("비밀번호");
		PW_Label.setFont(new Font("굴림", Font.PLAIN, 16));
		PW_Label.setBounds(37, 137, 77, 25);
		contentPane.add(PW_Label);
		
		JLabel RoomName_Label = new JLabel("방 제목");
		RoomName_Label.setFont(new Font("굴림", Font.PLAIN, 16));
		RoomName_Label.setBounds(37, 69, 77, 25);
		contentPane.add(RoomName_Label);
		
		RoomName_TextField = new JTextField();
		RoomName_TextField.setFont(new Font("굴림", Font.PLAIN, 12));
		RoomName_TextField.setBounds(126, 65, 123, 36);
		contentPane.add(RoomName_TextField);
		RoomName_TextField.setColumns(10);
		
		PW_TextField = new JTextField();
		PW_TextField.setFont(new Font("굴림", Font.PLAIN, 12));
		PW_TextField.setColumns(10);
		PW_TextField.setBounds(126, 133, 123, 36);
		PW_TextField.setEditable(false);
		PW_TextField.setEnabled(false);
		contentPane.add(PW_TextField);
		
		
		JButton Create_Room_Button = new JButton("방 생성");
		Create_Room_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String RoomName = RoomName_TextField.getText();
				String PW = PW_TextField.getText();
				if(RoomName.equals(""))
					JOptionPane.showMessageDialog(null, "방 이름을 입력해주세요.", "방생성 실패", JOptionPane.ERROR_MESSAGE);
				else {
					if(PW.equals(""))			// 같은지 비교하는 메소드
						PW = "none";
					
					String send = "MakeRoom" + "|token|" + RoomName + "|token|" + PW;
					pw.println(send);
					pw.flush();				
					try {
						String result = br.readLine();
						if(result.equals("CAN")){
							setVisible(false);
							Runnable r = new chatting_page(socket,RoomName);
							Thread t = new Thread(r);
							t.start();
						}
						else {
							JOptionPane.showMessageDialog(null, "이미 생성된 방이 있습니다", "방생성 중복", JOptionPane.ERROR_MESSAGE);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		Create_Room_Button.setFont(new Font("굴림", Font.PLAIN, 14));
		Create_Room_Button.setBounds(12, 206, 102, 42);
		contentPane.add(Create_Room_Button);
		
		JButton Join_Button = new JButton("접속");
		Join_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent J_AE)
			{
					String RoomName = RoomName_TextField.getText();
					String PW = PW_TextField.getText();
					if(RoomName.equals(""))
						JOptionPane.showMessageDialog(null, "방 이름을 입력해주세요.", "방접속 실패", JOptionPane.ERROR_MESSAGE);
					else {
					
					if(PW.equals(""))			// 같은지 비교하는 메소드
						PW = "none";
					
					String send = "JoinRoom" + "|token|" + RoomName + "|token|" + PW;
					pw.println(send);
					pw.flush();				
					try {
						String result = br.readLine();
						if(result.equals("CAN")){
							setVisible(false);
							Runnable r = new chatting_page(socket,RoomName);
							Thread t = new Thread(r);
							t.start();
						}
						else {
							JOptionPane.showMessageDialog(null, "생성된 방이 없습니다.", "방접속 오류", JOptionPane.ERROR_MESSAGE);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		});
		Join_Button.setFont(new Font("굴림", Font.PLAIN, 14));
		Join_Button.setBounds(137, 206, 93, 42);
		contentPane.add(Join_Button);
		
		
		JButton btnNewButton = new JButton("로그아웃");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new login_page(socket);
			}
		});
		btnNewButton.setBounds(267, 206, 97, 42);
		contentPane.add(btnNewButton);
		
		
		JRadioButton Open_RadioButton = new JRadioButton("공개 방");
		Open_RadioButton.setSelected(true);
		Open_RadioButton.setFont(new Font("굴림", Font.PLAIN, 16));
		Open_RadioButton.setBounds(280, 71, 121, 23);
		contentPane.add(Open_RadioButton);
		
		JRadioButton Secret_RadioButton = new JRadioButton("비공개 방");
		Secret_RadioButton.setFont(new Font("굴림", Font.PLAIN, 16));
		Secret_RadioButton.setBounds(280, 110, 121, 23);
		contentPane.add(Secret_RadioButton);

		ButtonGroup RoomSet = new ButtonGroup();
		RoomSet.add(Open_RadioButton);
		RoomSet.add(Secret_RadioButton);
		
		Open_RadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PW_TextField.setText("");
				PW_TextField.setEnabled(false);
				PW_TextField.setEditable(false);
			}
		});
		Secret_RadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PW_TextField.setEnabled(true);
				PW_TextField.setEditable(true);
			}
		});
	}
}
