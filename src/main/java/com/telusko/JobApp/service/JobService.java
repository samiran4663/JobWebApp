package com.telusko.JobApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusko.JobApp.model.JobPost;
import com.telusko.JobApp.repo.JobRepo;

@Service
public class JobService {

	@Autowired
	JobRepo jbr;
	public void addjob(JobPost jobpost)
	{
		jbr.databaseAddJob(jobpost);
	}
	public List<JobPost> getAlljob()
	{
		return jbr.getAlljobs();
	}
}
