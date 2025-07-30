package com.telusko.JobApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telusko.JobApp.model.JobPost;
import com.telusko.JobApp.service.JobService;

@Controller
public class JobController 
{
	@Autowired
	JobService jobservice;
	@RequestMapping({"/","home"})
	public String home()
	{
		return "home";
	}
	@RequestMapping("addjob")
	public String addjob()
	{
		return "addjob";
	}
	@PostMapping("handleForm")
	public String formhandle(@ModelAttribute JobPost jobPost)
	{
		jobservice.addjob(jobPost);
		return "success";
	}
	@GetMapping("viewalljobs")
	public String viewAlljobs(Model m)
	{
		List<JobPost>jobposts=jobservice.getAlljob();
		m.addAttribute("jobPosts", jobposts);
		return "viewalljobs";
		
	}
}
