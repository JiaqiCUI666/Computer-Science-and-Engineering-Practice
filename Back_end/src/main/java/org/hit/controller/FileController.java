package org.hit.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.hit.pojo.*;
import org.hit.service.CosService;
import org.hit.utils.ResponseData;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hit.service.EmailService;

@RestController
@Slf4j
@Api(value="文件controller",tags={"文件操作接口"})
public class FileController {
    @Resource
    private CosService cosService;

    @Resource
    private EmailService emailService;

    // 上传文件
    @PostMapping("/file/upload")
    @ApiOperation(value = "上传.eml文件")
//    @ApiImplicitParam(value = "上传文件",
//            name = "file",
//            dataType = "__File",
//            required = true
//    )
    public ResponseData<Object> upload(  @RequestPart @RequestParam("file") MultipartFile file) throws Exception {
        log.info("upload");
        MultipartFile[] files = new MultipartFile[1];
        files[0] = file;
        if (file.isEmpty()) {
            return ResponseData.builder().data(null).message("上传失败").status(200).success(0).build();
        }
        String originalFileName = file.getOriginalFilename();
        // 获得文件流
        InputStream inputStream = null;
        inputStream = file.getInputStream();
        //同时将文件保存在static里一份
        InputStream copyInputStream = file.getInputStream();
        //Date yyyy-MM-dd_HH-mm-ss
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String time = sdf.format(date);

        String filePath = "/emails/" + time+ ".eml";
        File dest = new File(filePath);
        FileUtils.copyInputStreamToFile(copyInputStream, dest);

        PackageSend packageSend = new PackageSend(true,filePath);

        //建立socket通信，127.0.0.1，  端口9999
        PackageReceive res = getRes(packageSend);
        //String res = cosService.upload(files);
        if (res == null) {
            return ResponseData.builder().data(null).message("解析失败").status(200).success(0).build();
        }
        if (res.getIs_pfish_email().is_pfish()){
            BlackInfo blackInfo = new BlackInfo();
            blackInfo.setEmail(res.getSender_email_address());
            blackInfo.setIp(res.getSender_ip_address());
            List<BlackInfo> blackList = emailService.getBlackListByBlackEmail(blackInfo.getEmail());
            for (BlackInfo blackInfo1 : blackList){
                if (blackInfo1.getIp().equals(blackInfo.getIp())){
                    return ResponseData.builder().data(res).message("解析成功").status(200).successs().build();
                } else {
                    log.info("addBlackList");
                    emailService.addBlackList(blackInfo);
                }
            }
            if (blackList.size() == 0){
                log.info("addBlackList");
                emailService.addBlackList(blackInfo);
            }
        }
        return ResponseData.builder().data(res).message("解析成功").status(200).successs().build();
    }

    private static PackageReceive getRes(PackageSend packageSend) throws IOException {
        String serverAddress = "127.0.0.1";
        int port = 9999;
        //发送数据

        Socket socket = new Socket(serverAddress, port);
        OutputStream outputStream = socket.getOutputStream();
        //json序列化, fastjson

        String json = com.alibaba.fastjson.JSON.toJSONString(packageSend);
        outputStream.write(json.getBytes());
        log.info("发送数据成功");

        //接收数据
        log.info("接收数据");

        //time out 20s
        socket.setSoTimeout(20000);
        if (socket.isClosed()){
            return null;
        }


        InputStream inputStream1 = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream1.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len, "UTF-8"));
        }
        //sb -> json -> PackageReceive
        JSONObject jsonObject = new JSONObject(sb.toString());
        PackageReceive res = new PackageReceive();

        res.setSender_email_address(jsonObject.getString("sender_email_address"));
        res.setSender_ip_address(jsonObject.getString("sender_ip_address"));

        JSONObject pfish_email = jsonObject.getJSONObject("is_phish_email");
        Pfish_email pfish_email1 = new Pfish_email();
        pfish_email1.set_pfish(pfish_email.getBoolean("is_pfish"));
        pfish_email1.setConfidence((float) pfish_email.getDouble("confidence"));

        List<Url> urlList = JSON.parseArray(jsonObject.getJSONArray("url_list").toString(), Url.class);
        List<Attachment> attachmentList = JSON.parseArray(jsonObject.getJSONArray("attachment_list").toString(), Attachment.class);
        res.setIs_pfish_email(pfish_email1);
        res.setUrlList(urlList);
        res.setAttachments_list(attachmentList);
        //解析第一个key data by fastjson




//        output_dic = {
//                'sender_email_address': str,
//                'sender_ip_address': str,
//                'is_phish_email': dict,    # 是恶意的是True，反之是False
//        'url_list': [],     # 是恶意的是True，反之是False
//        'attachment_list': [],
//}
//
//        Pfish_email = {
//                'is_pfish': bool,
//                'confidence':float
//}
//
//        Url = {
//                'url':str,
//                'is_bad':bool,
//                'confidence':float
//}
//
//        Attachment = {
//                'name':str,
//                'is_bad':bool,
//                'reason':str
//}

        //关闭资源
        inputStream1.close();
        outputStream.close();
        socket.close();
        return res;
    }

    @PostMapping("/file/upload_text")
    @ApiOperation(value = "上传文本形式邮件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "text", value = "邮件文本", required = true, dataType = "String")
    })
    public ResponseData<Object> upload_text(String text) throws Exception {
        log.info("upload_text");
        if (text==null || text.isEmpty() ) {
            return ResponseData.builder().data(null).message("上传失败").status(200).success(0).build();
        }
        PackageSend packageSend = new PackageSend(false,text);

        //建立socket通信，127.0.0.1，  端口9999
        PackageReceive res = getRes(packageSend);


        if (res == null) {
            return ResponseData.builder().data(null).message("解析失败").status(200).success(0).build();
        }
        return ResponseData.builder().data(res).message("解析成功").status(200).successs().build();

    }

    // 删除文件
    @PostMapping("/file/delete")
    @Operation(summary = "删除文件(暂时无用，保留)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "文件url", required = true, dataType = "String")
    })
    public ResponseData<Object> delete(String url) {
        log.info("delete");
        String res = cosService.delete(url);
        if (res == null) {
            return ResponseData.builder().data(null).message("删除失败").status(200).success(0).build();
        }
        return ResponseData.builder().data(null).message("删除成功").status(200).successs().build();
    }

    // 下载文件
//    @GetMapping("/file/download")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "url", value = "文件url", required = true, dataType = "String")
//    })
//    public ResponseData<Object> download(String url) throws IOException {
//        log.info("download");
//        //在static里找到url对应的文件
//        File file = new File("src/main/resources/static/" + url);
//        if (!file.exists()) {
//            return ResponseData.builder().data(null).message("下载失败").status(200).success(0).build();
//        }
//        //把文件转成输入流
//        InputStream res = FileUtils.openInputStream(file);
//        return ResponseData.builder().data(res).message("下载成功").status(200).successs().build();
//
//
////        InputStream res = cosService.read(url);
////        //把这里的res转成文件存在static里
////        if (res == null) {
////            return ResponseData.builder().data(null).message("下载失败").status(200).success(0).build();
////        }
////        //转成文件后返回文件路径
////        File file = new File("static/" + url);
////        //把res写入file
////        FileUtils.copyInputStreamToFile(res, file);
////
////        return ResponseData.builder().data(url).message("下载成功").status(200).successs().build();
//
//    }

}
