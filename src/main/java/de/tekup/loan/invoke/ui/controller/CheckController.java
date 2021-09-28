package de.tekup.loan.invoke.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.tekup.loan.invoke.client.SoapClient;
import de.tekup.loan.soap.api.consume.loaneligebilty.CustomerRequest;
import de.tekup.loan.soap.api.consume.loaneligebilty.WsResponse;

@Controller
public class CheckController {
	
	@Autowired
	private SoapClient client;
	
	@GetMapping("/check/customer")
	public String checkCustomerForm(Model model) {
		CustomerRequest  request = new CustomerRequest();
		request.setCibilScore(500);
		model.addAttribute("request", request);
		return "request";
	}
	
	@PostMapping("/check/customer")
	public String checkCustomerWS(@ModelAttribute("request") CustomerRequest request, Model model) {
		WsResponse response = client.getLoanStatus(request);
		model.addAttribute("response", response);
		return "response";
	}

}
