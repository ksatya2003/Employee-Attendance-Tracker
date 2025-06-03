package com.example.attendance.service;

import com.example.attendance.entity.*;
import com.example.attendance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired private EmployeeRepository employeeRepo;
    @Autowired private AttendanceRepository attendanceRepo;

    public Attendance markAttendance(Long employeeId, String status) {
        Employee emp = employeeRepo.findById(employeeId).orElseThrow();
        Attendance att = new Attendance();
        att.setEmployee(emp);
        att.setDate(LocalDate.now());
        att.setStatus(status);
        return attendanceRepo.save(att);
    }

    public List<Attendance> getEmployeeAttendance(Long employeeId) {
        Employee emp = employeeRepo.findById(employeeId).orElseThrow();
        return attendanceRepo.findByEmployee(emp);
    }

    public List<Object[]> getDepartmentWiseSummary() {
        return attendanceRepo.getDepartmentWiseSummary();
    }
}
