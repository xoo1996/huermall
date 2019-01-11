package cn.edu.hdu.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.hdu.Code.UploadCode;


public class FileUtil {
	
	
	public static void downloadLocal(HttpServletResponse response, String fileName, String filePath) throws FileNotFoundException {
		System.out.println(filePath);
		// 读到流中
		InputStream inStream = new FileInputStream(filePath);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		try {
			// URLEncoder.encode(fileName, "UTF-8") 解决中文文件名显示不全和乱码的情况
			response.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
/*	public static void downloadLocal(HttpServletResponse response,String filename, String fileName1) throws FileNotFoundException{
		try {
			
			//String fileName = request.getSession().getServletContext().getRealPath("images")+"/l1.jpg";  
	        //获取输入流  
	        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName1)));  
	        //假如以中文名下载的话  
	       // String filename = "l1.jpg";  
	        //转码，免得文件名中文乱码 
	       // System.out.println(fileName1);
	        filename = URLEncoder.encode(filename,"UTF-8");  
	        //设置文件下载头  
	        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
	        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
	        response.setContentType("multipart/form-data");   
	        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
	        int len = 0;  
	        while((len = bis.read()) != -1){  
	        //	System.out.println(len);
	            out.write(len);  
	            out.flush();  
	        }  
	        out.close();  
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}*/
	/**
	 * 根据本地文件路径删除文件 方法描述
	 * 
	 * @param path
	 */
	public static void deleteFileByFilePath(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * 将文本内容独处转为字符串 方法描述
	 * 
	 * @param buffer
	 * @param filePath
	 * @throws IOException
	 */
	public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
		InputStream is = new FileInputStream(filePath);
		String line; // 用来保存每行读取的内容
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		line = reader.readLine(); // 读取第一行
		while (line != null) { // 如果 line 为空说明读完了
			buffer.append(line); // 将读到的内容添加到 buffer 中
			buffer.append("\n"); // 添加换行符
			line = reader.readLine(); // 读取下一行
		}
		reader.close();
		is.close();
	}
	
	public static boolean saveFile(MultipartFile file, String filePath) throws IOException {
		try {
			File tempFile = new File(filePath);
			if (tempFile.exists()) {
				tempFile.delete();
			}
			if (!tempFile.getParentFile().exists()) {
				tempFile.getParentFile().mkdirs();
			}
			if (!tempFile.exists()) {
				tempFile.createNewFile();
			}
			file.transferTo(tempFile);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}
	
	/**
	 * 图片上传
	 * 
	 * @return 返回相对路径
	 * @param photo
	 *            图片文件
	 * @param photoFileName
	 *            文件名
	 * @param savePath
	 *            文件保存路径
	 * @return
	 */
	public static String fileUpload(MultipartFile photo, String photoFileName, String savePath, HttpServletRequest request) {
		if (photo == null) {
			return null;
		} // 如果上传图片不为空则进行上传图片操作
		// 文件存储路径
		String path = request.getSession().getServletContext().getRealPath("") + File.separator + savePath;
		// String path = ServletActionContext.getServletContext().getRealPath("")+File.separator+savePath;
		// 获取当前文件类型
		String type = photoFileName.substring(photoFileName.lastIndexOf(".") + 1, photoFileName.length());
		// 获取当前系统时间字符串
		String time = new SimpleDateFormat("yyMMddssSSS").format(new Date());
		// 构建新文件名
		String newFileName = time + "." + type;
		// 按指定路径重命名构建文件
		File savefile = new File(path, newFileName);
		// 若保存文件的文件夹不存在则创建
		if (!savefile.getParentFile().exists()) {
			savefile.getParentFile().mkdirs();
		}
		try {// 将上传文件的内容转移到新建文件中
			photo.transferTo(savefile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return savePath + newFileName;
	}
	
	/**
	 * 通过本地文件访问json并读取
	 * 
	 * @param path
	 *            ：E:/svn/05.Hospital/templatedept_uuid.json
	 * @return：json文件的内容
	 */
	public static String ReadFile(String path) {
		String laststr = "";
		File file = new File(path);// 打开文件
		BufferedReader reader = null;
		try {
			FileInputStream in = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));// 读取文件
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr = laststr + tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException el) {
				}
			}
		}
		return laststr;
	}

	public static boolean saveImg(MultipartFile file,String filePath) throws IOException{
		try {
			File tempFile = new File(filePath);  
			if(tempFile.exists()){
				tempFile.delete();
			}
			if (!tempFile.getParentFile().exists()) {  
			    tempFile.getParentFile().mkdirs();  
			}  
			if (!tempFile.exists()) {  
			    tempFile.createNewFile();  
			}  
			file.transferTo(tempFile);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}  
	}
	
	private static String getFileName(String fileName){
		Date date = new Date();
		return date.getTime() + "_" + fileName;
	}
	public static String getFilePath(String moduleDir,String fileName,
			Long seriesId){
		String rootDir = UploadCode.ROOT_DIR;
		return rootDir + moduleDir  + "/" +seriesId + "/" +  getFileName(fileName);
	}
	
	public static String getFilePath(String moduleDir,String fileName){
		String rootDir = UploadCode.ROOT_DIR;
		return rootDir + moduleDir  + "/" + getFileName(fileName) ;
	}
	
	public static String getUrl(String moduleName,Long id){
		return UploadCode.ROOT_URL + moduleName + "/" + id;
	}
	
    /** 
     *  根据路径删除指定的目录或文件，无论存在与否 
     *@param sPath  要删除的目录或文件 
     *@return 删除成功返回 true，否则返回 false。 
     */  
    public static boolean DeleteFolder(String sPath) {  
        File file = new File(sPath);  
        // 判断目录或文件是否存在  
        if (!file.exists()) {  // 不存在返回 false  
            return false;  
        } else {  
            // 判断是否为文件  
            if (file.isFile()) {  // 为文件时调用删除文件方法  
                return deleteFile(sPath);  
            } else {  // 为目录时调用删除目录方法  
                return deleteDirectory(sPath);  
            }  
        }  
    }  
    
    /** 
     * 删除单个文件 
     * @param   sPath    被删除文件的文件名 
     * @return 单个文件删除成功返回true，否则返回false 
     */  
    public static boolean deleteFile(String sPath) {  
        File file = new File(sPath);  
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) {  
            file.delete();  
            return true;
        }  
        return false;  
    } 
    
    /** 
     * 删除目录（文件夹）以及目录下的文件 
     * @param   sPath 被删除目录的文件路径 
     * @return  目录删除成功返回true，否则返回false 
     */  
    public static boolean deleteDirectory(String sPath) {  
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
        if (!sPath.endsWith(File.separator)) {  
            sPath = sPath + File.separator;  
        }  
        File dirFile = new File(sPath);  
        //如果dir对应的文件不存在，或者不是一个目录，则退出  
        if (!dirFile.exists() || !dirFile.isDirectory()) {  
            return false;  
        }  
        boolean flag = true;  
        //删除文件夹下的所有文件(包括子目录)  
        File[] files = dirFile.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            //删除子文件  
            if (files[i].isFile()) {  
                flag = deleteFile(files[i].getAbsolutePath());  
                if (!flag) break;  
            } //删除子目录  
            else {  
                flag = deleteDirectory(files[i].getAbsolutePath());  
                if (!flag) break;  
            }  
        }  
        if (!flag) return false;  
        //删除当前目录  
        if (dirFile.delete()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
}
