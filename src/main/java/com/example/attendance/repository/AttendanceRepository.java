package com.example.attendance.repository;

import com.example.attendance.entity.Attendance;
import com.example.attendance.entity.Employee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEmployee(Employee employee);

    @Query("SELECT a.employee.department, COUNT(a) FROM Attendance a WHERE a.status = 'Present' GROUP BY a.employee.department")
    List<Object[]> getDepartmentWiseSummary();
}
