package Data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialize {
    public Serialize(){}

    public void serialize(String path, Object obj) throws IOException {
        FileOutputStream outfile = new FileOutputStream(path);
        ObjectOutputStream outobject = new ObjectOutputStream(outfile);
        outobject.writeObject(obj);
        outobject.close();
    }
}
