package cn.structured.sa.bo;

import cn.structured.sa.entity.Employee;
import lombok.Data;

import java.util.List;

/**
 * 创建职工BO对象
 *
 * @author cqliut
 * @version 2023.0714
 * @since 1.0.1
 */
@Data
public class UpdateEmployeeBO {

    /**
     * 职工信息
     */
    private Employee employee;

    /**
     * 用户角色列表
     */
    private List<Long> roleIds;

}
