package com.epam.esm.validator;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.TagDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Validator {
    private static final String ID_REGEX = "\\d+";
    private static final String NAME_REGEX = "[\\w \\!\\?\\,\\.]{0,45}";
    private final static String DESCRIPTION_REGEX = "[\\w ,.!?\\-\\d]{0,1000}";

    public boolean validateId(String id) {
        boolean result = false;
        if(id != null && !id.isEmpty() && id.matches(ID_REGEX)) {
            try {
                Long localId = Long.parseLong(id);
                result = validateId(localId);
            } catch (NumberFormatException e) {
                result = false;
            }
        }
        return result;
    }

    public boolean validateId(Long id) {
        boolean result = false;
        if (id != null || id > 1L) {
            result = true;
        }
        return result;
    }

    public boolean validateName(String name) {
        boolean result = false;
        if(name != null && !name.isBlank()) {
            result = name.matches(NAME_REGEX);
        }
        return result;
    }

    public boolean validateDescription(String description) {
        boolean result = false;
        if (description != null && !description.isBlank()) {
            result = description.matches(DESCRIPTION_REGEX);
        } else if (description == null) {
            result = true;
        }
        return result;
    }

    public boolean validatePrice(BigDecimal price) {
        boolean result = false;
        if(price.compareTo(BigDecimal.ZERO) >= 0) {
            result = true;
        }
        return result;
    }

    public boolean validateDuration(int duration) {
        boolean result = false;
        if(duration >= 0) {
            result = true;
        }
        return result;
    }

    public boolean validateCertificate(CertificateDto certificateDto) {
        String name = certificateDto.getName();
        String description = certificateDto.getDescription();
        BigDecimal price = certificateDto.getPrice();
        int duration = certificateDto.getDuration();
        return validateName(name) &&
                validateDescription(description) &&
                validatePrice(price) &&
                validateDuration(duration);
    }

    public boolean validateTag(TagDto tagDto) {
        String name = tagDto.getName();
        return validateName(name);
    }
}
