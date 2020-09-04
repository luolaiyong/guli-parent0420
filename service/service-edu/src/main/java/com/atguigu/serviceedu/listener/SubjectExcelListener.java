package com.atguigu.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.servicebase.handler.GuliException;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.excel.ExcelSubjectData;
import com.atguigu.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {
    //因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能自动注入其他对象
    //不能实现数据库操作
    public EduSubjectService subjectService;
    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //一行一行去读取excle内容
    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {

        //1 判断数据
        if(excelSubjectData==null){
            throw new GuliException(20001,"文件数据为空");
        }
        //2读取一行数据，有两个值，第一个是一级分类，第二个是二级分类
        //3判断一级分类是否重复
        EduSubject existOneSubject = this.existOnesubject(subjectService, excelSubjectData.getOneSubjectName());
        if(existOneSubject==null){//没有相同一级分类，进行添加
            //3.1没有重复，插入一级分类
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(excelSubjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }

        //4获取一级分类id
        String pid = existOneSubject.getId();
        //5判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwosubject(subjectService, excelSubjectData.getTwoSubjectName(), pid);
        if(existTwoSubject==null){
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(excelSubjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }

    }

    //判断一级分类不能重复
    private EduSubject existOnesubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断一级分类不能重复
    private EduSubject existTwosubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
