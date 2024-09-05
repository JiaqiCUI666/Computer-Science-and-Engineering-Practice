package org.hit.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.CannedAccessControlList;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.hit.config.CosConfig;
import org.hit.utils.ResponseData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class CosService{

    @Resource
    private COSClient cosClient;

    @Resource
    private CosConfig cosConfig;


    @Transactional(rollbackFor = Exception.class)
    public String upload(MultipartFile[] files) {

        String res = "";
        try {
            for (MultipartFile file : files) {
                String originalFileName = file.getOriginalFilename();
                // 获得文件流
                InputStream inputStream = null;
                inputStream = file.getInputStream();
                //同时将文件保存在static里一份
                InputStream copyInputStream = file.getInputStream();
                File dest = new File("src/main/resources/static/" + originalFileName);
                FileUtils.copyInputStreamToFile(copyInputStream, dest);

                // 设置文件路径
                String filePath = getFilePath(originalFileName, "");
                // 上传文件
                String bucketName = cosConfig.getBucketName();
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(file.getSize());
                objectMetadata.setContentType(file.getContentType());
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filePath, inputStream, objectMetadata);
                cosClient.putObject(putObjectRequest);
                cosClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
                String url = filePath;
                res += url + ",";
            }
            String paths = res.substring(0, res.length() - 1);
            return "https://image-1326902616.cos.ap-beijing.myqcloud.com"+paths;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cosClient.shutdown();
        }
        return null;
    }

    /**
     * 删除文件
     * @param fileName 文件名称
     * @return
     */
    public String delete(String fileName) {
        cosConfig.cosClient();
        // 文件桶内路径
        String filePath = getDelFilePath(fileName, "");
        cosClient.deleteObject(cosConfig.getBucketName(), filePath);
        return "删除成功";
    }

    /**
     * 读取文件
     * @param fileName 文件名称
     *
     */
    public InputStream read(String fileName) {
        cosConfig.cosClient();
        // 文件桶内路径
        String filePath = getDelFilePath(fileName, "");
        return cosClient.getObject(cosConfig.getBucketName(), filePath).getObjectContent();

    }


    /**
     * 生成文件路径
     * @param originalFileName 原始文件名称
     * @param folder 存储路径
     * @return
     */
    private String getFilePath(String originalFileName, String folder) {
        // 获取后缀名
        String fileType = originalFileName.substring(originalFileName.lastIndexOf("."));
        // 以文件后缀来存储在存储桶中生成文件夹方便管理
        String filePath = folder + "/";
        // 去除文件后缀 替换所有特殊字符
        filePath += originalFileName.substring(0, originalFileName.lastIndexOf(".")).replaceAll("[\\pP\\p{Punct}]", "");
        //今天的日期，YYYY_MM_DD
        filePath += new SimpleDateFormat("yyyy_MM_dd").format(new Date());

        filePath +=  fileType;

        log.info("filePath：" + filePath);
        return filePath;
    }
    /**
     * 生成文件路径
     * @param originalFileName 原始文件名称
     * @param folder 存储路径
     * @return
     */
    private String getDelFilePath(String originalFileName, String folder) {
        // 获取后缀名
        String fileType = originalFileName.substring(originalFileName.lastIndexOf("."));
        // 以文件后缀来存储在存储桶中生成文件夹方便管理
        String filePath = folder + "/";
        // 去除文件后缀 替换所有特殊字符
        String fileStr = originalFileName.substring(0, originalFileName.lastIndexOf(".")).replaceAll("[\\pP\\p{Punct}]", "");

        filePath += fileStr + fileType;
        log.info("filePath：" + filePath);
        return filePath;
    }
}