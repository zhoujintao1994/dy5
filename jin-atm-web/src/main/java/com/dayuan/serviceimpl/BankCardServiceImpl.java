package com.dayuan.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayuan.domain.BankCard;
import com.dayuan.domain.Flow;
import com.dayuan.domain.PageHolder;
import com.dayuan.dto.FLowTenDTO;
import com.dayuan.exception.BizException;
import com.dayuan.mapper.BankCardMapper2;
import com.dayuan.mapper.FlowMapper;
import com.dayuan.utill.DateUtils;

@Component
public class BankCardServiceImpl implements BankCardService {

	@Autowired
	private BankCardMapper2 bankCardMapper;

	@Autowired
	private FlowMapper flowMapper;

	@Autowired
	private RedisService redisService;

	@Override
	public PageHolder listBankCard(int userId, int currentPage) {

		int totalElement = bankCardMapper.countBankCard(userId);

		PageHolder ph = new PageHolder(currentPage, totalElement);

		List<BankCard> list = bankCardMapper.listBankCard(userId, ph.getOffset(), PageHolder.PRE_PAGE_NUM);
		System.out.println(list);

		ph.setObj(list);

		return ph;
	}

	@Override
	public void deleteCard(int cardId) {

		int rows = bankCardMapper.deleteCard(cardId);
		if (1 != rows) {
			throw new BizException("删除失败");
		}
	}

	@Override
	public List<FLowTenDTO> listFLow(int userId) {
		// TODO Auto-generated method stub
		List<FLowTenDTO> saveListFlow =	redisService.listFLow(userId);
		
		if(null != saveListFlow) {
			System.out.println("流水走缓存");
			return saveListFlow;
		}
		List<Flow> list = flowMapper.listFlowByUseId(userId, 10);

		List<FLowTenDTO> listFLow = new ArrayList<>(list.size());

		for (Flow flow : list) {
			FLowTenDTO dto = new FLowTenDTO();
			listFLow.add(dto);
			dto.setAmount(flow.getAmount());
			dto.setCardNum(flow.getCardNum());
			dto.setCreateTime(DateUtils.dateToString(flow.getCreateTime()));
			dto.setDescript(flow.getDescript());
			System.out.println(flow.getDescript());
			dto.setFlowType(flow.getFlowType());
			dto.setId(flow.getId());

		}
		redisService.saveListFlow(userId, listFLow);
		return listFLow;
	}

	@Override
	public List<BankCard> listBankCard(int userId) {
		
		List<BankCard> list = redisService.listBankCard(userId);

		if (null != list) {
			System.out.println(">>>>>>>>走缓存");
			return list;
		}
		System.out.println(">>>>>>>>没走缓存");
		list = bankCardMapper.listBankCard(userId, null, null);

		// 保存到缓存
		redisService.saveBankCard(list, userId);

		return list;

	}

}