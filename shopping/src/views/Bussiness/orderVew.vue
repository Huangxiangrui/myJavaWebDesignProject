<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="商品">
        <el-input v-model="formInline.buss" placeholder="关键词"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="formInline.status" placeholder="选择状态" clearable>
          <el-option label="未发货" value="0"></el-option>
          <el-option label="运输中" value="1"></el-option>
          <el-option label="已到达" value="2"></el-option>
          <el-option label="已收货" value="3"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onsubmit()">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="tableData" style="width: 100%">
      <!-- <el-table-column label="ID" prop="cid"> </el-table-column> -->
      <el-table-column label="订单ID" prop="oid"> </el-table-column>
      <el-table-column label="收货人" prop="uname"> </el-table-column>
      <el-table-column label="商品名" prop="pname"> </el-table-column>
      <el-table-column label="数量" prop="numbers"></el-table-column>
      <el-table-column label="总价" prop="totalprice"></el-table-column>
      <el-table-column label="状态" prop="status">
        <template v-slot="scope">
          {{ statusMap.get(scope.row.status + "") }}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button
            @click="handleRecieve(scope.row)"
            :disabled="scope.row.status !== 0"
            >发货</el-button
          >
          <el-button
            type="danger"
            @click="handleDelete(scope.row)"
            :disabled="scope.row.status !== 3"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
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
import { getorderlist, updorder, delorder } from "@/api/orderApi.js";
import { getoneuser } from "@/api/userApi";
export default {
  data() {
    return {
      current: 1,
      totalPage: 0,
      formInline: {},
      tableData: [],
      param: {},
      bussiness: {},
      statusMap: new Map([
        ["0", "未发货"],
        ["1", "运输中"],
        ["2", "已到达"],
        ["3", "已收货"],
      ]),
    };
  },
  methods: {
    getlist() {
      this.param.bid = this.bussiness.bid;
      getorderlist(this.current, this.param).then((resp) => {
        if (resp.data.code == "00000") {
          this.tableData = resp.data.data.records;
          this.totalPage = resp.data.data.total;
          for (var item in this.tableData) {
            (() => {
              var i = item;
              getoneuser({ uid: this.tableData[i].uid }).then((resp) => {
                this.tableData[i].uname = resp.data.data.uname;
              });
            })();
          }
        }
      });
    },
    onsubmit() {
      this.param.pname = this.formInline.buss;
      this.param.status = this.formInline.status;
      this.getlist();
      this.param = {};
    },
    handleCurrentChange(val) {
      this.current = val;
      this.getlist();
    },
    handleRecieve(row) {
      this.$confirm("是否确定发货?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "danger",
      }).then(() => {
        this.order = row;
        this.order.status = 1;
        updorder(this.order)
          .then((resp) => {
            if (resp.data.code == "00000") {
              this.$message({
                type: "success",
                message: "已确认收货",
              });
              this.getlist();
            } else {
              this.$message({
                type: "error",
                message: "无法确认收货",
              });
            }
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消",
            });
          });
      });
    },
    handleDelete(row) {
      this.$confirm("是否确定删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "info",
      }).then(() => {
        this.order = row;
        delorder(this.order)
          .then((resp) => {
            if (resp.data.code == "00000") {
              this.$message({
                type: "success",
                message: "已删除",
              });
              this.getlist();
            } else {
              this.$message({
                type: "error",
                message: "无法删除",
              });
            }
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消",
            });
          });
      });
    },
  },
  created() {
    this.bussiness = JSON.parse(sessionStorage.getItem("bussiness"));
    this.getlist();
  },
};
</script>
  <style scoped>
</style>