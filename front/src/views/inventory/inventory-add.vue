<template>
  <!-- 添加采购单对话框 -->
  <el-dialog v-model="visible" title="添加盘点单" width="600px" @close="close" destroy-on-close>
    <el-form ref="inventoryAddForm" :model="inventoryAdd" label-position="right" label-width="120px">
      <el-form-item label="商品：" prop="productName">
        <span>{{ inventoryAdd.productName }}</span>
      </el-form-item>
      <el-form-item label="仓库：" prop="storeName">
        <span>{{ inventoryAdd.storeName }}</span>
      </el-form-item>
      <el-form-item label="供货商：" prop="supplyName">
        <span>{{ inventoryAdd.supplyName }}</span>
      </el-form-item>
      <el-form-item label="产地：" prop="placeName">
        <span>{{ inventoryAdd.placeName }}</span>
      </el-form-item>
      <el-form-item label="库存数量：" prop="sourceNum">
        <span>{{ inventoryAdd.sourceNum }}</span>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="addInventory">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { post, tip } from "@/common"

// 该页面的可见性
const visible = ref(false);

// 添加采购单对象
const inventoryAdd = reactive({
  productId: '',
  productName: '',
  placeName:'',
  storeId: '',
  storeName: '',
  sourceNum: '',
  productInvent:null,
  createBy: '',
  supplyName:''
});



// 关闭
const close = () => {
  for(let prop in inventoryAdd){
    inventoryAdd[prop] = '';
  }
  
  visible.value = false;
}

// 该对话框打开，进行数据初始化
const open = (commodity) => {
  visible.value = true;
  for(let prop in commodity){
    inventoryAdd[prop] = commodity[prop];
  }
  inventoryAdd.sourceNum=commodity.productInvent;
};

const inventoryAddForm = ref();
// 定义
const emit = defineEmits(["ok"]);
// 添加采购单提交
const addInventory = () => {
  inventoryAddForm.value.validate((valid) => {
    if(valid){
      post('/inventory/inventory-add', inventoryAdd).then(result => {
        emit('ok', inventoryAdd.storeId);
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