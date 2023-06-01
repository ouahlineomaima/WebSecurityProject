package com.webSecurity.webSecurityProject.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PingService {

	public ResponseEntity<?> pingIP(String ip) {

		// split the ip into octets
		String[] octet = ip.split("\\.");

		// check if all octets are integer
		try {
			Integer.parseInt(octet[0]);
			Integer.parseInt(octet[1]);
			Integer.parseInt(octet[2]);
			Integer.parseInt(octet[3]);

			// check if length of octects equal to 4 and gather octets
			if (octet.length == 4) {
				ip = octet[0] + '.' + octet[1] + '.' + octet[2] + '.' + octet[3];
				// try to adress
				try {
					InetAddress inet = InetAddress.getByName(ip);
					System.out.println("Sending Ping Request to " + ip);
					// try to test adress rechable or not
					try {
						System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");

						if (inet.isReachable(5000)) {
							String pingResult = "";
							String pingCmd = "ping " + ip;
							// execute the ping
							try {
								Runtime r = Runtime.getRuntime();
								Process p = r.exec(pingCmd);

								BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
								String inputLine;
								while ((inputLine = in.readLine()) != null) {
									System.out.println(inputLine);
									pingResult += inputLine;
								}
								in.close();
								return ResponseEntity.ok().body("Ping successful :\n" + pingResult);

							} catch (IOException e) {
								System.out.println(e);
								return ResponseEntity.internalServerError().body("Can't execute the ping");
							}
						} else {
							return ResponseEntity.internalServerError().body("Host is NOT reachable");
						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return ResponseEntity.internalServerError().body("Host is NOT reachable");
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return ResponseEntity.internalServerError().body("Wrong address IP");
				}
			} else {
				return ResponseEntity.internalServerError().body("Not a valid address IP :(");
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return ResponseEntity.internalServerError().body("Not a valid address IP :(");
		}

	}
}
