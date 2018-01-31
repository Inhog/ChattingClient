package practice0118;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.JEditorPane;
import java.awt.ScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Color;

public class chatting_page extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public chatting_page() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea TextLog = new JTextArea();
		TextLog.setBounds(1, 1, 241, 30);
		TextLog.setFont(new Font("Monospaced", Font.PLAIN, 13));
		TextLog.setText("Input message");
		contentPane.add(TextLog);
		
		JScrollPane Text_scrollPane = new JScrollPane(TextLog);
		Text_scrollPane.setBounds(0, 421, 191, 32);
		contentPane.add(Text_scrollPane);
		
		JLabel RoomName_Label = new JLabel("\"방 제목\"");
		RoomName_Label.setBackground(Color.WHITE);
		RoomName_Label.setHorizontalAlignment(SwingConstants.CENTER);
		RoomName_Label.setBounds(0, 0, 273, 30);
		contentPane.add(RoomName_Label);
		
		JLabel Member_Label = new JLabel("멤버");
		Member_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Member_Label.setBounds(273, 0, 134, 30);
		contentPane.add(Member_Label);
		
		JButton Send_Button = new JButton("전송");
		Send_Button.setBounds(191, 421, 82, 32);
		contentPane.add(Send_Button);
		
		JTextPane ChatLog_TextPane = new JTextPane();
		ChatLog_TextPane.setText("ddddd");
		contentPane.add(ChatLog_TextPane);
		
		JScrollPane Chat_scrollPane = new JScrollPane(ChatLog_TextPane);
		Chat_scrollPane.setBounds(0, 30, 273, 389);
		contentPane.add(Chat_scrollPane);
		
		JButton Exit_Button = new JButton("나가기");
		Exit_Button.setBounds(281, 422, 112, 32);
		contentPane.add(Exit_Button);
		
		JTextPane Member_TextPane = new JTextPane();
		JScrollPane Member_scrollPane = new JScrollPane(Member_TextPane);
		Member_scrollPane.setBounds(273, 30, 130, 389);
		contentPane.add(Member_scrollPane);
	
		
	}
}
