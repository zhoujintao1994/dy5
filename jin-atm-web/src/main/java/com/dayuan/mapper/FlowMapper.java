package com.dayuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuan.domain.Flow;




public interface FlowMapper {
	
	int addFlow(Flow flow);

	List<Flow> listFlow(@Param("cardNumber") String cardNum, @Param("offset")int offset, @Param("prePage")int prePage); //ƫ���� ÿҳ��ѯ����
	
	int countFlow(String cardNumber);//ͳ������
	
	List<Flow> listFlowByUseId(@Param("userId") Integer userId, @Param("prePage") int prePage);
	
}
