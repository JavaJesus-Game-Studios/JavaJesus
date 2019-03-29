package editors.quest;

public class StylesheetFactory {
	
	public static String makeStylesheet() { 
		String ss = " node {" + 
				"        fill-color: red;" + 
				"        size: 30;" + 
				"        text-size: 30;" + 
				"    }" + 
				"    node#0 {" + 
				"        stroke-mode: plain;" + 
				"        stroke-color: blue;" + 
				"    }" +
				"    node.complete {" + 
				"        fill-color: green;" + 
				"    }" +
				"    node.incomplete {" + 
				"        fill-color: red;" + 
				"    }";
		
		return ss;
	}

}
