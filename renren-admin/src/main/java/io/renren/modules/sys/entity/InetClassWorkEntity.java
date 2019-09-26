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
@TableName("inet_class_work")
public class InetClassWorkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 课堂作业表 布置作业用的
	 */
	@TableId
	private Long id;
	/**
	 * 课堂id
	 */
	private Long classId;
	/**
	 * 附件 文件url
	 */
	private String fileUrl;
	/**
	 * 详情 富文本
	 */
	private String details;
	/**
	 * 提交作业截止时间
	 */
	private Date stopTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建者id
	 */
	private Long createUserId;

}
