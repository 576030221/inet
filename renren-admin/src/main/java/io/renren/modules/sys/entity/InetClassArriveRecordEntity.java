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
@TableName("inet_class_arrive_record")
public class InetClassArriveRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 到课记录表
	 */
	@TableId
	private Long id;
	/**
	 * 到课者id
	 */
	private Long userId;
	/**
	 * 课堂id
	 */
	private Long classId;
	/**
	 * 创建时间。到课时间
	 */
	private Date createTime;

}
