package io.renren.modules.curriculum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.curriculum.entity.InetClassWordRecordEntity;

import java.util.Map;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-09-26 13:03:46
 */
public interface InetClassWordRecordService extends IService<InetClassWordRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

