package com.gyxsh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyxsh.dao.ApplicantDao;
import com.gyxsh.entities.Applicant;
import com.gyxsh.entities.AptStatus;
import com.gyxsh.entities.Department;
import com.gyxsh.entities.PageBean;

@Service
public class ApplicantService {
	@Autowired
	private ApplicantDao applicantDao;
	
	/**
	 * 新增、修改 申请者信息
	 * @param applicant 新的申请者类
	 */
	public void saveOrUpdate(Applicant applicant){
		applicantDao.saveOrUpdate(applicant);
	}
	
	/**
	 * 根据id 软删除 applicant
	 * @param id
	 */
	public void pretendDeleteById(int id){
		Applicant apt=applicantDao.getById(id);
		apt.setAble(0);
		applicantDao.saveOrUpdate(apt);
	}
	
	/**
	 * 查询所有申请者的数据
	 * @return 返回一个applicant类的list集合
	 */
	public List<Applicant> getAll(){
		return applicantDao.getAll();
	}
	
	/**
	 * 根据申请者id查询相应的申请者信息
	 * @param id 申请者id
	 * @return 申请者信息
	 */
	public Applicant getById(int id){
		return applicantDao.getById(id);
	}
	
	/**
	 * 根据申请者id删除对应数据
	 * @param id 申请者id
	 */
	public void deleteById(int id){
		applicantDao.deleteById(id);
	}
	
	/**
	 * 根据申请者id 改变申请者状态(包括 是否发送邮件 重置为0 未发送)
	 * @param id
	 * @param statusId
	 */
	public void updateStatus(int id,int statusId){
		Applicant apt=applicantDao.getById(id);
		apt.setAptStatus(new AptStatus(statusId));
		apt.setAptEmailed(0);
		applicantDao.saveOrUpdate(apt);
	}
	
	/**
	 * 根据申请者状态id 和当前处理部门 查询(deptId为0 则返回所有)
	 * @param statusId 状态id
	 * @param deptId 当前处理部门id（0返回所有）
	 * @return
	 */
	public List<Applicant> getByStatusAndDept(int statusId,int deptId){
		if(deptId==0){
			return applicantDao.getByStatus(statusId);
		}else{
			return applicantDao.getByStatusAndDept(statusId,deptId);
		}
	}
	
	/**
	 * 根据申请者状态id 和上一个处理部门 查询(针对被调剂者  deptId为0 则返回所有)
	 * @param statusId 状态id
	 * @param deptId 上一个处理部门id（0返回所有）
	 * @return
	 */
	public List<Applicant> getByStatusAndPreDeptAdjusted(int statusId,int deptId){
		if(deptId==0){
			return applicantDao.getByStatusAdjusted(statusId);
		}else{
			return applicantDao.getByStatusAndPreDeptAdjusted(statusId, deptId);
		}
	}
	
	
	/**
	 * 根据 淘汰申请者 和当前处理部门 查询(deptId为0 则返回所有)
	 * @param statusId 状态id
	 * @param deptId 当前处理部门id（0返回所有）
	 * @return
	 */
	public List<Applicant> getByFail(int deptId){
		if(deptId==0){
			return applicantDao.getByFail();
		}else{
			return applicantDao.getByFailAndDept(deptId);
		}
	}
	
	/**
	 * 根据状态id 和 部门id查询总记录数
	 * @param statusId 状态id
	 * @param deptId 部门id
	 * @return
	 */
	public Integer getRowCountByStatusAndDept(int statusId,int deptId){
		return applicantDao.getRowCountByStatusAndDept(statusId, deptId);
	}
	
	/*
	 * 下面均为分页
	 */
	/**
	 * 根据申请者的 状态和当前处理部门 来查询信息(若deptId为0 则为所有部门)(分页查询)
	 * @param statusId 申请者状态的id
	 * @param deptId 当前处理部门id
	 * @param ps pageSize 一页显示记录数
	 * @param cp currentPage 当前第几页
	 * @return
	 */
	public PageBean queryForPageByStatusAndDept(int statusId,int deptId,int ps,int cp){
		PageBean pageBean=new PageBean();
		pageBean.setPs(ps);//每页记录数
		pageBean.setCp(cp);//当前页数
		if(deptId==0){
			pageBean.setTr(applicantDao.getRowCountByStatus(statusId));
			List<Applicant> list=applicantDao.queryForPageByStatus(statusId, pageBean.getOffset(), ps);
			pageBean.setApplicants(list);
		}else{
			pageBean.setTr(applicantDao.getRowCountByStatusAndDept(statusId, deptId));//总记录数
			List<Applicant> list=applicantDao.queryForPageByStatusAndDept(statusId, deptId, pageBean.getOffset(), ps);
			pageBean.setApplicants(list);
		}
		return pageBean;
	}
	
	
	/**
	 * 根据申请者的 状态和当前处理部门  模糊姓名  来查询信息(若deptId为0 则为所有部门)(分页查询)
	 * @param name 模糊查询的 姓名
	 * @param statusId	申请者的状态 id
	 * @param deptId 当前处理部门的 id
	 * @param ps 一页显示记录数
	 * @param cp 当前页
	 * @return
	 */
	public PageBean queryForPageByLikeNameAndStatusAndDept(String name,int statusId,int deptId
			,int ps,int cp){
		PageBean pageBean=new PageBean();
		pageBean.setPs(ps);//每页记录数
		pageBean.setCp(cp);//当前页数
		if(deptId==0){
			pageBean.setTr(applicantDao.getRowCountByLikeNameAndStatus(name, statusId));
			List<Applicant> list=applicantDao.queryForPageByLikeNameAndStatus(name, statusId, pageBean.getOffset(), ps);
			pageBean.setApplicants(list);
		}else{
			pageBean.setTr(applicantDao.getRowCountByLikeNameAndStatusAndDept(name, statusId, deptId));//总记录数
			List<Applicant> list=applicantDao.queryForPageByLikeNameAndStatusAndDept(name, statusId, deptId, pageBean.getOffset(), ps);
			pageBean.setApplicants(list);
		}
		return pageBean;
	}
	
