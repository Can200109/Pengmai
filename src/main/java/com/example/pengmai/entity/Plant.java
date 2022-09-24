package com.example.pengmai.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Plant {
    @Id
    private String plantId;
    private String plantTitle;
    private String plantText;
    private String plantMonth;
}
