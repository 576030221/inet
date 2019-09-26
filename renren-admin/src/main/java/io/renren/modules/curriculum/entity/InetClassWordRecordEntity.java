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
@TableName("inet_class_word_record")
public class InetClassWordRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 课堂作业记录表 用于记录学生提交的作业
	 */
	@TableId
	private Long id;
	/**
	 * 课堂id
	 */
	private Long classId;
	/**
	 * 提交者id
	 */
	private Long userId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 作业文件名称
	 */
	private String fileName;
	/**
	 * 作业文件地址
	 */
	private String fileUrl;
	/**
	 * 教师回执时间
	 */
	private Date returnTime;
	/**
	 * 教师回执分数
	 */
	private String returnScore;
	/**
	 * 教师回执详情点评
	 */
	private String returnDatails;
	/**
	 * 教师回执文件地址 可有可无
	 */
	private String returnFileUrl;

}
