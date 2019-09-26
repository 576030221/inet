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
@TableName("inet_grade")
public class InetGradeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 年级表 id
	 */
	@TableId
	private Long id;
	/**
	 * 年级名称。17【级】。 18【级】
	 */
	private String name;

}
