package com.manage.crm.util;

/**
 * 心情。
 * 
 * @author wei
 *
 */
public class Mood
{
    private int type; // 心情类型。
    
    private int num; // 心情数量。
    
    private Long newsID; // 新闻id。
    

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Long getNewsID() {
		return newsID;
	}

	public void setNewsID(Long newsID) {
		this.newsID = newsID;
	}
    
    
}
