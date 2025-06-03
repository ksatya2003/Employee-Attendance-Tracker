package com.example.attendance.controller;

import com.example.attendance.entity.Attendance;
import com.example.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired private AttendanceService attendanceService;

    @PostMapping("/mark")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Attendance> mark(@RequestParam Long employeeId, @RequestParam String status) {
        return ResponseEntity.ok(attendanceService.markAttendance(employeeId, status));
    }

    @GetMapping("/employee/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Attendance>> getByEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(attendanceService.getEmployeeAttendance(id));
    }

    @GetMapping("/summary")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Object[]>> getSummary() {
        return ResponseEntity.ok(attendanceService.getDepartmentWiseSummary());
    }
}

