package com.example.pengmai.repository;

import com.example.pengmai.entity.Notice;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice,String> {
    Notice findByNoticeId(String id);
    List<Notice> findAllByOrderByNoticeTimeDesc();
    List<Notice> findNoticeByNoticeContentContainingOrNoticeTitleContaining(String content, String title);
}
