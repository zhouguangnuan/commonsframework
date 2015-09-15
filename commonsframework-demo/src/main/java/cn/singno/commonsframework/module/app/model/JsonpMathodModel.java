package cn.singno.commonsframework.module.app.model;

import java.util.Map;

public class JsonpMathodModel
{
	private String url;
	private Map<String, String> map; 
	
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public Map<String, String> getMap()
	{
		return map;
	}
	public void setMap(Map<String, String> map)
	{
		this.map = map;
	}
}
