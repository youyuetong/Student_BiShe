package edu.job.controller;

import edu.job.base.BaseController;
import edu.job.base.viewobject.UniversalResult;
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
 * @date 2020-04-14 21:18:42
 */
@RestController
@RequestMapping("/explainMeeting")
public class ExplainMeetingController extends BaseController {

    @Autowired
    private ExplainMeetingService explainMeetingService;


    @PostMapping("/doquery")
    public ResponseEntity<Object> doquery(@RequestBody String json, HttpServletRequest request) {


    }

    @GetMapping("")
    public ResponseEntity<Object> getExplainMeetingList() {

    }

    @PutMapping()
    public ResponseEntity<Object> putExplainMeeting() {


    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteExplainMeeting(@RequestBody @Valid Object id) {


    }
}
