package com.appsdeveloperblog.app.ws.ui.model.response;

import org.apache.log4j.Logger;

public class OperationStatusModel {

	private String operationResult;
	private String operationName;

	public String getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(String operationResult) {
		this.operationResult = operationResult;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

}
