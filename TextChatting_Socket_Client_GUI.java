package chap10_Homework2_22112204_박형건;
/*
 * 파일명: chap10_Homework1_22112204_박형건 
 * 프로그램의 목적 및 기능 : Full-duplex, bidirectional Text Chatting Program
 * - class TextChatting_Socket_Client_GUI
 * 프로그램 작성자 : 박형건 (2023/11/16)
 * 학번 : 22112204
 * =======================
 * 최종 수정일: 2023.11.22
 * =======================
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;



public class TextChatting_Socket_Client_GUI extends JFrame{
	static private JTextArea jtxt_display_area=null;
	private JTextArea jtxt_msg_input_area=null;
	private String input_msg=null;
	static private JTextField jtxt_serv_addr=null;
	static private JTextField jtxt_cli_addr=null;
	static private DataInputStream sockDataInputstream=null;
	static private DataOutputStream sockDataOutputstream=null;
	
	private static int port_no=5056;
	
	public TextChatting_Socket_Client_GUI()
	{
		setTitle("JavaSwing-based TextChatting_Client");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridLayout gridLayout=new GridLayout(7,2,5,5);
		
		Container c=getContentPane();
		c.setBackground(Color.LIGHT_GRAY);
		c.setLayout(new FlowLayout());
		
		JPanel pnl_addr=new JPanel();
		Border border_addr=BorderFactory.createTitledBorder("Servrer/Client Address");
		pnl_addr.setBorder(border_addr);
		c.add(pnl_addr);
		
		JLabel I_serv_addr=new JLabel("Server Addr");
		pnl_addr.add(I_serv_addr);
		JTextField tf_serv_addr=new JTextField("127.0.0.1");
		tf_serv_addr.setBackground(Color.YELLOW);
		pnl_addr.add(tf_serv_addr);
		
		JLabel I_cli_addr=new JLabel("ClientAddr");
		pnl_addr.add(I_cli_addr);
		
		JTextField tf_cli_addr=new JTextField("127.0.0.1");
		tf_cli_addr.setBackground(Color.YELLOW);
		pnl_addr.add(tf_cli_addr);
		
		JPanel pnl_display_area=new JPanel();
		Border border_text_area=BorderFactory.createTitledBorder("Program Progress / Received Message");
		pnl_display_area.setBorder(border_text_area);
		
		c.add(pnl_display_area);
		
		JTextArea display_area=new JTextArea("Constructor excuted...\n",15,30);
		pnl_display_area.add(new JScrollPane(display_area));
		jtxt_display_area=display_area;
		
		JPanel pnl_message_input_area=new JPanel();
		Border border_message_input_area=BorderFactory.createTitledBorder("Input message to be sent");
		pnl_message_input_area.setBorder(border_message_input_area);
		
		c.add(pnl_message_input_area);
		
		JTextArea msg_input_area=new JTextArea("Sample mesage to be sent to server",3,30);
		pnl_message_input_area.add(new JScrollPane(msg_input_area));
		jtxt_msg_input_area=msg_input_area;
		
		JButton send_button=new JButton("Send Text Message to Server");
		send_button.setLocation(100,100);
		send_button.setSize(150,50);
		send_button.setBackground(Color.GREEN);
		send_button.addActionListener(new ActionHandler());
		c.add(send_button);
		
		setSize(400,600);
		setVisible(true);
		
		
	}
	static private void setDataInputStream(DataInputStream sockDinStr)
	{
		sockDataInputstream=sockDinStr;
		
	}
	static private void setDataOutputStream(DataOutputStream sockDoutStr)
	{
		sockDataOutputstream=sockDoutStr;
	}
	
	private class ActionHandler implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton b=(JButton)e.getSource();
			if(b.getText().equals("Send Text Message to Server"))
			{
				String input_msg=jtxt_msg_input_area.getText();
				jtxt_display_area.append("<<"+input_msg+"\n");
				jtxt_msg_input_area.setText("");
				try {
					sockDataOutputstream.writeUTF(input_msg);
				}catch(IOException e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args)throws IOException{
		InetAddress inetAddr=null;
		InetAddress myAddr=null;
		int serv_port_no=5056;
		Socket servSocket=null;
		DataInputStream sockDataInputstream=null;
		DataOutputStream sockDataOutputstream=null;
		
		TextChatting_Socket_Client_GUI gui_TxtChat_Server =new TextChatting_Socket_Client_GUI();
		
		myAddr=InetAddress.getByName("localhost");
		System.out.print("Socket Client::My inetaddress = " + myAddr+"\n");
		jtxt_display_area.append("Socket Client:: client_inetaddr= "+myAddr+"\n");
		jtxt_display_area.append("Socket Client:: Trying to connect server ....\n");
		servSocket=new Socket("127.0.0.1", serv_port_no);
		
		SocketAddress serv_addr=servSocket.getLocalSocketAddress();
		System.out.print("Socket Client:: Socket is opened ....\n");
		jtxt_display_area.append("Socket Client::Socket is opened ....\n");
		
		if(servSocket==null)
		{
			System.out.println("Error in socket creation/opening, cliSocket is null!!");
			return;
		}
		else
		{
			inetAddr=servSocket.getInetAddress();
			System.out.print("Socket client::connected to server ("+servSocket+") ....\n");
		}
		jtxt_display_area.append("Socket client::connected to server ("+servSocket+") ....\n");
		try
		{
			sockDataInputstream=new DataInputStream(servSocket.getInputStream());
			setDataInputStream(sockDataInputstream);
			sockDataOutputstream=new DataOutputStream(servSocket.getOutputStream());
			setDataOutputStream(sockDataOutputstream);
			
			System.out.print("Socket_client is ready now ....\n");
			jtxt_display_area.append("Socket_client is ready now ....\n");
			String recvMsgStr;
			String msgStrToSent;
			while(true)
			{
				try {
					recvMsgStr=sockDataInputstream.readUTF();
					
					if(recvMsgStr.equals("Exit"))
					{
						System.out.println("Server "+servSocket+" sent exit...");
						System.out.println("Connection closing...");
						jtxt_display_area.append("Servcer "+servSocket+" sent exit...");
						jtxt_display_area.append("Connection closing...");
						if(servSocket !=null)
						{
							servSocket.close();
						}
						System.out.println("Closed");
						jtxt_display_area.append("Closed");
						
						break;
					}
					jtxt_display_area.append(">> "+recvMsgStr+"\n");
				}catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
			try
			{
				sockDataInputstream.close();
				sockDataOutputstream.close();
				
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}catch(Exception e) {
			if(servSocket !=null)
				servSocket.close();
			servSocket.close();
			e.printStackTrace();
		}
	}

}

