package cn.structured.admin.endpoint.assembler;

import cn.structured.admin.dto.DictCategoryDTO;
import cn.structured.admin.dto.DictItemDTO;
import cn.structured.admin.entity.DictCategory;
import cn.structured.admin.entity.DictItem;
import cn.structured.admin.vo.DictCategoryVO;
import cn.structured.admin.vo.DictItemVO;

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
    public static DictCategory assemblerDictCategory(DictCategoryDTO createDictCategory) {
        DictCategory dictCategory = new DictCategory();
        dictCategory.setName(createDictCategory.getName());
        dictCategory.setEnabled(createDictCategory.getEnabled());
        dictCategory.setCode(createDictCategory.getCode());
        dictCategory.setRemark(createDictCategory.getRemark());
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
        dictCategoryVO.setEnabled(dictCategory.getEnabled());
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
        dictItem.setValue(dictItemDto.getValue());
        dictItem.setCode(dictItemDto.getCode());
        dictItem.setSort(dictItemDto.getSort());
        dictItem.setEnabled(dictItemDto.getEnabled());
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
        dictItemVO.setSort(dictItem.getSort());
        dictItemVO.setValue(dictItem.getValue());
        dictItemVO.setEnabled(dictItem.getEnabled());
        dictItemVO.setOperatorTime(dictItem.getUpdateTime());
        return dictItemVO;
    }
}
