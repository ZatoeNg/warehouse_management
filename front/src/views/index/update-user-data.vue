<template>
  <!-- 添加信息修改对话框 -->
  <el-dialog v-model="visible" title="信息修改" width="25%" @close="close" destroy-on-close>
    <el-form ref="userUpdateForm" :model="userUpdate" label-position="top">
      <el-form-item label="账号：" prop="userCode">
        <el-input v-model="userUpdate.userCode" autocomplete="off" disabled/>
      </el-form-item>
      <el-form-item label="名称：" prop="userName">
        <el-input v-model="userUpdate.userName" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="confirmUser">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import {ref, reactive} from 'vue'
import {post, put, tip} from "@/common";

const visible = ref(false); // 该页面的可见性
// 添加角色对象
const userUpdate = reactive({
  userName: '',
  userCode: '',
});

// 关闭
const close = () => {
  userUpdate.userName = '';
  userUpdate.userCode = '';
  visible.value = false;
}


// 该对话框打开
const open = (value) => {
  visible.value = true;

  for(let val in value)userUpdate[val] = value[val];

};

const userUpdateForm = ref();
// 定义
const emit = defineEmits(["ok"]);
// 信息提交
const confirmUser = () => {
  userUpdateForm.value.validate(valid => {
    if(valid){
      put('user/updateUserData', userUpdate).then(result => {
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