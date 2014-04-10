package fr.game.engine.scripts;

import java.util.Hashtable;

public class ScriptBank {
	private Hashtable<String, Script> scripts;
	public ScriptBank() {
		scripts = new Hashtable<>();
	}
	
	public void loadScripts() {
		addScript(new Gravity());
	}
	
	public void addScript(Script s) {
		if(s != null)
			scripts.put(s.getName(), s);
	}
	
	public Script getScript(String name) {
		return scripts.get(name);
	}
	
	public void removeScript(String name) {
		scripts.remove(name);
	}
}
