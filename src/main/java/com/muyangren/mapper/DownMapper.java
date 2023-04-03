package com.muyangren.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muyangren.entity.Down;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: muyangren
 * @Date: 2023/4/2
 * @Description: com.muyangren.mapper
 * @Version: 1.0
 */
public interface DownMapper extends BaseMapper<Down> {

    List<Down> DownPage(@Param("page") IPage<Down> page,Down down);
}
