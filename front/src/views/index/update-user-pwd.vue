<template>
  <!-- 修改用户对话框 -->
  <el-dialog v-model="visible" title="修改用户密码" width="25%">
    <el-form ref="userUpdateForm" :model="userPwdUpdate"  :rules="rules" label-position="top">

      <el-form-item label="用户名：">
        <el-input v-model="userPwdUpdate.userCode" autocomplete="off" disabled />
      </el-form-item>

      <el-form-item label="密码：" prop="userPwd">
        <el-input type="password" show-password v-model="userPwdUpdate.userPwd" autocomplete="off" />
      </el-form-item>

      <el-form-item label="确认密码：" prop="confirmPassword">
        <el-input type="password" show-password v-model="userPwdUpdate.confirmPassword" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="visible=false">取消</el-button>
        <el-button type="primary" @click="updateUser">确认修改</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { put, tip } from "@/common";

const visible = ref(false); // 该页面的可见性
// 修改用户对象
const userPwdUpdate = reactive({
  userCode:'',
  userId: '',
  userPwd: '',
  confirmPassword: ''
});


// 该对话框打开并初始化
const open = (user) => {
  userPwdUpdate.userCode = user.userCode;
  userPwdUpdate.userId = user.userId;
  visible.value = true;
};

const close = () => {
  userPwdUpdate.userCode = '';
  userPwdUpdate.userId = '';
  userPwdUpdate.userPwd = '';
  userPwdUpdate.confirmPassword = '';
  visible.value = false;
}


const rules = reactive({
  userPwd: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码为6-20位', trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, message: '请再次输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码为6-20位', trigger: 'blur' }
  ]
})

const userUpdateForm = ref();
// 定义
const emit = defineEmits(["ok"]);


// 修改用户提交
const updateUser = () => {
  if(userPwdUpdate.userPwd!==userPwdUpdate.confirmPassword){
    tip.error("两次密码必须保持一致！");
    return;
  }
  userUpdateForm.value.validate(valid => {
    if (valid) {
      put('/user/updateUserPwd', userPwdUpdate).then(result => {
        emit('ok');
        tip.success(result.message);
        visible.value = false; // 关闭对话框
      });
    }
  });
}



defineExpose({open});
</script>
<style scoped>

</style>