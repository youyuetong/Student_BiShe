package edu.job.service;

import BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 的Service
 * @author caoxingxing
 * @date 2020-04-14 21:15:14
 */
@Service
public class ChatService extends BaseService<Chat> {

    @Autowired
    private ChatMapper chatMapper;


}
