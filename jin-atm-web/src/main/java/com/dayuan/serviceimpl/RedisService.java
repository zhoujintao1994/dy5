package com.dayuan.serviceimpl;

import java.util.List;

import com.dayuan.domain.BankCard;
import com.dayuan.dto.FLowTenDTO;



public interface RedisService {
	
	List<BankCard> listBankCard(int userId);
	
	void saveBankCard(List<BankCard> cards, int userId);
	
	void delBankCard(int userId);

	List<FLowTenDTO> listFLow(int userId);

	void saveListFlow(int userId, List<FLowTenDTO> value);
	
	void dellistFLow(int userId);

	boolean setNX(String key, long time);

}
