package edu.job.controller;

import edu.job.base.BaseController;
import edu.job.common.viewobject.UniversalResult;
import edu.job.service.HunterExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


/**
 * 的Controller
 * @author caoxingxing
 * @date 2020-05-05 16:47:43
 */
@RestController
@RequestMapping("/hunterExperience")
public class HunterExperienceController extends BaseController {

    @Autowired
    private HunterExperienceService hunterExperienceService;


    @PostMapping("/doquery")
    public ResponseEntity<Object> doquery(@RequestBody String json, HttpServletRequest request) {


    }

    @GetMapping("")
    public ResponseEntity<Object> getHunterExperienceList() {

    }

    @PutMapping()
    public ResponseEntity<Object> putHunterExperience() {


    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteHunterExperience(@RequestBody @Valid Object id) {


    }
}
