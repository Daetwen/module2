package com.epam.esm.controller;

import com.epam.esm.constant.FindParameter;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificateHasTagDto;
import com.epam.esm.exception.ServiceSearchException;
import com.epam.esm.exception.ServiceValidationException;
import com.epam.esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/certificates")
public class CertificateController {
    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @RequestMapping(value="/certificate_create",method = RequestMethod.POST)
    public int create(@RequestBody CertificateDto certificateDto) {
        return certificateService.create(certificateDto);
    }

    @GetMapping
    public List<CertificateDto> getAll() {
        return certificateService.findAll();
    }

    @GetMapping("/{id}")
    public CertificateDto getOne(@PathVariable String id)
            throws ServiceSearchException, ServiceValidationException {
        return certificateService.findById(id);
    }

    @GetMapping("/certificate_parameters")
    public List<CertificateDto> getSomeByParameters(
            @RequestParam(value = "tagName", required = false) String tagName,
            @RequestParam(value = "part", required = false) String part,
            @RequestParam(value = "sort", required = false) String sort) {
        Map<String, String> parameters = new HashMap<>();
        if (tagName != null) {
            parameters.put(FindParameter.TAG_NAME, tagName);
        }
        if (part != null) {
            parameters.put(FindParameter.NAME_OR_DESC_PART, part);
        }
        if (sort != null) {
            parameters.put(FindParameter.SORT, sort);
        }
        return certificateService.findByParameters(parameters);
    }

    @RequestMapping(value="/certificate_update", method = RequestMethod.PATCH)
    public int update(@RequestBody CertificateDto certificateDto) {
        return certificateService.update(certificateDto);
    }

    @RequestMapping(value="/certificate_update_tag", method = RequestMethod.PUT)
    public int updateTagFromCertificate(@RequestBody CertificateHasTagDto certificateHasTagDto) {
        return certificateService.updateAddTagToCertificate(certificateHasTagDto);
    }

    @RequestMapping(value="/certificate_delete/{id}", method = RequestMethod.DELETE)
    public int delete(@PathVariable String id) {
        return certificateService.deleteById(id);
    }

    @RequestMapping(value="/certificate_delete_tag", method = RequestMethod.DELETE)
    public int deleteTagFromCertificate(@RequestBody CertificateHasTagDto certificateHasTagDto) {
        return certificateService.deleteTagFromCertificate(certificateHasTagDto);
    }
}
