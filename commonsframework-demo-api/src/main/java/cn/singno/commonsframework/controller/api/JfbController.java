package cn.singno.commonsframework.controller.api;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.singno.commonsframework.bean.JsonResult;
import cn.singno.commonsframework.bean.Page;
import cn.singno.commonsframework.constants.DefaultDescribableEnum;
import cn.singno.commonsframework.entity.Category;
import cn.singno.commonsframework.entity.Product;

@Controller
@RequestMapping("/jfb")
@SuppressWarnings("all")
public class JfbController
{
        @RequestMapping(value="/product/categorys", method=RequestMethod.GET)
	@ResponseBody
	public Object product_categorys(HttpServletRequest request)
	{
                List<Category> list = Lists.newArrayList();
                Category parant_1 = new Category(1, "聚方便", "no_category_01", null, 1);
                
                Category parant_2 = new Category(2, "本来生鲜", "no_category_02", null, 1);
                List<Category> parant_2_children = Lists.newArrayList();
                parant_2_children.add(new Category(21, "生鲜", "no_category_021", null, 2));
                parant_2_children.add(new Category(22, "奶制品", "no_category_022", null, 2));
                parant_2_children.add(new Category(23, "母婴", "no_category_023", null, 2));
                parant_2_children.add(new Category(24, "粮油副食", "no_category_024", null, 2));
                parant_2_children.add(new Category(25, "休闲食品", "no_category_025", null, 2));
                parant_2_children.add(new Category(26, "进口食品", "no_category_026", null, 2));
                parant_2.setChildren(parant_2_children);
                
                Category parant_3 = new Category(3, "特惠精选", "no_category_03", null, 1);
                List<Category> parant_3_children = Lists.newArrayList();
                parant_3_children.add(new Category(31, "生鲜", "no_category_031", null, 2));
                parant_3_children.add(new Category(32, "奶制品", "no_category_032", null, 2));
                parant_3_children.add(new Category(33, "母婴", "no_category_033", null, 2));
                parant_3_children.add(new Category(34, "粮油副食", "no_category_034", null, 2));
                parant_3_children.add(new Category(35, "休闲食品", "no_category_035", null, 2));
                parant_3_children.add(new Category(36, "进口食品", "no_category_036", null, 2));
                parant_3.setChildren(parant_3_children);
                
                list.add(parant_1);
                list.add(parant_2);
                list.add(parant_3);
	        
                JsonResult jsonResult = new JsonResult(DefaultDescribableEnum.SUCCESS);
                Map<String, Object> data = Maps.newHashMap();
                data.put("list", list);
                jsonResult.setData(data);
	        return jsonResult;
	}
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
        @ResponseBody
        public Object products(Page page)
        {
	        List<Product> content = Lists.newArrayList();
	        if(page.getNumber() % 1 == 0)
	        {
	                
	                
	                
//	                <div class="right">
//                        <div class="banner"><img src="${webRoot}/static/images/jfb/guanggao.png"></div>
//                        <div class="p_one_box">
//                                <div class="img"><img src="${webRoot}/static/images/jfb/1.jpg"></div>
//                                <div class="name">延安宜川红富士 12粒装（70-75mm）</div>
//                                <div class="gg">1箱</div>
//                                <div class="price">￥39.00</div>
//                                <div class="ad">
//                                        <input type="button" class="del" style="display:none">
//                                        <input type="text" class="text">
//                                        <input type="button" class="add">
//                                </div>
//                        </div>
//                        <div class="p_one_box">
//                                <div class="img"><img src="${webRoot}/static/images/jfb/2.jpg"></div>
//                                <div class="name">八两阳光水果玉米【4根装】</div>
//                                <div class="gg">4根装</div>
//                                <div class="price">￥18.80</div>
//                                <div class="ad">
//                                        <input type="button" class="del">
//                                        <input type="text" class="text" value="2">
//                                        <input type="button" class="add">
//                                </div>
//                        </div>
//                        <div class="p_one_box">
//                                <div class="img"><img src="${webRoot}/static/images/jfb/3.jpg"></div>
//                                <div class="name">【高鹏推荐】进口山竹 大果（4A-6A） 1kg</div>
//                                <div class="gg">1kg</div>
//                                <div class="price">￥48.00</div>
//                                <div class="ad">
//                                        <input type="button" class="del">
//                                        <input type="text" class="text" value="2">
//                                        <input type="button" class="add">
//                                </div>
//                        </div>
//                        <div class="p_one_box">
//                                <div class="img"><img src="${webRoot}/static/images/jfb/4.jpg"></div>
//                                <div class="name">泰国进口龙眼 特级 1kg</div>
//                                <div class="gg">1kg</div>
//                                <div class="price">￥25.80</div>
//                                <div class="ad">
//                                        <input type="button" class="del" style="display:none">
//                                        <input type="text" class="text">
//                                        <input type="button" class="add">
//                                </div>
//                        </div>
//                        <div class="p_one_box">
//                                <div class="img"><img src="${webRoot}/static/images/jfb/5.jpg"></div>
//                                <div class="name">进口牛油果 4粒</div>
//                                <div class="gg">4粒/440-520g</div>
//                                <div class="price">￥33.80</div>
//                                <div class="ad">
//                                        <input type="button" class="del" style="display:none">
//                                        <input type="text" class="text">
//                                        <input type="button" class="add">
//                                </div>
//                        </div>
//                </div>
//        </div>
	        } 
	        else 
	        {
	                
	        }
	        
	        page.setContent(content);
	        page.setTotalElements(21L);
	        page.setTotalPages(3);
	        
	        JsonResult jsonResult = new JsonResult(DefaultDescribableEnum.SUCCESS, page);
                return jsonResult;
        }
}
