package com.sincera.intern.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassGen {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		System.out.println(encoder.encode("admin"));

		/*EmailService service = new EmailServiceImpl();
		EmailDetails details = new EmailDetails();
		details.setSubject("dhruvchile.com");
		details.setSubject("Springboot Test");
		details.setMsgBody("Hi,\n\nThanks for the Springboot Email Util Class");
		String status = service.sendSimpleMail(details);
		System.out.println(status);*/
		
	}
}

//$2a$10$G6BnV0YosGu3VUemnp039eMtrgbcvhrBAa2PecuujGekTRwSRIUPS
//$2a$10$o17vDWmUp63GQPuWMhTbxeY9woH/gdLfokhYmsQRMdVlOX9ul8dgm