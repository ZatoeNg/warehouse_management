/**
 * 路由
 */

import { createRouter, createWebHistory } from 'vue-router'
import { default as routes, existsRoute } from './routes';
import { BASE_URL, LOGIN_ROUTE_NAME } from '@/common/constants';
import {getLocalToken} from "@/common";

const router = createRouter({
	history: createWebHistory(BASE_URL),
	routes
});

// 白名单
const whiteList = [LOGIN_ROUTE_NAME,"Register"];

// 全局前置导航守卫
router.beforeEach(async (to, from) => {
	if(!existsRoute(to.path)){
		return {name:'NotFound',query:{toPath:to.path}}
	}

	return true;
})

export default router;