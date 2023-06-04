import request from '../utils/request'
 
// 获取书城列表分页条件查询
export function adminlogin(admin){
    return request({
        url: `/login/admin`,
        method: 'post',
        data:admin
    })
}

export function userlogin(user){
    return request({
        url:`/login/user`,
        method:'post',
        data:user
    })
}

export function bussinesslogin(bussiness){
    return request({
        url:'/login/bussiness',
        method:'post',
        data:bussiness
    })
}

export function register(user){
    return request({
        url:'/login/register',
        method:'post',
        data:user
    })

}