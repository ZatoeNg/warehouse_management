package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//接收角色分配权限的DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignAuthDto {
    private Integer roleId;

    private List<Integer> authIds;
}
