<template>
    <div>
      <h2 style="text-align:center;">申请商户列表</h2>
      <el-table :data="tableData" style="width: 50%; margin: auto" :row-class-name="tableRowClassName">
            <el-table-column prop="uid" label="用户ID" width="180" header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="uname" label="用户名" width="180" header-align="center" align="center">
            </el-table-column>
            <el-table-column label="操作" header-align="center" align="center">
              <template v-slot="scope">
              <el-button type="success" @click="handleApproval(scope.row.uid)">允许</el-button>
              </template>
            </el-table-column>
          </el-table>
          <br>
          <el-pagination
            style="width: 50%; margin: auto"
            @current-change="handleCurrentChange"
            :current-page="current"
            :page-size="5"
            layout="total,prev, pager, next, jumper"
            :total="totalPage"
          >
          </el-pagination>
    </div>
  </template>
  
  <script>
  import {getUserlist,addBuss} from '@/api/adminhome'
  export default {
    data () {
      return {
        current:1,
        totalPage:0,
        param:{
          uid:null
        },
        tableData: []
      }
    },
    methods:{
      getlist(){
       // console.log("enter getuserlist");
        getUserlist(this.current,this.param).then((resp)=>{
        if (resp.data.code !== "00000") {
          this.tableData = resp.data.records;
          this.totalPage=resp.data.total;
        } else {
          this.$message({
            message: "申请列表显示失败",
            type: "warning",
          });
        }
        })
      },
      handleApproval(id){
        this.param.uid=id;
        addBuss(this.param).then((resp)=>{
        if (resp.data.code === "00000") {
          this.tableData = resp.data.records;
        } else {
          this.$message({
            message: "申请失败",
            type: "warning",
          });
        }
        })
        this.param.id=null;
        this.getlist();
      },
      handleCurrentChange(val) {
        this.current=val;
        this.getproductlist();
      }
    },
    created(){
      this.getlist();
    }
  }
  </script>