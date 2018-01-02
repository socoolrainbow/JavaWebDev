package lesson06.vo;

import java.sql.Date;

public class Project
{
	/*
	 * projects table
	pno integer not null comment '프로젝트일련번호',
	pname varchar(255) not null comment '프로젝트명',
	content text not null comment '설명',
	sta_date datetime  null comment '시작일',
	end_date datetime not null comment '종료일',
	state integer not null comment '상태',
	cre_date datetime not null comment '생성일',
	tags varchar(255) null comment '태그'
	*/
	protected int no;
	protected String title;
	protected String content;
	protected Date startDate;
	protected Date endDate;
	protected int state;
	protected Date createdDate;
	protected String tags;
	
	public int getNo()
	{
		return no;
	}
	public Project setNo(int no)
	{
		this.no = no;
		return this;
	}
	public String getTitle()
	{
		return title;
	}
	public Project setTitle(String title)
	{
		this.title = title;
		return this;
	}
	public String getContent()
	{
		return content;
	}
	public Project setContent(String content)
	{
		this.content = content;
		return this;
	}
	public Date getStartDate()
	{
		return startDate;
	}
	public Project setStartDate(Date startDate)
	{
		this.startDate = startDate;
		return this;
	}
	public Date getEndDate()
	{
		return endDate;
	}
	public Project setEndDate(Date endDate)
	{
		this.endDate = endDate;
		return this;
	}
	public int getState()
	{
		return state;
	}
	public Project setState(int state)
	{
		this.state = state;
		return this;
	}
	public Date getCreatedDate()
	{
		return createdDate;
	}
	public Project setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
		return this;
	}
	public String getTags()
	{
		return tags;
	}
	public Project setTags(String tags)
	{
		this.tags = tags;
		return this;
	}
}
