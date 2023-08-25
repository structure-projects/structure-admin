package cn.structured.sa.controller.api;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.sa.client.vo.FileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件控制器
 *
 * @author chuck
 * @since JDK1.8
 */
@Api(tags = "文件管理")
@RestController
@RequestMapping(value = "/file")
@RequiredArgsConstructor
public class FileController {

    // todo 读配置 ，添加全域管理机构
    private final String uploadPath = "D:/admin-file";

    @ApiOperation(value = "上传文件", notes = "返回文件存储路径")
    @PostMapping(value = "/upload")
    public ResResultVO<FileVO> upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(StrUtil.DOT));
        String fileName = IdUtil.simpleUUID() + suffix;
        String fileUrl = "http://localhost:18004/file/viewImg/" + fileName;
        FileVO resFile = new FileVO();
        resFile.setName(fileName);
        resFile.setUrl(fileUrl);
        File targetFile = new File(uploadPath, fileName);
        try {
            file.transferTo(targetFile);
        } catch (Exception ignored) {
            return ResultUtilSimpleImpl.fail("", "文件上传失败", null);
        }
        return ResultUtilSimpleImpl.success(resFile);
    }

    @ApiOperation(value = "预览图片")
    @GetMapping(value = "/viewImg/{fileName}")
    public void viewImg(@PathVariable(value = "fileName") String fileName, HttpServletResponse response) {
        try (InputStream fileInputStream = new FileInputStream(new File(uploadPath, fileName))) {
            response.setContentType(ContentType.IMAGE_PNG.toString());
            IoUtil.copy(fileInputStream, response.getOutputStream());
            response.getOutputStream().close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "下载文件")
    @GetMapping(value = "/download/{fileName}")
    public void download(@PathVariable(value = "fileName") String fileName, HttpServletResponse response) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(uploadPath, fileName))) {
            response.setContentType(ContentType.APPLICATION_OCTET_STREAM.toString());
            IoUtil.copy(fileInputStream, response.getOutputStream());
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
