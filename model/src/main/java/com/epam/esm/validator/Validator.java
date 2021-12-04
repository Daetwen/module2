package com.epam.esm.validator;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.TagDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Validator {
    private static final String ID_REGEX = "\\d+";
    private static final String NAME_REGEX = "[\\w \\!\\?\\,\\.]{0,45}";
    private final static String DESCRIPTION_REGEX = "[\\w ,.!?\\-\\d]{0,1000}";

    public boolean validateId(String id) {
        boolean result = false;
        if(StringUtils.isNotBlank(id) && id.matches(ID_REGEX)) {
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
        return (id != null || id > 1L);
    }

    public boolean validateName(String name) {
        boolean result = false;
        if(StringUtils.isNotBlank(name)) {
            result = name.matches(NAME_REGEX);
        }
        return result;
    }

    public boolean validateDescription(String description) {
        return description == null ||
                (StringUtils.isNotBlank(description) && description.matches(DESCRIPTION_REGEX));
    }

    public boolean validatePrice(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean validateDuration(int duration) {
        return duration >= 0;
    }

    public boolean validateCertificate(CertificateDto certificateDto) {
        return validateName(certificateDto.getName()) &&
                validateDescription(certificateDto.getDescription()) &&
                validatePrice(certificateDto.getPrice()) &&
                validateDuration(certificateDto.getDuration());
    }

    public boolean validateTag(TagDto tagDto) {
        return validateName(tagDto.getName());
    }
}
