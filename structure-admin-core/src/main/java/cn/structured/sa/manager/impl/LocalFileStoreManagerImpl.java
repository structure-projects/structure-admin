package cn.structured.sa.manager.impl;

import cn.structured.sa.manager.IFileStoreManager;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 本地文件存储
 *
 * @author chuck
 * @since JDK1.8
 */
public class LocalFileStoreManagerImpl implements IFileStoreManager {

    @Override
    public String upload(String name, InputStream inputStream) {
        return null;
    }

    @Override
    public OutputStream download(String name) {
        return null;
    }
}
