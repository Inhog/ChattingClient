package practice0118;

import practice0118.*;
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
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.JEditorPane;
import java.awt.ScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import javax.swing.ScrollPaneConstants;



public class chatting_page extends JFrame implements Runnable {
	private String line;
	private JPanel contentPane;
	private final static String newline = "\n";
	private JTextArea ChatLog_TextPane;
	private JScrollPane Chat_scrollPane;
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;
	private String Roomname;
	private JTextPane Member_TextPane;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	
	public chatting_page(Socket socket, String Roomname) {
		setResizable(false);
		
		
		this.socket = socket;
		this.Roomname = Roomname;
		try {
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		setVisible(true);
		setTitle("채팅창"); // 방 생성/참가의 버튼으로부터 호출되기 떄문에 활용을 위해 파라미터로 받는다.
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) { 
            	String send = "OutRoom" + "|token|" + Roomname;
            	pw.println(send);
				pw.flush();
				setVisible(false);
				new roomNum_page(socket);
            }
    });
		setBounds(100, 100, 418, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTextArea TextLog = new JTextArea();
		TextLog.setBounds(1, 1, 241, 30);
		TextLog.setFont(new Font("Monospaced", Font.PLAIN, 13));
		contentPane.add(TextLog);
	
		
		JScrollPane Text_scrollPane = new JScrollPane(TextLog);
		Text_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Text_scrollPane.setBounds(0, 414, 191, 39);
		contentPane.add(Text_scrollPane);
		
		JLabel RoomName_Label = new JLabel(Roomname);
		RoomName_Label.setBackground(Color.WHITE);
		RoomName_Label.setHorizontalAlignment(SwingConstants.CENTER);
		RoomName_Label.setBounds(0, 0, 273, 30);
		contentPane.add(RoomName_Label);
		
		JLabel Member_Label = new JLabel("멤버");
		Member_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Member_Label.setBounds(273, 0, 134, 30);
		contentPane.add(Member_Label);
		
		JButton Send_Button = new JButton("전송");
		Send_Button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String text = TextLog.getText();
				String[] MSGS = text.split(" ");
				
				if(text.trim().length() > 1000) {	//천 자 미만으로 써달라고 경고창띄우기..지우지는 않기.
					JOptionPane.showMessageDialog(null, "천 자 미만으로 입력해주십시오.", "입력 실패", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(text.trim().length() == 0) {		// 공백이면 TextField 비우기
					TextLog.setText("");
				}
				
				else if(MSGS[0].equals("/w"))		// 귓속말
				{
					if(MSGS.length >= 3)
					{
						String Wsend = "whis"+ "|token|" + Roomname + "|token|"+ MSGS[1] + "|token|";
						
						ChatLog_TextPane.append("[귓속말] "+ MSGS[1] + "에게 : ");
						
						for(int i = 2; i < MSGS.length ; i++) {
							Wsend += (MSGS[i] + " ");
						ChatLog_TextPane.append(MSGS[i]+" ");
						}
						ChatLog_TextPane.append("\n");
						
						
						pw.println(Wsend);
						pw.flush();
						TextLog.setText("");
					}
					
					else
					{
						TextLog.setText("");
						ChatLog_TextPane.append("귓속말 사용범 : /w 받는사람ID 할말\n");
		            	ChatLog_TextPane.setCaretPosition(ChatLog_TextPane.getText().length());	
					}
				}
				
				else
				{
					String send = "msg"+ "|token|" + Roomname + "|token|" + text;
					if(!(text.equals(""))) {
						pw.println(send);
						pw.flush();
						TextLog.setText("");
					}
				}
			}
		});
		Send_Button.setBounds(191, 414, 82, 40);
		contentPane.add(Send_Button);
		
		
		TextLog.setLineWrap(true);
		TextLog.setWrapStyleWord(true);

		
		TextLog.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e) {
				JTextArea t= (JTextArea)e.getSource();
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String[] MSGS = t.getText().split(" ");
					 
					if(t.getText().trim().length() > 1000) {		//천 자 미만으로 써달라고 경고창띄우기..지우지는 않기.
						JOptionPane.showMessageDialog(null, "천 자 미만으로 입력해주십시오.", "입력 실패", JOptionPane.ERROR_MESSAGE);
					}
					
					else if(t.getText().trim().length() == 0) {
						TextLog.setText("");
						e.consume();
					}
					
					else if(MSGS[0].equals("/w")) {
						if(MSGS.length >= 3)
						{
							String Wsend = "whis"+ "|token|" + Roomname + "|token|"+ MSGS[1] + "|token|";
							ChatLog_TextPane.append("[귓속말] "+ MSGS[1] + "에게 : ");
							
							for(int i = 2; i < MSGS.length ; i++) {
								Wsend += (MSGS[i] + " ");
							ChatLog_TextPane.append(MSGS[i]+" ");
							}
							ChatLog_TextPane.append("\n");
							
							pw.println(Wsend);
							pw.flush();								
			            	ChatLog_TextPane.setCaretPosition(ChatLog_TextPane.getText().length());	
							TextLog.setText("");
							e.consume();
						}
						
						else
						{
							TextLog.setText("");
							e.consume();
							ChatLog_TextPane.append("귓속말 사용법 : /w 받는사람ID 할말\n");
			            	ChatLog_TextPane.setCaretPosition(ChatLog_TextPane.getText().length());	
						}
					}
					
					else {
						String send = "msg" + "|token|" + Roomname + "|token|" + TextLog.getText();
						pw.println(send);
						pw.flush();
						TextLog.setText("");
						e.consume();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton Exit_Button = new JButton("나가기");
		Exit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String send = "OutRoom" + "|token|" + Roomname;
            	pw.println(send);
				pw.flush();
				setVisible(false);
				new roomNum_page(socket);
			}
		});
		
		ChatLog_TextPane = new JTextArea();
		ChatLog_TextPane.setEditable(false);
		contentPane.add(ChatLog_TextPane);
		ChatLog_TextPane.setLineWrap(true);
		ChatLog_TextPane.setWrapStyleWord(true);
		
		JScrollPane Chat_scrollPane_1 = new JScrollPane(ChatLog_TextPane);
		Chat_scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Chat_scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Chat_scrollPane_1.setBounds(0, 30, 273, 381);
		contentPane.add(Chat_scrollPane_1);
		Exit_Button.setBounds(273, 413, 130, 40);
		contentPane.add(Exit_Button);
		
		
		Member_TextPane = new JTextPane();
		Member_TextPane.setEditable(false);
		JScrollPane Member_scrollPane = new JScrollPane(Member_TextPane);
		Member_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Member_scrollPane.setBounds(273, 30, 130, 381);
		contentPane.add(Member_scrollPane);

		
	}
	
	@Override
	public void run() {
		boolean finish = false;
		try {
			while((line=br.readLine())!=null) {
				//while((!Thread.currentThread().isInterrupted()) {
				System.out.println(line+"읽음");
				String array[] = line.split("\\|token\\|");
				
	            switch (array[0]) {

	            case "OUT": // [1] : 나간사람ID
	            	ChatLog_TextPane.append(array[1]+"님이 나가셨습니다."+"\n");
	            	ChatLog_TextPane.setCaretPosition(ChatLog_TextPane.getText().length());
	                break;
	       
	               
	            case "msg": // [1] : ID , [2] : 내용
	            	ChatLog_TextPane.append(array[1]+ " : " + array[2] + "\n");
	            	ChatLog_TextPane.setCaretPosition(ChatLog_TextPane.getText().length());
	                break;
	                
	                
	            case "whisper": // [1] : ID , [2] : 내용
	            	ChatLog_TextPane.append("[귓속말] " + array[1] + "으로부터 : " + array[2] + "\n");
	            	ChatLog_TextPane.setCaretPosition(ChatLog_TextPane.getText().length());
	                break;
	                
	                
	            case "GuestList": //[1] : 방제, [2] : 내용
	            	String members = "";
	            	for(int i = 1 ; i < array.length ; i++)
	            		members += (array[i] + "\n");
	            	Member_TextPane.setText(members);
	            	break;
	            	
	            case "NEW":	// [1] : 들어온 사람 ID
	            	ChatLog_TextPane.append(array[1]+"님이 들어오셨습니다."+"\n");
	            	ChatLog_TextPane.setCaretPosition(ChatLog_TextPane.getText().length());
	                break;
	            
	            case "Bye":
	            	finish = true;
	            }
	            if(finish)
	            	break;
				//ChatLog_TextPane.append(line+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}