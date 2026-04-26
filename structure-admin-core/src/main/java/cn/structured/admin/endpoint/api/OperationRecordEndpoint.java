package cn.structured.admin.endpoint.api;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.common.vo.ReqPage;
import cn.structure.common.vo.ResPage;
import cn.structured.admin.api.dto.OperationQuery;
import cn.structured.admin.api.vo.OperationRecordVO;
import cn.structured.admin.endpoint.assembler.OperationAssembler;
import cn.structured.admin.entity.OperationRecord;
import cn.structured.admin.service.IOperationRecordService;
import cn.structured.mybatis.plus.starter.convert.ResPageConvert;
import cn.structured.mybatis.plus.starter.core.QueryJoinPageListWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "操作记录")
@RestController
@RequestMapping("/api/operation-record")
@AllArgsConstructor
public class OperationRecordEndpoint {

    // 查询操作记录
    private final IOperationRecordService operationRecordService;

    @ApiOperation("查询操作记录")
    @GetMapping("/list")
    public ResResultVO<ResPage<OperationRecordVO>> queryOperationRecord(ReqPage reqPage, OperationQuery query) {
        OperationRecord operationRecord = new OperationRecord();
        operationRecord.setAction(query.getAction());
        operationRecord.setModule(query.getModule());
        operationRecord.setStatus(query.getStatus());
        QueryJoinPageListWrapper<OperationRecord> wrapper = new QueryJoinPageListWrapper<>(operationRecord);
        wrapper.setSearch(reqPage.getKeyword());
        wrapper.addSearch("ip_address");
        wrapper.addTime("update_time");
        wrapper.setBeginTime(query.getStartTime());
        wrapper.setEndTime(query.getEndTime());
        IPage<OperationRecord> page = operationRecordService.page(new Page<>(reqPage.getCurrentPage(), reqPage.getPageSize()), wrapper);
        return ResultUtilSimpleImpl.success(ResPageConvert.convert(page, OperationAssembler::assembler));
    }
}
