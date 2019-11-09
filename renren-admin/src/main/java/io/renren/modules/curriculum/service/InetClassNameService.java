package io.renren.modules.curriculum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.curriculum.entity.InetClassNameEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-09-26 13:03:46
 */
public interface InetClassNameService extends IService<InetClassNameEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<InetClassNameEntity> listByGradeNumber(String gradeNumber);
}

