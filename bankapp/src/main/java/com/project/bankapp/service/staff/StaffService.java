package com.project.bankapp.service.staff;

import com.project.bankapp.dto.response.StaffResponse;
import com.project.bankapp.dto.response.Status;
import com.project.bankapp.model.ERole;
import com.project.bankapp.model.Role;
import com.project.bankapp.model.Staff;
import com.project.bankapp.model.User;
import com.project.bankapp.repository.RoleRepository;
import com.project.bankapp.repository.StaffRepository;
import com.project.bankapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public boolean existsByUsername(String staffUserName) {
        return staffRepository.existsByStaffUserName(staffUserName);
    }

    @Transactional
    public Staff saveStaff(Staff staff) {
        // Step 1: Save staff data to STAFF_TBL
        staff.setStatus(Status.DISABLE);
        Staff savedStaff = staffRepository.save(staff);

        // Step 2: Create a user in USERS_TBL using the staff's username and password
        User user = new User(staff.getStaffUserName(), staff.getStaffPassword());

        // Step 3: Set the user's role to STAFF
        Optional<Role> staffRole = roleRepository.findByName(ERole.STAFF);
        Set<Role> roles = new HashSet<>();
        staffRole.ifPresent(roles::add);
        user.setRoles(roles);

        // Step 4: Save the user to USERS_TBL
        userRepository.save(user);

        // Step 5: Return the saved staff
        return savedStaff;
    }

    public List<StaffResponse> getAllStaff(){
        List<Staff> staffList = staffRepository.findAll();
        List<StaffResponse> staffResponses = new ArrayList<>();

        for(Staff staff: staffList){
            StaffResponse staffResponse = new StaffResponse();
            staffResponse.setStaffId(staff.getStaffId());
            staffResponse.setStaffUserName(staff.getStaffUserName());
            staffResponse.setStatus(staff.getStatus());

            staffResponses.add(staffResponse);
        }
        return staffResponses;
    }

    public boolean updateStaffStatus(Long staffId, Status status){
        Optional<Staff> staffOptional = staffRepository.findById(staffId);

        if(staffOptional.isPresent()){
            Staff staff = staffOptional.get();
            staff.setStatus(status);
            staffRepository.save(staff);
            return true;
        }else {
            return false; //staff not found!
        }
    }

}
