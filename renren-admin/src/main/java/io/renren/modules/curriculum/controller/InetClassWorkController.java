package io.renren.modules.curriculum.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.curriculum.entity.InetClassWorkEntity;
import io.renren.modules.curriculum.service.InetClassWorkService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-09-26 13:03:46
 */
@RestController
@RequestMapping("curriculum/inetclasswork")
public class InetClassWorkController {
    @Autowired
    private InetClassWorkService inetClassWorkService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("curriculum:inetclasswork:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = inetClassWorkService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("curriculum:inetclasswork:info")
    public R info(@PathVariable("id") Long id){
        InetClassWorkEntity inetClassWork = inetClassWorkService.getById(id);

        return R.ok().put("inetClassWork", inetClassWork);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("curriculum:inetclasswork:save")
    public R save(@RequestBody InetClassWorkEntity inetClassWork){
        inetClassWorkService.save(inetClassWork);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("curriculum:inetclasswork:update")
    public R update(@RequestBody InetClassWorkEntity inetClassWork){
        ValidatorUtils.validateEntity(inetClassWork);
        inetClassWorkService.updateById(inetClassWork);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("curriculum:inetclasswork:delete")
    public R delete(@RequestBody Long[] ids){
        inetClassWorkService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
