package com.dayuan.serviceimpl;

import com.dayuan.domain.PageHolder;
import com.dayuan.dto.MessageDTO;

public interface MessageService {

	MessageDTO setMessage(int userId);

	// MessageDTO modifyMessage(int userId);

	// MessageDTO flowMessage(int userId);
	PageHolder listMessage( int currentPage,int userId);
	
	void readMessage(int userId);
	
	
}
