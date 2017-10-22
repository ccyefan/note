package org.tarena.note.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteUtil;

@Service("userService")//扫描
public class UserServiceImpl 
	implements UserService{
	
	@Resource//注入
	private UserMapperDao userDao;//注入

	@Transactional(readOnly=true)//只读
	public NoteResult checkLogin(
		String name, String password) {
		NoteResult result = new NoteResult();
		User user = 
			userDao.findByName(name);
		if(user == null){
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
		//将用户输入的密码加密处理
		String md5_pwd = NoteUtil.md5(password);
		//将加密后的密码和数据库中加密的结果对比
		if(!user.getCn_user_password().equals(md5_pwd)){
			result.setStatus(2);
			result.setMsg("密码不正确");
			return result;
		}
		result.setStatus(0);
		result.setMsg("登录成功");
		result.setData(user.getCn_user_id());//需要将用户ID传出去
		return result;
	}

	@Transactional//可读可写
	public NoteResult regist(
		String name, String password, String nick) {
		NoteResult result = new NoteResult();
		//检测用户名是否唯一
		User has_user = userDao.findByName(name);
		if(has_user!=null){
			result.setStatus(1);
			result.setMsg("用户名已被占用");
			return result;
		}
		//保存
		User user = new User();
		user.setCn_user_name(name);//设置用户名
		String md5_pwd = NoteUtil.md5(password);
		user.setCn_user_password(md5_pwd);//设置密码
		user.setCn_user_desc(nick);//设置昵称
		String uid = NoteUtil.createId();
		user.setCn_user_id(uid);//设置ID
		userDao.save(user);//写入cn_user表
		//其他逻辑如果发生异常可以撤销save的sql操作
		String s = null;
//		s.length();
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
	}

}
