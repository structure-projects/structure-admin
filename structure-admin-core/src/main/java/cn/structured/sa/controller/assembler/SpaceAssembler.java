package cn.structured.sa.controller.assembler;

import cn.structured.sa.client.dto.space.CreateSpaceDTO;
import cn.structured.sa.client.dto.space.UpdateSpaceDTO;
import cn.structured.sa.client.vo.SpaceVO;
import cn.structured.sa.entity.Space;

/**
 * 空间装配器
 *
 * @author chuck
 * @since JDK1.8
 */
public class SpaceAssembler {

    private SpaceAssembler() {
    }

    /**
     * 装配空间PO
     *
     * @param createSpace 创建空间DTO
     * @return
     */
    public static Space assembler(CreateSpaceDTO createSpace) {
        Space space = new Space();
        space.setName(createSpace.getName());
        space.setCode(createSpace.getCode());
        space.setType(createSpace.getType());
        return space;
    }

    /**
     * 装配空间PO
     *
     * @param updateSpace 修改空间DTO
     * @return
     */
    public static Space assembler(UpdateSpaceDTO updateSpace) {
        Space space = new Space();
        space.setId(updateSpace.getId());
        space.setName(updateSpace.getName());
        space.setCode(updateSpace.getCode());
        space.setType(updateSpace.getType());
        return space;
    }

    public static SpaceVO assembler(Space space) {
        SpaceVO spaceVO = new SpaceVO();
        spaceVO.setId(space.getId());
        spaceVO.setName(space.getName());
        spaceVO.setCode(space.getCode());
        spaceVO.setType(space.getType());
        spaceVO.setEnabled(space.getEnabled());
        spaceVO.setOperator(space.getOperator());
        spaceVO.setOperatorTime(space.getUpdateTime());
        spaceVO.setOrganizationId(space.getOrganizationId());
        return spaceVO;
    }
}
