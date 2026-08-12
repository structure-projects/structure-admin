package cn.structured.admin.core.endpoint.assembler;

import cn.structured.admin.core.entity.DictItem;
import cn.structured.admin.common.vo.OptionVO;

/**
 * create by chuck 2023/8/4
 *
 * @author chuck
 * @since JDK1.8
 */
public class OptionAssembler {

    public static OptionVO assemblerOption(DictItem dictItem) {
        OptionVO option = new OptionVO();
        option.setLabel(dictItem.getName());
        option.setValue(dictItem.getValue());
        return option;
    }
}
