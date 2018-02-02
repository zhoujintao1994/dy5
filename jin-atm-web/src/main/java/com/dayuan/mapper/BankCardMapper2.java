package com.dayuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuan.domain.BankCard;




public interface BankCardMapper2 {

	// BankCard getBankCard6(@Param("cardNumber") String cardNum, @Param("id") int
	// id);

	BankCard getBankCard(String cardNumber);

	int addCard(BankCard bankCard);

	int modifyBalance(@Param("cardNumber") String cardNumber, @Param("sum") String sum, @Param("version") int version);
	
	List<BankCard> listBankCard(@Param("userId") int userId, @Param("offset") Integer offset,@Param("prePage") Integer prePage);
			
	int deleteCard(int id);

	int countBankCard(int userId);
}
