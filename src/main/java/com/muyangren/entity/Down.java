package com.muyangren.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: muyangren
 * @Date: 2023/4/2
 * @Description: com.muyangren.entity
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Down {

    /**
     * 需要下载的id集合
     */
    private String caseInfoIds;
}
