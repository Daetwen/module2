package com.epam.esm.service.impl;

import com.epam.esm.builder.CertificateBuilder;
import com.epam.esm.builder.CertificateDtoBuilder;
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
import com.epam.esm.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CertificateServiceImplTest {

    private CertificateService certificateService;
    private Validator validator;
    private CertificateDao certificateDao;
    private TagDao tagDao;
    private CertificateDto certificateDtoTest1;
    private Certificate certificateTest1;
    private CertificateHasTagDto certificateHasTagDto;
    private Tag tagTest1;

    @BeforeEach
    public void setUp() {
        validator = mock(Validator.class);
        LocaleManager localeManager = mock(LocaleManager.class);
        certificateDao = mock(CertificateDao.class);
        tagDao = mock(TagDao.class);
        certificateService = new CertificateServiceImpl(certificateDao, tagDao, validator, localeManager);
        List<TagDto> tagDtoList = new ArrayList<>();
        List<Tag> tagList = new ArrayList<>();
        tagTest1 = new Tag(5L, "New Year");

        CertificateDtoBuilder certificateDtoBuilder = new CertificateDtoBuilder();
        certificateDtoBuilder.setId(2L);
        certificateDtoBuilder.setName("Name");
        certificateDtoBuilder.setDescription("Description");
        certificateDtoBuilder.setPrice(new BigDecimal(5000));
        certificateDtoBuilder.setDuration(100);
        certificateDtoBuilder.setCreateDate(LocalDateTime.parse("2021-11-20T21:30:19"));
        certificateDtoBuilder.setLastUpdateDate(LocalDateTime.parse("2021-11-22T21:25:37"));
        certificateDtoTest1 = certificateDtoBuilder.build();
        certificateDtoTest1.setTags(tagDtoList);

        CertificateBuilder certificateBuilder = new CertificateBuilder();
        certificateBuilder.setId(2L);
        certificateBuilder.setName("Name");
        certificateBuilder.setDescription("Description");
        certificateBuilder.setPrice(new BigDecimal(5000));
        certificateBuilder.setDuration(100);
        certificateBuilder.setCreateDate(LocalDateTime.parse("2021-11-20T21:30:19"));
        certificateBuilder.setLastUpdateDate(LocalDateTime.parse("2021-11-22T21:25:37"));
        certificateTest1 = certificateBuilder.build();
        certificateTest1.setTags(tagList);

        certificateHasTagDto = new CertificateHasTagDto("4", "5");
    }

    @Test
    public void createTestTrue() {
        int expected = 1;
        when(validator.validateCertificate(any(CertificateDto.class))).thenReturn(true);
        when(certificateDao.create(any(Certificate.class))).thenReturn(1);
        int actual = certificateService.create(certificateDtoTest1);
        assertEquals(expected, actual);
    }

    @Test
    public void createTestFalse1() {
        int expected = 0;
        when(validator.validateCertificate(any(CertificateDto.class))).thenReturn(false);
        int actual = certificateService.create(certificateDtoTest1);
        assertEquals(expected, actual);
    }

    @Test
    public void createTestFalse2() {
        int expected = 0;
        when(validator.validateCertificate(any(CertificateDto.class))).thenReturn(true);
        when(certificateDao.create(any(Certificate.class))).thenReturn(0);
        int actual = certificateService.create(certificateDtoTest1);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTestTrue() throws ServiceSearchException, ServiceValidationException {
        CertificateDto expected = certificateDtoTest1;
        Optional<Certificate> localCertificate = Optional.ofNullable(certificateTest1);
        when(validator.validateId(anyString())).thenReturn(true);
        when(certificateDao.findById(anyLong())).thenReturn(localCertificate);
        CertificateDto actual = certificateService.findById("5");
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTestFalse1() {
        when(validator.validateId(anyString())).thenReturn(false);
        assertThrows(ServiceValidationException.class,
                () -> certificateService.findById("f5g"));
    }

    @Test
    public void findByIdTestFalse2() {
        when(validator.validateId(anyString())).thenReturn(true);
        when(certificateDao.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ServiceSearchException.class,
                () -> certificateService.findById("5"));
    }

    @Test
    public void findAllTestTrue() {
        List<CertificateDto> expected = new ArrayList<>();
        expected.add(certificateDtoTest1);
        List<Certificate> listForWork = new ArrayList<>();
        listForWork.add(certificateTest1);
        when(certificateDao.findAll()).thenReturn(listForWork);
        List<CertificateDto> actual = certificateService.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void updateTestTrue() {
        int expected = 1;
        when(validator.validateCertificate(any(CertificateDto.class))).thenReturn(true);
        when(certificateDao.update(any(Certificate.class))).thenReturn(1);
        int actual = certificateService.update(certificateDtoTest1);
        assertEquals(expected, actual);
    }

    @Test
    public void updateTestFalse1() {
        int expected = 0;
        when(validator.validateCertificate(any(CertificateDto.class))).thenReturn(false);
        int actual = certificateService.update(certificateDtoTest1);
        assertEquals(expected, actual);
    }

    @Test
    public void updateTestFalse2() {
        int expected = 0;
        when(validator.validateCertificate(any(CertificateDto.class))).thenReturn(true);
        when(certificateDao.update(any(Certificate.class))).thenReturn(0);
        int actual = certificateService.update(certificateDtoTest1);
        assertEquals(expected, actual);
    }

    @Test
    public void updateAddTagToCertificateTestTrue() {
        int expected = 1;
        Optional<Certificate> certificateOptional = Optional.ofNullable(certificateTest1);
        Optional<Tag> tagOptional = Optional.ofNullable(tagTest1);
        when(validator.validateId(anyString())).thenReturn(true);
        when(certificateDao.findById(anyLong())).thenReturn(certificateOptional);
        when(tagDao.findById(anyLong())).thenReturn(tagOptional);
        when(certificateDao.updateAddTagToCertificate(anyLong(), anyLong()))
                .thenReturn(1);
        int actual = certificateService.updateAddTagToCertificate(certificateHasTagDto);
        assertEquals(expected, actual);
    }

    @Test
    public void updateAddTagToCertificateTestFalse1() {
        int expected = 0;
        when(validator.validateId(anyString())).thenReturn(false);
        int actual = certificateService.updateAddTagToCertificate(certificateHasTagDto);
        assertEquals(expected, actual);
    }

    @Test
    public void updateAddTagToCertificateTestFalse2() {
        int expected = 0;
        when(validator.validateId(anyString())).thenReturn(true);
        when(certificateDao.findById(anyLong())).thenReturn(Optional.empty());
        int actual = certificateService.updateAddTagToCertificate(certificateHasTagDto);
        assertEquals(expected, actual);
    }

    @Test
    public void updateAddTagToCertificateTestFalse3() {
        int expected = 0;
        Optional<Certificate> certificateOptional = Optional.ofNullable(certificateTest1);
        when(validator.validateId(anyString())).thenReturn(true);
        when(certificateDao.findById(anyLong())).thenReturn(certificateOptional);
        when(tagDao.findById(anyLong())).thenReturn(Optional.empty());
        int actual = certificateService.updateAddTagToCertificate(certificateHasTagDto);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteByIdTestTrue() {
        int expected = 1;
        when(validator.validateId(anyString())).thenReturn(true);
        when(certificateDao.deleteById(anyLong())).thenReturn(1);
        int actual = certificateService.deleteById("5");
        assertEquals(expected, actual);
    }

    @Test
    public void deleteByIdTestFalse1() {
        int expected = 0;
        when(validator.validateId(anyString())).thenReturn(false);
        int actual = certificateService.deleteById("5");
        assertEquals(expected, actual);
    }

    @Test
    public void deleteByIdTestFalse2() {
        int expected = 0;
        when(validator.validateId(anyString())).thenReturn(true);
        when(certificateDao.deleteById(anyLong())).thenReturn(0);
        int actual = certificateService.deleteById("5");
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTagFromCertificateTestTrue() {
        int expected = 1;
        when(validator.validateId(anyString())).thenReturn(true);
        when(certificateDao.deleteTagFromCertificate(anyLong(), anyLong()))
                .thenReturn(1);
        int actual = certificateService.deleteTagFromCertificate(certificateHasTagDto);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTagFromCertificateTestFalse() {
        int expected = 0;
        when(validator.validateId(anyString())).thenReturn(false);
        int actual = certificateService.deleteTagFromCertificate(certificateHasTagDto);
        assertEquals(expected, actual);
    }
}
