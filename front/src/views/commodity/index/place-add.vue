<template>
  <!-- 添加角色对话框 -->
  <el-dialog v-model="visible" title="场地添加" width="25%" @close="close" destroy-on-close>
    <el-form ref="placeAddForm" :model="placeAdd" :rules="rules" label-position="top">
      <el-form-item label="场地名称：" prop="placeName">
        <el-input v-model="placeAdd.placeName" autocomplete="off" />
      </el-form-item>
      <el-form-item label="场地编号：" prop="placeNum">
        <el-input v-model="placeAdd.placeNum" autocomplete="off" />
      </el-form-item>
      <el-form-item label="场地备注：" prop="introduce">
        <el-input v-model="placeAdd.introduce" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="addPlace">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { post, tip } from "@/common";

const visible = ref(false); // 该页面的可见性
// 添加角色对象
const placeAdd = reactive({
  placeName: '',
  placeNum: '',
  introduce: '',
});

// 关闭
const close = () => {
  placeAdd.placeName = '';
  placeAdd.placeNum = '';
  placeAdd.introduce = '';
  visible.value = false;
}

// 输入框内容的基本规则
const rules = reactive({
  placeName: [
    { required: true, message: '请输入场地名', trigger: 'blur' }
  ],
  placeNum: [
    { required: true, message: '请输入场地编号', trigger: 'blur' }
  ],
  introduce: [
    { required: true, message: '请输入备注', trigger: 'blur' }
  ]
})

// 该对话框打开
const open = () => {
  visible.value = true;
};

const placeAddForm = ref();
// 定义
const emit = defineEmits(["ok"]);
// 添加角色提交
const addPlace = () => {
  placeAddForm.value.validate(valid => {
    if(valid){
      post('/place/place-add', placeAdd).then(result => {
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