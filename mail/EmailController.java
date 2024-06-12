package com.userManagement.controller.mail;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.userManagement.configGmail.EmailDto;
import com.userManagement.configGmail.GmailConfig;
import com.userManagement.configproperties.AppProperties;

@Controller
public class EmailController {

	@Autowired
	private GmailConfig config;
	
	@Autowired
	private AppProperties appProperties;
	
	@GetMapping("/sendMail")
	public String loademail(Model model)
	{
		EmailDto mail = new EmailDto();
		
		model.addAttribute("mail", mail);
		
		return "mails";
	}
	
	
	
	@PostMapping("/email")
	public String saveUser(EmailDto emailDto ,Model model)
	{
		Map<String, String> messages = appProperties.getMessages();
		boolean mail = config.sendMail(
				emailDto.getTo(),
				emailDto.getFrom(),
				emailDto.getSubject(),
				emailDto.getText());
		
		if(mail)
		{
			model.addAttribute("SUCCESS", messages.get("sendMailSuccess"));
		}
		else
		{
			model.addAttribute("sentFail", messages.get("sentFail"));
			
		}
			
		return "emailstatus";

}
}