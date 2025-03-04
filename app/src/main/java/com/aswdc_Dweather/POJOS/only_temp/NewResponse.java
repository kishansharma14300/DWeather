package com.aswdc_Dweather.POJOS.only_temp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewResponse{

	@SerializedName("success")
	private boolean success;

	@SerializedName("response")
	private List<ResponseItem> response;

	@SerializedName("error")
	private Object error;

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setResponse(List<ResponseItem> response){
		this.response = response;
	}

	public List<ResponseItem> getResponse(){
		return response;
	}

	public void setError(Object error){
		this.error = error;
	}

	public Object getError(){
		return error;
	}

	@Override
 	public String toString(){
		return 
			"NewResponse{" + 
			"success = '" + success + '\'' + 
			",response = '" + response + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}