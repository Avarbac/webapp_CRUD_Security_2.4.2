package org.example.service;

import org.example.models.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface RoleService {

    List<Role> allRoles(); // получение всех ролей

    Role findByRoleName(String role); // получение роли по имени

    Set<Role> getSetOfRoles(String[] roleNames);
}
