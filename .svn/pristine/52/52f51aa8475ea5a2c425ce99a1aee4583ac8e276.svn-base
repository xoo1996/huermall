package cn.edu.hdu.config;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class webSend {

	public static String sendService(Object[] opAddEntryArgs,String url,String method) throws Exception{
		String result=null;
		try {
			
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options =serviceClient.getOptions();
			EndpointReference targetEPR = new EndpointReference(url);
			options.setTo(targetEPR);
			 QName opAddEntry = new QName("http://service.cm.com",method);
			 //list.toArray(a)
			// Object[] opAddEntryArgs = new Object[] {user};
			 Class[] classes = new Class[] { String.class };
			 result=(String)serviceClient.invokeBlocking(opAddEntry,opAddEntryArgs, classes)[0];
			 System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

}
