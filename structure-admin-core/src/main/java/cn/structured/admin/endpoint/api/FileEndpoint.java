package cn.structured.admin.endpoint.api;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.structure.common.entity.ResResultVO;
import cn.structure.common.exception.CommonException;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.admin.configuration.AdminProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * 文件控制器
 *
 * @author chuck
 * @since JDK1.8
 */
@Slf4j
@Api(tags = "文件管理")
@RestController
@RequestMapping(value = "/api/files")
public class FileEndpoint {

    @Resource
    private AdminProperties properties;

    @ApiOperation(value = "上传文件", notes = "返回文件存储路径")
    @PostMapping(value = "/upload")
    public ResResultVO<String> upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(StrUtil.DOT));
        String fileName = IdUtil.simpleUUID() + suffix;
        String fileUrl = properties.getHost() + "/api/files/viewImg/" + fileName;
        File targetFile = new File(properties.getUploadPath(), fileName);
        try {
            file.transferTo(targetFile);
        } catch (Exception ignored) {
            return ResultUtilSimpleImpl.fail("", "文件上传失败", null);
        }
        return ResultUtilSimpleImpl.success(fileUrl);
    }

    @ApiOperation(value = "预览图片")
    @GetMapping(value = "/viewImg/{fileName}")
    public void viewImg(@PathVariable(value = "fileName") String fileName, HttpServletResponse response) {
        try (InputStream fileInputStream = Files.newInputStream(new File(properties.getUploadPath(), fileName).toPath())) {
            response.setContentType(ContentType.IMAGE_PNG.toString());
            IoUtil.copy(fileInputStream, response.getOutputStream());
            response.getOutputStream().close();
        } catch (IOException e) {
            log.error("image view error -> {}", e.getMessage());
            throw new CommonException();
        }
    }

    @ApiOperation(value = "下载文件")
    @GetMapping(value = "/download/{fileName}")
    public void download(@PathVariable(value = "fileName") String fileName, HttpServletResponse response) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(properties.getUploadPath(), fileName))) {
            response.setContentType(ContentType.APPLICATION_OCTET_STREAM.toString());
            IoUtil.copy(fileInputStream, response.getOutputStream());
            response.getOutputStream().close();
        } catch (IOException e) {
            log.error("download file error -> {}", e.getMessage());
            throw new CommonException();
        }
    }
}