	/**
	 * 根据被调剂申请者的 状态和上一个处理部门 来查询信息(若deptId为0 则为所有部门)(分页查询)
	 * @param statusId 被调剂申请者状态的id
	 * @param deptId 上一个处理部门id
	 * @param ps pageSize 一页显示记录数
	 * @param cp currentPage 当前第几页
	 * @return
	 */
	public PageBean queryForPageByStatusAndDeptAdjusted(int statusId,int deptId,int ps,int cp){
		PageBean pageBean=new PageBean();
		pageBean.setPs(ps);//每页记录数
		pageBean.setCp(cp);//当前页数
		if(deptId==0){
			pageBean.setTr(applicantDao.getRowCountByStatusAdjusted(statusId));
			List<Applicant> list=applicantDao.queryForPageByStatusAdjusted(statusId, pageBean.getOffset(), ps);
			pageBean.setApplicants(list);
		}else{
			pageBean.setTr(applicantDao.getRowCountByStatusAndDeptAdjusted(statusId, deptId));//总记录数
			List<Applicant> list=applicantDao.queryForPageByStatusAndDeptAdjusted(statusId, deptId, pageBean.getOffset(), ps);
			pageBean.setApplicants(list);
		}
		return pageBean;
	}
	
	/**
	 * 根据被调剂申请者的 状态和上一个处理部门   模糊姓名  来查询信息(若deptId为0 则为所有部门)(分页查询)
	 * @param name 模糊查询的 姓名
	 * @param statusId	被调剂申请者的状态 id
	 * @param deptId 上一个处理部门的 id
	 * @param ps 一页显示记录数
	 * @param cp 当前页
	 * @return
	 */
	public PageBean queryForPageByLikeNameAndStatusAndDeptAdjusted(String name,int statusId
			,int deptId,int ps,int cp){
		PageBean pageBean=new PageBean();
		pageBean.setPs(ps);//每页记录数
		pageBean.setCp(cp);//当前页数
		if(deptId==0){
			pageBean.setTr(applicantDao.getRowCountByLikeNameAndStatusAdjusted(name, statusId));
			List<Applicant> list=applicantDao.queryForPageByLikeNameAndStatusAdjusted(name, statusId, pageBean.getOffset(), ps);
			pageBean.setApplicants(list);
		}else{
			pageBean.setTr(applicantDao.getRowCountByLikeNameAndStatusAndDeptAdjusted(name, statusId, deptId));//总记录数
			List<Applicant> list=applicantDao.queryForPageByLikeNameAndStatusAndDeptAdjusted(name, statusId, deptId, pageBean.getOffset(), ps);
			pageBean.setApplicants(list);
		}
		return pageBean;
	}
	
	
	/**
	 * 根据淘汰申请者的 状态和当前处理部门 来查询信息(若deptId为0 则为所有部门)(分页查询)
	 * @param statusId 淘汰申请者状态的id
	 * @param deptId 当前处理部门id
	 * @param ps pageSize 一页显示记录数
	 * @param cp currentPage 当前第几页
	 * @return
	 */
	public PageBean queryForPageByFailAndDept(int deptId,int ps,int cp){
		PageBean pageBean=new PageBean();
		pageBean.setPs(ps);//每页记录数
		pageBean.setCp(cp);//当前页数
		if(deptId==0){
			pageBean.setTr(applicantDao.getRowCountByFail());
			List<Applicant> list=applicantDao.queryForPageByFail(pageBean.getOffset(), ps);
			pageBean.setApplicants(list);
		}else{
			pageBean.setTr(applicantDao.getRowCountByFailAndDept(deptId));//总记录数
			List<Applicant> list=applicantDao.queryForPageByFailAndDept(deptId, pageBean.getOffset(), ps);
			pageBean.setApplicants(list);;
		}
		return pageBean;
	}
	
	/**
	 * 根据淘汰申请者的 状态和当前处理部门   模糊姓名  来查询信息(若deptId为0 则为所有部门)(分页查询)
	 * @param name 模糊查询的 姓名
	 * @param statusId	淘汰申请者的状态 id
	 * @param deptId 当前处理部门的 id
	 * @param ps 一页显示记录数
	 * @param cp 当前页
	 * @return
	 */
	public PageBean queryForPageByLikeNameAndFailAndDept(String name
			,int deptId,int ps,int cp){
		PageBean pageBean=new PageBean();
		pageBean.setPs(ps);//每页记录数
		pageBean.setCp(cp);//当前页数
		if(deptId==0){
			pageBean.setTr(applicantDao.getRowCountByLikeNameAndFail(name));
			List<Applicant> list=applicantDao.queryForPageByLikeNameAndFail(name, pageBean.getOffset(), ps);
			pageBean.setApplicants(list);
		}else{
			pageBean.setTr(applicantDao.getRowCountByLikeNameAndFailAndDept(name, deptId));//总记录数
			List<Applicant> list=applicantDao.queryForPageByLikeNameAndFailAndDept(name, deptId, pageBean.getOffset(), ps);
			pageBean.setApplicants(list);
		}
		return pageBean;
	}
}
