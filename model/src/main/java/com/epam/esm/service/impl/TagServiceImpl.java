package com.epam.esm.service.impl;

import com.epam.esm.constant.LanguagePath;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ServiceSearchException;
import com.epam.esm.exception.ServiceValidationException;
import com.epam.esm.service.TagService;
import com.epam.esm.util.LocaleManager;
import com.epam.esm.util.TagConverter;
import com.epam.esm.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagConverter tagConverter;
    private final Validator validator;
    private final TagDao tagDao;
    private final LocaleManager localeManager;

    @Autowired
    public TagServiceImpl(TagDao tagDao, Validator validator,
                          TagConverter tagConverter, LocaleManager localeManager) {
        this.tagDao = tagDao;
        this.validator = validator;
        this.tagConverter = tagConverter;
        this.localeManager = localeManager;
    }

    @Override
    public int create(TagDto tagDto) {
        int countOfCreation = 0;
        if (validator.validateTag(tagDto)) {
            countOfCreation = tagDao.create(tagConverter.convertTagDtoToTag(tagDto));
        }
        return countOfCreation;
    }

    @Override
    public TagDto findById(String id) throws ServiceSearchException, ServiceValidationException {
        if (validator.validateId(id)) {
            Long localId = Long.parseLong(id);
            Optional<Tag> result = tagDao.findById(localId);
            return checkTag(result);
        } else {
            throw new ServiceValidationException(
                    localeManager.getLocalizedMessage(LanguagePath.TAG_ERROR_VALIDATION));
        }
    }

    @Override
    public TagDto findByName(String name) throws ServiceSearchException, ServiceValidationException {
        if (validator.validateName(name)) {
            Optional<Tag> result = tagDao.findByName(name);
            return checkTag(result);
        } else {
            throw new ServiceValidationException(
                    localeManager.getLocalizedMessage(LanguagePath.TAG_ERROR_VALIDATION));
        }
    }

    @Override
    public List<TagDto> findAll() {
        List<TagDto> tagDtoList = new ArrayList<>();
        for (Tag element : tagDao.findAll()) {
            tagDtoList.add(tagConverter.convertTagToTegDto(element));
        }
        return tagDtoList;
    }

    @Override
    public int deleteById(String id) {
        int resultCountOfDeletes = 0;
        if (validator.validateId(id)) {
            Long localId = Long.parseLong(id);
            resultCountOfDeletes = tagDao.deleteById(localId);
        }
        return resultCountOfDeletes;
    }

    private TagDto checkTag(Optional<Tag> tag) throws ServiceSearchException {
        if (tag.isPresent()) {
            return tagConverter.convertTagToTegDto(tag.get());
        } else {
            throw new ServiceSearchException(
                    localeManager.getLocalizedMessage(LanguagePath.TAG_ERROR_NOT_FOUND));
        }
    }
}
