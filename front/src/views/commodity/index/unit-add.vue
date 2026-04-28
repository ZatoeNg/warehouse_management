<template>
  <!-- 添加角色对话框 -->
  <el-dialog v-model="visible" title="单位添加" width="25%" @close="close" destroy-on-close>
    <el-form ref="unitAddForm" :model="unitAdd" :rules="rules" label-position="top">
      <el-form-item label="单位名称：" prop="unitName">
        <el-input v-model="unitAdd.unitName" autocomplete="off" />
      </el-form-item>
      <el-form-item label="单位描述：" prop="unitDesc">
        <el-input v-model="unitAdd.unitDesc" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="addUnit">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { post, tip } from "@/common";

const visible = ref(false); // 该页面的可见性
// 添加角色对象
const unitAdd = reactive({
  unitName: '',
  unitDesc: '',
});

// 关闭
const close = () => {
  unitAdd.unitName = '';
  unitAdd.unitDesc = '';
  visible.value = false;
}

// 输入框内容的基本规则
const rules = reactive({
  unitName: [
    { required: true, message: '请输入单位名', trigger: 'blur' }
  ],
  unitDesc: [
    { required: true, message: '请输入备注', trigger: 'blur' }
  ]
})

// 该对话框打开
const open = () => {
  visible.value = true;
};

const unitAddForm = ref();
// 定义
const emit = defineEmits(["ok"]);
// 添加角色提交
const addUnit = () => {
  unitAddForm.value.validate(valid => {
    if(valid){
      post('/unit/unit-add', unitAdd).then(result => {
        emit('ok');
        tip.success(result.message);
        visible.value = false; // 关闭对话框
      });
    }
  });
}

defineExpose({ open });
</script>
<style scoped>

</style>