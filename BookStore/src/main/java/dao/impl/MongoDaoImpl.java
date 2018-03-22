package dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import dao.MongoDao;

public class MongoDaoImpl implements MongoDao{
	private MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void savePicture(String fileName, File imageFile) throws IOException {
		DB db = mongoTemplate.getDb();
		GridFS gridFS =new GridFS(db, "picture");
		if(gridFS.findOne(fileName)==null){
			GridFSInputFile gridFile = gridFS.createFile(imageFile);
			gridFile.setFilename(fileName);
			gridFile.save();      
		}
		else{
			deletePicture(fileName);
			savePicture(fileName,imageFile);
		}
	}

	@Override
	public void deletePicture(String fileName) {
		DB db = mongoTemplate.getDb();
		GridFS gridFS =new GridFS(db, "picture"); 
		gridFS.remove(fileName);
	}

	@Override
	public GridFSDBFile getPicture(String fileName) {
		DB db = mongoTemplate.getDb();
		GridFS gridFS= new GridFS(db,"picture"); 
		GridFSDBFile res=gridFS.findOne(fileName);
		if(res==null){
			res=getPicture(String.valueOf(-1));
		}
		return res;
	}
	
}
