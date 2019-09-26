package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-09-26 10:33:37
 */
@Data
@TableName("inet_class_name")
public class InetClassNameEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 课堂名称表
	 */
	@TableId
	private Long id;
	/**
	 * 课堂名称
	 */
	private String name;
	/**
	 * 属于哪个年级 外键
	 */
	private Long gradeId;
	/**
	 * 属于哪个部门
	 */
	private Long deptId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建者
	 */
	private Long createUserId;

}
