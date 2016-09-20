package com.gyxsh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyxsh.dao.MemberDao;
import com.gyxsh.entities.Applicant;
import com.gyxsh.entities.Member;
import com.gyxsh.entities.PageBean;
import com.gyxsh.entities.PageBeanMember;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;
	
	/**
	 * 根据成员id 查询成员信息
	 * @param id 成员id
	 * @return
	 */
	public Member getById(int id){
		return memberDao.getById(id);
	}
	
	/**
	 * 根据成员id 查询成员信息 包括软删除
	 * @param id
	 * @return
	 */
	public Member getByIdAll(int id){
		return memberDao.getByIdAll(id);
	}
	
	/**
	 * 新增、修改 学生会成员信息
	 * @param member 成员类
	 */
	public void saveOrUpdate(Member member){
		memberDao.saveOrUpdate(member);
	}
	
	/**
	 * 查询所有成员信息（able=1）
	 * @return
	 */
	public List<Member> getAll(){
		return memberDao.getAll();
	}
	
	/**
	 * 根据成员的部门id 来查询信息(若deptId为0 则为所有部门)(分页查询)
	 * @param deptId 部门id
	 * @param ps pageSize 一页显示记录数
	 * @param cp currentPage 当前第几页
	 * @return
	 */
	public PageBeanMember queryForPageByDept(int deptId,int ps,int cp){
		PageBeanMember pageBeanMember=new PageBeanMember();
		pageBeanMember.setPs(ps);//每页记录数
		pageBeanMember.setCp(cp);//当前页数
		if(deptId==0){
			pageBeanMember.setTr(memberDao.getRowCountAll());
			List<Member> list=memberDao.queryForPageAll(pageBeanMember.getOffset(), ps);
			pageBeanMember.setMember(list);
		}else{
			pageBeanMember.setTr(memberDao.getRowCountByDept(deptId));//总记录数
			List<Member> list=memberDao.queryForPageByDept(deptId, pageBeanMember.getOffset(), ps);
			pageBeanMember.setMember(list);
		}
		return pageBeanMember;
	}
	
	/**
	 * 根据注销成员的部门id 来查询信息(若deptId为0 则为所有部门)(分页查询) able=0
	 * @param deptId 部门id
	 * @param ps pageSize 一页显示记录数
	 * @param cp currentPage 当前第几页
	 * @return
	 */
	public PageBeanMember queryForPageByDeptDisable(int deptId,int ps,int cp){
		PageBeanMember pageBeanMember=new PageBeanMember();
		pageBeanMember.setPs(ps);//每页记录数
		pageBeanMember.setCp(cp);//当前页数
		if(deptId==0){
			pageBeanMember.setTr(memberDao.getRowCountAllDisable());
			List<Member> list=memberDao.queryForPageAllDisable(pageBeanMember.getOffset(), ps);
			pageBeanMember.setMember(list);
		}else{
			pageBeanMember.setTr(memberDao.getRowCountByDeptDisable(deptId));//总记录数
			List<Member> list=memberDao.queryForPageByDeptDisable(deptId, pageBeanMember.getOffset(), ps);
			pageBeanMember.setMember(list);
		}
		return pageBeanMember;
	}
}
