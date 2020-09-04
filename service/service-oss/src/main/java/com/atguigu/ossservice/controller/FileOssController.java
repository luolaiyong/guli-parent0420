package com.atguigu.ossservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.ossservice.service.FileService;
import com.atguigu.ossservice.utils.ConstantPropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(description="上传文件管理")
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class FileOssController {
    @Autowired
    private FileService fileService;

    @ApiOperation(value = "上传文件")
    @PostMapping("fileUpload")
    public R fileUploadOss(MultipartFile file,String host){
        //1得到上传文件
        //2上传阿里云oss
        if(!StringUtils.isEmpty(host)){
            ConstantPropertiesUtil.FILE_HOST = host;
        }
        String url = fileService.uploadFileOss(file);
        //3返回oss地址
        return R.ok().data("url",url);
    }

}
