package android;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class GCMPusher {
	
	private static final String GOOGLE_SERVER_KEY = "AIzaSyC-GyONqnze0qLLMTqePO1LNHj5F0f-ZUc";//Done -AdN
	static final String MESSAGE_KEY = "message";	

	public static void main(String[] args) {
		MulticastResult result = null;

  
		// GCM RedgId of Android device to send push notification
		//String regId = "APA91bFfPw77GeGtyaAfhnV6s_D9dMZvdZsrzf4HIbPWpYAAt5ONWClwl40iUxNgU3hNRiOMwLEk4dd5N9XaezDNmVnqZYu1-Mv1mc3kNqaDUd5qf8w35GBBP19NPDt3SaByMzBWUUpe2bMvPjIKCbFzWwsGVidS2A";
		List<String> sendToList = new ArrayList<String>();
		sendToList.add(Address.aditya); 
		sendToList.add(Address.ajay);
		sendToList.add(Address.ajay1);
		sendToList.add(Address.sandeep);
		sendToList.add(Address.sandeep1);
		   
		try {  
			boolean sent = false;
			Integer count = 0;
			while(!sent)
			{ 
			String userMessage = "System is Go!!"+ (new Date());
			Sender sender = new Sender(GOOGLE_SERVER_KEY);
			Message message = new Message.Builder().timeToLive(100)  
					.delayWhileIdle(true).addData(MESSAGE_KEY, userMessage).build();
			//System.out.println("regId: " + regId);
			   
			 
				try {
				result = sender.send(message, sendToList, 100);
				sent = true;
				System.out.println("Success");
				} catch (IOException ioe) {
					//ioe.printStackTrace(); 
					System.out.println("Attempt: "+count++);
					Thread.sleep(1000);
				} 
			}
			System.out.println(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
