package hyc.upload.dataupload.httpUtil;

import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPostForm {
    public static JSONObject httpPost(String url,JSONObject params) throws IOException {


        URL uri = null;

        uri = new URL(url);

        HttpURLConnection conn = null;
        InputStream in = null;


        conn = (HttpURLConnection) uri.openConnection();


        conn.setReadTimeout(30 * 1000); // 缓存的最长时间



        conn.setConnectTimeout(30 * 1000);

        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false); // 不允许使用缓存

        conn.setRequestMethod("POST");

        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");

        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        // conn.setRequestProperty("Content-Type", "text/xml; charset=GB2312");
        conn.setRequestProperty("accept","application/json");
        BufferedReader reader = null;
        JSONObject jsobj= new JSONObject();
        if(params!=null) {

            byte[] writebytes = params.toString().getBytes("UTF-8");

            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
            OutputStream outwritestream;

            outwritestream = conn.getOutputStream();
            outwritestream.write(writebytes );

            outwritestream.flush();
            outwritestream.close();
            StringBuffer buffer=new StringBuffer();
            try{


            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(),"UTF-8"));
                //System.out.println(reader.readLine().toString());
              //      ;

                String temp;
                while ((temp = reader.readLine()) != null) {
                    buffer.append(temp);
                    buffer.append("\n");
                }

                jsobj=  JSONObject.fromObject( buffer.toString());

            }
            }finally {
                reader.close();
            }





        }




        return jsobj;


    }
}
