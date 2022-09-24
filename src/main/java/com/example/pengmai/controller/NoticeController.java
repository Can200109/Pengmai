package com.example.pengmai.controller;

import com.example.pengmai.domain.Result;
import com.example.pengmai.entity.Notice;
import com.example.pengmai.service.NoticeService;
import com.example.pengmai.utill.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Notice")
@Transactional
@CrossOrigin
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    //添加、修改
    @RequestMapping("/update")
    private Result<Notice> update(Notice notice){
        return ResultUtil.success(noticeService.update(notice));
    }
    //删除
    @RequestMapping("/delete")
    private Result<Notice> delete(Notice notice){
        if (notice ==null || noticeService.delete(notice)==null){
            return ResultUtil.error("该短语不存在");
        }
        return ResultUtil.success(noticeService.delete(notice));
    }
    //查找所有
    @RequestMapping("/findAll")
    private Result<List<Notice>> findAll(){
        return ResultUtil.success(noticeService.findAll());
    }
    @RequestMapping("/findLike")
    private Result<List<Notice>> findLike(String str){
        return ResultUtil.success(noticeService.findLike(str));
    }
    //根据ID查找
    @RequestMapping("/findById")
    private Result<Notice> findById(Notice notice){
        return ResultUtil.success(noticeService.findNotice(notice.getNoticeId()));
    }
    //根据重要程度查找

}
