package oa.utils;





import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	//16��������
	private static final String[] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F",};
	//����ͨ�ַ�ת��MD5�ַ�
	public static String encodeByMd5(String password){
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] results = md5.digest(password.getBytes());
			return byteArrayToString(results);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//���ֽ�����ת���ַ�
	private static String byteArrayToString(byte[] results){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<results.length;i++){
			sb.append(byteToString(results[i]));
		}
		return sb.toString();
	}
	//���ֽ�ת��ʮ������ַ�
	private static String byteToString(byte b){
		int n = b;
		if(n<0){
			//byte�͹���Ϊ256����ֵ[0-255]
			n = 256 + n;//256+(-116)
		}
		//0-255��Ӧ��16������Ϊ(0-FF)
		//10������ת16�������һλ����16���ڶ�λģ��16��
		int d1 = n / 16;//15//F
		int d2 = n % 16;//15//F
		return hex[d1] + hex[d2];
	}
}



