package com.dayuan.serviceimpl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.dayuan.domain.BankCard;
import com.dayuan.dto.FLowTenDTO;


@Service
public class RedisServiceImpl implements RedisService{
	
	@Autowired
	private RedisTemplate<String, String> template;
	
	@Resource(name="redisTemplate")
	private ValueOperations<String, List<BankCard>> cardTemplate;
	
	@Resource(name="redisTemplate")
	private ValueOperations<String, List<FLowTenDTO>> flowTemplate;

	@Override
	public List<BankCard> listBankCard(int userId) {
		String key = "atm:card:" + userId;
		return cardTemplate.get(key);
	}
	
	@Override
	public void saveBankCard(List<BankCard> cards, int userId) {
		String key = "atm:card:" + userId;
		cardTemplate.set(key, cards);
	}
	
	@Override
	public void delBankCard(int userId) {
		String key = "atm:card:" + userId;
		template.delete(key);
	}
	
	@Override
	public List<FLowTenDTO> listFLow(int userId) {
		String key = "atm:flow:" + userId;
		return flowTemplate.get(key);
	}

	@Override
	public void saveListFlow(int userId, List<FLowTenDTO> value) {
		String key = "atm:flow:" + userId;
		flowTemplate.set(key, value);
	}

	@Override
	public void dellistFLow(int userId) {
		String key = "atm:flow:" + userId;
		template.delete(key);
		System.out.println("key="+key);
	}

	@Override
	public boolean setNX(String key, long time) {
		
		//通过setIfAbsent(K key, V value)方法判断变量值key是否存在
		// //如果key存在返回false；不存在，就存入reidis返回true  
		boolean flag = template.opsForValue().setIfAbsent(key, "");
		if (flag) {
			template.expire(key, time, TimeUnit.SECONDS);//过期时间
		}
		
		return flag;
	

	}
}
