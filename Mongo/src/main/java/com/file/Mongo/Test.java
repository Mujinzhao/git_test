package com.file.Mongo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;  
import java.io.InputStream;  
import java.net.UnknownHostException;  
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.mongodb.BasicDBObject;  
import com.mongodb.DB;  
import com.mongodb.DBCollection;  
import com.mongodb.DBObject;  
import com.mongodb.Mongo;  
import com.mongodb.MongoException;  
import com.mongodb.gridfs.GridFS;  
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;  
/**
 * Hello world!
 *
 */
public class Test 
{
	Mongo connection;
	DB db;
	DBCollection collection;
	GridFS myFS;
	
	String host="localhost";
	int port=27017;
    public static void main( String[] args ) throws UnknownHostException, MongoException, NoSuchAlgorithmException, FileNotFoundException
    {
    	Test t=new Test();
    	String path="D:\\Users\\xk_zhang\\Desktop\\picture.jpg";
    	String filename="picture.jpg";
    	t.save(new FileInputStream(path), filename);
    }
    
    public Test() throws UnknownHostException, MongoException, NoSuchAlgorithmException{
    	init();
    }
    public void init() throws UnknownHostException, MongoException, NoSuchAlgorithmException{
    	connection=new Mongo(host,port);
    	db=connection.getDB("test");
    	myFS=new GridFS(db,"fs");
    }
    public void save(InputStream in,String filename){
    	int pot=0;
    	pot=filename.indexOf(".");
    	GridFSFile gridfsfile=myFS.createFile(in);
    	gridfsfile.put("filename", filename);
    	gridfsfile.put("userId", 1);
    	gridfsfile.put("uploadDate", new Date());
    	gridfsfile.put("contentType", filename.substring(pot));
    	gridfsfile.save();
    	return ;
    }
}
