package com.sg.bankaccount.controller.mapper;

public abstract class AbstractAPIMapper<D, T> {
	
	public abstract T convertToDTO(D domain);
	

}
