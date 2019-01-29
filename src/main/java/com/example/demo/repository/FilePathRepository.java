package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.pojo.FilePath;
 
@Repository
@Transactional
public interface FilePathRepository extends CrudRepository<FilePath, Integer> {
	
	@Query(value = "select * from uml where uml like %?1%", nativeQuery = true)
    @Modifying
    List<FilePath> findUserByGroupName(String groupName);

}
