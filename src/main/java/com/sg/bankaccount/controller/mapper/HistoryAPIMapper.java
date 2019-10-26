package com.sg.bankaccount.controller.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.sg.bankaccount.controller.dto.HistoryDTO;
import com.sg.bankaccount.domain.History;

public class HistoryAPIMapper extends AbstractAPIMapper<History, HistoryDTO>{

	@Override
	public HistoryDTO convertToDTO(History domain) {
		HistoryDTO dto = new HistoryDTO();
		dto.setAmount(domain.getAmount());
		dto.setDate(domain.getDate());
		dto.setOperation(domain.getOperation().toString());
		return dto;
	}
	
	public List<HistoryDTO> convertToDTOs(List<History> domain) {
		List<HistoryDTO> result = new  ArrayList<HistoryDTO>();
		if(!CollectionUtils.isEmpty(domain)) {
			for (History history : domain) {
				HistoryDTO dto = new HistoryDTO();
				dto.setAmount(history.getAmount());
				dto.setDate(history.getDate());
				dto.setOperation(history.getOperation().toString());
				result.add(dto);
			}
		}
		return result;
	}

}
