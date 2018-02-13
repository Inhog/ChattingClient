package practice0118;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.net.*;

import java.io.*;


public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

         
         
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					 Socket socket = new Socket("183.101.147.154", 10001);
//
//			         pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
//
//			         br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
//
//			         BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

					new login_page(socket);		// 소켓을 파라미터로 넘겨줌.
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
	});
	}
}
