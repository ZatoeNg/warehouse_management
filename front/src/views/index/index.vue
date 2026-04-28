<template>
  <div style="text-align:center; background-color:#f1f1f3; height:100%; padding:0px; margin:0px;">
    <h1 style="font-size: 50px; display: inline">欢迎你!&nbsp;</h1>
    <h1 style="font-size: 50px; display: inline" v-text="currUser.userName"></h1>
    <el-descriptions title="个人中心" :column="2" size="40" border>
      <el-descriptions-item>
        <template #label>
          名称
        </template>
        {{ `${currUser.userName}` }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          账号
        </template>
        {{ `${currUser.userCode}` }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          角色
        </template>
        <el-tag
            type="success"
            disable-transitions>{{ UserTypes == 0 ? "超级管理员" : (UserTypes == 2 ? "管理员" : "用户") }}
        </el-tag>
      </el-descriptions-item>
      <el-descriptions-item v-if="currUser.userCode!='anonymous'">
        <template #label>
          操作
        </template>
        <el-link type="primary" @click="openUpdateData(currUser)">修改信息</el-link>
        <el-link type="primary" @click="openUpdatePwd(currUser)">修改密码</el-link>
      </el-descriptions-item>
    </el-descriptions>

    <date-utils></date-utils>
  </div>

  <update-data ref="updateDataRef" @ok="getCurrentUser"></update-data>
  <update-pwd ref="updatePwdRef"></update-pwd>


</template>

<script setup>
import DateUtils from "@/views/index/DateUtils.vue";
import {ref} from "vue";
import {get} from "@/common";


// 获取当前登录用户
const currUser = ref({});
const getCurrentUser = () => {
  get("/curr-user").then(result => {
    currUser.value = result.data;
    getUserTypes(currUser.value.userName);
  });
}
getCurrentUser();

let UserTypes = ref({});
const getUserTypes = (currUsers) => {
  if (currUsers == "超级管理员") UserTypes.value = 0;
  else if (currUsers == "匿名用户") UserTypes.value = 1;
  else if (currUsers.substring(currUsers.length - 3) == "管理员") UserTypes.value = 2;
  else UserTypes.value = 3;
}


// 跳向修改
import updateData from "@/views/index/update-user-data.vue";
const updateDataRef = ref();
const openUpdateData = (value) => {
  updateDataRef.value.open(value);
};

// 点击修改密码
import UpdatePwd from "@/views/index/update-user-pwd.vue";
const updatePwdRef = ref();
const openUpdatePwd = (row) => {
  updatePwdRef.value.open(row);
}


</script>

<style scoped>
.el-descriptions {
  width: 90%;
  margin: 0 auto;
  text-align: center;
}

.el-link {
  margin-right: 8px;
}
</style>