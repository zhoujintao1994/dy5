package com.dayuan.serviceimpl;

import java.text.DecimalFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dayuan.controller.BankCardController;
import com.dayuan.domain.BankCard;
import com.dayuan.domain.Flow;
import com.dayuan.domain.PageHolder;
import com.dayuan.domain.Transfer;
import com.dayuan.exception.BizException;
import com.dayuan.handler.TransferMessageHandler2;
import com.dayuan.mapper.BankCardMapper2;
import com.dayuan.mapper.FlowMapper;
import com.dayuan.mapper.TransferMapper;
import com.dayuan.utill.CardUtils;
import com.dayuan.utill.MoneyUtil;
import com.dayuan.websocket.handler.ATMWebSocketHandler;

@Component
public class AtmServiceImpl implements AtmService {

	@Autowired
	private BankCardMapper2 bankCardMapper;

	@Autowired
	private FlowMapper flowMapper;

	@Autowired
	private TransferMapper thansferMapper;
	
	@Autowired
	private RedisService redisService;

	@Autowired
	private ATMWebSocketHandler atmWebSocketHandler;
	
	@Autowired
	private TransferMessageHandler2 transferMessageHandler2;

	private Logger log = LoggerFactory.getLogger(BankCardController.class);

	@Override
	public BankCard openAccount(String passWord, int userId) {// ����
		System.out.println("111111111111111111111111111111111");
		// TODO Auto-generated method stub
		BankCard bankCard = new BankCard();
		bankCard.setSum("0.00");
		bankCard.setVersion(0);
		System.out.println(bankCard.getSum());

		String cardNumber = null;

		for (int i = 0; i < 3; i++) {
			String tempNum = CardUtils.createCardNum();
			BankCard existBc = bankCardMapper.getBankCard(cardNumber);
			if (null != existBc) {
				System.out.println("第" + i + "机会");
				continue;
			}
			cardNumber = tempNum;
			break;
		}

		if (null == cardNumber) {
			try {
				throw new BizException("卡号重复，请重新输入");
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}

		bankCard.setCardNumber(cardNumber);
		bankCard.setPassWord(passWord);
		bankCard.setUserId(userId);
		
		int rows = bankCardMapper.addCard(bankCard);
		if (1 != rows) {
			throw new BizException("开户失败");
		}
		redisService.delBankCard(userId);
		return bankCard;

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deposit(String sum, String cardNumber, String passWord) {
		// TODO Auto-generated method stub

		BankCard bankCard = bankCardMapper.getBankCard(cardNumber);
		System.out.println(bankCard);
		if (null == bankCard) {// 银行卡号不存在
			throw new BizException("银行卡号不存在或密码不正确");
		}

		if (!bankCard.getPassWord().equals(passWord)) {// �������

			throw new BizException("银行卡号不存在或密码不正确");

		}
		CardUtils.checkAmountAndFormat(sum);
		// CardUtill.checkAmount(sum);

		DecimalFormat df = new DecimalFormat("#0.00");
		sum = df.format(Double.parseDouble(sum));
		System.out.println("format=" + sum);

		bankCard.setSum(MoneyUtil.plus(bankCard.getSum(), sum));

		int rows = bankCardMapper.modifyBalance(bankCard.getCardNumber(), bankCard.getSum(), bankCard.getVersion());

		if (1 != rows) {

			throw new BizException("存款失败");
		}

		Flow flow = new Flow();
		flow.setAmount(sum);
		flow.setCardNum(cardNumber);
		flow.setDescript("存钱");
		flow.setFlowType(1);

		rows = flowMapper.addFlow(flow);
		if (1 != rows) {
			throw new BizException("存款失败");
		}
		redisService.delBankCard(bankCard.getUserId());
		 redisService.dellistFLow(bankCard.getUserId());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void draw(String sum, String cardNumber, String passWord) {
		// TODO Auto-generated method stub

		BankCard bankCard = bankCardMapper.getBankCard(cardNumber);

		if (null == bankCard) {
			throw new BizException("银行卡号不存在或密码不正确");
		}

		if (!bankCard.getPassWord().equals(passWord)) {
			throw new BizException("银行卡号不存在或密码不正确");
		}

		sum = CardUtils.checkAmountAndFormat(sum);
		System.out.println("format=" + sum);

		String newSum = MoneyUtil.sub(bankCard.getSum(), sum);

		if (Double.parseDouble(newSum) < 0) {
			throw new BizException("余额不足");
		}

		bankCard.setSum(newSum);
		int rows = bankCardMapper.modifyBalance(bankCard.getCardNumber(), bankCard.getSum(), bankCard.getVersion());
		if (1 != rows) {
			throw new BizException("取款失败");
		}

		Flow flow = new Flow();
		flow.setAmount(sum);
		flow.setCardNum(cardNumber);
		flow.setDescript("取款");
		flow.setFlowType(2);

		rows = flowMapper.addFlow(flow);
		if (1 != rows) {
			throw new BizException("添加流水失败");
		}
		redisService.delBankCard(bankCard.getUserId());
		redisService.dellistFLow(bankCard.getUserId());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void transfer(String sum, String inCardNum, String outCardNum, String passWord) {
		// TODO Auto-generated method stub

		BankCard outCard = bankCardMapper.getBankCard(outCardNum);

		if (null == outCard) {
			throw new BizException("银行卡号不存在或密码不正确");
		}

		if (!outCard.getPassWord().equals(passWord)) {
			throw new BizException("银行卡号不存在或密码不正确");
		}

		sum = CardUtils.checkAmountAndFormat(sum);
		System.out.println("format=" + sum);

		String newSum = MoneyUtil.sub(outCard.getSum(), sum);

		if (Double.parseDouble(newSum) < 0) {
			throw new BizException("余额不足，无法转账");
		}

		outCard.setSum(newSum);
		int rows = bankCardMapper.modifyBalance(outCard.getCardNumber(), outCard.getSum(), outCard.getVersion());
		if (1 != rows) {
			throw new BizException("转账失败");
		}

		Flow flow = new Flow();
		flow.setAmount(sum);
		flow.setCardNum(outCardNum);
		flow.setDescript("转账支出");
		flow.setFlowType(3);

		rows = flowMapper.addFlow(flow);
		if (1 != rows) {
			throw new BizException("添加流水失败");
		}

		BankCard inCard = bankCardMapper.getBankCard(inCardNum);
		if (null == inCard) {
			throw new BizException("银行卡号不存在");
		}

		String inSum = MoneyUtil.plus(inCard.getSum(), sum);

		inCard.setSum(inSum);
		rows = bankCardMapper.modifyBalance(inCard.getCardNumber(), inCard.getSum(), inCard.getVersion());
		if (1 != rows) {
			throw new BizException("转账失败");
		}

		flow = new Flow();
		flow.setAmount(sum);
		flow.setCardNum(inCardNum);
		flow.setDescript("转账收入");
		flow.setFlowType(4);

		rows = flowMapper.addFlow(flow);
		if (1 != rows) {
			throw new BizException("添加流水失败");
		}
	}

	@Override
	public PageHolder queryFlow(String cardNumber, String passWord, int currentPage) {
		// TODO Auto-generated method stub
		BankCard bankCard = bankCardMapper.getBankCard(cardNumber);
		System.out.println(bankCard);
		if (null == bankCard) {
			throw new BizException("银行卡号不存在或密码不正确");
		}

		if (!bankCard.getPassWord().equals(passWord)) {
			throw new BizException("银行卡号不存在或密码不正确");
		}

		int totalElement = flowMapper.countFlow(cardNumber);
		PageHolder ph = new PageHolder(currentPage, totalElement);
		List<Flow> list = flowMapper.listFlow(cardNumber, ph.getOffset(), PageHolder.PRE_PAGE_NUM);
		System.out.println("getOffset1=" + ph.getOffset());
		System.out.println("PRE_PAGE_NUM1=" + PageHolder.PRE_PAGE_NUM);
		ph.setObj(list);

		return ph;
	}

	@Override
	public BankCard getBankCard(String cardNumber) {
//		BankCard banKCard = bankCardMapper.getBankCard(cardNumber);
//		redisService.dellistFLow(banKCard.getUserId());
		return bankCardMapper.getBankCard(cardNumber);
	}

	@Override
	public int countFlow(String cardNumber, String passWord) {
		return flowMapper.countFlow(cardNumber);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void outTransfer(String sum, String inCardNum, String outCardNum, String passWord) {
		// TODO Auto-generated method stub
		BankCard outCard = bankCardMapper.getBankCard(outCardNum);

		if (null == outCard) {
			throw new BizException("银行卡号不存在或密码不正确");
		}

		if (!outCard.getPassWord().equals(passWord)) {
			throw new BizException("银行卡号不存在或密码不正确");
		}

		sum = CardUtils.checkAmountAndFormat(sum);

		String newSum = MoneyUtil.sub(outCard.getSum(), sum);

		if (Double.parseDouble(newSum) < 0) {
			throw new BizException("余额不足，无法转账");
		}

		outCard.setSum(newSum);
		int rows = bankCardMapper.modifyBalance(outCard.getCardNumber(), outCard.getSum(), outCard.getVersion());
		if (1 != rows) {
			throw new BizException("转账失败");
		}

		Flow flow = new Flow();
		flow.setAmount(sum);
		flow.setCardNum(outCardNum);
		flow.setDescript("转账支出");
		flow.setFlowType(3);

		rows = flowMapper.addFlow(flow);
		if (1 != rows) {
			throw new BizException("添加流水失败");
		}

		Transfer transfer = new Transfer();
		transfer.setTfBankcard(outCardNum);
		transfer.setCfBankcard(inCardNum);
		transfer.setTfBlance(sum);
		transfer.setStatus(0);

		 rows = thansferMapper.addTransf(transfer);
		 
		 if (1 != rows) {
				throw new BizException("增加转账记录失败");
			}
		 redisService.delBankCard(outCard.getUserId());
		 redisService.dellistFLow(outCard.getUserId());
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void intTransfer(Transfer transfer) {
		// TODO Auto-generated method stub
		//获取那张筛选出来的转账表里转入卡
		BankCard bankCard = bankCardMapper.getBankCard(transfer.getCfBankcard());
		
		//原有金额和转入金额相加
		String addSum = MoneyUtil.plus(bankCard.getSum(), transfer.getTfBlance());
		
		//修改添加银行卡
		int rows = bankCardMapper.modifyBalance(transfer.getCfBankcard(), addSum, bankCard.getVersion());
		
		if(1 != rows) {
			throw new BizException("处理转账失败");
		}
		
		Flow flow = new Flow();
		flow = new Flow();
		flow.setAmount(transfer.getTfBlance());
		flow.setCardNum(transfer.getCfBankcard());
		flow.setDescript("转账收入");
		flow.setFlowType(4);
		
		rows = flowMapper.addFlow(flow);
		if (1 != rows) {
			throw new BizException("转账流水添加失败");
		}
		
		thansferMapper.modifyStatus(1, transfer.getId());
		//转账成功 修改状态为1
		
		// 发消息给客户端
		 transferMessageHandler2.sendMessage( bankCard.getUserId(), "收到一笔转账" + transfer.getTfBlance() + "元");
		 redisService.delBankCard(bankCard.getUserId());
		 redisService.dellistFLow(bankCard.getUserId());
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void rooBack(Transfer transfer) {
		// TODO Auto-generated method stub
		//获取那张筛选出来的转账表里转出卡
		BankCard bankCard = bankCardMapper.getBankCard(transfer.getTfBankcard());
		// 把钱回滚到那张转出卡中
		String addSum = MoneyUtil.plus(bankCard.getSum(), transfer.getTfBlance());
		
		int rows = bankCardMapper.modifyBalance(transfer.getTfBankcard(), addSum, bankCard.getVersion());
		//把satatus状态改为3：取消转账
		if(1 != rows) {
			throw new BizException("取消转账失败！！");
		}
		
		Flow flow = new Flow();
		flow = new Flow();
		flow.setAmount(transfer.getTfBlance());
		flow.setCardNum(transfer.getCfBankcard());
		flow.setDescript("取消转账");
		flow.setFlowType(5);
		
		rows = flowMapper.addFlow(flow);
		if (1 != rows) {
			throw new BizException("取消转账流水添加失败");
		}
		thansferMapper.modifyStatus(3, transfer.getId());
		redisService.delBankCard(bankCard.getUserId());
		 redisService.dellistFLow(bankCard.getUserId());
	}
}
