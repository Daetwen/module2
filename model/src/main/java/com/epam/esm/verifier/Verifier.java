package com.epam.esm.verifier;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.TagDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Verifier {
    private static final String ID_REGEX = "\\d+";
    private static final String NAME_REGEX = "[\\w \\!\\?\\,\\.]{0,45}";
    private final static String DESCRIPTION_REGEX = "[\\w ,.!?\\-\\d]{0,1000}";

    public boolean isValidId(String id) {
        boolean result = false;
        if(StringUtils.isNotBlank(id) && id.matches(ID_REGEX)) {
            try {
                Long localId = Long.parseLong(id);
                result = isValidId(localId);
            } catch (NumberFormatException e) {
                result = false;
            }
        }
        return result;
    }

    public boolean isValidId(Long id) {
        return (id != null || id > 1L);
    }

    public boolean isValidName(String name) {
        return StringUtils.isNotBlank(name) ? name.matches(NAME_REGEX) : false;
    }

    public boolean isValidDescription(String description) {
        return description == null ||
                (StringUtils.isNotBlank(description) && description.matches(DESCRIPTION_REGEX));
    }

    public boolean isValidPrice(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean isValidDuration(int duration) {
        return duration >= 0;
    }

    public boolean isValidCertificate(CertificateDto certificateDto) {
        return isValidName(certificateDto.getName()) &&
                isValidDescription(certificateDto.getDescription()) &&
                isValidPrice(certificateDto.getPrice()) &&
                isValidDuration(certificateDto.getDuration());
    }

    public boolean isValidTag(TagDto tagDto) {
        return isValidName(tagDto.getName());
    }
}