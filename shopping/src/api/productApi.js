import request from '../utils/request'

export function getprodlist(current,product){
    return request({
        url:`/prod/productlist/${current}`,
        method:'post',
        data:product
    })
}
export function gettype(){
    return request({
        url:`/prod/typelist`,
        method:'post'
    })
}

export function getBs(){
    return request({
        url:`/businessman/getBs`,
        method:'post',
    })
}

export function getOneProd(product){
    return request({
        url:`/prod/getoneprod`,
        method:'post',
        data:product
    })
}

export function updprod(product){
    return request({
        url:'/prod/updprod',
        method:'post',
        data:product
    })
}

export function addprod(product){
    return request({
        url:'/prod/addprod',
        method:'post',
        data:product
    })
}

export function delprod(product){
    return request({
        url:'/prod/delprod',
        method:'post',
        data:product
    })
}