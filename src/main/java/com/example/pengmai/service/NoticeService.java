package com.example.pengmai.service;

import com.example.pengmai.auth.CheckLogin;
import com.example.pengmai.entity.Notice;
import com.example.pengmai.repository.PestControlRepository;
import com.example.pengmai.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class NoticeService {
    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    PestControlRepository pestControlRepository;
    @CheckLogin
    public Notice update(Notice notice){
        if(noticeRepository.findByNoticeId(notice.getNoticeId())==null){
            notice.setNoticeId(UUID.randomUUID().toString());
        }
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        notice.setNoticeTime(dateFormat.format(date));
        return noticeRepository.save(notice);
    }
    public Notice findNotice(String id){
        return noticeRepository.findByNoticeId(id);
    }
    @CheckLogin
    public Notice delete(Notice notice){
         noticeRepository.delete(notice);
        return notice;
    }
    public List<Notice> findLike(String str){
        return noticeRepository.findNoticeByNoticeContentContainingOrNoticeTitleContaining(str,str);
    }
    public List<Notice> findAll(){
        return noticeRepository.findAllByOrderByNoticeTimeDesc();
    }
}
