package editors.quest;

import org.json.simple.JSONObject;

public interface IDataLoaded {
	
	public void onLoaded(JSONObject data);
	
	public void onNodeCreated(String current, String previous, String future);
	
	public void onNodeRemoved(String current);
	
	public void onNodeModified(String child, String parent, String future);

}
