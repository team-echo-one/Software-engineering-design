package bean;

import java.util.Date;

public class Message
{
	long id;
	String title;
	String content;
	Date releaseDate;

	public void setId(long id)
	{
		this.id = id;
	}

	public long getId()
	{
		return id;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getContent()
	{
		return content;
	}

	public void setReleaseDate(Date releaseDate)
	{
		this.releaseDate = releaseDate;
	}

	public Date getReleaseDate()
	{
		return releaseDate;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}

	@Override
	public String toString()
	{
		return "Message [id=" + id + ", title=" + title + ", content=" + content + ", releaseDate=" + releaseDate + "]";
	}

}
