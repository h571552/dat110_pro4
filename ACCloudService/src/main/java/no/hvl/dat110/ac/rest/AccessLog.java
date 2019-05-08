package no.hvl.dat110.ac.rest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

public class AccessLog {
	
	private AtomicInteger cid;
	protected ConcurrentHashMap<Integer, AccessEntry> log;
	
	public AccessLog () {
		this.log = new ConcurrentHashMap<Integer,AccessEntry>();
		cid = new AtomicInteger(0);
	}

	// TODO: add an access entry for the message and return assigned id
	public int add(String message) {
		
		int i = cid.get();
		AccessEntry entry = new AccessEntry(cid.get(), message);
		log.put(cid.get(), entry);
		cid.set(cid.get() + 1);
		
		return i;
	}
		
	// TODO: retrieve a specific access entry 
	public AccessEntry get(int id) {
		
		return log.get(id);
		
	}
	
	// TODO: clear the access entry log
	public void clear() {
		
		log.clear();
		cid.set(0);
	}
	
	// TODO: JSON representation of the access log
	public String toJson () {
    	
		/*
		int i = 0;
		
		String json = "";
		json+= "[\n";
		for(AccessEntry entry : log.values()) {
			
			i++;
			
			json += "\t{\n\t\t\"id\": " + entry.getId() + "," + "\n\t\t"
					+ "\"message: \"" + entry.getMessage() + "\"" + "\n"
					+ "\t}";
			
			if(i < log.size())
				json+=",";
			
			json+="\n";
			
		}
		json+="]";
    	*/
		
		int i = 0;
		
		String json = "[";
		Gson gson = new Gson();
		
		for(AccessEntry entry : log.values()) {
			i++;
			json += gson.toJson(entry, AccessEntry.class);
			
			if(i < log.size())
				json += ",";
		}
		
		json += "]";
		
    	return json;//json;
    }
}
