package cn.structured.admin.service.impl;

import cn.structured.admin.entity.OperationRecord;
import cn.structured.admin.mapper.OperationRecordMapper;
import cn.structured.admin.service.IOperationRecordService;
import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class OperationRecordServiceImpl extends BaseServiceImpl<OperationRecordMapper, OperationRecord> implements IOperationRecordService {

}
