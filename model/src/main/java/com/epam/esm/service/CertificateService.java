package com.epam.esm.service;

import com.epam.esm.builder.CertificateBuilder;
import com.epam.esm.builder.CertificateDtoBuilder;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificateHasTagDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.ServiceSearchException;
import com.epam.esm.exception.ServiceValidationException;

import java.util.List;
import java.util.Map;

/**
 * The interface Certificate service.
 */
public interface CertificateService {

    /**
     * Create one certificate.
     *
     * @param certificateDto the certificate dto to create
     * @return the int number of created elements
     */
    int create(CertificateDto certificateDto);

    /**
     * Find by id one certificate dto.
     *
     * @param id the id for search
     * @return the certificate dto
     * @throws ServiceSearchException     the service search exception
     * @throws ServiceValidationException the service validation exception
     */
    CertificateDto findById(String id) throws ServiceSearchException, ServiceValidationException;

    /**
     * Find all certificates.
     *
     * @return the list of all certificates in the database
     */
    List<CertificateDto> findAll();

    /**
     * Find all certificates with the required parameters.
     *
     * @param parameters the parameters for search and sort
     * @return the list of found certificates
     */
    List<CertificateDto> findByParameters(Map<String, String> parameters);

    /**
     * pdate one certificate.
     *
     * @param certificateDto the certificate dto from which the parameters for the update will be taken
     * @return number of renewed certificates
     */
    int update(CertificateDto certificateDto);

    /**
     * Update create one certificate link with a tag.
     *
     * @param certificateHasTagDto the certificate has tag dto with parameters for creation
     * @return number of links created
     */
    int updateAddTagToCertificate(CertificateHasTagDto certificateHasTagDto);

    /**
     * Delete certificate by id.
     *
     * @param id the id
     * @return number of certificates removed
     */
    int deleteById(String id);

    /**
     * Delete one certificate link with a tag.
     *
     * @param certificateHasTagDto the certificate has tag dto with parameters for removing
     * @return number of removed links
     */
    int deleteTagFromCertificate(CertificateHasTagDto certificateHasTagDto);

    /**
     * Convert certificate to certificate dto.
     *
     * @param certificate the certificate
     * @return the certificate dto
     */
    default CertificateDto convertCertificateToCertificateDto(Certificate certificate) {
        CertificateDtoBuilder certificateDtoBuilder = new CertificateDtoBuilder();
        certificateDtoBuilder.setId(certificate.getId());
        certificateDtoBuilder.setName(certificate.getName());
        certificateDtoBuilder.setDescription(certificate.getDescription());
        certificateDtoBuilder.setPrice(certificate.getPrice());
        certificateDtoBuilder.setDuration(certificate.getDuration());
        certificateDtoBuilder.setCreateDate(certificate.getCreateDate());
        certificateDtoBuilder.setLastUpdateDate(certificate.getLastUpdateDate());
        return certificateDtoBuilder.build();
    }

    /**
     * Convert certificate dto to certificate.
     *
     * @param certificateDto the certificate dto
     * @return the certificate
     */
    default Certificate convertCertificateDtoToCertificate(CertificateDto certificateDto) {
        CertificateBuilder certificateBuilder = new CertificateBuilder();
        certificateBuilder.setId(certificateDto.getId());
        certificateBuilder.setName(certificateDto.getName());
        certificateBuilder.setDescription(certificateDto.getDescription());
        certificateBuilder.setPrice(certificateDto.getPrice());
        certificateBuilder.setDuration(certificateDto.getDuration());
        certificateBuilder.setCreateDate(certificateDto.getCreateDate());
        certificateBuilder.setLastUpdateDate(certificateDto.getLastUpdateDate());
        return certificateBuilder.build();
    }
}
