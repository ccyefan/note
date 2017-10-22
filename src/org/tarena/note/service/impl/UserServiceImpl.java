package org.tarena.note.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteUtil;

@Service("userService")//ɨ��
public class UserServiceImpl 
	implements UserService{
	
	@Resource//ע��
	private UserMapperDao userDao;//ע��

	@Transactional(readOnly=true)//ֻ��
	public NoteResult checkLogin(
		String name, String password) {
		NoteResult result = new NoteResult();
		User user = 
			userDao.findByName(name);
		if(user == null){
			result.setStatus(1);
			result.setMsg("�û���������");
			return result;
		}
		//���û������������ܴ���
		String md5_pwd = NoteUtil.md5(password);
		//�����ܺ����������ݿ��м��ܵĽ���Ա�
		if(!user.getCn_user_password().equals(md5_pwd)){
			result.setStatus(2);
			result.setMsg("���벻��ȷ");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		result.setData(user.getCn_user_id());//��Ҫ���û�ID����ȥ
		return result;
	}

	@Transactional//�ɶ���д
	public NoteResult regist(
		String name, String password, String nick) {
		NoteResult result = new NoteResult();
		//����û����Ƿ�Ψһ
		User has_user = userDao.findByName(name);
		if(has_user!=null){
			result.setStatus(1);
			result.setMsg("�û����ѱ�ռ��");
			return result;
		}
		//����
		User user = new User();
		user.setCn_user_name(name);//�����û���
		String md5_pwd = NoteUtil.md5(password);
		user.setCn_user_password(md5_pwd);//��������
		user.setCn_user_desc(nick);//�����ǳ�
		String uid = NoteUtil.createId();
		user.setCn_user_id(uid);//����ID
		userDao.save(user);//д��cn_user��
		//�����߼���������쳣���Գ���save��sql����
		String s = null;
//		s.length();
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
		return result;
	}

}
