package cn.structured.sa.bo;

import cn.structured.sa.entity.Employee;
import cn.structured.sa.entity.User;
import lombok.Data;

import java.util.List;

/**
 * 保存职工BO对象
 *
 * @author cqliut
 * @version 2023.0714
 * @since 1.0.1
 */
@Data
public class SaveEmployeeBO {

    /**
     * 职工信息
     */
    private Employee employee;

    /**
     * 用户信息
     */
    private User user;

    /**
     * 用户角色列表
     */
    private List<Long> roleIds;

}
