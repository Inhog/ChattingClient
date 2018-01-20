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
import javax.swing.JButton;
import javax.swing.JLabel;

public class signUp_page extends JFrame {

	private JPanel contentPane;
	private JTextField IDField;
	private JTextField Id_Field;
	private JTextField PWField;
	private JTextField Pw_Field;
	private JButton Signup_Button;
	private JButton Close_Button;
	private JLabel lblNewLabel;
	private login_page lp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signUp_page frame = new signUp_page();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public signUp_page() {
		lp = new login_page();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		IDField = new JTextField();
		IDField.setText("ID");
		IDField.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		IDField.setEditable(false);
		IDField.setColumns(10);
		IDField.setBackground(new Color(169, 169, 169));
		IDField.setBounds(40, 43, 106, 21);
		contentPane.add(IDField);
		
		Id_Field = new JTextField();
		Id_Field.setColumns(10);
		Id_Field.setBounds(160, 38, 123, 36);
		contentPane.add(Id_Field);
		
		PWField = new JTextField();
		PWField.setText("PW");
		PWField.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		PWField.setEditable(false);
		PWField.setColumns(10);
		PWField.setBackground(new Color(169, 169, 169));
		PWField.setBounds(40, 119, 106, 21);
		contentPane.add(PWField);
		
		Pw_Field = new JTextField();
		Pw_Field.setColumns(10);
		Pw_Field.setBounds(160, 111, 123, 36);
		contentPane.add(Pw_Field);
		
		Signup_Button = new JButton("\uAC00\uC785");
		Signup_Button.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		Signup_Button.setBounds(40, 174, 95, 23);
		contentPane.add(Signup_Button);
		
		Close_Button = new JButton("\uCDE8\uC18C");
		Close_Button.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		Close_Button.setBounds(171, 174, 95, 23);
		contentPane.add(Close_Button);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(89, 236, 95, 36);

		contentPane.add(lblNewLabel);
	}
}
