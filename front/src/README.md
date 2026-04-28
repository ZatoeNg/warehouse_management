[//]: # (运行说明)
* 默认使用npm也可以
* npm install -g yarn
* yarn install
* 以下操作用于无法安装的情况
* 删除package-lock.json
* npm cache clean --force
* 官方源：npm config set registry https://registry.npmjs.org/
* 换源：npm config set registry https://registry.npmmirror.com/


[//]: # (目录说明)
# 目录说明
## views组件目录
* index 首页图片
* auth 权限树
* commodity 商品
* instore 入库
* outstore 出库
* purchase 采购
* role 角色
* statistice:charts 图表
* store 仓库
* transshipment 转载
* user 用户

## common 目录
* ddo 数据字典
* excel
* request 请求池
* router 路由
* sureLoading 加载
* tip 信息
* constants 常量定义
* 格式化date
* debounce 防抖和节流
* index common统一对外接口
* token 本地存储