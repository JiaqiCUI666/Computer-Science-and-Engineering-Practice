package org.hit.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "实例")
@ApiModel("实例信息类")
public class Case {

    @Schema(description = "实例id")
    private int id;

    @Schema(description = "实例title")
    private String title;

    @Schema(description = "实例content")
    private String content;

    @Schema(description = "实例category")
    private String category;


}
