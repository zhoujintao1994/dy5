package com.dayuan.serviceimpl;



import java.util.List;

import com.dayuan.domain.BankCard;
import com.dayuan.domain.Flow;
import com.dayuan.domain.PageHolder;
import com.dayuan.dto.FLowTenDTO;



public interface BankCardService {

	PageHolder listBankCard(int userId, int currentPage);
	
	void deleteCard(int cardId);
	
	List<BankCard> listBankCard(int userId);
	
	List<FLowTenDTO> listFLow(int userId);

}
