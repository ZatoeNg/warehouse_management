<template>
  <!-- 添加转载单对话框 -->
  <el-dialog v-model="visible" title="添加转载单" width="500px" @close="close" destroy-on-close>
    <el-form ref="transferAddForm" :model="transferAdd" :rules="rules" label-position="top">
      <el-form-item label="商品名称：" prop="productName">
        <el-input v-model="transferAdd.productName" disabled />
      </el-form-item>
      <el-form-item label="商品编号：" prop="targetProductNum">
        <el-input v-model="transferAdd.targetProductNum" />
      </el-form-item>
      <el-form-item label="库存：" prop="productInvent">
        <el-input v-model="transferAdd.productInvent" disabled />
      </el-form-item>
      <el-form-item label="转载数量：" prop="transNum">
        <el-input v-model="transferAdd.transNum"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="allTransfer">全部转载</el-button>
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="addTransfer">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { get, post, tip } from "@/common"

// 该页面的可见性
const visible = ref(false);

// 添加转载对象
const transferAdd = reactive({
  productName: '',
  targetProductNum: '',
  productInvent: '',
  transNum: '',
  sourceStoreId: '',
  sourceProductId: '',
  targetStoreId: '',
  isDisable:false
});

// 转载数量大于0，并且小于等于库存
const validateTransNum = (rule, transNum, callback) => {
  if (transNum === '') return callback(new Error('请输入转载数量！'));
  // 库存
  const productInvent = parseInt(transferAdd.productInvent);
  if(parseInt(transNum)<=0) return callback(new Error('转载数量必须大于0！'));
  if(parseInt(transNum)>productInvent) return callback(new Error('转载数量不能大于库存！'));
  return true;
}

// 表单验证规则
const rules = reactive({
  transNum: [
    { validator: validateTransNum, trigger: 'blur' }
  ]
})

// 关闭
const close = () => {
  for(let prop in transferAdd){
    transferAdd[prop] = '';
  }

  visible.value = false;
}

// 该对话框打开，进行数据初始化
const open = (commodity, targetStoreId) => {
  visible.value = true;
  transferAdd.productName = commodity.productName;
  transferAdd.targetProductNum = commodity.productNum;
  transferAdd.productInvent = commodity.productInvent;
  transferAdd.sourceStoreId = commodity.storeId;
  transferAdd.sourceProductId = commodity.productId;
  transferAdd.targetStoreId = targetStoreId;
};

const transferAddForm = ref();
// 定义
const emit = defineEmits(["ok"]);
// 添加转载单提交
const addTransfer = () => {
  transferAddForm.value.validate(valid => {
    if(valid){
      post('/transshipment/transshipment-add', transferAdd).then(result => {
        emit('ok');
        tip.success(result.message);
        visible.value = false; // 关闭对话框
      });
    }
  });
}

const allTransfer =() =>
{
  transferAdd.transNum = transferAdd.productInvent;
}

defineExpose({ open });
</script>

<style>


</style>