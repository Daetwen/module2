package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TagDaoImpl implements TagDao {

    private static final BeanPropertyRowMapper<Tag> rowMapper =
            new BeanPropertyRowMapper<>(Tag.class);
    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE_TAG = "INSERT INTO tags (name) VALUES(?)";
    private static final String FIND_BY_ID = "SELECT id, name FROM tags WHERE id=?";
    private static final String FIND_BY_NAME = "SELECT id, name FROM tags WHERE name=?";
    private static final String FIND_BY_CERTIFICATE_ID = "SELECT tags.id, tags.name FROM tags " +
            "JOIN gift_certificates_has_tags ON tags_id = tags.id " +
            "JOIN gift_certificates ON gift_certificates_id = gift_certificates.id " +
            "WHERE gift_certificates.id=?";
    private static final String FIND_ALL = "SELECT id, name FROM tags";
    private static final String DELETE_BY_ID = "DELETE FROM tags WHERE id=?";

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Tag tag) {
        return jdbcTemplate.update(CREATE_TAG, tag.getName());
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return jdbcTemplate.query(FIND_BY_ID, rowMapper, id).stream().findAny();
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return jdbcTemplate.query(FIND_BY_NAME, rowMapper, name).stream().findAny();
    }

    @Override
    public List<Tag> findByCertificateId(Long id) {
        return jdbcTemplate.query(FIND_BY_CERTIFICATE_ID, rowMapper, id);
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(FIND_ALL, rowMapper);
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(DELETE_BY_ID, id);
    }
}
