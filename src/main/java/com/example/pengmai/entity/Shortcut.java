package com.example.pengmai.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Shortcut {
    @Id
    private String shortcutId;
    private String shortcutKind;//种类
    private String shortcutDescribe;//描述
    private String shortcutContent;//内容
}
