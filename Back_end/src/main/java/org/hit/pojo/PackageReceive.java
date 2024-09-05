package org.hit.pojo;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageReceive {

    String sender_email_address;
    String sender_ip_address;
    Pfish_email is_pfish_email;
    List<Url> urlList;
    List<Attachment> attachments_list;



}

