package com.dayuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuan.domain.Flow;




public interface FlowMapper {
	
	int addFlow(Flow flow);

	List<Flow> listFlow(@Param("cardNumber") String cardNum, @Param("offset")int offset, @Param("prePage")int prePage); //偏移量 每页查询条数
	
	int countFlow(String cardNumber);//统计条数
	
	List<Flow> listFlowByUseId(@Param("userId") Integer userId, @Param("prePage") int prePage);
	
}
