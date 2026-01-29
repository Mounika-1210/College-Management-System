package com.ramanasoft.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class EmailService {

	@Value("${zeptomail.api.url}")
	private String zeptoMailUrl;
    @Value("${zeptomail.api.key}")
    private String zeptoMailApiKey;
    
    
    private final RestTemplate template= new RestTemplate();
    public void sendEmailOtp(String toEmail, String otp) {
        Map<String, Object> requestBody = Map.of(
            "from", Map.of(
                "address", "noreplay@gmail.com",
                "name", "Yourapp"
            ),
            "to", List.of(
                Map.of(
                    "email_address", Map.of(
                        "address", toEmail,
                        "name", "User name"
                    )
                )
            ),
            "subject", "Your Otp code",
            "htmlbody", "<h2> otp is : <b>" + otp + "</b></h2>"
        );
        
        	HttpHeaders headers= new HttpHeaders();
             headers.set("Authorization", "Zoho-enczapikey " + zeptoMailApiKey);
             headers.setContentType(MediaType.APPLICATION_JSON);
             
             HttpEntity<Map<String , Object>>entity=new HttpEntity<>(requestBody,headers);
             
             try {
            	 ResponseEntity<String>response=template.exchange(
            			 zeptoMailUrl, HttpMethod.POST,entity,String.class);
            	 System.out.println("Email sent response "+response.getStatusCode()+" " +response.getBody());
             }
             catch(Exception e) {
            	 System.out.println("Error Message"+e.getMessage());
            	 e.printStackTrace();
             }
}
}
