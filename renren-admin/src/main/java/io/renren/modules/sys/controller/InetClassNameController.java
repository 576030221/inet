package io.renren.modules.sys.controller;

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

import io.renren.modules.sys.entity.InetClassNameEntity;
import io.renren.modules.sys.service.InetClassNameService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-09-26 10:33:37
 */
@RestController
@RequestMapping("sys/inetclassname")
public class InetClassNameController {
    @Autowired
    private InetClassNameService inetClassNameService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:inetclassname:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = inetClassNameService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:inetclassname:info")
    public R info(@PathVariable("id") Long id){
        InetClassNameEntity inetClassName = inetClassNameService.getById(id);

        return R.ok().put("inetClassName", inetClassName);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:inetclassname:save")
    public R save(@RequestBody InetClassNameEntity inetClassName){
        inetClassNameService.save(inetClassName);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:inetclassname:update")
    public R update(@RequestBody InetClassNameEntity inetClassName){
        ValidatorUtils.validateEntity(inetClassName);
        inetClassNameService.updateById(inetClassName);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:inetclassname:delete")
    public R delete(@RequestBody Long[] ids){
        inetClassNameService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
