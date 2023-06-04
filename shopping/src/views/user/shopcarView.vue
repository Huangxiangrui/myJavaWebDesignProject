<template>
  <div>
    <h2 style="text-align: center">购物清单</h2>
    <el-table :data="tableData" style="width: 100%">
      <!-- <el-table-column label="ID" prop="cid"> </el-table-column> -->
      <el-table-column label="商品名" prop="pname"> </el-table-column>
      <el-table-column label="图片">
        <template v-slot="scope">
          <img
            v-if="scope.row.pimage !== undefined"
            :src="scope.row.pimage"
            lazy
            style="width: 100px; height: 80px"
          />
        </template>
      </el-table-column>
      <el-table-column label="价格" prop="pprice" width="100px">
      </el-table-column>
      <el-table-column
        label="数量"
        prop="numbers"
        header-align="center"
        size="small"
      >
        <template v-slot="scope">
          <el-input-number
            v-model="scope.row.numbers"
            @change="handleChange(scope.row)"
            :min="1"
            :max="10"
            label="选择数量"
          ></el-input-number>
        </template>
      </el-table-column>
      <el-table-column label="总价" prop="totalprice" width="100px">
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button @click="handlePay(scope.row)">支付</el-button>
          <el-button type="danger" @click="handleDelete(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
      <el-table-column align="right">
        <template v-slot:header>
          <!--具名插槽语法 -->
          <div class="header-container">
            <el-input v-model="search" placeholder="输入关键字搜索" />
            <el-button @click="handlesearch()">查询</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <br />
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
import { getcarshoplist, updcarshop, delcarshop } from "@/api/shopcar.js";
import { getOneProd, updprod } from "@/api/productApi.js";
import { addorder } from "@/api/orderApi.js";
export default {
  data() {
    return {
      num: 1,
      search: "",
      tableData: [],
      param: {},
      current: 1,
      totalPage: 0,
      product: {
        pid: null,
      },
      user: {},
      order: {},
    };
  },
  methods: {
    getlist() {
      this.param.uid = this.user.uid;
      getcarshoplist(this.current, this.param).then((resp) => {
        if (resp.data.code == "00000") {
          this.tableData = resp.data.data.records;
          this.totalPage = resp.data.data.total;
          // 使用闭包，解决异步函数调用的延迟问题
          for (let item in this.tableData) {
            (() => {
              let currentItem = this.tableData[item];
              this.product.pid = currentItem.pid;
              getOneProd(this.product).then((resp1) => {
                currentItem.pimage = String(
                  "http://localhost:80/images/" + resp1.data.data.pimage
                );
                currentItem.pprice = resp1.data.data.pprice;
                currentItem.totalprice = String(
                  currentItem.numbers * currentItem.pprice
                );
              });
            })(); //立即调用函数表达式。它的作用是为了创建一个作用域来避免变量污染全局作用域，同时保证代码能够正确运行。
          }
        }
      });
    },
    // 解决异步问题的方法是使用回调函数或async/await语法。
    // 将handlePay方法标记为async，然后将getOneProd方法和updprod方法放在await语句中以确保在异步操作完成后执行。
    // 将flag的定义移到异步操作内部，以确保它在异步操作完成后被正确设置。
    async handlePay(row) {
      try {
        await this.$confirm("是否支付该商品?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "info",
        });
        this.product.pid = row.pid;
        var numbers = 0;
        var resp = await getOneProd(this.product);
        numbers = resp.data.data.pnumber - row.numbers;
        if (numbers >= 0) {
          this.product.pnumber = numbers;
          await updprod(this.product);
          this.order = {
            uid: this.user.uid,
            bid: row.bid,
            pid: row.pid,
            totalprice: row.totalprice,
            status: 0,
            pname: row.pname,
            numbers: row.numbers,
          };
          var orderResp = await addorder(this.order);
          if (orderResp.data.code == "00000") {
            this.$message({
              type: "success",
              message: "支付成功!",
            });
            this.param = {};
            this.getlist();
          } else {
            this.$message({
              type: "error",
              message: "支付失败!",
            });
          }
        } else {
          this.$message({
            type: "error",
            message: "支付失败!,请确认商品库存，以及购买数量",
          });
        }
      } catch (error) {
        this.$message({
          type: "info",
          message: "已取消支付",
        });
      }
    },
    handleDelete(row) {
      this.$confirm("是否删除该商品?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.param.cid = row.cid;
          delcarshop(this.param).then((resp) => {
            if (resp.data.code == "00000") {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              this.param = {};
              this.getlist();
            } else {
              this.$message({
                type: "error",
                message: "删除失败!",
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    handleCurrentChange(val) {
      this.current = val;
      this.getlist();
    },
    handleChange(row) {
      row.totalprice = row.pprice * row.numbers;
      this.param.cid = row.cid;
      this.param.numbers = row.numbers;
      updcarshop(this.param);
    },
    handlesearch() {
      this.param.pname = this.search;
      this.getlist();
    },
  },
  created() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
    this.getlist();
  },
};
</script>
<style scoped>
.header-container {
  display: flex;
  align-items: center;
  /* 使用flex布局和align-items: center属性来让它们在同一行并垂直居中 */
}
</style>