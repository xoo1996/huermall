package cn.edu.hdu.printpdf;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import cn.edu.hdu.pojo.Battery;
import cn.edu.hdu.pojo.MemBat;
import cn.edu.hdu.pojo.Store;




public class PrintGetEviPdf {
	
	/**
	 * 打印赠送电池凭证
	 * @param Name 用户姓名
	 * @param GctStoreNm 领取店铺名称
	 * @param NowTime 领取时间
	 * @param BatNm 电池名字
	 * @param BatteryQty 赠送数量
	 * @param TotalGetBatQty 累计领取数量
	 * @param getBatQty 领取数量
	 * @param BatteryReQty 剩余数量
	 * @param GctAddr 领取店铺地址
	 * @param GctTel 领取店铺电话
	 * @param TempletPdfNm 模板名称
	 * @param PdfFilePath 文件相对地址
	 * @param OutPdfPreNm 输出文件前缀名称
	 * @param request 请求
	 * @return
	 */
	public static final String printGetEviPdf(String Name,String GctStoreNm, String NowTime,String BatNm,String BatteryQty, String TotalGetBatQty,String GetBatQty,String BatteryReQty,String GctAddr,String GctTel,String TempletPdfNm,String PdfFilePath,String OutPdfPreNm,HttpServletRequest request){

		try {			
			//模板地址
			String templatePath =request.
					getSession().getServletContext().getRealPath("/file")+"/"+TempletPdfNm+".pdf"; 
			// 生成的新文件路径
			String newPDFPath = request.
					getSession().getServletContext().getRealPath("");
			newPDFPath = newPDFPath.substring(0, newPDFPath.lastIndexOf('h')-1);
			String randnm = (Math.random()*10000+10000)+"";
			randnm = randnm.substring(0, 5);
			
			//确认路径存在
			String temPath = newPDFPath;
			File file = new File(temPath+PdfFilePath);
			if(!file.exists())
				file.mkdir();
			
			newPDFPath+=PdfFilePath+"\\"+OutPdfPreNm+randnm+".pdf";

			PdfDocument pdf = new PdfDocument(new PdfReader(templatePath), new PdfWriter(newPDFPath));
	        PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
	        Map<String, PdfFormField> fields = form.getFormFields();
	        
			//处理中文问题  
			PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);  
			String[] str = {
					Name, 
					GctStoreNm,
					//new java.sql.Date(System.currentTimeMillis()).toString(), 
					NowTime,
					GctAddr,
					GctTel, 
					BatNm,
					//BatteryType, 
					BatteryQty,
					TotalGetBatQty,
					GetBatQty, 
					BatteryReQty,
					 };
			int i = 0;
			java.util.Iterator<String> it = fields.keySet().iterator();
			while (it.hasNext()) {
				//获取文本域名称
				String name = it.next().toString();
				//填充文本域
				fields.get(name).setValue(str[i++]).setFont(font).setFontSize(12);
				System.out.println(name);
			}	
			form.flattenFields();//设置表单域不可编辑			
			pdf.close();
			
			return randnm;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
	}
	
	public static final String printReGetEviPdf(String Name,String GctStoreNm, String NowTime,String BatNm, String BatteryType,String BatteryQty,String GetBatQty,String BatteryReQty,String GctAddr,String GctTel,String TempletPdfNm,String PdfFilePath,String OutPdfPreNm,HttpServletRequest request){

		try {			
			//模板地址
			String templatePath =request.
					getSession().getServletContext().getRealPath("/file")+"/"+TempletPdfNm+".pdf"; 
			// 生成的新文件路径
			String newPDFPath = request.
					getSession().getServletContext().getRealPath("");
			newPDFPath = newPDFPath.substring(0, newPDFPath.lastIndexOf('h')-1);
			String randnm = (Math.random()*10000+10000)+"";
			randnm = randnm.substring(0, 5);
			
			//确认路径存在
			String temPath = newPDFPath;
			File file = new File(temPath+PdfFilePath);
			if(!file.exists())
				file.mkdir();
			
			newPDFPath+=PdfFilePath+"\\"+OutPdfPreNm+randnm+".pdf";

			PdfDocument pdf = new PdfDocument(new PdfReader(templatePath), new PdfWriter(newPDFPath));
	        PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
	        Map<String, PdfFormField> fields = form.getFormFields();
	        
			//处理中文问题  
			PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);  
			String[] str = {
					Name, 
					GctStoreNm,
					//new java.sql.Date(System.currentTimeMillis()).toString(), 
					NowTime,
					GctAddr,
					GctTel, 
					BatNm,
					BatteryType, 
					BatteryQty,
					GetBatQty, 
					BatteryReQty,
					 };
			int i = 0;
			java.util.Iterator<String> it = fields.keySet().iterator();
			while (it.hasNext()) {
				//获取文本域名称
				String name = it.next().toString();
				//填充文本域
				fields.get(name).setValue(str[i++]).setFont(font).setFontSize(12);
				System.out.println(name);
			}	
			form.flattenFields();//设置表单域不可编辑			
			pdf.close();
			
			return randnm;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
	}
}
