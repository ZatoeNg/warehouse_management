<template>
  <!-- 添加角色对话框 -->
  <el-dialog v-model="visible" title="品牌添加" width="25%" @close="close" destroy-on-close>
    <el-form ref="brandAddForm" :model="brandAdd" :rules="rules" label-position="top">
      <el-form-item label="品牌名称：" prop="brandName">
        <el-input v-model="brandAdd.brandName" autocomplete="off" />
      </el-form-item>
      <el-form-item label="品牌符号：" prop="brandLeter">
        <el-input v-model="brandAdd.brandLeter" autocomplete="off" maxlength="1"/>
      </el-form-item>
      <el-form-item label="品牌备注：" prop="brandDesc">
        <el-input v-model="brandAdd.brandDesc" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="addBrand">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { post, tip } from "@/common";

const visible = ref(false); // 该页面的可见性
// 添加角色对象
const brandAdd = reactive({
  brandName: '',
  brandLeter: '',
  brandDesc: '',
});

// 关闭
const close = () => {
  brandAdd.brandName = '';
  brandAdd.brandLeter = '';
  brandAdd.brandDesc = '';
  visible.value = false;
}

// 输入框内容的基本规则
const rules = reactive({
  brandName: [
    { required: true, message: '请输入品牌名', trigger: 'blur'}
  ],
  brandLeter: [
    { required: true, message: '请输入品牌符号', trigger: 'blur'}
  ],
  brandDesc: [
    { required: true, message: '请输入品牌描述', trigger: 'blur' }
  ]
})

// 该对话框打开
const open = () => {
  visible.value = true;
};

const brandAddForm = ref();
// 定义
const emit = defineEmits(["ok"]);
// 添加角色提交
const addBrand = () => {
  brandAddForm.value.validate(valid => {
    if(valid){
      post('/brand/brand-add', brandAdd).then(result => {
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