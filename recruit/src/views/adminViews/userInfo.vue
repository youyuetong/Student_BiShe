<template>
  <div>
    <el-tabs type="border-card" tabPosition="left" style="width:1000px;height: 1000px;" class="hr">
    <el-tab-pane>
    <span slot="label">企业审核<i class="el-icon-arrow-right"></i></span>
   <manage-enterprise></manage-enterprise>
  </el-tab-pane>

   <el-tab-pane >
    <span slot="label">设置<i class="el-icon-arrow-right"></i></span>
   <setting></setting>
  </el-tab-pane>
</el-tabs>
  </div>
</template>

<script>
import setting from '../../components/setting'
import manageEnterprise from '../../components/admintabs/manageEnterprise'

export default {
  data () {
    return {
      adminRefresh: 0
    }
  },
  components: {
    setting,
    manageEnterprise,
  },
  mounted () {
    this.adminRefresh = this.$route.params.adminRefresh !== undefined ? this.$route.params.adminRefresh : 0
  },
  watch: {
    adminRefresh () {
      location.reload()
    }
  }
}
</script>

<style>
.hr {
   margin: 20px auto auto auto;
}
</style>
