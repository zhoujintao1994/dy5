package com.dayuan.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayuan.domain.Transfer;
import com.dayuan.mapper.TransferMapper;
import com.dayuan.serviceimpl.AtmService;



@Component("transferCancelTask")
public class TransferCancelTask {
	
	private static final Logger log = LoggerFactory.getLogger(TransferCancelTask.class);
	
	@Autowired
	private TransferMapper transferMapper;
	
	@Autowired
	private AtmService atmService;
	
	public void doTask() {
		List<Transfer> lists = transferMapper.selectStatus(2);
		log.info("转账失败卡数："+lists.size());
		
		for(Transfer transfer:lists) {
			try{
				atmService.rooBack(transfer);
				log.info("转账取消回滚卡号："+transfer.getTfBankcard());
			}catch (Exception e) {
				log.error("转账取消回滚卡号失败："+transfer.getTfBankcard(), e);
			}
		}
	}

}
