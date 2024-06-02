package com.tcs.trs.repository;

import org.springframework.data.repository.CrudRepository;

import com.tcs.trs.entity.Loan;

public interface LoanRepository extends CrudRepository<Loan, Integer>{
	
}
