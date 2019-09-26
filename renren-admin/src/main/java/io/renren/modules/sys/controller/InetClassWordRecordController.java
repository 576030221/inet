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

import io.renren.modules.sys.entity.InetClassWordRecordEntity;
import io.renren.modules.sys.service.InetClassWordRecordService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-09-26 10:33:36
 */
@RestController
@RequestMapping("sys/inetclasswordrecord")
public class InetClassWordRecordController {
    @Autowired
    private InetClassWordRecordService inetClassWordRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:inetclasswordrecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = inetClassWordRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:inetclasswordrecord:info")
    public R info(@PathVariable("id") Long id){
        InetClassWordRecordEntity inetClassWordRecord = inetClassWordRecordService.getById(id);

        return R.ok().put("inetClassWordRecord", inetClassWordRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:inetclasswordrecord:save")
    public R save(@RequestBody InetClassWordRecordEntity inetClassWordRecord){
        inetClassWordRecordService.save(inetClassWordRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:inetclasswordrecord:update")
    public R update(@RequestBody InetClassWordRecordEntity inetClassWordRecord){
        ValidatorUtils.validateEntity(inetClassWordRecord);
        inetClassWordRecordService.updateById(inetClassWordRecord);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:inetclasswordrecord:delete")
    public R delete(@RequestBody Long[] ids){
        inetClassWordRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
