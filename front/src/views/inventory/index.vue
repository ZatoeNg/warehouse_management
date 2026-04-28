<template>
  <!-- 盘点列表 -->
  <div>
    <el-form inline class="searchForm">
      <el-form-item>
        <el-select v-model="params.storeId" style="width: 120px;" clearable>
          <el-option v-for="store of storeList" :label="store.storeName" :value="store.storeId"
                     :key="store.storeId"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-input v-model="params.productName" placeholder="商品名称" style="width: 120px;" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="params.inventoryState" style="width: 130px;" clearable>
          <el-option label="未盘点" :value="0"></el-option>
          <el-option label="盘亏" :value="1"></el-option>
          <el-option label="盘盈" :value="2"></el-option>
          <el-option label="正常" :value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getInventoryPageList">
          <el-icon>
            <svg t="1646977561352" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
                 p-id="3057" width="200" height="200">
              <path
                  d="M986.304 871.424L747.328 630.4c-2.816-2.752-5.888-4.928-8.768-7.232 40.32-62.464 63.936-136.832 63.936-216.96 0-220.16-176.96-398.592-395.392-398.592C188.8 7.616 11.712 186.048 11.712 406.208s177.088 398.592 395.392 398.592a391.232 391.232 0 0 0 215.36-64.576c2.24 3.072 4.352 6.08 7.04 8.832l239.04 241.024a82.688 82.688 0 0 0 117.76 0 84.48 84.48 0 0 0 0-118.656m-579.2-192.512c-149.12 0-270.528-122.368-270.528-272.704 0-150.4 121.344-272.768 270.528-272.768 149.12 0 270.528 122.432 270.528 272.768 0 150.4-121.408 272.704-270.528 272.704"
                  p-id="3058"></path>
            </svg>
          </el-icon>
          &nbsp;查&nbsp;&nbsp;询
        </el-button>
      </el-form-item>
    </el-form>
  </div>

  <!-- 表格 -->
  <el-table :data="inventoryPageList" style="width: 100%;margin-top: 10px;" table-layout="auto" size="large" border
            stripe>
    <el-table-column prop="inventoryId" label="ID" sortable/>
    <el-table-column prop="storeName" label="仓库名" sortable/>
    <el-table-column prop="productName" label="商品名" sortable/>
    <el-table-column prop="sourceNum" label="库存数量" sortable/>
    <el-table-column prop="inventoryNum" label="实际数量" sortable/>
    <el-table-column prop="userCode" label="盘点人" sortable/>
    <el-table-column prop="inventoryTime" label="盘点时间" sortable/>
    <el-table-column label="盘点状态" sortable>
      <template #default="props">
        <span
            :class="{red:(props.row.inventoryState==0 || props.row.inventoryState==1), green: (props.row.inventoryState==2|| props.row.inventoryState==3)}">{{ props.row.inventoryState == 0 ? "未盘点" : (props.row.inventoryState == 1 ? "盘亏" : props.row.inventoryState == 2?"盘盈":"正常") }}</span>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template #default="props">
        <el-button v-if="!props.row.inventoryNum || props.row.inventoryNum==0" type="primary" title="修改盘点单"
                   :icon="Edit" circle @click="openInventoryUpdate(props.row)"/>
        <el-button v-if="!props.row.inventoryNum || props.row.inventoryNum==0" type="danger" title="删除盘点单"
                   :icon="Delete" circle @click="deleteInventory(props.row.inventoryId)"/>
        <el-button v-if="props.row.inventoryState==0 && props.row.inventoryNum>0" type="primary"
                   @click="confirmInventory(props.row)">确认盘点
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <!-- 分页 -->
  <el-pagination
      background
      :total="params.totalNum"
      :page-sizes="[5, 10, 15, 20, 25, 30]"
      v-model:page-size="params.pageSize"
      v-model:currentPage="params.pageNum"
      layout="total, sizes, prev, pager, next, jumper"
      style="margin-top: 20px;"
      @size-change="changeSize"
      @current-change="changeCurrent"
  />

  <!-- 修改盘点单对话框 -->
  <inventory-update ref="inventoryUpdateRef" @ok="getInventoryPageList"></inventory-update>

</template>

<script setup>
import {reactive, ref} from 'vue';
import {useRoute, useRouter} from 'vue-router'
import {get, post, del, tip, export2excel} from "@/common";
import {Search, Edit, Check, Message, Star, Delete} from '@element-plus/icons-vue'

const route = useRoute(); // 获取路由信息

// 分页模糊查询数据
const params = reactive({
  storeId: null,
  productName: '',
  inventoryState: '',
  pageSize: 5,
  pageNum: 1,
  totalNum: 0
})

// 表格数据
const inventoryPageList = ref();
// 获取分页模糊查询结果
const getInventoryPageList = () => {
  if (route.query.storeId) {
    params.storeId = parseInt(route.query.storeId);
  }
  // 后台获取查询结果
  get("/inventory/inventory-page-list", params).then(result => {
    inventoryPageList.value = result.data.resultList;
    params.totalNum = result.data.totalNum;
  });
}
getInventoryPageList();

// 所有仓库
const storeList = ref();
// 获取所有仓库
const getStoreList = () => {
  get("/purchase/store-list").then(result => {
    storeList.value = result.data;
  });
}
getStoreList();


// 跳向修改盘点
import InventoryUpdate from "./inventory-update.vue";
const inventoryUpdateRef = ref();
const openInventoryUpdate = (values) => {
  inventoryUpdateRef.value.open(values);
};

// 删除盘点单
const deleteInventory = (inventoryId) => {
  del(`/inventory/inventory-delete/${inventoryId}`, null, {
    title: "提示",
    message: "您确定删除该盘点单吗？"
  }).then(result => {
    tip.success(result.message);
    getInventoryPageList();
  });
}


// 修改每页显示条数
const changeSize = (size) => {
  params.pageSize = size;
  // 重新查询
  getInventoryPageList();
}
// 修改当前页码
const changeCurrent = (num) => {
  params.pageNum = num;
  // 重新查询
  getInventoryPageList();
}
</script>

<style scoped>
.searchForm {
  margin-top: 20px;
  display: flex;
}

.red {
  color: red;
}

.green {
  color: green;
}
</style>