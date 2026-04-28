<template>
  <!-- 商品列表 -->
  <div>
    <el-form class="searchForm">
      <el-form-item>
        <el-select v-model="params.storeId" style="width: 120px;" clearable>
          <el-option v-for="store of storeList" :label="store.storeName" :value="store.storeId" :key="store.storeId"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="margin-left: 10px;">
        <el-input v-model="params.productName" placeholder="商品名称" style="width: 120px;"  clearable></el-input>
      </el-form-item>
      <el-form-item style="margin-left: 10px;">
        <el-autocomplete
          v-model="params.brandName"
          :fetch-suggestions="brandSearch"
          value-key="brandName"
          clearable
          placeholder="品牌"
          style="width: 120px;"
        />
      </el-form-item>
      <el-form-item style="margin-left: 10px;">
        <el-input v-model="params.typeName" placeholder="类型" style="width: 120px;"  clearable></el-input>
      </el-form-item>
      <el-form-item style="margin-left: 10px;">
        <el-input v-model="params.supplyName" placeholder="供应商" style="width: 120px;"  clearable></el-input>
      </el-form-item>
      <el-form-item style="margin-left: 10px;">
        <el-input v-model="params.placeName" placeholder="产地" style="width: 120px;"  clearable></el-input>
      </el-form-item>
      <el-form-item style="margin-left: 10px;">
        <el-select v-model="params.upDownState" placeholder="上/下架" style="width: 120px;" clearable>
          <el-option label="未上架" :value="0"></el-option>
          <el-option label="已上架" :value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="margin-left: 10px;">
        <el-select v-model="params.isOverDate" placeholder="已/未过期" style="width: 120px;" clearable>
          <el-option label="未过期" :value="0"></el-option>
          <el-option label="已过期" :value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="margin-left: 10px;">
        <el-button type="primary" @click="getCommodityPageList">
          <el-icon>
            <svg t="1646977561352" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3057" width="200" height="200"><path d="M986.304 871.424L747.328 630.4c-2.816-2.752-5.888-4.928-8.768-7.232 40.32-62.464 63.936-136.832 63.936-216.96 0-220.16-176.96-398.592-395.392-398.592C188.8 7.616 11.712 186.048 11.712 406.208s177.088 398.592 395.392 398.592a391.232 391.232 0 0 0 215.36-64.576c2.24 3.072 4.352 6.08 7.04 8.832l239.04 241.024a82.688 82.688 0 0 0 117.76 0 84.48 84.48 0 0 0 0-118.656m-579.2-192.512c-149.12 0-270.528-122.368-270.528-272.704 0-150.4 121.344-272.768 270.528-272.768 149.12 0 270.528 122.432 270.528 272.768 0 150.4-121.408 272.704-270.528 272.704" p-id="3058"></path></svg>
          </el-icon>
          &nbsp;查&nbsp;&nbsp;询
        </el-button>
      </el-form-item>
    </el-form>
  </div>

  <!-- 表格 -->
  <el-table ref="multipleTableRef" :data="commodityPageList" @selection-change="handleSelectionChange" style="width: 100%; margin-top: 10px;" table-layout="auto" size="large" border stripe>
    <el-table-column type="selection" width="55" />
    <el-table-column label="商品图片">
      <template #default="props">
          <el-image style="width: 60px; height: 60px" :src="WAREHOUSE_CONTEXT_PATH + props.row.imgs" fit="fill" />
      </template>
    </el-table-column>
    <el-table-column prop="productName" label="商品名称" sortable />
    <el-table-column prop="brandName" label="品牌" sortable />
    <el-table-column prop="typeName" label="类型" sortable />
    <el-table-column prop="supplyName" label="供应商" sortable />
    <el-table-column prop="placeName" label="产地" sortable />
    <el-table-column prop="storeName" label="仓库" sortable />
    <el-table-column prop="productInvent" label="库存" sortable />
    <el-table-column label="上/下架" sortable>
      <template #default="props">
          <span>{{props.row.upDownState==0?"未上架":"已上架"}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="salePrice" label="售价" sortable />
    <el-table-column prop="unitName" label="单位" sortable/>
    <el-table-column prop="productDate" label="生产日期" sortable />
    <el-table-column prop="suppDate" label="保质期" sortable />
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

</template>

<script setup>
import {reactive, ref} from 'vue';
import {get, WAREHOUSE_CONTEXT_PATH} from "@/common";
import {useRouter} from "vue-router";

const router = useRouter(); // 获取路由器

// 分页模糊查询数据
const params = reactive({
  storeId: null,
  productName: '',
  brandName: '',
  typeName: '',
  supplyName: '',
  placeName: '',
  upDownState: '',
  isOverDate: '',
  pageSize: 5,
  pageNum: 1,
  totalNum: 0
})

// 表格数据
const commodityPageList = ref();

// 获取分页模糊查询结果
const getCommodityPageList = () => {
  get("/product/product-page-list", params).then(result => {
    commodityPageList.value = result.data.resultList;
    params.totalNum = result.data.totalNum;
  });
}
getCommodityPageList();

// 所有仓库
const storeList = ref();
// 获取所有仓库
const getStoreList = () => {
  get("/product/store-list").then(result => {
    storeList.value = result.data;
  });
}
getStoreList();

// 所有品牌
const brandList = ref();
// 获取所有品牌
const getBrandList = () => {
  get("/product/brand-list").then(result => {
    brandList.value = result.data;
  });
}
getBrandList();

// 所有分类-分层结构
const categoryTree = ref();
// 获取所有分类
const getCategoryTree = () => {
  get("/product/category-tree").then(result => {
    categoryTree.value = result.data;
  });
}
getCategoryTree();


// 品牌模糊查询框的联想输入
const brandSearch = (queryString, callback) => {
  const results = queryString
      ? brandList.value.filter(createFilter(queryString))
      : brandList.value
  // 回调方法，返回联想的数据
  callback(results);
}

const createFilter = (queryString) => {
  return (brand) => {
    return brand.brandName.indexOf(queryString) != -1;
  }
}

// 修改每页显示条数
const changeSize = (size) => {
  params.pageSize = size;
  // 重新查询
  getCommodityPageList();
}
// 修改当前页码
const changeCurrent = (num) => {
  params.pageNum = num;
  // 重新查询
  getCommodityPageList();
}
</script>

<style scoped>
.searchForm {
  margin-top: 20px;
  display: flex;
}

.el-link {
  margin-right: 8px;
}
</style>