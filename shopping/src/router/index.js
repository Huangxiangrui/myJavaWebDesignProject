import { createRouter, createWebHashHistory } from 'vue-router'
import LoginView from '@/views/login/loginView.vue'
import RegisterView from '@/views/register/RegisterView.vue'
import adminHomeView from '@/views/Home/adminHomeView.vue'
import userHomeView from '@/views/Home/userHomeView.vue'
import shoppingView from '@/views/user/shoppingView.vue'
import applyView from '@/views/user/applyBsView.vue'
import shopcarView from '@/views/user/shopcarView.vue'
import orderView from '@/views/user/orderView.vue'
import bshomeView from '@/views/Home/bussinessHome'
import infoView from '@/views/Bussiness/InfoView.vue'
import prodView from '@/views/Bussiness/prodView'
import BsorderView from '@/views/Bussiness/orderVew.vue'
const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: LoginView

  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/register',
    component: RegisterView
  },
  {
    path: '/adminhome',
    component: adminHomeView,
    meta: {
      requireAuth: true    //为true时 需要判断路由
    },
  },
  {
    path: '/userhome',
    component: userHomeView,
    meta: {
      requireAuth: true    //为true时 需要判断路由
    },
    children: [
      {
        path: '/userhome/shopping',
        component: shoppingView
      },
      {
        path: '/userhome/apply',
        component: applyView
      },
      {
        path: '/userhome/shopcar',
        component: shopcarView
      },
      {
        path: '/userhome/order',
        component: orderView
      }
    ]
  },
  {
    path: '/bussinessHome',
    component: bshomeView,
    meta: {
      requireAuth: true    //为true时 需要判断路由
    },
    children: [
      {
        path: '/bussinessHome/prod',
        component: prodView
      },
      {
        path: '/bussinessHome/order',
        component: BsorderView
      },
      {
        path: '/bussinessHome/info',
        component: infoView
      },
    ]
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    //判断该路由是否需要登录权限
    //前面 meta:{ requireAuth:true },
    let loginInfo = null; //存储当前身份存储的session信息
    var end = to.path.indexOf('/', 1);
    if (end < 0) {
      end = to.path.length;
    }
    let identity = to.path.substring(1, end); //获取当前访问的路由路径例如：'/adminhome'->'admin' '/userhome/shopping'->'userhome'
    // console.log(end);
    // console.log(identity);
    //判断当前身份是admin还是user
    if (identity === 'adminhome') {
      loginInfo = sessionStorage.getItem('admin');
    } else if (identity === 'userhome') {
      console.log("user...")
      loginInfo = sessionStorage.getItem('user');
    } else if (identity == "bussinessHome") {
      loginInfo = sessionStorage.getItem('bussiness');
    }

    if (loginInfo) {
      //当前身份已经登录
      next();
    } else {
      //未登录，跳转到登录页面
      next('/login')
    }
  } else {
    next()
  }
})

export default router
