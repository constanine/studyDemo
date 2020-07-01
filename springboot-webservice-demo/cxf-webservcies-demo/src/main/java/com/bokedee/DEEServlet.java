package com.bokedee;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DEEServlet {
	public static String main(String[] args) throws Exception{
        URL url = null;
        HttpURLConnection connection = null;
        try {	
        		//�õ����ʵ�ַ��URL
                url = new URL("http://localhost:48000/BokeDee/httpService");
                //�õ�������ʶ���java.net.HttpURLConnection
                connection = (HttpURLConnection) url.openConnection();
                //�����Ƿ���HttpURLConnection���
                connection.setDoOutput(true);
                //�����Ƿ��httpUrlConnection����
                connection.setDoInput(true);
                //��������ʽ
                connection.setRequestMethod("POST");
                //�����Ƿ�ʹ�û���
                connection.setUseCaches(false);
                // ���ó�ʱʱ��
//              connection.setConnectTimeout(3000);
                //����
                connection.connect();
                //HTTP���ĵ�������ͨ��OutputStream��д��ģ� ������д������ݲ����������͵����磬
                //���Ǵ������ڴ滺�����У������ر�ʱ������д�����������HTTP���ġ�
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                
                //�Կ��ܳ��������ַ��� requestBody ���б��봦��
                String action = args[0];
                String xml = args[1];
                
                
                //�����洫��payload�ǲ�����ҵ���������������������ˣ�dee������Ҫһ��map��һ��list
//                String requestBody = 
//                		"<List>"+xml+"</List>";
//                requestBody = URLEncoder.encode(requestBody, "utf-8");
                String requestBody = URLEncoder.encode(xml, "utf-8");
             

                // д�������������
                out.write(("action="+action+"&requestBody="+requestBody).getBytes("utf-8"));
                out.flush();
                out.close();
                //����getInputStream()����ʱ������һ�������������ڴ��ж�ȡ����������HTTP����ķ�����Ϣ��
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                }
                reader.close();
                //bufferΪ������ 
                String deeresult = buffer.toString();
                System.out.println("DEEResponse ="+deeresult);
                return deeresult;
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
                if (connection != null) {
                	 // �Ͽ�����
                        connection.disconnect();
                }
        }
        return "";
   } 
}
