package io.renren.modules.curriculum.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.shiro.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.curriculum.entity.InetClassEntity;
import io.renren.modules.curriculum.service.InetClassService;
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
@RequestMapping("curriculum/inetclass")
public class InetClassController {
    @Autowired
    private InetClassService inetClassService;




    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("curriculum:inetclass:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = inetClassService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("curriculum:inetclass:info")
    public R info(@PathVariable("id") Long id){
        InetClassEntity inetClass = inetClassService.getById(id);

        return R.ok().put("inetClass", inetClass);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("curriculum:inetclass:save")
    public R save(@RequestBody InetClassEntity inetClass){
        inetClass.setCreateTime(new Date());
        inetClass.setCreateUserId(ShiroUtils.getUserId());
        inetClass.setStatus(1);
        inetClassService.save(inetClass);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("curriculum:inetclass:update")
    public R update(@RequestBody InetClassEntity inetClass){
        ValidatorUtils.validateEntity(inetClass);
        inetClassService.updateById(inetClass);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("curriculum:inetclass:delete")
    public R delete(@RequestBody Long[] ids){
        inetClassService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
