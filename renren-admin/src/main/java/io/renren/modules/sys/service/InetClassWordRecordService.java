package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.InetClassWordRecordEntity;

import java.util.Map;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-09-26 10:33:36
 */
public interface InetClassWordRecordService extends IService<InetClassWordRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

