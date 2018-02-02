package com.dayuan.task;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayuan.domain.Transfer;
import com.dayuan.mapper.TransferMapper;
import com.dayuan.serviceimpl.AtmService;

@Component("transferProcessTask")
public class TransferProcessTask {

	private static final Logger log = LoggerFactory.getLogger(TransferProcessTask.class);
	@Autowired
	private TransferMapper transferMapper;

	@Autowired
	private AtmService atmService;

	public void doTask() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -20);

		List<Transfer> lists = transferMapper.list4TransferIn(0, cal.getTime());
		log.info(">>>>>>>本次查询待转账记录：" + lists.size());
		for(Transfer transfer : lists) {
			try {
				atmService.intTransfer(transfer);
			}catch(Exception e) {
				log.error("转账失败，回滚卡号:"+transfer.getTfBankcard(), e);
				transferMapper.modifyStatus(2, transfer.getId());
			}
		}
		
	}
}
