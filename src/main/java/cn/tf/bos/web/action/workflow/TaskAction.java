package cn.tf.bos.web.action.workflow;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;

import com.opensymphony.xwork2.ActionContext;

import cn.tf.bos.domain.user.User;
import cn.tf.bos.domain.zm.TransferInfo;
import cn.tf.bos.web.action.BaseAction;

public class TaskAction extends BaseAction{
	
	public String findgrouptask(){
		TaskService taskService=processEngine.getTaskService();
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		List<Task>  tasks=taskService.findGroupTasks(user.getId());
		ActionContext.getContext().put("tasks", tasks);
		return "findgrouptask";
	}
	
	//拾取组任务
	public String takeTask(){
		TaskService taskService=processEngine.getTaskService();
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		taskService.takeTask(taskId, user.getId());
		
		return "takeTask";
	}
	private String taskId;
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	//查看个人任务
	public String findpersonaltask(){
		TaskService taskService=processEngine.getTaskService();
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		List<Task>  tasks=taskService.findPersonalTasks(user.getId());
		ActionContext.getContext().put("tasks", tasks);
		
		return "findpersonaltask";
	}

	//保存中转信息
	public String saveTransferinfo(){
		TransferInfo  transferInfo=new TransferInfo();
		transferInfo.setInfo(info);
		transferInfo.setArrive(arrive);
		transferInfo.setUpdateTime(new Date());

		bosTaskService.complieteTransferInfoTask(transferInfo,taskId);
		
		return "saveTransferinfo";
	}
	
	private String info;
	private String arrive;
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getArrive() {
		return arrive;
	}

	public void setArrive(String arrive) {
		this.arrive = arrive;
	}
	
	
}
