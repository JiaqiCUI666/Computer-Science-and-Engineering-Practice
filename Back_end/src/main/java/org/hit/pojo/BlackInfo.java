package org.hit.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "黑名单")
@ApiModel("黑名单实体类")
public class BlackInfo {

        @Schema(description = "黑名单id")
        private int id;

        @Schema(description = "黑名单email")
        String email;

        @Schema(description = "黑名单ip")
        String ip;


}
