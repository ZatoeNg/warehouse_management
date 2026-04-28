[//]: # (项目启动)
# 项目启动
* redis-server 开启redis
* 开启mySQL

[//]: # (表说明)
# 表说明：database(db_warehouse)
* auth_info 权限菜单表，界面左菜单栏
* user_info 用户表:存放用户信息
* user_role 用户权限表：存放用户权限间的关系
* role 角色表：存放用户角色类型
* role_auth:角色权限中间表
* brand：品牌表
* buy_list：入库单
* in_store：入库表
* out_store：出库表
* place：产地表
* product：商品表
* product_type：分类表
* store：仓库表
* supply：供应商表
* unit：单位表
* trans_list：
* inventory：盘点表

[//]: # (后端目录说明)
# 后端目录说明
* beans：实体类
* config：配置类
* controller:控制类
* dto：多表字段合并成一个对象的类
* exception:异常处理类
* filter：自定义过滤器类
* mapper：映射数据库接口,DAO层
* service：业务层，控制mapper操作数据库，并返回数据到controller层
* utils:工具类
* resources:mapper----mapper实现
* resources:static:img:upload----图片上传下载路径

[//]: # (主要文件说明)
# 主要文件说明
* BackApplication：SpringBoot启动文件
* pom：Mvaven核心配置文件(项目依赖)
* application.properties：SpringBoot配置文件

