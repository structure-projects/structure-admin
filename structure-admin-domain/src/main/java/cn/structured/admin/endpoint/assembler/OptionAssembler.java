package cn.structured.admin.endpoint.assembler;

import cn.structured.admin.entity.DictItem;
import cn.structured.admin.api.vo.OptionVO;

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
