<template>
  <div class="app-container">
    <!--    搜索-->
    <MySearchHeader
      :query="query"
      :show-time-button="false"
      @handleCreate="handleCreate"
      @handleSearch="handleSearch"
    />
    <!--    内容-->
    <LabelTableList
      :list="list"
      @handleUpdate="handleUpdate"
      @deleteData="deleteData"
      @updateColor="updateColor"
    />
    <!-- 分页-->
    <MyPagination
      v-show="total > 0"
      :total="total"
      :page.sync="query.current"
      :limit.sync="query.limit"
      @pagination="getList"
    />
    <!--    操作-->
    <el-dialog
      :title="dialogStatus === 'create' ? '添加分类' :'修改分类'"
      :visible.sync="dialogFormVisible"
      width="30%"
    >
<!--      <el-form :model="temp" :rules="labelRules" label-position="left" label-width="50px" size="small" ref="labelForm">-->
<!--        <el-form-item label="名称" prop="name">-->
<!--          <el-input v-model="temp.name" class="m-input-width-80pre" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="颜色" prop="color">-->
<!--          <el-select v-model="temp.color" placeholder="请选择" class="m-input-width-80pre">-->
<!--            <el-option v-for="(item) in colors" :key="item" :label="item" :value="item" @change="dataChange">-->
<!--              <span style="float:left;"><el-tag :color="item" class="m-input-width-100" /></span>-->
<!--              <span style="float:right;">{{ item }}</span>-->
<!--            </el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--      </el-form>-->

<!--      <div slot="footer">-->
<!--        <el-button type="info" size="small" @click="cancel">取 消</el-button>-->
<!--        <el-button type="primary" size="small" @click="dialogStatus === 'create' ? createData(temp) :updateData(temp)">确 定</el-button>-->
<!--      </div>-->
      <CustomLabel :dialog-status="dialogStatus" @addCategorySuccess="getList" @cancel="cancel"/>
    </el-dialog>
  </div>
</template>

<script>

import { getCategoryListPage, updateCategoryColor, delCategory } from '@/api/category'
import { minix } from '../minix'

export default {
  name: 'Category',
  mixins: [minix],
  mounted() {
    this.getList()
  },
  methods: {
    // 获取标签列表
    getList() {
      this.listLoading = true
      getCategoryListPage(this.query).then(res => {
        if (res.code === 200) {
          this.list = res.result.records
          this.total = res.result.total
          this.listLoading = false
        }
      })
      setTimeout(() => {
        this.listLoading = false
      })
    },
    // 只修改颜色
    updateColor(category) {
      updateCategoryColor(category).then(res => {
        if (res.code === 200) {
          this.$message.success('修改成功！')
        }
      })
      this.restTemp()
    },

    // 删除操作
    deleteData(obj) {
      const { data, index } = obj
      this.$confirm('此操作将永久删除该分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delCategory(data.cid).then(res => {
          if (res.code === 200) {
            this.$message.success('删除成功')
            this.list.splice(index, 1)
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<style scoped>
.m-input-width-100 {
  width: 100px !important;
}

.m-input-width-80pre {
  width: 80% !important;
}

.el-tag {
  color: #ffffff;
  width: 100px !important;
}
</style>
