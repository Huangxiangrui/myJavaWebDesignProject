import request from '../utils/request'
export function applyBs(user){
    return request({
        url:`/user/apply`,
        method:'post',
        data:user
    })
}

export function getoneuser(user){
    return request({
        url:`/user/getoneuser`,
        method:'post',
        data:user
    })
}