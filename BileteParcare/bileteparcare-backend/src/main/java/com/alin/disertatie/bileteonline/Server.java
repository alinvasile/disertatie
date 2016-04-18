package com.alin.disertatie.bileteonline;


import javax.xml.ws.Endpoint;

import com.alin.disertatie.bileteonline.ws.BileteOnlineImpl;

/**
 * 
 * Test class for WS functionality.
 * 
 * @author Alin Vasile
 * @version 1.0
 *
 */
public class Server {

	public static void main(String[] args){
		new Server().testWSDeployment();
	}
	
	public void testWSDeployment(){
		System.out.println("Starting Server");
		BileteOnlineImpl implementor = new BileteOnlineImpl();
		String address = "http://127.0.0.1:9040";
		Endpoint.publish(address, implementor);
	}
	
}
