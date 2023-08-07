package cn.structured.sa.controller.assembler;

import cn.structured.sa.client.dto.dict.CreateDictCategoryDTO;
import cn.structured.sa.client.dto.dict.DictItemDTO;
import cn.structured.sa.client.dto.dict.UpdateDictCategoryDTO;
import cn.structured.sa.client.vo.DictCategoryVO;
import cn.structured.sa.client.vo.DictItemVO;
import cn.structured.sa.client.vo.OptionVO;
import cn.structured.sa.entity.DictCategory;
import cn.structured.sa.entity.DictItem;
import cn.structured.sa.enums.DictType;

/**
 * 字典装配器
 *
 * @author cqliut
 * @version 2023.0713
 * @since 1.0.1
 */
public class DictAssembler {

    private DictAssembler() {
    }

    /**
     * 装配成字典类
     *
     * @param createDictCategory 创建字典类DTO
     * @return 字典类
     */
    public static DictCategory assemblerDictCategory(CreateDictCategoryDTO createDictCategory) {
        DictCategory dictCategory = new DictCategory();
        dictCategory.setName(createDictCategory.getName());
        dictCategory.setCode(createDictCategory.getCode());
        dictCategory.setSequence(createDictCategory.getSequence());
        dictCategory.setRemark(createDictCategory.getRemark());
        dictCategory.setUpperLimit(createDictCategory.getUpperLimit());
        dictCategory.setType(DictType.USER.getValue());
        return dictCategory;
    }

    /**
     * 装配成字典类
     *
     * @param updateDictCategory 修改字典类DTO
     * @return 字典类
     */
    public static DictCategory assemblerDictCategory(UpdateDictCategoryDTO updateDictCategory) {
        DictCategory dictCategory = new DictCategory();
        dictCategory.setId(updateDictCategory.getId());
        dictCategory.setName(updateDictCategory.getName());
        dictCategory.setCode(updateDictCategory.getCode());
        dictCategory.setSequence(updateDictCategory.getSequence());
        dictCategory.setRemark(updateDictCategory.getRemark());
        dictCategory.setUpperLimit(updateDictCategory.getUpperLimit());
        dictCategory.setType(DictType.USER.getValue());
        return dictCategory;
    }

    /**
     * 装配成字典类VO对象
     *
     * @param dictCategory 字典类
     * @return 典类VO对象
     */
    public static DictCategoryVO assemblerDictCategory(DictCategory dictCategory) {
        DictCategoryVO dictCategoryVO = new DictCategoryVO();
        dictCategoryVO.setId(dictCategory.getId());
        dictCategoryVO.setName(dictCategory.getName());
        dictCategoryVO.setCode(dictCategory.getCode());
        dictCategoryVO.setRemark(dictCategory.getRemark());
        dictCategoryVO.setUpperLimit(dictCategory.getUpperLimit());
        dictCategoryVO.setSequence(dictCategory.getSequence());
        dictCategoryVO.setType(dictCategory.getType());
        dictCategoryVO.setEnabled(dictCategory.getEnabled());
        dictCategoryVO.setOperator(dictCategory.getOperator());
        dictCategoryVO.setOperatorTime(dictCategory.getUpdateTime());
        dictCategoryVO.setOrganizationId(dictCategory.getOrganizationId());
        return dictCategoryVO;
    }

    /**
     * 装配成字典项数据实体
     *
     * @param dictItemDto 字典项DTO
     * @return 字典项数据实体
     */
    public static DictItem assemblerDictItem(DictItemDTO dictItemDto) {
        DictItem dictItem = new DictItem();
        dictItem.setId(dictItemDto.getId());
        dictItem.setValue(dictItemDto.getValue());
        dictItem.setCode(dictItemDto.getCode());
        dictItem.setName(dictItemDto.getName());
        return dictItem;
    }

    /**
     * 装配成字典项-VO
     *
     * @param dictItem 字典项数据实体
     * @return 字典项-VO
     */
    public static DictItemVO assemblerDictItem(DictItem dictItem) {
        DictItemVO dictItemVO = new DictItemVO();
        dictItemVO.setId(dictItem.getId());
        dictItemVO.setName(dictItem.getName());
        dictItemVO.setCode(dictItem.getCode());
        dictItemVO.setValue(dictItem.getValue());
        dictItemVO.setEnabled(dictItem.getEnabled());
        dictItemVO.setOperator(dictItem.getOperator());
        dictItemVO.setOperatorTime(dictItem.getUpdateTime());
        return dictItemVO;
    }
}
