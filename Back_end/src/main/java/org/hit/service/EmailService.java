package org.hit.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.util.MimeMessageParser;
import org.hit.mapper.EmailMapper;
import org.hit.pojo.BlackInfo;
import org.hit.pojo.Case;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class EmailService {

    @Resource
    private EmailMapper emailMapper;

    public List<BlackInfo> getBlackList() {
        return emailMapper.getBlackList();
    }

    public List<Case> getCaseList() {
        return emailMapper.getCaseList();
    }

    public int addBlackList(BlackInfo blackInfo) {
        return emailMapper.addBlackList(blackInfo);
    }

    public List<BlackInfo> getBlackListByBlackEmail(String blackEmail) {
        return emailMapper.getBlackListByBlackEmail(blackEmail);
    }

    public int addCase(Case thisCase) {
        return emailMapper.addCase(thisCase);
    }


    public Object parserEmail(MimeMessage msg, MimeMessageParser parser) throws Exception {
        String sender_address = parser.getFrom();
        String sender_address_ip;
        String r = msg.getHeader("Received")[0];

        Pattern datapattern = Pattern.compile(".+[(.+)].+", Pattern.CASE_INSENSITIVE);
        Matcher tagMatcher = datapattern.matcher(r);
        if (tagMatcher.find()) {
            System.out.println("IP:" + tagMatcher.group(1));
            sender_address_ip = tagMatcher.group(1);
        }



        return null;
    }

    public Object parserEmailText(String text) {
        return null;
    }
}
