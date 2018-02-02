package com.dayuan.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuan.domain.Transfer;

public interface TransferMapper {
	
	int addTransf(Transfer transfer);
	
	List<Transfer> list4TransferIn(@Param("status")Integer status, @Param("deadTime")Date deadTime);
	
	Transfer selectTransf(int id);
	
	int modifyStatus(@Param("newStatus") Integer newStatus, @Param("id") Integer id);
	
	List<Transfer> selectStatus(@Param("status")Integer status);
}
