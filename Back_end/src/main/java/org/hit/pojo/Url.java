package org.hit.pojo;


import lombok.Data;

@Data
public class Url {
    String url;
    boolean is_bad;
    float confidence;
}
