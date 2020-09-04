package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.entity.vo.OneSubjectVo;
import com.atguigu.serviceedu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author mark
 * @since 2020-09-01
 */
@Api(description="课程分类管理")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @ApiOperation(value = "添加课程分类")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        //调用接口
        subjectService.importSubjectData(file,subjectService);
        return R.ok();
    }

    @ApiOperation(value = "查询课程分类")
    @GetMapping
    public R getAllSubject(){
        List<OneSubjectVo> allSubject = subjectService.getAllSubject();
        return R.ok().data("allSubject",allSubject);
    }

}

