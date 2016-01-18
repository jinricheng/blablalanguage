package Models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Event{

	public int id;
	public String estab;
	public int estabId;
	public String lang;
	public int langId;
	public String name;
	public String description;
	public String tim;
	public List<String> users;
	public Date date;
	public Event(){
		users = new ArrayList<String>();
	}
	public Event(int id, String estab, String lang, String name ,String t){
		this.id=id;
		this.estab=estab;
		this.lang=lang;
		this.name=name;
		this.tim = t;
		users = new ArrayList<String>();
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstab() {
		return estab;
	}

	public void setEstab(String estab) {
		this.estab = estab;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTim() {
		return tim;
	}

	public void setTim(String tim) {
		this.tim = tim;
	}
		
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String descrip){
		
		this.description=descrip;
	}
	
	public List<String> getPointedUserNameList(){
		return this.users;
	}
	
	public void addUserToEvent(String u){
		if(!this.users.contains(u)){
			this.users.add(u);
		}	
	}
	
	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}
	
	
	public int getEstabId() {
		return estabId;
	}

	public void setEstabId(int estabId) {
		this.estabId = estabId;
	}
}