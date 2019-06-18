package com.iot.dao.mailWarningDao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface IMailWarningDao {
    /**
     * 查询发件人列表,email集合字符串
     * @param eventId
     */
    public String queryEmails(String eventCode);

}
