package com.epam.esm.controller;

import com.epam.esm.constant.LanguagePath;
import com.epam.esm.dto.TagDto;
import com.epam.esm.exception.ControllerException;
import com.epam.esm.exception.ServiceSearchException;
import com.epam.esm.exception.ServiceValidationException;
import com.epam.esm.service.TagService;
import com.epam.esm.util.LocaleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final LocaleManager localeManager;

    @Autowired
    public TagController(TagService tagService, LocaleManager localeManager) {
        this.tagService = tagService;
        this.localeManager = localeManager;
    }

    @RequestMapping(value="/tag_create",method = RequestMethod.POST)
    public int create(@RequestBody TagDto tagDto) {
        return tagService.create(tagDto);
    }

    @GetMapping
    public List<TagDto> getAll() {
        return tagService.findAll();
    }

    @GetMapping("/tag")
    public TagDto getOne(@RequestParam(value = "id", required = false) String id,
                         @RequestParam(value = "name", required = false) String name)
            throws ControllerException, ServiceSearchException, ServiceValidationException {
        TagDto tagDto = null;
        if (id == null && name == null) {
            throw new ControllerException(
                    localeManager.getLocalizedMessage(LanguagePath.PARAMETERS_ERROR_NOT_FOUND));
        } else if (id == null && name != null) {
            tagDto = tagService.findByName(name);
        } else if (id != null && name == null) {
            tagDto = tagService.findById(id);
        }
        return tagDto;
    }

    @RequestMapping(value="/tag_delete/{id}",method = RequestMethod.DELETE)
    public int delete(@PathVariable String id) {
        return tagService.deleteById(id);
    }
}