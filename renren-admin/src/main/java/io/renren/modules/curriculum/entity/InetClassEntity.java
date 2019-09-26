package io.renren.modules.curriculum.entity;

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
 * @date 2019-09-26 13:03:46
 */
@Data
@TableName("inet_class")
public class InetClassEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 课堂的名称，取自课堂名称表
	 */
	private Long classNameId;
	/**
	 * 课堂开始时间
	 */
	private Date startTime;
	/**
	 * 课堂持续时间
	 */
	private Integer continuousTime;
	/**
	 * 教室 门牌号
	 */
	private String classroom;
	/**
	 * 状态 0：上课中 -1:下课  1:未上课
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建者id
	 */
	private Long createUserId;

}
