package com.epam.esm.dao.impl;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.entity.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class CertificateDaoImpl implements CertificateDao {

    private static final String CREATE_CERTIFICATE = "INSERT INTO gift_certificates " +
            "(name, description, price, duration, create_date, last_update_date) VALUES(?,?,?,?,?,?)";
    private static final String FIND_CERTIFICATE_BY_ID = "SELECT " +
            "id, name, description, price, duration, create_date, last_update_date " +
            "FROM gift_certificates WHERE id=?";
    private static final String FIND_ALL_CERTIFICATES = "SELECT " +
            "id, name, description, price, duration, create_date, last_update_date " +
            "FROM gift_certificates";
    private static final String UPDATE_CERTIFICATE = "UPDATE gift_certificates SET " +
            "name=COALESCE(?, name), " +
            "description=COALESCE(?, description), " +
            "price=COALESCE(?, price), " +
            "duration=COALESCE(?, duration) " +
            "last_update_date=? " +
            "WHERE id=?";
    private static final String UPDATE_ADD_TAG_TO_CERTIFICATE =
            "INSERT INTO gift_certificates_has_tags (gift_certificates_id, tags_id) VALUES(?,?)";
    private static final String DELETE_CERTIFICATE = "DELETE FROM gift_certificates WHERE id=?";
    private static final String DELETE_TAG_FROM_CERTIFICATE =
            "DELETE FROM gift_certificates_has_tags WHERE gift_certificates_id=? AND tags_id=?";

    private static final BeanPropertyRowMapper<Certificate> rowMapper =
            new BeanPropertyRowMapper<>(Certificate.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Certificate certificate) {
        certificate.setCreateDate(OffsetDateTime.now());
        certificate.setLastUpdateDate(OffsetDateTime.now());
        return jdbcTemplate.update(CREATE_CERTIFICATE,
                certificate.getName(),
                certificate.getDescription(),
                certificate.getPrice(),
                certificate.getDuration(),
                certificate.getCreateDate(),
                certificate.getLastUpdateDate());
    }

    @Override
    public Optional<Certificate> findById(Long id) {
        return jdbcTemplate.query(FIND_CERTIFICATE_BY_ID, rowMapper, id).stream().findAny();
    }

    @Override
    public List<Certificate> findAll() {
        return jdbcTemplate.query(FIND_ALL_CERTIFICATES, rowMapper);
    }

    @Override
    public List<Certificate> findByParameters(String parameters, Object... arguments) {
        return jdbcTemplate.query(parameters, rowMapper, arguments);
    }

    @Override
    public int update(Certificate certificate) {
        certificate.setLastUpdateDate(OffsetDateTime.now());
        return jdbcTemplate.update(UPDATE_CERTIFICATE,
                certificate.getName(),
                certificate.getDescription(),
                certificate.getPrice(),
                certificate.getDuration(),
                certificate.getLastUpdateDate(),
                certificate.getId());
    }

    @Override
    public int updateAddTagToCertificate(Long certificateId, Long tagId) {
        return jdbcTemplate.update(UPDATE_ADD_TAG_TO_CERTIFICATE, certificateId, tagId);
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(DELETE_CERTIFICATE, id);
    }

    @Override
    public int deleteTagFromCertificate(Long certificateId, Long tagId) {
        return jdbcTemplate.update(DELETE_TAG_FROM_CERTIFICATE, certificateId, tagId);
    }
}
