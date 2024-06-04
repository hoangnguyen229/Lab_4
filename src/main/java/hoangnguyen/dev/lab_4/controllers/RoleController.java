package hoangnguyen.dev.lab_4.controllers;

import hoangnguyen.dev.lab_4.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;


}
