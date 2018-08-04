package Data;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserialize {
    public Deserialize(){}

    public Object deserializer(String path) throws Exception {
        FileInputStream inFile = new FileInputStream(path);
        ObjectInputStream d = new ObjectInputStream(inFile);
        Object o = d.readObject();
        d.close();
        return o;
    }
}
