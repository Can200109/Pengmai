package com.example.pengmai.service;

import com.example.pengmai.auth.CheckLogin;
import com.example.pengmai.entity.Shortcut;
import com.example.pengmai.repository.ShortcutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class ShortcutService {
    @Autowired
    private ShortcutRepository shortcutRepository;
    @CheckLogin
    public Shortcut updateShortcut(Shortcut shortcut) {
        if(shortcutRepository.findShortcutByShortcutId(shortcut.getShortcutId())==null){
            shortcut.setShortcutId(UUID.randomUUID().toString());
        }
        return shortcutRepository.save(shortcut);
    }
    @CheckLogin
    public void delete(Shortcut shortcut) {
        shortcut = findShortcut(shortcut.getShortcutId());
        shortcutRepository.delete(shortcut);
    }
    public Shortcut findShortcut(String id){
        return shortcutRepository.findShortcutByShortcutId(id);
    }

    public List<Shortcut> findLike(String str){
        return shortcutRepository.findShortcutByShortcutContentContainingOrShortcutDescribeContainingOrShortcutKindContaining(str,str,str);
    }
    public List<Shortcut> findAll(){
        return shortcutRepository.findAll();
    }
}
