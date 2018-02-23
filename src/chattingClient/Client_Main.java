package chattingClient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.SystemColor;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Client_Main {

	private JFrame frmIp;
	private JTextField IP_TextField;
	static private Client_Main window;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Client_Main();
					window.frmIp.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client_Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIp = new JFrame();
		frmIp.setTitle("IP 입력창");
		frmIp.setResizable(false);
		frmIp.setBounds(100, 100, 373, 237);
		frmIp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIp.getContentPane().setLayout(null);
		
		IP_TextField = new JTextField();
		IP_TextField.setBounds(66, 38, 231, 35);
		frmIp.getContentPane().add(IP_TextField);
		IP_TextField.setColumns(10);
		
		JLabel IP_Label = new JLabel("서버 IP");
		IP_Label.setBounds(12, 38, 57, 35);
		frmIp.getContentPane().add(IP_Label);
		
		JTextArea EX_TextArea = new JTextArea();
		EX_TextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		EX_TextArea.setBackground(UIManager.getColor("Button.background"));
		EX_TextArea.setEnabled(false);
		EX_TextArea.setEditable(false);
		EX_TextArea.setTabSize(14);
		EX_TextArea.setText("EX ) 1.2.3.4");
		EX_TextArea.setBounds(66, 83, 162, 38);
		frmIp.getContentPane().add(EX_TextArea);
		
		JButton Join_Button = new JButton("접속");
		Join_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Server_ip = IP_TextField.getText();
				Socket socket;
				try {
					socket = new Socket(Server_ip, 10001);
					new login_page(socket);
					window.frmIp.setVisible(false);
				} catch (UnknownHostException e) {
					JOptionPane.showMessageDialog(null, "해당 서버가 존재하지 않습니다.", "접속 실패", JOptionPane.ERROR_MESSAGE);
					IP_TextField.setText("");
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "해당 서버가 존재하지 않습니다.", "접속 실패", JOptionPane.ERROR_MESSAGE);
					IP_TextField.setText("");
					e.printStackTrace();
				}
				// 소켓을 파라미터로 넘겨줌.
			}
		});
		Join_Button.setFont(new Font("굴림", Font.PLAIN, 14));
		Join_Button.setBounds(66, 131, 97, 48);
		frmIp.getContentPane().add(Join_Button);
		
		JButton Erase_Button = new JButton("지우기");
		Erase_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IP_TextField.setText("");
			}
		});
		Erase_Button.setFont(new Font("굴림", Font.PLAIN, 14));
		Erase_Button.setBounds(201, 131, 97, 48);
		frmIp.getContentPane().add(Erase_Button);
	}
}
