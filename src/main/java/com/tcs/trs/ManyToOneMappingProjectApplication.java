package com.tcs.trs;

import java.util.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.tcs.trs.dto.CustomerDTO;
import com.tcs.trs.dto.LoanDTO;
import com.tcs.trs.exception.HDFCBankException;
import com.tcs.trs.service.CustomerLoanService;

@SpringBootApplication
public class ManyToOneMappingProjectApplication implements CommandLineRunner{
	@Autowired
	private CustomerLoanService customerLoanService;
	
	@Autowired
	private Environment environment;
	
	public static void main(String[] args) {
		SpringApplication.run(ManyToOneMappingProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		getLoanDetails();
		//addLoanAndCustomer();
		
	}
	
	

	public void getLoanDetails() {
		                                                                                    
		 
		 try {
			 Integer loanId =12;
			LoanDTO loanDTO = customerLoanService.getLoanDetails(loanId);
			System.out.println(loanDTO);
			
		} catch (HDFCBankException e) {
			e.printStackTrace();
		}
		
	/*	public void addLoanAndCustomer(){
			LoanDTO loanDTO = new LoanDTO();
			 loanDTO.setLoanId(123);
			 loanDTO.setAmount(new Double(500000));
			 loanDTO.setLoanIssueDate(LocalDate.parse("2017-06-03"));
			 loanDTO.setStatus("Home Loan");
			 CustomerDTO customerDTO=new CustomerDTO();
			 customerDTO.setCustomerId(103);
			 customerDTO.setName("Naveen");
			 customerDTO.setEmailId("naveen@gmail.com");
			 customerDTO.setDateOfBirth(LocalDate.parse("1998-07-04"));
			 loanDTO.setCustomer(customerDTO);
			 Integer loanAndCustomerId = customerLoanService.addLoanAndCustomer(loanDTO);
			String property = environment.getProperty("UserInterface.NEW_LOAN_CUSTOMER_SUCCESS") +"  : " +loanAndCustomerId;
			
			System.out.println(property);
		}*/
		 
		 
	/*	 public void sanctionLoanToExistingCustomer() {
			 
			 Integer customerId=123;
			 LoanDTO loanDTO =new LoanDTO();
			 loanDTO.set
			 
			 
		 }*/
	}

}
