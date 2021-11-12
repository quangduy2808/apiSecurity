package com.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.news.converter.NewConverter;
import com.news.dto.NewDTO;
import com.news.entity.CategoryEntity;
import com.news.entity.NewEntity;
import com.news.repositoy.CategoryRepository;
import com.news.repositoy.NewRepository;
import com.news.service.INewService;

@Service
public class NewService implements INewService {
	
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private NewConverter newConverter;
	
	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAll(pageable).getContent();
		for (NewEntity item: entities) {
			NewDTO newDTO = newConverter.toDto(item);
			models.add(newDTO);
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int) newRepository.count();
	}

	@Override
	public NewDTO findById(long id) {
		NewEntity entity = newRepository.findOneById(id);
		return newConverter.toDto(entity);
	}
	
	@Override
	@Transactional
	public NewDTO save(NewDTO dto) {
		CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode());
		NewEntity newEntity = new NewEntity();
		if (dto.getId() != null) {
			NewEntity oldNew = newRepository.findOneById(dto.getId());
			oldNew.setCategory(category);
			newEntity = newConverter.toEntity(oldNew, dto);
		} else {
			newEntity = newConverter.toEntity(dto);
			newEntity.setCategory(category);
		}
		return newConverter.toDto(newRepository.save(newEntity));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id: ids) {
			newRepository.delete(newRepository.findOneById(id));
		}
	}

	@Override
	public List<NewDTO> findAll() {
		List<NewDTO> list = new ArrayList<NewDTO>();
		List<NewEntity> listEntity = newRepository.findAll();
		for (NewEntity newEntity : listEntity) {
			list.add(newConverter.toDto(newEntity));
		}
		return list;
		
	
	}
}
