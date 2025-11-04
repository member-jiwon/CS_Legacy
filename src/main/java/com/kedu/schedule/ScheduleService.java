package com.kedu.schedule;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedu.chat.ChatDAO;
import com.kedu.members.member.MemberDAO;


@Service
public class ScheduleService {
	@Autowired
	private ScheduleDAO dao;
	@Autowired
	private ChatDAO chatDao;
	@Autowired
	private MemberDAO memDao;
	
	//스케줄 업데이트 시키기
	public int insertPtoSchedule(String member_email, Timestamp pto_start_at,Timestamp pto_end_at) {
		
		//1. 해당 멤버가 들어잇는 채팅방 시퀀스 배열로 뽑아오기
		List<Integer> seqList = chatDao.getAllChatSeq(member_email);
	    if (seqList == null || seqList.isEmpty()) return 0;
		
		//2.하루 이상인지 아닌지 구별
	    LocalDate startDate = pto_start_at.toLocalDateTime().toLocalDate();
	    LocalDate endDate = pto_end_at.toLocalDateTime().toLocalDate();
	    String multiDay = endDate.isAfter(startDate) ? "y" : "n";
	    Timestamp adjustedEnd = Timestamp.valueOf(endDate.plusDays(1).atStartOfDay());//종료시간 그날의 자정으로 변경해놓음
	    
		//3. 채팅방시퀀스 배열포리치 돌리며 인서트
	    int insertedCount = 0;
	    for (int seq : seqList) {
	    	//이메일로 이름 가져오기
	    	String name = memDao.getNameByEmail(member_email);
	        ScheduleDTO schedule = new ScheduleDTO( 0,member_email, name + " 휴가",multiDay,"#007AFF",pto_start_at,adjustedEnd,null,seq);
	        insertedCount += dao.insertPtoSchedule(schedule);
	    }
	    return insertedCount;
	}
	
	
	
}