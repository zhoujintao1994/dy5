package com.dayuan.serviceimpl;

import java.util.List;

import com.dayuan.domain.BankCard;
import com.dayuan.domain.PageHolder;
import com.dayuan.domain.Transfer;


public interface AtmService {
	BankCard openAccount(String passWord,int userId);// ����

	void deposit(String sum, String cardNumber, String passWord);// ��Ǯ

	void draw(String sum, String cardNumber, String passWord);// ȡǮ

	void transfer(String sum, String inCardNum, String outCardNum, String passWord);// ת��

	PageHolder queryFlow(String cardNumber, String passWord, int currentPage);//��ҳ��ѯ��ˮ
	
	int countFlow(String cardNumber, String passWord);
	
	BankCard getBankCard(String cardNumber);
	
	void outTransfer(String sum, String inCardNum, String outCardNum, String passWord);
	
	void intTransfer(Transfer transfer);
	
	void rooBack(Transfer transfer);
	
}
