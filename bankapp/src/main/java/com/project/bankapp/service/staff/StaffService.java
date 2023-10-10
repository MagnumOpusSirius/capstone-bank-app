package com.project.bankapp.service.staff;

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
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

}
