package com.example.pengmai.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Notice {
    @Id
    private String noticeId;
    private String noticeContent;
    private String noticeTitle;
    private String noticeTime;
}
