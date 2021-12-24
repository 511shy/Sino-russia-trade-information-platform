package com.highcom.utils;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import java.util.Random;

public class AliyunSendShortMessage {

	//流程https://help.aliyun.com/document_detail/59210.html
    public static SendSmsResponse sendShortMessage(String phoneNumber, String signName, String templateCode, String templateParam) throws ClientException {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-beijing", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-beijing", "cn-beijing", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        //【阿里云】您正在申请手机注册，验证码为：${code}，5分钟内有效！
		//其中：
        //【阿里云】为短信签名。
		// 您正在申请手机注册，验证码为：${code}，5分钟内有效！。为短信模板。
		// 模板变量为：${code}。
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam(templateParam);
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
        	//请求成功
        }
        return sendSmsResponse;
    }

    //发送短信
    public static String sendCode(String phones, String code) {

        String signName = "";  //签名
        String templateCode = "";  //模板
        String templateParam = "{\"code\":\"" + code + "\"}"; //验证码
        SendSmsResponse sendSmsResponse = null;
        String message = "验证码发送失败";
        try {
            //调用发送验证码的方法
            sendSmsResponse = sendShortMessage(phones, signName, templateCode, templateParam);
            //获取返回的信息
            String messageCode = sendSmsResponse.getCode();

            //判断返回信息
            if (messageCode.equals("OK")) {
                message = "验证码发送成功";
            }else{
                throw new RuntimeException(message);
            }
           /* else if (messageCode.equals("isv.AMOUNT_NOT_ENOUGH")) {
                message = "账户余额不足";
            } else if (messageCode.equals("isv.INVALID_PARAMETERS")) {
                message = "参数异常";
            } else if (messageCode.equals("isv.MOBILE_NUMBER_ILLEGAL")) {
                message = "非法手机号";
            }*/

        } catch (ClientException e) {
            e.printStackTrace();
        }

        return message;
    }

    //生成随机数
    public static String getRandom(int n) {
        char[] code = "0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(code[new Random().nextInt(code.length)]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String random = getRandom(6);
        String s = sendCode("1241165l8", random);
        System.out.println("s = " + s);
    }
}
