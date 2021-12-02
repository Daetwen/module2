package com.epam.esm.service.impl;

import com.epam.esm.builder.CertificateBuilder;
import com.epam.esm.builder.CertificateDtoBuilder;
import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.CertificateService;
import com.epam.esm.validator.Validator;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class CertificateServiceImpl {

    private CertificateService certificateService;
    private Validator validator;
    private CertificateDao certificateDao;
    private TagDao tagDao;
    private CertificateDto certificateDtoTest1;
    private Certificate certificateTest1;
    private TagDto tagDtoTest1;
    private Tag tagTest1;

    @BeforeEach
    public void setUp() {
        validator = mock(Validator.class);
        certificateDao = mock(CertificateDao.class);
        tagDao = mock(TagDao.class);
        List<TagDto> tagDtoList = new ArrayList<>();
        List<Tag> tagList = new ArrayList<>();

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

        tagDtoTest1 = new TagDto(5L, "Hello");
    }
}
