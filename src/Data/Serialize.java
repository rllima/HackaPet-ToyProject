package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialize {
	private String path;
    public Serialize(String path){
    	this.path = creatPath(path);
    }
    private String creatPath(String path) {
    	String newPath = System.getProperty("user.home") + path;
    	return newPath;
    }
    public void serialize(Object obj,String fileName) throws IOException {
    	File directory = new File(this.path);
    	if(!directory.exists()) {
    		directory.mkdir();
    	}
        FileOutputStream outfile = new FileOutputStream(fileName);
        ObjectOutputStream outobject = new ObjectOutputStream(outfile);
        outobject.writeObject(obj);
        outobject.close();
    }
    public String getPath(){
    	return this.path;
    }
}
