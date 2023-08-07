package cn.structured.sa.controller.assembler;

import cn.structured.sa.client.vo.OptionVO;
import cn.structured.sa.entity.Dept;
import cn.structured.sa.entity.Menu;
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

    public static List<OptionVO> assemblerForDept(List<Dept> deptList) {
        Map<Long, OptionVO> optionMap = deptList.stream().collect(Collectors.toMap(Dept::getId, OptionAssembler::assemblerOption));
        List<OptionVO> parentOptionList = new ArrayList<>();
        deptList.forEach(dept -> {
            Long pid = dept.getPid();
            Long id = dept.getId();
            OptionAssembler.assemblerParentOptionList(parentOptionList, optionMap, pid, id);
        });
        return parentOptionList;
    }

    public static OptionVO assemblerOption(Object object) {
        OptionVO option = new OptionVO();
        BeanUtils.copyProperties(object, option);
        return option;
    }
    public static List<OptionVO> assemblerForMenu(List<Menu> menuList) {
        Map<Long, OptionVO> optionMap = menuList.stream().collect(Collectors.toMap(Menu::getId, OptionAssembler::assemblerOption));
        List<OptionVO> parentOptionList = new ArrayList<>();
        menuList.forEach(menu -> {
            Long pid = menu.getPid();
            Long id = menu.getId();
            OptionAssembler.assemblerParentOptionList(parentOptionList, optionMap, pid, id);
        });
        return parentOptionList;
    }

    private static OptionVO assemblerOption(Menu menu) {
        OptionVO option = new OptionVO();
        BeanUtils.copyProperties(menu, option);
        return option;
    }

    private static void assemblerParentOptionList(List<OptionVO> parentOptionList, Map<Long, OptionVO> optionMap, Long pid, Long id) {
        OptionVO parentOption = optionMap.get(pid);
        OptionVO currentOption = optionMap.get(id);
        if (null == parentOption) {
            parentOptionList.add(currentOption);
        } else {
            List<OptionVO> children = parentOption.getChildren();
            if (null == children) {
                children = new ArrayList<>();
                parentOption.setChildren(children);
            }
            children.add(currentOption);
        }
    }
}
