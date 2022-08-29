/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EmployeeAndList;

import ValidateAndIFile.XFile;
import java.io.File;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tltkh
 */
public class EmployeeList extends Employee{
    public ArrayList<Employee> list = new ArrayList<>();
    private String path = "D:\\data\\data.txt";
    public void saveToFile() throws Exception{
        XFile.writeObject(path, list);
    }
    
    // load file
    public void loadFromFile() throws Exception{
        File file = new File(path);
        
        if(file.exists()){
            list = (ArrayList<Employee>) XFile.readObject(path);
        }else{
            initEmployeeData();
        }
        
    }
    
    
    // create DataEmployee
     private void initEmployeeData(){
        list.add(new Employee("1","Phong","Phong@gmail.com",1000, 20));
        list.add(new Employee("2","Khanh","Khanh@gmail.com",6000, 20));
        list.add(new Employee("3","Hieu","Hieu@gmail.com",2000, 20));
    }
    
    private int currentIndex = 0;
    
    public String getCurrentEmployeeInfo(){
        return "Record" +" "+ (currentIndex +1) +" "+ "of" +" "+ list.size();
    }
    
    public void setCurrentEmployee(Employee emp){
        currentIndex = list.indexOf(emp);
    }
    
    public void first(){
        if(currentIndex >0){
            currentIndex = 0;
        }
    }
    public void previous(){
       if(currentIndex >0){
            currentIndex--;
        } 
    }
    public void next(){
        if(currentIndex < list.size() -1){
            currentIndex++;
        }
    }
    public void last(){
        if(currentIndex < list.size() -1){
            currentIndex = list.size() -1;
        }
    }
    public int getCurrentEmployeeIndex(){
        return currentIndex;
    }
    public Employee getCurrentEmployee(){
        if(list.size()==0)
            return null;
        return list.get(currentIndex);
    }
    
    public void add(Employee emp){
        list.add(emp);
    }
    
    public boolean update(Employee emp){
        Employee existedEmp = findByID(emp.getEmployeeID());
        boolean flag = false;
        if(existedEmp != null){
            existedEmp.setName(emp.getName());
            existedEmp.setAge(emp.getAge());
            existedEmp.setEmail(emp.getEmail());
            existedEmp.setSalary(emp.getSalary());
            
            return true;
        }
        return flag;
    }
    
    public void renderToTable(DefaultTableModel tblModel){
        tblModel.setRowCount(0);
        
        for(Employee employee : list){
            Object[] row = new Object[]{
                employee.getEmployeeID(), employee.getName(),
                employee.getAge(), employee.getEmail(), employee.getSalary()
            };
            tblModel.addRow(row);
        }
        tblModel.fireTableDataChanged();
    }
    
    // find by ID
    public Employee findByID(String employeeId){
        for(Employee employee : list){
            if(employee.getEmployeeID().equals(employeeId)){
                return employee;
            }  
        }
        return null;
    }
    
    //find by Name
    public Employee findByName(String employeeName){
        for(Employee employee : list){
            if(employee.getName().equals(employeeName)){
                return employee;
            }
        }
        return null;
    }
    
    // delete by Id
    public boolean deleteById(String employeeId){
        for(Employee employee : list){
            if(employee.getEmployeeID().equals(employeeId)){
                list.remove(employee);
                return true;
            } 
        }
        return false;
    }
}
