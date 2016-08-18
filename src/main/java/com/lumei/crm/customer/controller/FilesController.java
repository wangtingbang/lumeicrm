package com.lumei.crm.customer.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lumei.crm.commons.bean.SystemProperties;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.util.DateTimeUtil;
import com.lumei.crm.commons.util.KeyGenerator;
import com.lumei.crm.commons.util.SessionUtil;
import com.lumei.crm.customer.biz.FilesBusiness;
import com.lumei.crm.customer.dto.Files;
import com.lumei.crm.customer.entity.TFiles;

@Controller
@RequestMapping(value="/files")
public class FilesController {
	@Autowired
	FilesBusiness filesBusiness;
	public static String ROOTPATH = "";
	static{
		ROOTPATH = SystemProperties.get("lumei.attachment.file.path");
	}
	@RequestMapping(value="upload")
	@ResponseBody
	public int upload(HttpServletRequest request) throws IOException{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");
		String fileName = getFileName(file);
		if(StringUtils.isBlank(fileName)){
			return 0;
		}
		Files files = new Files();
		String userId = request.getParameter("userId");
		String serviceId = request.getParameter("serviceId");
		files.setId(KeyGenerator.uuid());
		files.setUserId(userId);
		files.setServiceId(serviceId);
		files.setFileName(fileName);
		files.setCreateUserId(SessionUtil.currentUserId());
		files.setCreateTime(DateTimeUtil.now());
		files.setUpdateUserId(SessionUtil.currentUserId());
		files.setUpdateTime(DateTimeUtil.now());
		
		String packageName = getPackageName(request, files.getId());
		String path = ROOTPATH + packageName;
		File p = new File(path);
		if(!p.exists()){
			p.mkdirs();
		}
		path += "/"+fileName;
		File f = new File(path);
		files.setUri(path);
		FileCopyUtils.copy(file.getBytes(),f);
		return callBack(f,files,request);
	}
	
	protected int callBack(File file, Files files, HttpServletRequest request){
		filesBusiness.create(files);
		return 0;
	}
	
	protected String getPackageName(HttpServletRequest request, String id){
		String userId = request.getParameter("userId");
		String serviceId = request.getParameter("serviceId");
		return "/"+userId+"/"+serviceId+"/"+id;
	}
	
	protected String getFileName(MultipartFile file){
		String fname = file.getOriginalFilename();
		return fname;
	}
	
	@RequestMapping(value="delete/{id}",method = RequestMethod.GET)
	@ResponseBody
	public int delete(@PathVariable("id")String id){
		id = StringUtils.isEmpty(id)?"":id;
		Files files = filesBusiness.find(id,Files.class);
		if(files == null){
			return 0;
		}
		String path = files.getUri();
		File f = new File(path);
		if(f.exists()){
			f.delete();
		}
		filesBusiness.delete(id,Files.class);
		return 0;
	}
	
	@RequestMapping(value="img/{id}.{suff}",method = RequestMethod.GET)
	@ResponseBody
	public void img(@PathVariable("id")String id,@PathVariable("suff")String suff, HttpServletResponse response) throws Exception{
		id = StringUtils.isEmpty(id)?"":id;
		Files files = filesBusiness.find(id,Files.class);
		if(files == null){
			return;
		}
		String path = files.getUri();
		File file = new File(path);
		BufferedInputStream bis = null;   
		BufferedOutputStream bos = null;  
		try {
			bis = new BufferedInputStream(new FileInputStream(file)); 
//			response.setContentType("image/"+suff);
			bos = new BufferedOutputStream(response.getOutputStream());   
			byte[] buff = new byte[2048];
			int bytesRead;   
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
			    bos.write(buff, 0, bytesRead);   
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
		if (bis != null)   
		    bis.close();   
		if (bos != null)   
		    bos.close();   
		}   
	}
	
	@RequestMapping(value="download/{id}",method = RequestMethod.GET)
	@ResponseBody
	public void download(@PathVariable("id")String id, HttpServletResponse response) throws Exception{
		BufferedInputStream bis = null;   
		BufferedOutputStream bos = null;  
		try {
			id = StringUtils.isEmpty(id)?"":id;
			Files files = filesBusiness.find(id,Files.class);
			if(files == null){
				return;
			}
			String path = files.getUri();
			String fileName = files.getFileName();
			File file = new File(path);
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(file.length()));
			bis = new BufferedInputStream(new FileInputStream(file));   
			bos = new BufferedOutputStream(response.getOutputStream());   
			byte[] buff = new byte[2048];   
			int bytesRead;   
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
			    bos.write(buff, 0, bytesRead);   
			}
		} catch (Exception e) {   
		    e.printStackTrace();
		} finally {   
		if (bis != null)   
		    bis.close();   
		if (bos != null)   
		    bos.close();   
		}   
	}
	
	@RequestMapping(value="list/{id}",method = RequestMethod.GET)
	@ResponseBody
	public List fileList(@PathVariable("id")String id) throws Exception{
		id = StringUtils.isEmpty(id)?"":id;
		List<Files> files = filesBusiness.list(Example.newExample(TFiles.class).paramEqualTo("serviceId", id));
		return files;
	}
}
