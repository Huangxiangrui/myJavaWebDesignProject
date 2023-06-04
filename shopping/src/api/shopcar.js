import request from '../utils/request'

export function getcarshoplist(current,carshop){
    return request({
        url:`/carshop/getcarshoplist/${current}`,
        method:'post',
        data:carshop
    })
}

export function addcarshop(carshop){
    return request({
        url:`/carshop/addcarshop`,
        method:'post',
        data:carshop
    })
}

export function delcarshop(carshop){
    return request({
        url:`/carshop/del`,
        method:'post',
        data:carshop
    })
}

export function updcarshop(carshop){
    return request({
        url:`/carshop/upd`,
        method:'post',
        data:carshop
    })
}