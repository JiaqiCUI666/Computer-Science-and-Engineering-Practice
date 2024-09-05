package org.hit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hit.pojo.BlackInfo;
import org.hit.pojo.Case;

import java.util.List;


@Mapper
public interface EmailMapper {

    //获取所有黑名单list
    @Select("select * from BLACKLIST")
    public List<BlackInfo> getBlackList();


    //获取所有实例list
    @Select("select * from CASES")
    public List<Case> getCaseList();


    //增加黑名单
    @Insert("insert into BLACKLIST(email,ip) values(#{email},#{ip})")
    public int addBlackList(BlackInfo blackInfo);


    //增加实例
    @Insert("insert into CASE(title,content,category) values(#{title},#{content},#{category})")
    public int addCase(Case thisCase);


    //根据黑名单email获取实例list
    @Select("select * from BLACKLIST where email=#{email}")
    List<BlackInfo> getBlackListByBlackEmail(String blackEmail);
}
