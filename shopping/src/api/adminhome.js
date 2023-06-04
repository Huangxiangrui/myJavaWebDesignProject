import request from '../utils/request'
 
// 获取书城列表分页条件查询
export function getBuslist(current,bussiness){
    return request({
        url: `/admin/listbuss/${current}`,
        method: 'post',
        data:bussiness
    })
}

export function getUserlist(current,user){
    return request({
        url:`/admin/listuser/${current}`,
        method:'post',
        data:user
    })
}

export function addBuss(user){
    return request({
        url:`/admin/add`,
        method:'post',
        data:user
    })
}

export function updBuss(bussiness){
    return request({
        url:`/admin/upd`,
        method:'post',
        data:bussiness
    })
}