package cn.structured.admin.endpoint.assembler;

import cn.structured.admin.entity.Dept;
import cn.structured.admin.entity.DictItem;
import cn.structured.admin.entity.Menu;
import cn.structured.admin.vo.OptionVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * create by chuck 2023/8/4
 *
 * @author chuck
 * @since JDK1.8
 */
public class OptionAssembler {


    public static OptionVo assemblerOption(Object object) {
        OptionVo option = new OptionVo();
        BeanUtils.copyProperties(object, option);
        return option;
    }

    public static OptionVo assemblerOption(DictItem dictItem) {
        OptionVo option = new OptionVo();
        option.setLabel(dictItem.getName());
        option.setValue(dictItem.getValue());
        return option;
    }

    public static List<OptionVo> assemblerForMenu(List<Menu> menuList) {
        Map<Long, OptionVo> optionMap = menuList
                .stream()
                .collect(Collectors.toMap(Menu::getId, OptionAssembler::assemblerOption));
        List<OptionVo> parentOptionList = new ArrayList<>();
        menuList.forEach(menu -> {
            Long pid = menu.getParentId();
            Long id = menu.getId();
            OptionAssembler.assemblerParentOptionList(parentOptionList, optionMap, pid, id);
        });
        return parentOptionList;
    }

    public static List<OptionVo> assemblerForDept(List<Dept> deptList) {
        Map<Long, OptionVo> optionMap = deptList
                .stream()
                .collect(Collectors.toMap(Dept::getId, OptionAssembler::assemblerOption));
        List<OptionVo> parentOptionList = new ArrayList<>();
        deptList.forEach(dept -> {
            Long pid = dept.getParentId();
            Long id = dept.getId();
            OptionAssembler.assemblerParentOptionList(parentOptionList, optionMap, pid, id);
        });
        return parentOptionList;
    }

    private static OptionVo assemblerOption(Dept dept) {
        OptionVo optionVo = new OptionVo();
        optionVo.setId(dept.getId());
        optionVo.setValue(dept.getId());
        optionVo.setLabel(dept.getName());
        return optionVo;
    }

    private static OptionVo assemblerOption(Menu menu) {
        OptionVo optionVo = new OptionVo();
        optionVo.setId(menu.getId());
        optionVo.setValue(menu.getCode());
        optionVo.setLabel(menu.getName());
        return optionVo;
    }

    private static void assemblerParentOptionList(List<OptionVo> parentOptionList, Map<Long, OptionVo> optionMap, Long pid, Long id) {
        OptionVo parentOption = optionMap.get(pid);
        OptionVo currentOption = optionMap.get(id);
        if (null == parentOption) {
            parentOptionList.add(currentOption);
        } else {
            List<OptionVo> children = parentOption.getChildren();
            if (null == children) {
                children = new ArrayList<>();
                parentOption.setChildren(children);
            }
            children.add(currentOption);
        }
    }
}
