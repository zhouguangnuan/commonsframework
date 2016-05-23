package cn.singno.commonsframework.controller.api;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import cn.singno.commonsframework.apiResultModelDoc.orders.AddOrder;
import cn.singno.commonsframework.bean.JsonResult;
import cn.singno.commonsframework.bean.Page;
import cn.singno.commonsframework.constants.DefaultDescribableEnum;
import cn.singno.commonsframework.entity.Order;
import cn.singno.commonsframework.utils.SerialUtils;
import cn.singno.commonsframework.validation.groups.SaveGroups;

@Api(value="orders", description="订单模块")
@RequestMapping("/api/orders")
@RestController
public class OrderController extends ApiBaseController
{
	@ApiOperation(value="创建订单", response=AddOrder.class, notes="用户创建订单")
	@RequestMapping(method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object addOrder(
		@RequestBody
		@Validated(value={SaveGroups.class})
		Order order,
		HttpServletRequest request)
	{
		System.out.println(request.getQueryString());
		System.out.println(JSON.toJSONString(request.getParameterMap()));
		
		JsonResult jsonResult = new JsonResult(DefaultDescribableEnum.SUCCESS);
		Map<String, Object> data = Maps.newHashMap();
		order.setId(SerialUtils.buildRefrenceId());
		data.put("order", order);
		jsonResult.setData(data);
		return jsonResult;
	}
	
	@ApiOperation(value="取消订单", response=JsonResult.class, notes="用户取消订单")
	@RequestMapping(value="/{orderId}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object deleteOrder(
		@ApiParam(required=true, value="订单ID")
		@PathVariable String orderId,
		HttpServletRequest request)
	{
		System.out.println(request.getQueryString());
		System.out.println(JSON.toJSONString(request.getParameterMap()));
		JsonResult jsonResult = new JsonResult(DefaultDescribableEnum.SUCCESS);
		return jsonResult;
	}
	
	@RequestMapping(value="/{orderId}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="修改订单", response=Order.class, notes="用户创建订单")
	public Object updateOrder(
		@ApiParam(required=true, value="订单ID")
		@PathVariable
		String orderId,
		@RequestBody
		@Validated(value={SaveGroups.class})
		Order order,
		HttpServletRequest request)
	{
		System.out.println(request.getQueryString());
		System.out.println(JSON.toJSONString(request.getParameterMap()));
		
		JsonResult jsonResult = new JsonResult(DefaultDescribableEnum.SUCCESS);
		Map<String, Object> data = Maps.newHashMap();
		order.setId(orderId);
		data.put("order", order);
		jsonResult.setData(data);
		return jsonResult;
	}
	
	@ApiOperation(value="订单详情", response=Order.class, notes="用户根据ID查询订单详细信息")
	@RequestMapping(value="/{orderId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object findOrderById(
		@ApiParam(required=true, value="订单ID")
		@PathVariable
		String orderId,
		HttpServletRequest request)
	{
		System.out.println(request.getQueryString());
		System.out.println(JSON.toJSONString(request.getParameterMap()));
		
		JsonResult jsonResult = new JsonResult(DefaultDescribableEnum.SUCCESS);
		Map<String, Object> data = Maps.newHashMap();
		Order order = new Order();
		order.setId(orderId);
		order.setProductId(SerialUtils.buildRefrenceId());
		order.setCount(10);
		data.put("order", order);
		jsonResult.setData(data);
		return jsonResult;
	}
	
	@ApiOperation(value="查询订单", response=Order.class, responseContainer="List", notes="用户根据条件查询订单")
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object searchOrder(
		@ApiParam("第几页")
		@RequestParam(required=false, defaultValue="1")
		Integer number,
		@ApiParam("每页记录数")
		@RequestParam(required=false, defaultValue="10")
		Integer size,
		@ApiParam("订单ID")
		@RequestParam(required=false)
		String id,
		@ApiParam("订单状态")
		@RequestParam(required=false)
		Integer orderType,
		HttpServletRequest request)
	{
		System.out.println(request.getQueryString());
		System.out.println(JSON.toJSONString(request.getParameterMap()));
		
		JsonResult jsonResult = new JsonResult(DefaultDescribableEnum.SUCCESS);
		List<Order> content = Lists.newArrayList();
		for (int i = 0; i < 5; i++) {
			Order orderInfo = new Order();
			if(null == id){
				orderInfo.setId(i+"");
			} else {
				orderInfo.setId(id);
			}
			if(null == orderType){
				orderInfo.setOrderType(RandomUtils.nextInt(10));
			} else {
				orderInfo.setOrderType(orderType);
			}
			orderInfo.setProductId(SerialUtils.buildRefrenceId());
			orderInfo.setCount(i);
			content.add(orderInfo);
		}
		Page page = new Page<Order>();
		Integer totalElements = 120;
		Integer totalPages = null;
		
		page.setNumber(number);
		page.setSize(size);
		page.setTotalElements((long)totalElements);
		if(totalElements % size > 0){
			totalPages = totalElements / size + 1;
		} else {
			totalPages = totalElements / size;
		}
		page.setTotalPages(totalPages);
		page.setContent(content);
		
		jsonResult.setPage(page);
		return jsonResult;
	}
}
