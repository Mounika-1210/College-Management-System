package com.ramanasoft.user.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtil {
	
	  private static final String SECRET = "thisIsA32ByteSecretKeyForJwt123456!";
	    private static final long EXPIRATION = 1000 * 60 * 60 * 24; 

	    private static final Key key = new SecretKeySpec(SECRET.getBytes(), SignatureAlgorithm.HS256.getJcaName());

	    public static String generateToken(String subject) {
	        return Jwts.builder()
	                .setSubject(subject)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
	                .signWith(key)
	                .compact();
	    }
}
