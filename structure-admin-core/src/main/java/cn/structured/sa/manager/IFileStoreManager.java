package cn.structured.sa.manager;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件存储
 *
 * @author chuck
 * @since JDK1.8
 */
public interface IFileStoreManager {

    /**
     * 上传文件
     *
     * @param name        文件名唯一标识
     * @param inputStream 输入流
     * @return 返回URL
     */
    String upload(String name, InputStream inputStream);

    /**
     * 下载文件
     *
     * @param name 文件唯一标识
     * @return 返回一个输出流
     */
    OutputStream download(String name);
}
