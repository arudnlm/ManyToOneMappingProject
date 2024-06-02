package com.tcs.trs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.tcs.trs.dto.CustomerDTO;
import com.tcs.trs.dto.LoanDTO;
import com.tcs.trs.entity.Customer;
import com.tcs.trs.entity.Loan;
import com.tcs.trs.exception.HDFCBankException;
import com.tcs.trs.repository.CustomerRepository;
import com.tcs.trs.repository.LoanRepository;

@Service(value = "customerLoanService")
@Transactional
public class CustomerLoanServiceImpl implements CustomerLoanService {
	
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public LoanDTO getLoanDetails(Integer loanId) throws HDFCBankException {
		Optional<Loan> optional = loanRepository.findById(loanId);
		Loan loan = optional.orElseThrow(()->new HDFCBankException("Service.LOAN_UNAVAILABLE"));
		
		LoanDTO loanDTO = new LoanDTO();		
		loanDTO.setAmount(loan.getAmount());
		loanDTO.setLoanId(loan.getLoanId());
		loanDTO.setLoanIssueDate(loan.getIssueDate());
		loanDTO.setStatus(loan.getStatus());
		
		Customer customer = loan.getCustomer();
		
		if (customer != null) {
			
			CustomerDTO customerDTO = new CustomerDTO();
			
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			
			loanDTO.setCustomer(customerDTO);
		}
	
		return loanDTO;
	}
	
	@Override
	public Integer addLoanAndCustomer(LoanDTO loanDTO) throws HDFCBankException {
		Loan loan = new Loan();
		loan.setAmount(loanDTO.getAmount());
		loan.setIssueDate(loanDTO.getLoanIssueDate());
		loan.setStatus("open");
		CustomerDTO customerDTO = loanDTO.getCustomer();
		Customer customer = new Customer();
		customer.setCustomerId(customerDTO.getCustomerId());
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setName(customerDTO.getName());
		loan.setCustomer(customer);
		
		loanRepository.save(loan);
		
		return loan.getLoanId();
	}
	
	@Override
	public Integer sanctionLoanToExistingCustomer(Integer customerId, LoanDTO loanDTO) throws HDFCBankException {	
		Loan loan = new Loan();
		loan.setAmount(loanDTO.getAmount());
		loan.setIssueDate(loanDTO.getLoanIssueDate());
		loan.setStatus(loanDTO.getStatus());
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(()->new HDFCBankException("Service.CUSTOMER_UNAVAILABLE"));
		loan.setCustomer(customer);
		loanRepository.save(loan);
		return loan.getLoanId();
	}
	
	@Override
	public void closeLoan(Integer loanId) throws HDFCBankException {
		Optional<Loan> optional = loanRepository.findById(loanId);
		Loan loan = optional.orElseThrow(()->new HDFCBankException("Service.LOAN_UNAVAILABLE"));
		loan.setStatus("Closed");
	}
	
	public void deleteLoan(Integer loanId) throws HDFCBankException{
		Optional<Loan> optional = loanRepository.findById(loanId);
		Loan loan = optional.orElseThrow(()->new HDFCBankException("Service.LOAN_UNAVAILABLE"));
		loan.setCustomer(null);
		loanRepository.delete(loan);
	}
	
}
	