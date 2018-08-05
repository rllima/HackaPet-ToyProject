package Data;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserialize {
	private String path;
    public Deserialize(String path){
    	this.path = path;
    }
    
    public Object deserializer(String fileName) throws Exception {
    	
        FileInputStream inFile = new FileInputStream(fileName);
        ObjectInputStream d = new ObjectInputStream(inFile);
        Object o = d.readObject();
        d.close();
        return o;
    }
    public String getPath() {
    	return this.path;
    }
}
