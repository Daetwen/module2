package com.epam.esm.service.impl;

import com.epam.esm.constant.FindParameter;
import com.epam.esm.constant.FindRequest;
import com.epam.esm.constant.LanguagePath;
import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificateHasTagDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ServiceSearchException;
import com.epam.esm.exception.ServiceValidationException;
import com.epam.esm.service.CertificateService;
import com.epam.esm.util.LocaleManager;
import com.epam.esm.util.TagConverter;
import com.epam.esm.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final Validator validator;
    private final CertificateDao certificateDao;
    private final TagDao tagDao;
    private final LocaleManager localeManager;

    @Autowired
    public CertificateServiceImpl(CertificateDao certificateDao, TagDao tagDao,
                                  Validator validator, LocaleManager localeManager) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
        this.validator = validator;
        this.localeManager = localeManager;
    }

    @Override
    public int create(CertificateDto certificateDto) {
        int countOfCreation = 0;
        if (validator.validateCertificate(certificateDto)) {
            countOfCreation = certificateDao.create(
                    CertificateService.super.convertCertificateDtoToCertificate(certificateDto));
        }
        return countOfCreation;
    }

    @Override
    public CertificateDto findById(String id) throws ServiceSearchException, ServiceValidationException {
        if (validator.validateId(id)) {
            Long localId = Long.parseLong(id);
            Optional<Certificate> result = certificateDao.findById(localId);
            if (result.isPresent()) {
                CertificateDto certificateDto =
                        CertificateService.super.convertCertificateToCertificateDto(result.get());
                appendTagsForOne(certificateDto);
                return certificateDto;
            } else {
                throw new ServiceSearchException(
                        localeManager.getLocalizedMessage(LanguagePath.CERTIFICATE_ERROR_NOT_FOUND));
            }
        } else {
            throw new ServiceValidationException(
                    localeManager.getLocalizedMessage(LanguagePath.CERTIFICATE_ERROR_VALIDATION));
        }
    }

    @Override
    public List<CertificateDto> findAll() {
        List<CertificateDto> certificateDtoList = new ArrayList<>();
        for (Certificate element : certificateDao.findAll()) {
            certificateDtoList.add(CertificateService.super.convertCertificateToCertificateDto(element));
        }
        return appendTags(certificateDtoList);
    }

    @Override
    public List<CertificateDto> findByParameters(Map<String, String> parameters) {
        StringBuilder resultRequest;
        if (parameters.containsKey(FindParameter.SORT)) {
            resultRequest = createRequest(parameters);
            resultRequest.append(FindRequest.SORT_BY_NAME).append(parameters.get(FindParameter.SORT));
        } else {
            resultRequest = createRequest(parameters);
        }
        Object[] arguments = createArguments(parameters);
        List<Certificate> certificateList =
                certificateDao.findByParameters(resultRequest.toString(), arguments);
        List<CertificateDto> certificateDtoList =
                covertListOfCertificateToCertificateDto(certificateList);
        return appendTags(certificateDtoList);
    }

    @Override
    public int update(CertificateDto certificateDto) {
        int resultCountOfUpdate = 0;
        if (validator.validateCertificate(certificateDto)) {
            resultCountOfUpdate = certificateDao.update(
                    CertificateService.super.convertCertificateDtoToCertificate(certificateDto));
        }
        return resultCountOfUpdate;
    }

    @Override
    public int updateAddTagToCertificate(CertificateHasTagDto certificateHasTagDto) {
        String certificateId = certificateHasTagDto.getCertificateId();
        String tagId = certificateHasTagDto.getTagId();
        int resultCountOfUpdate = 0;
        if (validator.validateId(certificateId) && validator.validateId(tagId)) {
            Long localCertificateId = Long.parseLong(certificateId);
            Long localTagId = Long.parseLong(tagId);
            if (certificateDao.findById(localCertificateId).isPresent()
                    && tagDao.findById(localTagId).isPresent()) {
                resultCountOfUpdate =
                        certificateDao.updateAddTagToCertificate(localCertificateId, localTagId);
            }
        }
        return resultCountOfUpdate;
    }

    @Override
    public int deleteById(String id) {
        int resultCountOfDeletes = 0;
        if (validator.validateId(id)) {
            Long localId = Long.parseLong(id);
            resultCountOfDeletes = certificateDao.deleteById(localId);
        }
        return resultCountOfDeletes;
    }

    @Override
    public int deleteTagFromCertificate(CertificateHasTagDto certificateHasTagDto) {
        String certificateId = certificateHasTagDto.getCertificateId();
        String tagId = certificateHasTagDto.getTagId();
        int resultCountOfDeletes = 0;
        if (validator.validateId(certificateId) && validator.validateId(tagId)) {
            Long localCertificateId = Long.parseLong(certificateId);
            Long localTagId = Long.parseLong(tagId);
            resultCountOfDeletes =
                    certificateDao.deleteTagFromCertificate(localCertificateId, localTagId);
        }
        return resultCountOfDeletes;
    }

    private StringBuilder createRequest(Map<String, String> parameters) {
        String union = "UNION ";
        StringBuilder result = new StringBuilder();
        if (parameters.containsKey(FindParameter.TAG_NAME)) {
            result.append(FindRequest.FIND_CERTIFICATE_BY_TAG_NAME);
        }
        if (parameters.containsKey(FindParameter.NAME_OR_DESC_PART)) {
            if (result.length() == 0) {
                result.append(FindRequest.FIND_CERTIFICATE_BY_PART_OF_NAME_OR_DESCRIPTION);
            } else {
                result.append(union)
                        .append(FindRequest.FIND_CERTIFICATE_BY_PART_OF_NAME_OR_DESCRIPTION);
            }
        }
        return result;
    }

    private Object[] createArguments(Map<String, String> parameters) {
        List<Object> arguments = new ArrayList<>();
        if (parameters.containsKey(FindParameter.TAG_NAME)) {
            arguments.add(parameters.get(FindParameter.TAG_NAME));
        }
        if (parameters.containsKey(FindParameter.NAME_OR_DESC_PART)) {
            parameters.computeIfPresent(FindParameter.NAME_OR_DESC_PART,
                    (key, value) -> value = "%" + value + "%");
            arguments.add(parameters.get(FindParameter.NAME_OR_DESC_PART));
            arguments.add(parameters.get(FindParameter.NAME_OR_DESC_PART));
        }
        Object[] argumentsResult = new Object[arguments.size()];
        for (int i = 0; i < arguments.size(); i++) {
            argumentsResult[i] = arguments.get(i);
        }
        return argumentsResult;
    }

    private List<CertificateDto> appendTags(List<CertificateDto> certificateDtoList) {
        for (CertificateDto certificateDto : certificateDtoList) {
            appendTagsForOne(certificateDto);
        }
        return certificateDtoList;
    }

    private void appendTagsForOne(CertificateDto certificateDto) {
        List<Tag> tags = tagDao.findByCertificateId(certificateDto.getId());
        TagConverter tagConverter = new TagConverter();
        List<TagDto> tagsDto = new ArrayList<>();
        for (Tag tag : tags) {
            tagsDto.add(tagConverter.convertTagToTegDto(tag));
        }
        certificateDto.setTags(tagsDto);
    }

    private List<CertificateDto> covertListOfCertificateToCertificateDto(List<Certificate> certificates) {
        List<CertificateDto> certificateDtoList = new ArrayList<>();
        for (Certificate element : certificates) {
            certificateDtoList.add(CertificateService.super.convertCertificateToCertificateDto(element));
        }
        return certificateDtoList;
    }
}
