package oa.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
@Controller("homeAction")
public class HomeAction extends ActionSupport {
public String index(){
	return "index";
}
public String top(){
	return "top";
}
public String right(){
	return "right";
}
public String left(){
	return "left";
}
public String bottom(){
	return "bottom";
}
}
