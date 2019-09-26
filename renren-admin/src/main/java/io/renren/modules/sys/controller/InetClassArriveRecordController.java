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

import io.renren.modules.sys.entity.InetClassArriveRecordEntity;
import io.renren.modules.sys.service.InetClassArriveRecordService;
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
@RequestMapping("sys/inetclassarriverecord")
public class InetClassArriveRecordController {
    @Autowired
    private InetClassArriveRecordService inetClassArriveRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:inetclassarriverecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = inetClassArriveRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:inetclassarriverecord:info")
    public R info(@PathVariable("id") Long id){
        InetClassArriveRecordEntity inetClassArriveRecord = inetClassArriveRecordService.getById(id);

        return R.ok().put("inetClassArriveRecord", inetClassArriveRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:inetclassarriverecord:save")
    public R save(@RequestBody InetClassArriveRecordEntity inetClassArriveRecord){
        inetClassArriveRecordService.save(inetClassArriveRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:inetclassarriverecord:update")
    public R update(@RequestBody InetClassArriveRecordEntity inetClassArriveRecord){
        ValidatorUtils.validateEntity(inetClassArriveRecord);
        inetClassArriveRecordService.updateById(inetClassArriveRecord);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:inetclassarriverecord:delete")
    public R delete(@RequestBody Long[] ids){
        inetClassArriveRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
