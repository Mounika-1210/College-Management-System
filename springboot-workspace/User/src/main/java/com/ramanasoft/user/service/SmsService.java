package com.ramanasoft.user.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsService {
	@Value("${sms.api.user}")
    private String smsUser;

    @Value("${sms.api.password}")
    private String smsPassword;

    @Value("${sms.api.url}")
    private String smsUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendOtpSms(String mobileNumber, String otp) {
        try {
 
            String message = URLEncoder.encode("Your OTP is " + otp, StandardCharsets.UTF_8);

            String url = smsUrl + "?type=smsquicksend"
                    + "&user=" + smsUser
                    + "&pass=" + smsPassword
                    + "&mobile=" + mobileNumber
                    + "&sms_text=" + message;
            String response = restTemplate.getForObject(url, String.class);
            System.out.println("SMS sent successfully! Response: " + response);
        } catch (Exception e) {
            System.err.println("Error sending SMS: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
