package com.scb.jdbc.dao;

import java.util.List;

import com.scb.jdbc.bean.EmployeeBean;

public interface EmployeeDAO {
   public int insert(EmployeeBean emp);
   public int update(int id,EmployeeBean emp);
   public int delete(int id);
   public List<EmployeeBean> findAll();
   public EmployeeBean findById(int id);
}
