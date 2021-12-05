package com.epam.esm.controller;

import com.epam.esm.dto.TagDto;
import com.epam.esm.exception.ControllerException;
import com.epam.esm.exception.ServiceSearchException;
import com.epam.esm.exception.ServiceValidationException;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.ValidatorController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final ValidatorController validatorController;

    @Autowired
    public TagController(TagService tagService, ValidatorController validatorController) {
        this.tagService = tagService;
        this.validatorController = validatorController;
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
    @Description("For lookups, id takes precedence over multiple passed parameters.")
    public TagDto getOne(@RequestParam(value = "id", required = false) String id,
                         @RequestParam(value = "name", required = false) String name)
            throws ControllerException, ServiceSearchException, ServiceValidationException {
        TagDto tagDto = new TagDto();
        validatorController.validateParameters(id, name);
        if (StringUtils.isNotBlank(name)) {
            tagDto = tagService.findByName(name);
        }
        if (StringUtils.isNotBlank(id)) {
            tagDto = tagService.findById(id);
        }
        return tagDto;
    }

    @RequestMapping(value="/tag_delete/{id}",method = RequestMethod.DELETE)
    public int delete(@PathVariable String id) {
        return tagService.deleteById(id);
    }
}