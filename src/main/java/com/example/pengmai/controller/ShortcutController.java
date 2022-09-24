package com.example.pengmai.controller;

import com.example.pengmai.domain.Result;
import com.example.pengmai.entity.Shortcut;
import com.example.pengmai.service.ShortcutService;
import com.example.pengmai.utill.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Shortcut")
@Transactional
@CrossOrigin
public class ShortcutController {
    @Autowired
    ShortcutService shortcutService;
    //添加或修改
    @RequestMapping("/updateShortcut")
    private Result<Shortcut> addShortcut(Shortcut shortcut){
       return ResultUtil.success(shortcutService.updateShortcut(shortcut));
    }
    //删除
    @RequestMapping("/deleteShortcut")
    private void deleteShortcut(Shortcut shortcut){
        shortcutService.delete(shortcut);
    }
    //模糊查找，根据关键字
    @RequestMapping("/findLike")
    private Result<List<Shortcut>> findShortcut(String str){
        return ResultUtil.success(shortcutService.findLike(str));
    }
    //查找所有
    @RequestMapping("/findAll")
    private Result<List<Shortcut>> findAll(){return ResultUtil.success(shortcutService.findAll());}
}
