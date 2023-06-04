import request from '../utils/request'

export function getorderlist(current,order){
    return request({
        url:`/order/orderlist/${current}`,
        method:'post',
        data:order
    })
}
export function addorder(order){
    return request({
        url:`/order/addorder`,
        method:'post',
        data:order
    })
}
export function updorder(order){
    return request({
        url:`/order/updorder`,
        method:'post',
        data:order
    })
}
export function delorder(order){
    return request({
        url:`/order/delorder`,
        method:'post',
        data:order
    })
}
export function confirmorder(order){
    return request({
        url:`/order/isReady`,
        method:'post',
        data:order
    })
}