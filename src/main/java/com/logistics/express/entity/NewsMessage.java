package com.logistics.express.entity;

import java.util.List;

import com.logistics.express.entity.News;
import com.logistics.express.entity.BaseMessage;

public class NewsMessage extends BaseMessage{
	
    private int ArticleCount;
	
	private List<News>Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<News> getArticles() {
		return Articles;
	}

	public void setArticles(List<News> articles) {
		Articles = articles;
	}
	
	

}
