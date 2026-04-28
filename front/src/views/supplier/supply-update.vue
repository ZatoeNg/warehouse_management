<template>
  <!-- 修改采购单对话框 -->
  <el-dialog v-model="visible" title="修改供应商信息" width="400px" @close="close" destroy-on-close>
    <el-form ref="supplyUpdateForm" :model="supplyUpdate" label-position="top" label-width="120px">
      <el-form-item label="供应商编号：" prop="supplyNum">
        <el-input v-model="supplyUpdate.supplyNum" disabled />
      </el-form-item>
      <el-form-item label="供应商名称：" prop="supplyName">
        <el-input v-model="supplyUpdate.supplyName" />
      </el-form-item>
      <el-form-item label="备注：" prop="supplyIntroduce">
        <el-input v-model="supplyUpdate.supplyIntroduce" />
      </el-form-item>
      <el-form-item label="联系人名：" prop="concat">
        <el-input v-model="supplyUpdate.concat" controls-position="right" />
      </el-form-item>
      <el-form-item label="联系方式：" prop="phone">
        <el-input v-model="supplyUpdate.phone" controls-position="right" />
      </el-form-item>
      <el-form-item label="供应商地址：" prop="address">
        <el-input v-model="supplyUpdate.address" controls-position="right" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="updateSupply">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { put, tip } from "@/common"

// 该页面的可见性
const visible = ref(false);

// 添加供应商对象
const supplyUpdate = reactive({
  supplyNum:'',
  supplyName: '',
  supplyIntroduce:'',
  concat:'',
  phone:'',
  address:''
});


// 关闭
const close = () => {
  visible.value = false;
}

// 该对话框打开，进行数据初始化
const open = (supply) => {
  for(let prop in supply){
    supplyUpdate[prop] = supply[prop];
  }
  visible.value = true;
};

const supplyUpdateForm = ref();
// 定义
const emit = defineEmits(["ok"]);

// 添加供应商提交
const updateSupply = () => {
  supplyUpdateForm.value.validate((valid) => {
    if(valid){
      put('/supplier/supplier-update', supplyUpdate).then(result => {
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