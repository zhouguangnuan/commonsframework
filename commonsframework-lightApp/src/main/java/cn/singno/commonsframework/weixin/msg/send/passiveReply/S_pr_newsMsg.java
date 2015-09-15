package cn.singno.commonsframework.weixin.msg.send.passiveReply;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.singno.commonsframework.weixin.msg.send.SendMsg;

/**
 * <p>名称：S_pr_newsMsg.java</p>
 * <p>描述：回复图文消息</p>
 * <pre>
 *    被动回复用户消息
 * </pre>
 * @author 周光暖
 * @date 2015-5-27 上午10:20:41
 * @version 1.0.0
 */
public class S_pr_newsMsg extends SendMsg {

	private Integer articleCount;// 图文消息个数，限制为10条以内
	private List<Item> articles;// 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	
	@Override
	public Document toDocument() {
		Document doc = super.toDocument();
		Element xml = doc.getRootElement();
		xml.addElement("ArticleCount").setText(getArticleCount().toString());
		Element Articles = xml.addElement("Articles");
		for (Item item : articles) {
			Element itemXml = Articles.addElement("item");
			itemXml.addElement("Title").setText("<![CDATA[" + item.getTitle() + "]]>");
			itemXml.addElement("Description").setText("<![CDATA[" + item.getDescription() + "]]>");
			itemXml.addElement("PicUrl").setText("<![CDATA[" + item.getPicUrl() + "]]>");
			itemXml.addElement("Url").setText("<![CDATA[" + item.getUrl() + "]]>");
		}
		return doc;
	}

	public Integer getArticleCount()
	{
		return articleCount;
	}

	public void setArticleCount(Integer articleCount)
	{
		this.articleCount = articleCount;
	}

	public List<Item> getArticles() {
		return articles;
	}

	public void setArticles(List<Item> articles) {
		this.articles = articles;
	}
}
