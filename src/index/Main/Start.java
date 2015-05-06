package index.Main;

import index.Backstage.Print;

public class Start {
	private static long startTime = System.currentTimeMillis();
	public static long getStatTime(){
		return startTime;
	}
	private static String[] args;
	@SuppressWarnings("unused")
	private static class Do {
		static {
			if(args != null){
	    		if(args[0].equals("-gui") || args[0].equals("-GUI"))initClass("index.GUI.FramePrintStream");
	    	}
			Print.standard("Start INDEX !", "index.Main.Start$Do", Print.INFO);
			initClass("index.Main.Start$GUIClass");
	    	Print.standard("Time consuming: " + ((System.currentTimeMillis() - startTime) / 1000.0) + "s", "index.Main.Start.main", Print.INFO);
	    	Print.standard("The main thread is over !", "index.Main.Start$Do", Print.INFO);
		}
	}
    public static void main(String[] args){
    	if(Start.args == null)Start.args = args;
    	initClass("index.Main.Start$Do");
    }
    public static void initClass(String className){
    	try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			Print.standard("The class: " + className + "is not find !", "index.Main.Start.initClass", Print.ERROR);
		}
    }
    public enum GUIClass {
		Resources("index.GUI.Resources"), GUI("index.GUI.GUI");
        private String name;
        private GUIClass(String name){
            this.name = name;
        }
        static {
        	for (GUIClass c : GUIClass.values()){
            	initClass(c.name);
            }
        }
    }
}