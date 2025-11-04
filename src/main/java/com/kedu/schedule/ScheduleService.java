package com.kedu.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * 일정 (스케줄 관리) 관련 서비스
 */
@Service
public class ScheduleService {
    @Autowired
    private ScheduleDAO dao;

    // TODO: 일정 등록, 수정, 삭제 등 비즈니스 로직 구현
}