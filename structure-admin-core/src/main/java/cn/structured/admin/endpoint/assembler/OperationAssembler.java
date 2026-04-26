package cn.structured.admin.endpoint.assembler;

import cn.structured.admin.api.dto.OperationDTO;
import cn.structured.admin.api.vo.OperationRecordVO;
import cn.structured.admin.entity.OperationRecord;

public class OperationAssembler {


    private OperationAssembler() {
    }

    public static OperationRecordVO assembler(OperationRecord operationRecord) {
        OperationRecordVO operationRecordVO = new OperationRecordVO();
        operationRecordVO.setId(operationRecord.getId());
        operationRecordVO.setMid(operationRecord.getMid());
        operationRecordVO.setAction(operationRecord.getAction());
        operationRecordVO.setModule(operationRecord.getModule());
        operationRecordVO.setOperationParams(operationRecord.getOperationParams());
        operationRecordVO.setOperationResult(operationRecord.getOperationResult());
        operationRecordVO.setStatus(operationRecord.getStatus());
        operationRecordVO.setErrorMsg(operationRecord.getErrorMsg());
        operationRecordVO.setIpAddress(operationRecord.getIpAddress());
        operationRecordVO.setUserAgent(operationRecord.getUserAgent());
        operationRecordVO.setCostTime(operationRecord.getCostTime());
        operationRecordVO.setOperationTime(operationRecord.getUpdateTime());
        operationRecordVO.setOperationUser(operationRecordVO.getOperationUser());
        return operationRecordVO;
    }

    public static OperationRecord assembler(OperationDTO operation) {
        OperationRecord operationRecord = new OperationRecord();
        operationRecord.setMid(operation.getMid());
        operationRecord.setAction(operation.getAction());
        operationRecord.setModule(operation.getModule());
        operationRecord.setScore(operation.getScore());
        operationRecord.setOperationParams(operation.getOperationParams());
        operationRecord.setOperationResult(operation.getOperationResult());
        operationRecord.setStatus(operation.getStatus());
        operationRecord.setErrorMsg(operation.getErrorMsg());
        operationRecord.setIpAddress(operation.getIpAddress());
        operationRecord.setUserAgent(operation.getUserAgent());
        operationRecord.setCostTime(operation.getCostTime());
        return operationRecord;
    }
}
