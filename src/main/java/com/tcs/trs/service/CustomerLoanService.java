package com.tcs.trs.service;

import com.tcs.trs.dto.LoanDTO;
import com.tcs.trs.exception.HDFCBankException;

public interface CustomerLoanService {
	
	public LoanDTO getLoanDetails(Integer loanId) throws HDFCBankException;
	
	public Integer addLoanAndCustomer(LoanDTO loanDTO) throws HDFCBankException;
	
	public Integer sanctionLoanToExistingCustomer(Integer customerId,LoanDTO loanDTO) throws HDFCBankException;
	
	public void closeLoan(Integer loanId) throws HDFCBankException; 
	
	public void deleteLoan(Integer loanId) throws HDFCBankException;
	
}
