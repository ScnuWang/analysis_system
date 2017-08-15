package cn.geekview.analysisSystem.entity.model;

public class TdreamServer {
	private Integer pkId;

	private String serverName;

	private String serverUrl;

	private Integer serverWeight;

	private Integer threadCount;

	private Integer taskCode;

	private Integer taskKey;

	private Integer taskStatus;

	private String taskRemark;

	public Integer getPkId() {
		return pkId;
	}

	public void setPkId(Integer pkId) {
		this.pkId = pkId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName == null ? null : serverName.trim();
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl == null ? null : serverUrl.trim();
	}

	public Integer getServerWeight() {
		return serverWeight;
	}

	public void setServerWeight(Integer serverWeight) {
		this.serverWeight = serverWeight;
	}

	public Integer getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(Integer threadCount) {
		this.threadCount = threadCount;
	}

	public Integer getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(Integer taskCode) {
		this.taskCode = taskCode;
	}

	public Integer getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(Integer taskKey) {
		this.taskKey = taskKey;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskRemark() {
		return taskRemark;
	}

	public void setTaskRemark(String taskRemark) {
		this.taskRemark = taskRemark == null ? null : taskRemark.trim();
	}

}