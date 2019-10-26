package com.sg.bankaccount.service.impl.mapper;

public abstract class AbstractMapper<E, D> {
	
	public abstract E convertToEntity(D domain);
	
	public abstract D convertToDomain(E entity);
	

}
