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
 * @date 2020-04-14 21:15:14
 */
@RestController
@RequestMapping("/chat")
public class ChatController extends BaseController {

    @Autowired
    private ChatService chatService;


    @PostMapping("/doquery")
    public ResponseEntity<Object> doquery(@RequestBody String json, HttpServletRequest request) {


    }

    @GetMapping("")
    public ResponseEntity<Object> getChatList() {

    }

    @PutMapping()
    public ResponseEntity<Object> putChat() {


    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteChat(@RequestBody @Valid Object id) {


    }
}
