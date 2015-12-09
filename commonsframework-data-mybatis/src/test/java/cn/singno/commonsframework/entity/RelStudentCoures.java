package cn.singno.commonsframework.entity;

public class RelStudentCoures
{
	private Integer id;

	private Integer studentId;

	private Integer courseId;

	public RelStudentCoures()
	{
		super();
	}

	public RelStudentCoures(Integer studentId, Integer courseId)
	{
		super();
		this.studentId = studentId;
		this.courseId = courseId;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getStudentId()
	{
		return studentId;
	}

	public void setStudentId(Integer studentId)
	{
		this.studentId = studentId;
	}

	public Integer getCourseId()
	{
		return courseId;
	}

	public void setCourseId(Integer courseId)
	{
		this.courseId = courseId;
	}
}