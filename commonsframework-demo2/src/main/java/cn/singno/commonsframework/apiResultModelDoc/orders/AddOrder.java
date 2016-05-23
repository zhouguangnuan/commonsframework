package cn.singno.commonsframework.apiResultModelDoc.orders;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import cn.singno.commonsframework.entity.Order;

@ApiModel(subTypes={Order.class})
public class AddOrder {
	
	@ApiModelProperty(value="订单信息")
	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
