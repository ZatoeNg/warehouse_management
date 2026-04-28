package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//接收分配角色请求Json数据的Dto类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignRoleDto {
    private Integer userId;

    private List<String> roleCheckList;
}
