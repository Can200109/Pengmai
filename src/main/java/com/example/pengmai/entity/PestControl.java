package com.example.pengmai.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class PestControl {
    @Id
    private String pestId;
    private String pestTitle;
    private String pestContent;
}
