package com.github.Luiztins1.mixs.repository;

import com.github.Luiztins1.mixs.model.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FolderRepository extends JpaRepository<Folder, Integer> {
    Optional<Folder> findByFolderName(String folderName);
}
