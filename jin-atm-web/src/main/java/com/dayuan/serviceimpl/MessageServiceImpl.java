package com.dayuan.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayuan.domain.Message;
import com.dayuan.domain.PageHolder;
import com.dayuan.dto.MessageDTO;
import com.dayuan.dto.MessageDTO.Messageinfo;
import com.dayuan.dto.MessageString;
import com.dayuan.mapper.MessageMapper;
import com.dayuan.utill.DateUtils;

@Component
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messagemapper;

	// @Autowired
	// private MessageDTO messageDto ;

	@Override
	public MessageDTO setMessage(int userId) {
		
		// 统计未读消息数
		int rows = messagemapper.countMessage(userId);

		// 获得想要查询的三条数据
		List<Message> message3 = messagemapper.selectMessage(userId);
		//
		ArrayList<Messageinfo> messageList = new ArrayList<>(message3.size());

		for (Message list : message3) {
			Messageinfo messageinfo = new Messageinfo();

			// 装入ArrayList<Messageinfo> Messageinfo类型的
			messageList.add(messageinfo);
			// Messageinfo集合里设置 获取值
			messageinfo.setMsg(list.getMsg());
			messageinfo.setStatus(list.getStatus());
			messageinfo.setCreateTime(list.getCreateTime().getTime());
		}

		MessageDTO messageDto = new MessageDTO();
		messageDto.setCountMessage(rows);
		messageDto.setList(messageList);
//		messagemapper.updateStatus(1, userId);
		return messageDto;
		
	}

	@Override
	public PageHolder listMessage(int currentPageNum, int userId) {
		
		int totalElement =	messagemapper.countAllMessage(userId);
		PageHolder ph = new PageHolder(currentPageNum, totalElement);
		List<Message> messageAll = messagemapper.listMessage(userId, ph.getOffset(), PageHolder.PRE_PAGE_NUM);
		
		ArrayList<MessageString> msg = new ArrayList<>();
		for(Message list:messageAll) {
			MessageString messageString	= new MessageString();
			messageString.setCreateTime(DateUtils.dateToString(list.getCreateTime()));
			messageString.setId(list.getId());
			messageString.setMsg(list.getMsg());
			messageString.setUserId(list.getUserId());
			messageString.setStatus(list.getStatus());
			msg.add(messageString);
		}
		
		ph.setObj(msg);
		return ph;
	}

	@Override
	public void readMessage(int userId) {
		messagemapper.updateStatus(1, userId);
		
	}
	
	
}
//for (Message list: messageAll) {
//	MessageString stringMessage = new MessageString();
//	stringMessage.setCreateTime(DateUtils.dateToString(list.getCreateTime()));
//	stringMessage.setId(list.getId());
//	stringMessage.setMsg(list.getMsg());
//	msg.add(stringMessage);
//}
