package justbobinaround.adventure_builder;

import com.google.gson.Gson;

import java.io.Serializable;

public class Choice implements Serializable {
	public int id = 0;
	public String info = "";
	public int outcomePointer = 0;
	public String requiredItem = "NO_ITEM";
	public String givenItem = "NO_ITEM";
	
	public Choice(int id, String info, int pointer, String requiredItem, String givenItem){
		this.id = id;
		this.info = info;
		this.outcomePointer = pointer;
		this.requiredItem = requiredItem;
		this.givenItem = givenItem;
	}
	
	public String getRequiredItem() {
		return requiredItem;
	}
	
	public void setRequiredItem(String requiredItem) {
		this.requiredItem = requiredItem;
	}
	
	public String getGivenItem() {
		return givenItem;
	}
	
	public void setGivenItem(String givenItem) {
		this.givenItem = givenItem;
	}
	

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public int getOutcomePointer() {
		return outcomePointer;
	}
	
	public void setOutcomePointer(int outcomePointer) {
		this.outcomePointer = outcomePointer;
	}
	
	public Choice(int id, String info, int outcomePointer){
		this.id = id;
		this.info = info;
		this.outcomePointer = outcomePointer;
	}
	public String toJSON(){
		return new Gson().toJson(this);
	}
}