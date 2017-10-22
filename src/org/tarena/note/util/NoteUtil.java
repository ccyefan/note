package org.tarena.note.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public final class NoteUtil {
	
	/**
	 * md5���ܴ���
	 * @param msg Ҫ���ܵ��ַ���
	 * @return ���ܺ󾭹�������ַ���
	 */
	public static String md5(String msg){
		try{
			MessageDigest md = 
				MessageDigest.getInstance("MD5");
			byte[] input = msg.getBytes();
			byte[] output = md.digest(input);//�㷨����
//			System.out.println(output.length);
			//�����ܺ�Ľ������ַ���,����Base64����
			String base64_msg = 
				Base64.encodeBase64String(output);
			return base64_msg;
		}catch(Exception ex){
			return "";
		}
	}
	
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	
	public static void main(String[] args){
//		System.out.println(md5("1234"));
//		System.out.println(md5("fdffabchdksfhksahdkfhas"));
		System.out.println(createId());
	}
	
}




