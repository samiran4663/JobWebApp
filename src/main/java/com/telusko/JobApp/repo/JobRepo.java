package com.telusko.JobApp.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.telusko.JobApp.model.JobPost;

@Repository
public class JobRepo 
{
	
	@Autowired
	JdbcTemplate jdbc;
	public void databaseAddJob(JobPost jobpost) {
		
		String techStackStr = String.join(",", jobpost.getPostTechStack());
		int rows=jdbc.update("insert into JobPost (PostId,postProfile,postDesc,reqExperience,postTechStack) values(?,?,?,?,?)", jobpost.getPostId(),
				jobpost.getPostProfile(),jobpost.getPostDesc(),jobpost.getReqExperience(),techStackStr);
		
	}
	
	public List<JobPost> getAlljobs()
	{
		List<JobPost> posts = jdbc.query(
			    "SELECT * FROM JobPost",
			    (rs, rowNum) -> {
			        JobPost post = new JobPost();
			        post.setPostId(rs.getInt("PostId"));
			        post.setPostProfile(rs.getString("postProfile"));
			        post.setPostDesc(rs.getString("postDesc"));
			        post.setReqExperience(rs.getInt("reqExperience"));
			        String techstack=rs.getString("postTechStack");
			        List<String> list1=new ArrayList<>();
			        if(techstack!=null && !techstack.isEmpty())
			        {
			        	String sts[]=techstack.split(",");
				        for(int i=0;i<sts.length;i++)
				        {
				        	list1.add(sts[i]);
				        }
			        }
			        post.setPostTechStack(list1);
			        return post;
			    }
			);
		return posts;

	}
}
