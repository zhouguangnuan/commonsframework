package cn.singno.commonsframework.utils;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.singno.commonsframework.constants.DefaultSystemConst;


/**
 * <p>File：HtmlUtils.java</p>
 * <p>Title: Html解析处理工具类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-5 上午9:39:42</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class HtmlUtils
{
    private static final Logger logger = LoggerFactory
                                               .getLogger(HtmlUtils.class);

    private HtmlUtils()
    {
        super();
    }
    
    
    /**
     * 取得NodeList
     * @param htmlContent Html内容
     * @param tagName 标签名称
     * @param attName 属性名称
     * @param attValue 属性值
     * @param encode 编码格式
     * @return NodeList NodeList
     */
    public static NodeList getNodeList(String htmlContent, String tagName,
            String attName, String attValue, String encode)
    {
        Parser parser = Parser.createParser(htmlContent, encode);
        AndFilter andFilter = new AndFilter(new TagNameFilter(tagName), new HasAttributeFilter(attName, attValue));
        return getNodeList(parser, andFilter);
    }
    
    /**
     * 取得NodeList
     * @param htmlContent Html源码
     * @param tagName 标签名称
     * @param encode 编码格式
     * @return NodeList NodeList
     */
    public static NodeList getNodeList(String htmlContent, String tagName,
            String encode)
    {
        Parser parser = Parser.createParser(htmlContent, encode);
        return getNodeList(parser, tagName);
    }

    /**
     * 取得NodeList
     * @param parser Parser
     * @param andFilter AndFilter
     * @return NodeList nodeList
     */
    public static NodeList getNodeList(Parser parser, AndFilter andFilter)
    {
        NodeList nodeList = null;
        try
        {
            if (null != parser)
                nodeList = parser.extractAllNodesThatMatch(andFilter);
        }
        catch (ParserException e)
        {
        	logger.error(e.getMessage(), e);
        }
        return nodeList;
    }

    /**
     * 取得NodeList
     * @param parser Parser
     * @param tagName 标签名称
     * @return NodeList NodeList
     */
    public static NodeList getNodeList(Parser parser, String tagName)
    {
        NodeList nodeList = null;
        TagNameFilter tagFilter = new TagNameFilter(tagName);
        try
        {
            if (null != parser)
                nodeList = parser.extractAllNodesThatMatch(tagFilter);
        }
        catch (ParserException e)
        {
        	logger.error(e.getMessage(), e);
        }
        return nodeList;
    }

    /**
     * 根据标签名称，标签属性名称，标签属性值取得该标签内的html内容
     * @param parser Parser
     * @param tagName 标签名称
     * @param attName 标签的属性
     * @param attValue 标签的属性值
     * @return String 该标签内的html内容
     */
    public static String getHtmlByTagName(Parser parser, String tagName,
            String attName, String attValue)
    {
        String result = null;
        AndFilter andFilter = new AndFilter(new TagNameFilter(tagName),
                new HasAttributeFilter(attName, attValue));
        NodeList nodeList = getNodeList(parser, andFilter);
        if (null != nodeList)
        {
            result = nodeList.toHtml();
        }
        return result;
    }
    
    /**
     * 根据标签名称，标签属性名称，标签属性值取得该标签内的html内容
     * @param htmlContent 要解析的html内容
     * @param tagName 标签名称
     * @param attName 标签的属性
     * @param attValue 标签的属性值
     * @param encode 编码
     * @return String 该标签内的html内容
     */
    public static String getHtmlByTagName(String htmlContent, String tagName,
            String attName, String attValue, String encode)
    {
        if (StringUtils.isBlank(htmlContent))
            return null;
        else
        {
            Parser parser = Parser.createParser(htmlContent, encode);
            return getHtmlByTagName(parser, tagName, attName, attValue);
        }
    }

    /**
     * 通过Parser及tagName取得文本
     * @param parser Parser
     * @param tagName html源码中的标签名称
     * @return String[] 文本内容数组
     */
    public static String[] getTextByTagName(Parser parser, String tagName)
    {
        NodeList nodeList = getNodeList(parser, tagName);
        return getTextByNodeList(nodeList);
    }
    
    /**
     * 通过html源码及标签名取得文件
     * @param htmlContent html源码内容
     * @param tagName 标签名称
     * @param encode 编码格式
     * @return String[] 文本内容数组
     */
    public static String[] getTextByTagName(String htmlContent, String tagName,
            String attName, String attValue, String encode)
    {
        if (StringUtils.isBlank(htmlContent))
            return null;
        else
        {
            Parser parser = Parser.createParser(htmlContent, encode);
            AndFilter andFilter = new AndFilter(new TagNameFilter(tagName),
                    new HasAttributeFilter(attName, attValue));
            NodeList nodeList = getNodeList(parser, andFilter);
            return getTextByNodeList(nodeList);
        }
    }
    
    /**
     * 通过NodeList取得文本
     * @param nodeList NodeList
     * @return String[] 文本内容数组
     */
    public static String[] getTextByNodeList(NodeList nodeList)
    {
        String[] strings = null;
        if (null != nodeList)
        {
            int iSize = nodeList.size();
            strings = new String[iSize];
            for (int i = 0; i < nodeList.size(); i++)
            {
                Node node = nodeList.elementAt(i);
                strings[i] = StringUtils.trimToEmpty(node.toPlainTextString());
            }
        }
        return strings;
    }

    /**
     * 从url中取得title中的相关文字
     * @param url 网络地址
     * @return String 取得title中的相关文字
     * @throws ParserException ParserException
     */
    public static String getTitle(String url) throws ParserException
    {
        Parser myParser = new Parser(url);
        // 设置编码
        myParser.setEncoding(DefaultSystemConst.DEFAULT_UNICODE);
        String titleTag = "title";
        NodeFilter titleFilter = new TagNameFilter(titleTag);
        NodeList titleList = myParser.extractAllNodesThatMatch(titleFilter);
        int size = titleList.size();
        String title = null;
        if (size == 1)
        {
            TitleTag titleT = (TitleTag) titleList.elementAt(0);
            title = titleT.getTitle();
        }
        return title;
    }
}