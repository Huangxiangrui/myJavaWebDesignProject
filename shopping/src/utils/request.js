import axios from 'axios'
 
 
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: 'http://localhost:80',
  // 超时
  timeout: 10000
})
 
//第三步 创建拦截器  http request 拦截器
service.interceptors.request.use(
  config => {
      //debugger
      return config
  },
  err => {
      return Promise.reject(err);
  })
 
// http response 拦截器
service.interceptors.response.use(
  response => {
    return response;
      },error => {
      return Promise.reject(error.response) // 返回接口返回的错误信息
  });
 
export default service