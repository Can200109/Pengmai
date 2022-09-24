package com.example.pengmai.repository;

import com.example.pengmai.entity.Shortcut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortcutRepository extends JpaRepository<Shortcut,String> {
    Shortcut findShortcutByShortcutId(String id);
    List<Shortcut> findShortcutByShortcutContentContainingOrShortcutDescribeContainingOrShortcutKindContaining(String content,String describe,String kind);
}
