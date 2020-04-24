package com.xyhs.b2c.common.tools.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ljp
 * @apiNote
 * @date 14:24 2019/12/30
 **/
@Data
public class BaseEntity  implements Serializable {

    private static final long serialVersionUID = -8239034348377641656L;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
