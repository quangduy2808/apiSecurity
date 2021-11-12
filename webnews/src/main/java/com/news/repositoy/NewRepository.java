package com.news.repositoy;

import java.util.List;


import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.news.entity.NewEntity;

@Repository
public interface NewRepository extends JpaRepository<NewEntity, Long> {
	List<NewEntity> findALL(Pageable pageable);

	NewEntity findOneById(Long id);
	List<NewEntity> findAll();
}
