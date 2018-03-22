package dao;

import java.io.File;
import java.io.IOException;

import com.mongodb.gridfs.GridFSDBFile;

public interface MongoDao {
	
	public void savePicture(String fileName,File imageFile)throws IOException;
	
	public void deletePicture(String fileName);
	
	public GridFSDBFile getPicture(String fileName);
	
}
