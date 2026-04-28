<template>
  <!-- 修改盘点对话框 -->
  <el-dialog v-model="visible" title="修改盘点单" width="400px" @close="close" destroy-on-close>
    <el-form ref="inventoryUpdateForm" :model="inventoryUpdate" :rules="rules" label-position="top" label-width="120px">
      <el-form-item label="盘点号：" prop="inventoryId">
        <el-input v-model="inventoryUpdate.inventoryId" disabled />
      </el-form-item>
      <el-form-item label="仓库：" prop="storeName">
        <el-input v-model="inventoryUpdate.storeName" disabled />
      </el-form-item>
      <el-form-item label="商品名称：" prop="productName">
        <el-input v-model="inventoryUpdate.productName" disabled />
      </el-form-item>
      <el-form-item label="库存数量：" prop="sourceNum">
        <el-input v-model="inventoryUpdate.sourceNum" controls-position="right" disabled/>
      </el-form-item>
      <el-form-item label="实际库存数量：" prop="inventoryNum">
        <el-input v-model="inventoryUpdate.inventoryNum" controls-position="right" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="updateInventory">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { put, tip } from "@/common"

// 该页面的可见性
const visible = ref(false);

// 添加采购单对象
const inventoryUpdate = reactive({
  storeId:null,
  inventoryId: null,
  storeName: '',
  productName: '',
  sourceNum: '',
  inventoryNum: ''
});

// 表单验证规则
const rules = reactive({
  inventoryNum: [
    { required: true, message: '请输入实际库存数量', trigger: 'blur' }
  ]
})

// 关闭
const close = () => {
  visible.value = false;
}

// 该对话框打开，进行数据初始化
const open = (inv) => {
  console.log(inv)
  for(let prop in inv){
    inventoryUpdate[prop] = inv[prop];
  }
  console.log(inventoryUpdate)
  visible.value = true;
};

const inventoryUpdateForm = ref();
// 定义
const emit = defineEmits(["ok"]);
// 添加盘点单提交
const updateInventory = () => {
  inventoryUpdateForm.value.validate((valid) => {
    if(valid){
      put('/inventory/inventory-update', inventoryUpdate).then(result => {
        emit('ok');
        tip.success(result.message);
        visible.value = false; // 关闭对话框
      });
    }
  });
}

defineExpose({ open });
</script>

<style>


</style>