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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class roomNum_page extends JFrame {

	private JPanel contentPane;
	private JTextField RoomName_TextField;
	private JTextField PW_TextField;
	
	/**
	 * Create the frame.
	 */
	public roomNum_page(login_page lp) {
		setTitle("방 생성/참가"); // ID와 로그인창을 파라미터로 받음...(수정 로그인 페이지만 받음)
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){ //닫기버튼 설정
			@Override
			public void windowClosing(WindowEvent R_WE)
			{
				lp.setEnabled(true); // 로그인창 수정 가능
				setVisible(false); // 방번호 창 닫기
			}
		});
		setBounds(100, 100, 472, 281);
		setAlwaysOnTop(true); // 항상 맨 위로 설정!!
		lp.setEnabled(false); // 로그인창 수정 금지
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		RoomName_TextField = new JTextField();
		RoomName_TextField.setFont(new Font("굴림", Font.PLAIN, 12));
		RoomName_TextField.setBounds(126, 65, 123, 36);
		contentPane.add(RoomName_TextField);
		RoomName_TextField.setColumns(10);
		
		JButton Create_Room_Button = new JButton("방 생성");
		Create_Room_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lp.setVisible(false);
				setVisible(false);
				chatting_page c_cp= new chatting_page();
				c_cp.setVisible(true);
			}
		});
		Create_Room_Button.setFont(new Font("굴림", Font.PLAIN, 16));
		Create_Room_Button.setBounds(37, 206, 106, 21);
		contentPane.add(Create_Room_Button);
		
		JButton Join_Button = new JButton("접속");
		Join_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent J_AE)
			{
				lp.setVisible(false);
				setVisible(false);
				chatting_page j_cp = new chatting_page();
				j_cp.setVisible(true);
				
			}
		});
		
		Join_Button.setFont(new Font("굴림", Font.PLAIN, 16));
		Join_Button.setBounds(186, 206, 106, 21);
		contentPane.add(Join_Button);
		
		JButton Random_Join_Button = new JButton("랜덤 접속");
		Random_Join_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent RJ_AE)
			{
				lp.setVisible(false);
				setVisible(false);
				chatting_page rj_cp = new chatting_page();
				rj_cp.setVisible(true);
			}
		});
		Random_Join_Button.setFont(new Font("굴림", Font.PLAIN, 16));
		Random_Join_Button.setBounds(324, 206, 106, 21);
		contentPane.add(Random_Join_Button);
		
		JLabel RoomName_Label = new JLabel("방 제목");
		RoomName_Label.setFont(new Font("굴림", Font.PLAIN, 16));
		RoomName_Label.setBounds(37, 69, 77, 25);
		contentPane.add(RoomName_Label);
		
		JRadioButton Open_RadioButton = new JRadioButton("공개 방");
		Open_RadioButton.setSelected(true);
		//Open_RadioButton.setSelected(true);
		Open_RadioButton.setFont(new Font("굴림", Font.PLAIN, 16));
		Open_RadioButton.setBounds(280, 71, 121, 23);
		contentPane.add(Open_RadioButton);
		
		JRadioButton Secret_RadioButton = new JRadioButton("비공개 방");
		Secret_RadioButton.setFont(new Font("굴림", Font.PLAIN, 16));
		Secret_RadioButton.setBounds(280, 110, 121, 23);
		contentPane.add(Secret_RadioButton);

		
		
		PW_TextField = new JTextField();
		PW_TextField.setFont(new Font("굴림", Font.PLAIN, 12));
		PW_TextField.setColumns(10);
		PW_TextField.setBounds(126, 133, 123, 36);
		contentPane.add(PW_TextField);
		
		ButtonGroup RoomSet = new ButtonGroup();
		RoomSet.add(Open_RadioButton);
		RoomSet.add(Secret_RadioButton);
		
		Secret_RadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PW_TextField.setEnabled(false);
				PW_TextField.setEditable(false);
			}
		});
		Open_RadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PW_TextField.setEnabled(true);
				PW_TextField.setEditable(true);
			}
		});
		
		JLabel PW_Label = new JLabel("비밀번호");
		PW_Label.setFont(new Font("굴림", Font.PLAIN, 16));
		PW_Label.setBounds(37, 137, 77, 25);
		contentPane.add(PW_Label);
		
		
	}
}
