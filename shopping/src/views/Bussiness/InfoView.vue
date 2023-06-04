<template>
  <div>
    <el-alert v-if="bussiness.bstatus==1" title="您的账号被封禁，若有疑问请联系管理员" type="warning" show-icon>  </el-alert>
    <br>
    <el-descriptions title="商户信息" :column="1">
        <el-descriptions-item label="商户ID">{{bussiness.bid}}</el-descriptions-item>
      <el-descriptions-item label="商户名">{{bussiness.bname}}</el-descriptions-item>
      <el-descriptions-item label="状态">{{bussiness.bstatus==0?'正常':'封禁'}}</el-descriptions-item>
    </el-descriptions>
  </div>
</template>
<script>
export default {
    data(){
        return{
            bussiness:{},
        }
    },
    created(){
        this.bussiness= JSON.parse(sessionStorage.getItem("bussiness"));
    }
};
</script>
<style scoped>
</style>