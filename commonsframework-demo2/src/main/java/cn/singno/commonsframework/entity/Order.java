package cn.singno.commonsframework.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModelProperty;

import cn.singno.commonsframework.validation.groups.SaveGroups;

public class Order {
	
	@ApiModelProperty(value="订单记录ID")
	private String id;
	
	@ApiModelProperty(required=true, value="所购商品编号")
	@NotBlank(message="所购商品编号不能为空", groups=SaveGroups.class)
	private String productId;
	
	@ApiModelProperty(required=true, value="所购商品商品数量")
	@NotNull(message="所购商品商品数量不能为空", groups=SaveGroups.class)
	private Integer count;
	
	@ApiModelProperty(value="订单状态")
	private Integer orderType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
}
