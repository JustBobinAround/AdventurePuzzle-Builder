package justbobinaround.adventure_builder;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Story implements Serializable {
	public int id = 0;
	public String title = "";
	public String info = "";
	public ArrayList<Outcome> outcomes = new ArrayList<>();
	public ArrayList<String> requiredItems = new ArrayList<>();
	

	
	public Story(){
		requiredItems.add("NO_ITEM");
	}
	public ObservableList<Outcome> getObservableOutcomeList(){
		return FXCollections.observableList(outcomes);
	}
	public ObservableList<String> getObservableItemList(){
		return FXCollections.observableArrayList(requiredItems);
	}
	public void addOutcome(String info){
		Outcome o = new Outcome(outcomes.size(), info);
		outcomes.add(o);
	}
	public void setOutcomeInfo(int i, String info){
		outcomes.get(i).info = info;
	}
	public void removeOutcome(int i){
		if(i>0 && i<outcomes.size()){
			for(Choice c : outcomes.get(i).choices){
				if(c.outcomePointer>i){
					removeOutcome(c.outcomePointer);
				}
			}
			outcomes.remove(i);
		}
	}
	public void saveToJSON(String filename){
		if(outcomes.size()>0){
			try {
				String filetype = ".json";
				if(!filename.contains(filetype)){
					filename = filename + filetype;
				}
				if(filename.indexOf(filetype) ==filename.length()-filetype.length()-1){
					filename = filename + filetype;
				}
			
				//write string to file
				Files.write( Paths.get(filename), this.toJSON().getBytes(StandardCharsets.UTF_8));
				System.out.println("Data written to file successfully.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void saveAsSerialized(String filename){
		try{
			//Creating the object
			//Creating stream and writing the object
			String filetype = ".story";
			if(!filename.contains(filetype)){
				filename = filename + filetype;
			}
			if(filename.indexOf(filetype) ==filename.length()-filetype.length()-1){
				filename = filename + filetype;
			}
			
			FileOutputStream fout=new FileOutputStream(filename);
			ObjectOutputStream out=new ObjectOutputStream(fout);
			out.writeObject(this);
			out.flush();
			//closing the stream
			out.close();
			System.out.println("Story object has been serialized");
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public ArrayList<String> getRequiredItems() {
		return requiredItems;
	}
	
	public void setRequiredItems(ArrayList<String> requiredItems) {
		this.requiredItems = requiredItems;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public ArrayList<Outcome> getOutcomes() {
		return outcomes;
	}
	
	public void setOutcomes(ArrayList<Outcome> outcomes) {
		this.outcomes = outcomes;
	}
	
	public void loadSerialized(String filename){
		try{
			//Creating stream to read the object
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(filename));
			//printing the data of the serialized object
			Story loadedStory = (Story)in.readObject();
			this.id  = loadedStory.id;
			this.title = loadedStory.title;
			this.info = loadedStory.info;
			this.outcomes = loadedStory.outcomes;
			this.requiredItems = loadedStory.requiredItems;
			//closing the stream
			in.close();
		}catch(Exception e){System.out.println(e);}
		
	}
	
	public String toJSON(){
		return new Gson().toJson(this);
	}
	
}



